package com.cwa.medilabopatientapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotNull(message = "Birthdate is mandatory")
    @Past(message = "Birthdate must be a past date")
    private LocalDate birthdate;

    @NotBlank(message = "Gender is mandatory")
    private String gender;

    private String address;

    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number must follow pattern XXX-YYY-ZZZZ")
    private String phoneNumber;
}
