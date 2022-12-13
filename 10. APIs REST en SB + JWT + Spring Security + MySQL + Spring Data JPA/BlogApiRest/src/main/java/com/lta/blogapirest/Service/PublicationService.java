package com.lta.blogapirest.Service;

import com.lta.blogapirest.DTO.PublicationDTO;
import com.lta.blogapirest.Model.Publication;
import com.lta.blogapirest.Repository.PublicationRepository;
import com.lta.blogapirest.Service.Interface.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationService implements IPublicationService {
    @Autowired
    private PublicationRepository pr;

    @Override
    public List<PublicationDTO> GetAll() {
        List<Publication> pubL = pr.findAll();
        return pubL.stream().map(this::mapperToDTO).collect(Collectors.toList());
    }

    @Override
    public PublicationDTO Get(Long idP) {
        return null;
    }

    @Override
    public PublicationDTO Save(PublicationDTO pDTO) {
        //Mapping DTO to Entity -> Save -> Mapping Entity to DTO
        return this.mapperToDTO(pr.save(this.mapperToEntity(pDTO)));
    }

    @Override
    public PublicationDTO Update(PublicationDTO pDTO) {
        return null;
    }







    public PublicationDTO mapperToDTO(Publication pb){
        PublicationDTO pDTO = new PublicationDTO(pb.getTitle(),pb.getDescription(),pb.getContent());
        if(pb.getId() != null){
            pDTO.setId(pb.getId());
        }
        return pDTO;
    }
    public Publication mapperToEntity(PublicationDTO pDTO){
        Publication pub = new Publication(pDTO.getTitle(),pDTO.getDescription(),pDTO.getContent());
        if(pDTO.getId() != null){
            pub.setId(pDTO.getId());
        }
        return pub;
    }

}
