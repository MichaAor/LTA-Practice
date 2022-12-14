package com.lta.blogapirest.DTO;

import com.lta.blogapirest.Model.Publication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentaryDTO {
    private Long id;
    private String content;
    private String email;
    private String name;

    public CommentaryDTO(String content, String email, String name){
        this.content = content;
        this.email = email;
        this.name = name;
    }
}
