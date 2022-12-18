package com.lta.blogapirest.DTO;

import com.lta.blogapirest.Model.Publication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentaryDTO {
    private Long id;
    @NotEmpty(message = "The field cannot be empty.")
    @Size(min = 10,message = "You must enter at least 10 characters.")
    private String content;
    @NotEmpty(message = "The field cannot be empty.")
    @Email(message = "The entered text does not have email format.")
    private String email;
    @NotEmpty(message = "The field cannot be empty")
    private String name;

    public CommentaryDTO(String content, String email, String name){
        this.content = content;
        this.email = email;
        this.name = name;
    }
}
