package com.lta.invmanagapp.Service.IService;

import com.lta.invmanagapp.Model.CartArticle;

import java.util.List;
import java.util.Optional;

public interface ICartArticle {
    List<CartArticle> GetAll();
    Optional<CartArticle> Get(Long idC);
    void Save(CartArticle article);
    void Update(CartArticle article);
    void Delete(Long idC);
}
