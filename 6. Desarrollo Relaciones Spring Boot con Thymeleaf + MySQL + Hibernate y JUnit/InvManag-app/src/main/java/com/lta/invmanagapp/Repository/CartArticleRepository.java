package com.lta.invmanagapp.Repository;

import com.lta.invmanagapp.Model.CartArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartArticleRepository extends JpaRepository<CartArticle,Long> {
}
