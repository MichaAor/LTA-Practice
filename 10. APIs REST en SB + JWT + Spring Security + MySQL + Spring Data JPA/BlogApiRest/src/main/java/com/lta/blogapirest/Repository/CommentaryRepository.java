package com.lta.blogapirest.Repository;

import com.lta.blogapirest.Model.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary,Long> {
    List<Commentary> findByPublicationId(Long idP);
}
