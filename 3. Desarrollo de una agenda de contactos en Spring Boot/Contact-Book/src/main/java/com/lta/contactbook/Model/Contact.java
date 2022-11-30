package com.lta.contactbook.Model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Enter your Name")
    private String name;
    @NotEmpty(message="Enter your Lastname")
    private String lastname;
    @NotBlank(message="Enter your Phone")
    private String phone;
    @DateTimeFormat(iso = ISO.DATE)
    @Past(message = "Enter old date")
    @NotNull
    private LocalDate birthD;
    private LocalDateTime registerD;

    @PrePersist
    private void assignRecordDate(){
        this.registerD = LocalDateTime.now();
    }
}
