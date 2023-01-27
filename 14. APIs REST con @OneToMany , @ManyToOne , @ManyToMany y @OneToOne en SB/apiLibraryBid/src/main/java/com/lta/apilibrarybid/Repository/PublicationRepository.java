package com.lta.apilibrarybid.Repository;

import com.lta.apilibrarybid.Model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {

}
