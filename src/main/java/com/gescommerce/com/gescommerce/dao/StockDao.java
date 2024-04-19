package com.gescommerce.com.gescommerce.dao;

import com.gescommerce.com.gescommerce.modal.Article;
import com.gescommerce.com.gescommerce.modal.Stock;
import com.gescommerce.com.gescommerce.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockDao extends JpaRepository<Stock, Long> {
    //Optional<Stock> findByArticle(Article article);

    //Optional<Stock> findByArticleId(Long articleId);
}
