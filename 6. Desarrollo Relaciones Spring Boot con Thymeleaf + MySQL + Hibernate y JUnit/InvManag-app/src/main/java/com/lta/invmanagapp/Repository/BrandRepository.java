package com.lta.invmanagapp.Repository;

import com.lta.invmanagapp.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository  extends JpaRepository<Brand,Long> {
}

