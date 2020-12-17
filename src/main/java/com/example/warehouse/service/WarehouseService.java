package com.example.warehouse.service;

import com.example.warehouse.domain.Article;
import com.example.warehouse.domain.Product;
import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.repository.ArticleRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ArticleRepository articleRepository;

    public void removeProduct(Long id) {
        Optional<Product> result = productRepository.findById(id);

        if (result.isPresent()) {
            //update inventory
            Product product = result.get();
            for (Article item : product.getContain_articles()) {
                updateInventory(item.getId(), item.getStock());
            }
            //remove product
            productRepository.delete(product);
        }
    }

    public List<ProductDto> getProducts() {
        List<ProductDto> result = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        for (Product item : products) {
            ProductDto itemDto = new ProductDto();
            BeanUtils.copyProperties(item, itemDto);
            result.add(itemDto);
        }
        return result;
    }

    private void updateInventory(Long art_id, Integer stock) {
        Optional<Article> item = articleRepository.findById(art_id);
        if (item.isPresent()) {
            Article article = item.get();
            article.setStock(article.getStock() - stock);
            articleRepository.save(article);
        }
    }
}
