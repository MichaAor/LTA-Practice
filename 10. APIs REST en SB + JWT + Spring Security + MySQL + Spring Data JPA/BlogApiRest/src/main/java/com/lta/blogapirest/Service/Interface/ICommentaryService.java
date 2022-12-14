package com.lta.blogapirest.Service.Interface;

import com.lta.blogapirest.DTO.CommentaryDTO;

import java.util.List;

public interface ICommentaryService {
    CommentaryDTO Get(Long idC,Long idP);
    List<CommentaryDTO> GetByPublication(Long idP);
    CommentaryDTO Save(Long idP,CommentaryDTO cmtDTO);
    CommentaryDTO Update(Long idC,Long idP,CommentaryDTO cmtDTO);
    void Delete(Long idC,Long idP);
}
