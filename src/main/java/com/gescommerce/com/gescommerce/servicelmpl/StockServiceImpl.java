package com.gescommerce.com.gescommerce.servicelmpl;

import com.gescommerce.com.gescommerce.dao.ArticleDao;
import com.gescommerce.com.gescommerce.dao.StockDao;
import com.gescommerce.com.gescommerce.modal.Article;
import com.gescommerce.com.gescommerce.modal.Stock;
import com.gescommerce.com.gescommerce.rest.StockRest;
import com.gescommerce.com.gescommerce.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private final StockDao StockDao;

    @Autowired
    public StockServiceImpl(StockDao stockDao) {
        this.StockDao = stockDao;
    }

    @Override
    public Stock saveStock(Stock stock) {
        return StockDao.save(stock);
    }

    @Override
    public Stock getStockById(Long id) {
        Optional<Stock> optionalStock = StockDao.findById(id);
        return optionalStock.orElse(null);
    }

    @Override
    public List<Stock> getAllStocks() {
        return StockDao.findAll();
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        Optional<Stock> optionalStock = StockDao.findById(id);
        if (optionalStock.isPresent()) {
            Stock existingStock = optionalStock.get();
            existingStock.setArticleName(stock.getArticleName());
            existingStock.setQuantity(stock.getQuantity());
            return StockDao.save(existingStock);
        }
        return null;
    }

    @Override
    public void deleteStock(Long id) {
        StockDao.deleteById(id);
    }
}
