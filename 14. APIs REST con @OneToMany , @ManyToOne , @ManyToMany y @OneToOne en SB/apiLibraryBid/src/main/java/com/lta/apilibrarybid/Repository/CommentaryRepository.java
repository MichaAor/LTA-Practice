package com.lta.apilibrarybid.Repository;

import com.lta.apilibrarybid.Model.Commentary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary,Long> {
    Page<Commentary> findAllByPublicationId(Long idP, Pageable pageable);
    Optional<Commentary> findByIdAndPublicationId(Long idC,Long idP);
}
