package kr.hs.dgsw.dbook.dbookapi.Service;

import kr.hs.dgsw.dbook.dbookapi.Exception.DBookException;
import kr.hs.dgsw.dbook.dbookapi.Exception.TokenException;
import kr.hs.dgsw.dbook.dbookapi.Exception.UserException;
import kr.hs.dgsw.dbook.dbookapi.Json.DefaultResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.LoginResponse;
import kr.hs.dgsw.dbook.dbookapi.Json.TokenResponse;
import kr.hs.dgsw.dbook.dbookapi.Repository.ProfileImgRepository;
import kr.hs.dgsw.dbook.dbookapi.Repository.TokenRepository;
import kr.hs.dgsw.dbook.dbookapi.Repository.UserRepository;
import kr.hs.dgsw.dbook.dbookapi.VO.ProfileImg;
import kr.hs.dgsw.dbook.dbookapi.VO.Token;
import kr.hs.dgsw.dbook.dbookapi.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ProfileImgRepository profileImgRepository;

    @Override
    public DefaultResponse signup(User user) {

        try{

            User duplicateUser = userRepository.findByEmail(user.getEmail()).orElse(null);

            if(duplicateUser != null)
                throw new UserException("중복된 아이디입니다.");

            String password     = user.getPassword();
            String name         = user.getName();
            boolean numericFlag = false;

            if(password.length() < 8)
                throw new UserException("비밀번호를 확인해주세요.");
            else if(name.equals("") || name.length() < 2)
                throw new UserException("이름을 확인해주세요.");

            for(int i = 0 ;i < name.length();i++){
                int index = name.charAt(i);

                if(index >= 48 && index <= 57){
                    numericFlag = true;
                    break;
                }
            }

            if(numericFlag){
                throw new UserException("이름에 숫자가 들어갈 수 없습니다.");
            }

            password = convertSHA256(password);
            user.setPassword(password);

            userRepository.save(user);
            return new DefaultResponse(user);

        }catch (UserException e){
            return new DefaultResponse(401, e.getMessage());
        }
        catch (NullPointerException e){

            return new DefaultResponse(400, "항목이 입력되지 않았습니다.");
        }
    }

    @Override
    public LoginResponse login(User user) {

        Map<String, String> resObjectMap = new HashMap<>();

        try{

            if(findUser(user.getEmail()).equals("ok"))
                throw new UserException("없는 아이디입니다.");

            Optional<User> objUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new UserException("일치하는 회원이 없습니다.")));

            String resPassword = convertSHA256(user.getPassword());
            String password = objUser.get().getPassword();

            if(!resPassword.equals(password))
                throw new UserException("비밀번호가 일치하지 않습니다.");

            String userEmail = user.getEmail();
            String token = makeToken();
            String connectIP = findIpAddress();

            Optional<Token> objToken = tokenRepository.findByTokenOwnerId(userEmail);

            if(objToken.orElse(null) == null){
                tokenRepository.save(new Token(userEmail, token, connectIP));
            }else{
                objToken.map(found -> {
                    found.setToken(Optional.ofNullable(token).orElse(found.getToken()));
                    found.setConnectIP(Optional.ofNullable(connectIP).orElse(found.getConnectIP()));

                    return tokenRepository.save(found);
                });
            }

            resObjectMap.put("profileNo", user.getProfileFileNo().toString());
            resObjectMap.put("libraryName", user.getLibraryName());

            return new LoginResponse(userEmail, token, resObjectMap);
        } catch (UserException e){
            return new LoginResponse(401, e.getMessage());
        }
    }

    @Override
    public TokenResponse tokenCheck(String token) {
        try{
            Token objToken = tokenRepository.findByToken(token).orElseThrow(()-> new TokenException("존재하는 토큰이 없습니다."));
            String myIp = findIpAddress();
            String ownerId = objToken.getTokenOwnerId();

            if(!objToken.getConnectIP().equals(myIp)){
                throw new TokenException("잘못된 접근입니다.");
            }

            return new TokenResponse(ownerId, token);

        }catch (TokenException e){
            return new TokenResponse(e.getMessage());
        }
    }

    @Override
    public DefaultResponse fileUpload(MultipartFile file) {

        DefaultResponse defaultResponse = new DefaultResponse();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try{
            if(fileName.contains("..")) {
                throw new UserException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ProfileImg profileImg = new ProfileImg(fileName, file.getContentType(), file.getBytes());
            profileImgRepository.save(profileImg);

            defaultResponse.setData(profileImg.getImgNo());

        } catch (IOException e){
            throw new UserException("Could not store file " + fileName + ". Please try again!");
        }

        return defaultResponse;
    }

    @Override
    public ProfileImg getProfileImageInfo(Integer imageNo) {
        return profileImgRepository.findById(imageNo).orElse(null);
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email).orElseGet(()  -> new User("ok"));
    }

    private String convertSHA256(String password) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public String makeToken(){

        StringBuilder token = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            token.append((char) ((int) (random.nextInt(26)) + 97));
        }

        return token.toString();
    }

    public String findIpAddress(){

        String ipaddr = null;

        try {
            ipaddr = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ipaddr;
    }
}
