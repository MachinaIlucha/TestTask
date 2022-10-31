package com.illiapinchuk.testtask.controller;

import com.illiapinchuk.testtask.model.dto.UserDto;
import com.illiapinchuk.testtask.service.interfacies.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Illia Pinchuk
 */
@RestController
@RequestMapping(value = "/api/users", produces = APPLICATION_JSON_VALUE)
@Slf4j
public class UserRestController {
    private static final String ID = "userId";
    private static final String USER_GET_LOG = "User with id: {} was found";

    @Autowired
    private UserService userService;

    @Operation(summary = "Get user by its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found user",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = ID) Long userId){
       UserDto userResponse = userService.findById(userId);

        log.info(USER_GET_LOG, userId);

        return ResponseEntity.ok(userResponse);
    }
}
