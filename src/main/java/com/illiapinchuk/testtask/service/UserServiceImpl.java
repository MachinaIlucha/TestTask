package com.illiapinchuk.testtask.service;

import com.illiapinchuk.testtask.exception.UserNotFoundException;
import com.illiapinchuk.testtask.model.User;
import com.illiapinchuk.testtask.repository.UserRepository;
import com.illiapinchuk.testtask.service.interfacies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Date;
import java.util.Optional;

/**
 * @author Illia Pinchuk
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        Period period = Period.between(user.getDateOfBirth(), new Date());
        System.out.println(period.getYears());      // 4
        return ;
    }
}
