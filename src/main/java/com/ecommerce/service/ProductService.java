package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.exception.EntityNotFound;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public String deleteProduct(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return "Product deleted";
        }
        throw new EntityNotFound("Entity not found");

    }
}
