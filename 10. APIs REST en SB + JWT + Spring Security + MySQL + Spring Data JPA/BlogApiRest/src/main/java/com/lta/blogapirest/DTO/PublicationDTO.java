package com.lta.blogapirest.DTO;

import com.lta.blogapirest.Model.Commentary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicationDTO {
    private Long id;
    @NotEmpty
    @Size(min = 5,message = "You must enter at least 5 characters.")
    private String title;
    @NotEmpty
    @Size(min = 20,message = "You must enter at least 20 characters.")
    private String description;
    @NotEmpty(message = "Should not be empty")
    private String content;
    private Set<Commentary> commentaries;

    public PublicationDTO(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
