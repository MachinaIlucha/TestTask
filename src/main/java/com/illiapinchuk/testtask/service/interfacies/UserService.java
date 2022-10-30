package com.illiapinchuk.testtask.service.interfacies;

import com.illiapinchuk.testtask.model.User;

import java.util.Optional;

/**
 * Service for {@link com.illiapinchuk.testtask.model.User}
 * @author Illia Pinchuk
 */
public interface UserService {
    Optional<User> findById(Long id);
}
