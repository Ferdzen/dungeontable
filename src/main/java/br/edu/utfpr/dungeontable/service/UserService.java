package br.edu.utfpr.dungeontable.service;

import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
import br.edu.utfpr.dungeontable.model.User;
import br.edu.utfpr.dungeontable.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public User save(User user){
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name");
        } else if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "email");
        }

        boolean existsEmail = userRepository.findByEmailIgnoreCase(user.getEmail()).isPresent();
        if(existsEmail){
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        return userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User update(User user) {
        if (user.getId() == null) {
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        } else if (user.getName() == null || user.getName().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name");
        } else if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "email");
        }
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        }
        userRepository.deleteById(id);
    }
}
