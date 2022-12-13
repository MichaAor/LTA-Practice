package com.lta.blogapirest.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicationDTO {
    private Long id;
    private String title;
    private String description;
    private String content;

    public PublicationDTO(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
