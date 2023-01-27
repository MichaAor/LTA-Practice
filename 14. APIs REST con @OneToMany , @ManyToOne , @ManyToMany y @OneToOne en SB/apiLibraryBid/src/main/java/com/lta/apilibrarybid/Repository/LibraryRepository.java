package com.lta.apilibrarybid.Repository;

import com.lta.apilibrarybid.Model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {
}
