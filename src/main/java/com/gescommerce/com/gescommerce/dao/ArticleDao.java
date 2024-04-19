package com.gescommerce.com.gescommerce.dao;
import com.gescommerce.com.gescommerce.modal.Article;
import com.gescommerce.com.gescommerce.wrapper.ArticleWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ArticleDao extends JpaRepository<Article, Integer> {
    List<ArticleWrapper> getAllArticle();

    @Modifying
    @Transactional
    Integer updateArticleStatus(@Param("status") String status, @Param("id") Integer id);

    List<ArticleWrapper> getArticleByCategory(@Param("id") Integer id);

    ArticleWrapper getArticleById(@Param("id") Integer id);

    Optional<Article> findById(Long articleId);
}
