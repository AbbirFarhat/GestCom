package com.gescommerce.com.gescommerce.service;

import com.gescommerce.com.gescommerce.modal.Article;
import com.gescommerce.com.gescommerce.modal.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StockService {
    Stock saveStock(Stock stock);

    Stock getStockById(Long id);

    List<Stock> getAllStocks();

    Stock updateStock(Long id, Stock stock);

    void deleteStock(Long id);
}

