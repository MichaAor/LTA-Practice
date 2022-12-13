package com.lta.blogapirest.Service.Interface;

import com.lta.blogapirest.DTO.PublicationDTO;
import com.lta.blogapirest.DTO.PublicationRTR;

import java.util.List;

public interface IPublicationService {
    List<PublicationDTO> GetAll(int pageN,int pageS);
    PublicationRTR GetAllRTR(int pageN,int pageS,String sortBy,String sortDir);
    PublicationDTO Get(Long idP);
    PublicationDTO Save(PublicationDTO pDTO);
    PublicationDTO Update(PublicationDTO pDTO,Long idP);
    void Delete(Long idP);
}
