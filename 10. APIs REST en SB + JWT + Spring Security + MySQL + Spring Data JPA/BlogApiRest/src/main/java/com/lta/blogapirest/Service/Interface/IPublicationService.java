package com.lta.blogapirest.Service.Interface;

import com.lta.blogapirest.DTO.PublicationDTO;

import java.util.List;

public interface IPublicationService {
    List<PublicationDTO> GetAll();
    PublicationDTO Get(Long idP);
    PublicationDTO Save(PublicationDTO pDTO);
    PublicationDTO Update(PublicationDTO pDTO);

}
