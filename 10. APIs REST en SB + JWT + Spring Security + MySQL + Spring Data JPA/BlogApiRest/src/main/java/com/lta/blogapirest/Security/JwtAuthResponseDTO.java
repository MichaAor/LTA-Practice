package com.lta.blogapirest.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtAuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponseDTO(String token){
        this.accessToken = token;
    }


}
