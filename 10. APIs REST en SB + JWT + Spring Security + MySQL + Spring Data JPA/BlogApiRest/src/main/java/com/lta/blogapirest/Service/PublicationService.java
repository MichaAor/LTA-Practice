package com.lta.blogapirest.Service;

import com.lta.blogapirest.DTO.PublicationDTO;
import com.lta.blogapirest.DTO.PublicationRTR;
import com.lta.blogapirest.Exceptions.ResourceNotFoundException;
import com.lta.blogapirest.Model.Publication;
import com.lta.blogapirest.Repository.PublicationRepository;
import com.lta.blogapirest.Service.Interface.IPublicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationService implements IPublicationService {
    @Autowired
    private PublicationRepository pr;

    @Autowired
    private ModelMapper mmp;

    @Override
    public List<PublicationDTO> GetAll(int pageN, int pageS) {
        Pageable pageable = PageRequest.of(pageN,pageS);
        List<Publication> pubL = pr.findAll(pageable).getContent(); //Recupera datos paginados
        return pubL.stream().map(this::mapperToDTO).collect(Collectors.toList());
    }

    @Override
    public PublicationRTR GetAllRTR(int pageN, int pageS,String sortBy,String sortDir) {
        Pageable pageable = PageRequest.of(pageN,pageS,
                sortDir.compareToIgnoreCase(Sort.Direction.ASC.name())==0? //condicion si es igual asc
                        Sort.by(sortBy).ascending():Sort.by(sortBy).descending()); //tira sentencia xor
        Page<Publication> page = pr.findAll(pageable);
        List<Publication> pubL = page.getContent(); //Recupera datos paginados
        /*
        pubRTR.setContent(pubL.stream().map(this::mapperToDTO).collect(Collectors.toList()));
        pubRTR.setPageN(page.getNumber());  //num de pagina
        pubRTR.setPageS(page.getSize());    //tamanio
        pubRTR.setTotalEl(page.getTotalElements()); //elementos totales
        pubRTR.setTotalP(page.getTotalPages()); //total de paginas
        pubRTR.setLast(page.isLast());  //si es la ultima
         */
        return new PublicationRTR(
                pubL.stream().map(this::mapperToDTO).collect(Collectors.toList()),
                page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages(),
                page.isLast());
    }

    @Override
    public PublicationDTO Get(Long idP) {
        return this.mapperToDTO(pr.findById(idP)
                .orElseThrow(()->new ResourceNotFoundException("Publication","id",idP)));
    }

    @Override
    public PublicationDTO Save(PublicationDTO pDTO) {
        //Mapping DTO to Entity -> Save -> Mapping Entity to DTO
        return this.mapperToDTO(pr.save(this.mapperToEntity(pDTO)));
    }

    @Override
    public PublicationDTO Update(PublicationDTO pDTO, Long idP) {
        Publication pub = pr.findById(idP)
                .orElseThrow(()->new ResourceNotFoundException("Publication","id",idP));
        pub.setId(idP);
        pub.setTitle(pDTO.getTitle());
        pub.setDescription(pDTO.getDescription());
        pub.setContent(pDTO.getContent());
        return this.mapperToDTO(pr.save(pub));
    }

    @Override
    public void Delete(Long idP){
        pr.delete(pr.findById(idP).orElseThrow(()->new ResourceNotFoundException("Publication","id",idP)));
    }

    private PublicationDTO mapperToDTO(Publication pb){
        return mmp.map(pb,PublicationDTO.class);
    }
    private Publication mapperToEntity(PublicationDTO pDTO){
        return mmp.map(pDTO,Publication.class);
    }

}
