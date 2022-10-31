package com.illiapinchuk.testtask.service.interfacies;

import com.illiapinchuk.testtask.model.dto.UserDto;

/**
 * Service for {@link com.illiapinchuk.testtask.model.User}
 * @author Illia Pinchuk
 */
public interface UserService {
    UserDto findById(Long id);
}
