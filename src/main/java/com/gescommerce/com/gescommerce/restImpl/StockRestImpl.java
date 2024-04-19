package com.gescommerce.com.gescommerce.restImpl;
import com.gescommerce.com.gescommerce.constants.CommerceConstants;
import com.gescommerce.com.gescommerce.dao.StockDao;
import com.gescommerce.com.gescommerce.modal.Article;
import com.gescommerce.com.gescommerce.modal.Stock;
import com.gescommerce.com.gescommerce.rest.StockRest;
import com.gescommerce.com.gescommerce.service.StockService;
import com.gescommerce.com.gescommerce.utils.CommerceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("stock")
public class StockRestImpl {
    private final StockService stockService;

    @Autowired
    public StockRestImpl(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/add")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        Stock savedStock = stockService.saveStock(stock);
        return ResponseEntity.ok(savedStock);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Stock stock = stockService.getStockById(id);
        if (stock != null) {
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        Stock updatedStock = stockService.updateStock(id, stock);
        if (updatedStock != null) {
            return ResponseEntity.ok(updatedStock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        String message = "Stock with ID " + id + " has been deleted successfully.";
        return ResponseEntity.ok(message);
    }


}


