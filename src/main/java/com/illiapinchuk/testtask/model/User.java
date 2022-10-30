package com.illiapinchuk.testtask.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Illia Pinchuk
 */
@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity{
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
}
