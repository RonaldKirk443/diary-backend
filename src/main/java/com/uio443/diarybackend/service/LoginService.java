package com.uio443.diarybackend.service;

import com.uio443.diarybackend.enums.HiddenStatus;
import com.uio443.diarybackend.exception.EmailNotUniqueException;
import com.uio443.diarybackend.exception.UserNotFoundException;
import com.uio443.diarybackend.exception.UserNotUniqueException;
import com.uio443.diarybackend.exception.UsernameNotUniqueException;
import com.uio443.diarybackend.model.Login;
import com.uio443.diarybackend.model.User;
import com.uio443.diarybackend.repository.LoginRepository;
import com.uio443.diarybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository, UserRepository userRepository) {
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
    }

    public void addLogin(Login login) {
        if (loginRepository.existsByEmail(login.getEmail())) throw new EmailNotUniqueException();
        if (loginRepository.existsByUserId(login.getUserId())) throw new UserNotUniqueException();
        if (!userRepository.existsById(login.getUserId())) throw new UserNotFoundException(login.getUserId());
        String salt = getSalt();
        String encodedPass = hashPassword(login.getPass(), salt);
        login.setPass(encodedPass);
        login.setSalt(salt);
        loginRepository.save(login);
    }

    public Long getUserId(Login login) {
        if (!loginRepository.existsByEmail(login.getEmail())) return -1L;
        Login savedLogin = loginRepository.findByEmail(login.getEmail()).orElseThrow(() -> new EmailNotUniqueException());
        //Hash login pass
        String salt = savedLogin.getSalt();
        String encodedPass = hashPassword(login.getPass(), salt);

        // compare hashed login pass with saved login pass
        if (!savedLogin.getPass().equals(encodedPass)) return -2L;
        return savedLogin.getUserId();
    }

    public int updateLogin(Login login) {
        Login savedLogin = loginRepository.findByUserId(login.getUserId()).orElseThrow(() -> new UserNotFoundException(login.getUserId()));
        if (!login.getEmail().equals(savedLogin.getEmail())) {
            savedLogin.setEmail(login.getEmail());
        }
        if (login.getPass() != null){
            if (login.getOldPass() == null) return -2;
            String salt = savedLogin.getSalt();
            // Hash login oldPass and compare with savedLogin pass
            // If true then hash login pass and set savedLogin pass to it
            // Else return -2
            String encodedOldPass = hashPassword(login.getOldPass(), salt);
            if (!savedLogin.getPass().equals(encodedOldPass)) return -2;
            String encodedNewPass = hashPassword(login.getPass(), salt);
            savedLogin.setPass(encodedNewPass);
        }
        loginRepository.save(savedLogin);
        return 0;
    }

    public void deleteLoginByUserId(Long userId) {
        if(!loginRepository.existsByUserId(userId)) throw new UserNotFoundException(userId);

        loginRepository.deleteByUserId(userId);
    }

    private static String hashPassword(String input, String encodedSalt) {
        String hashed = "";
        byte[] salt = Base64.getDecoder().decode(encodedSalt);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] arr = md.digest(input.getBytes(StandardCharsets.UTF_8));
            hashed = Base64.getEncoder().encodeToString(arr);

        }
        catch (NoSuchAlgorithmException e){
            System.out.println("Hi");
        }
        return hashed;
    }

    private static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

}
