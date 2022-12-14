package com.lta.blogapirest.Service;

import com.lta.blogapirest.DTO.CommentaryDTO;
import com.lta.blogapirest.Exceptions.BlogAppException;
import com.lta.blogapirest.Exceptions.ResourceNotFoundException;
import com.lta.blogapirest.Model.Commentary;
import com.lta.blogapirest.Model.Publication;
import com.lta.blogapirest.Repository.CommentaryRepository;
import com.lta.blogapirest.Repository.PublicationRepository;
import com.lta.blogapirest.Service.Interface.ICommentaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentaryService implements ICommentaryService {
    @Autowired
    private CommentaryRepository cr;
    @Autowired
    private PublicationRepository pr;

    @Autowired
    private ModelMapper mmp;


    @Override
    public CommentaryDTO Get(Long idC,Long idP) {
        Publication pub = pr.findById(idP)
                .orElseThrow(()->new ResourceNotFoundException("Publication","id",idP));
        Commentary com = cr.findById(idC)
                .orElseThrow(()->new ResourceNotFoundException("Commentary","id",idC));
        if(!pub.getId().equals(com.getPublication().getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST
                    ,"The comment does not belong to the publication");
        }
        return mapperToDTO(com);
    }

    @Override
    public List<CommentaryDTO> GetByPublication(Long idP) {
        List<Commentary> commentaries = cr.findByPublicationId(idP);
        commentaries.forEach(System.out::println);
        return commentaries.stream().map(this::mapperToDTO).collect(Collectors.toList());
    }

    @Override
    public CommentaryDTO Save(Long idP, CommentaryDTO cmtDTO) {
        Commentary commentary = mapperToEntity(cmtDTO);
        commentary.setPublication(pr.findById(idP)
                .orElseThrow(()->new ResourceNotFoundException("Publication","id",idP)));
        return mapperToDTO(cr.save(commentary));
    }

    @Override
    public CommentaryDTO Update(Long idC,Long idP,CommentaryDTO cmtDTO) {
        Publication pub = pr.findById(idP)
                .orElseThrow(()->new ResourceNotFoundException("Publication","id",idP));
        Commentary com = cr.findById(idC)
                .orElseThrow(()->new ResourceNotFoundException("Commentary","id",idC));
        if(!pub.getId().equals(com.getPublication().getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST
                    ,"The comment does not belong to the publication");
        }
        com.setContent(cmtDTO.getContent());
        com.setEmail(cmtDTO.getEmail());
        com.setName(cmtDTO.getName());
    return mapperToDTO(cr.save(com));
    }

    @Override
    public void Delete(Long idC, Long idP) {
        Publication pub = pr.findById(idP)
                .orElseThrow(()->new ResourceNotFoundException("Publication","id",idP));
        Commentary com = cr.findById(idC)
                .orElseThrow(()->new ResourceNotFoundException("Commentary","id",idC));
        if(!pub.getId().equals(com.getPublication().getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST
                    ,"The comment does not belong to the publication");
        }
        cr.delete(com);
    }


    private CommentaryDTO mapperToDTO(Commentary commentary){
    return mmp.map(commentary,CommentaryDTO.class);
    }

    private Commentary mapperToEntity(CommentaryDTO comDTO){
    return mmp.map(comDTO,Commentary.class);
    }
}
