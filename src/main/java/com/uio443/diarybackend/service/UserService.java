package com.uio443.diarybackend.service;

import Exceptions.EmailNotUniqueException;
import Exceptions.UserNotFoundException;
import Exceptions.UsernameNotUniqueException;
import com.uio443.diarybackend.enums.HiddenStatus;
import com.uio443.diarybackend.model.User;
import com.uio443.diarybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException("Username", username));
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("Email", email));
    }

    public User addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) throw new UsernameNotUniqueException();
        if (userRepository.existsByEmail(user.getEmail())) throw new EmailNotUniqueException();

        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) throw new UserNotFoundException(id);

        userRepository.deleteUserById(id);
    }

    public User updateUser(User user) {
        Long id = user.getId();
        System.out.println(id);
        User oldUser = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));

        String newUsername = user.getUsername();
        String newEmail = user.getEmail();
        String newPfp = user.getPfpLink();
        HiddenStatus newHiddenStatus = user.getHiddenStatus();

        if (newUsername != null && newUsername.equals("") && !(newUsername.equals(oldUser.getUsername()))) {
            if(userRepository.existsByUsername(newUsername)) throw new UsernameNotUniqueException();
            oldUser.setUsername(newUsername);
        }

        if (newEmail != null && newEmail.equals("") && !(newEmail.equals(oldUser.getEmail()))) {
            if(userRepository.existsByEmail(newEmail)) throw new EmailNotUniqueException();
            oldUser.setEmail(newEmail);
        }

        if (newPfp != null && newPfp.equals("") && !(newPfp.equals(oldUser.getPfpLink()))) {
            oldUser.setPfpLink(newPfp);
        }

        if (newHiddenStatus != null && newHiddenStatus != oldUser.getHiddenStatus()) {
            oldUser.setHiddenStatus(newHiddenStatus);
        }

        return userRepository.save(oldUser);
    }


}