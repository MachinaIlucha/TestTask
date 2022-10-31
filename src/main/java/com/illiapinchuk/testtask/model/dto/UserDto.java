package com.illiapinchuk.testtask.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Incoming DTO to create a new record of {@link com.illiapinchuk.testtask.model.User}
 *
 * @author Illia Pinchuk
 */
@Data
public class UserDto {
    @JsonProperty(required = true)
    private Long id;

    @JsonProperty(required = true)
    @NotEmpty
    private String firstName;

    @JsonProperty(required = true)
    @NotEmpty
    private String lastName;

    @JsonProperty
    private int age;
}
