package com.lta.blogapirest.DTO;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicationRTR {
    private List<PublicationDTO> content;
    private int pageN;
    private int pageS;
    private long totalEl;
    private int totalP;
    private boolean last;

}
