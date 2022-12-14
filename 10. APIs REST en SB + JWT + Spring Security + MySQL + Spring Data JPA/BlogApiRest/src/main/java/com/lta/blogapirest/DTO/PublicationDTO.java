package com.lta.blogapirest.DTO;

import com.lta.blogapirest.Model.Commentary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicationDTO {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Commentary> commentaries;

    public PublicationDTO(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
