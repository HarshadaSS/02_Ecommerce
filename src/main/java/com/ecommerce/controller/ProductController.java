package com.ecommerce.controller;


import com.ecommerce.bean.GlobalResponseHandler;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/fetchproducts")
    public ResponseEntity<Object> getALlProducts(){
        return GlobalResponseHandler.createResponse("Found Products!",productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){
        return GlobalResponseHandler.createResponse("Found Product!",productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product){
        return GlobalResponseHandler.createResponse("Saved Product!",productService.saveProduct(product),HttpStatus.CREATED);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        return GlobalResponseHandler.createResponse("Deleted Product!",productService.deleteProduct(id),HttpStatus.OK);
    }
}
