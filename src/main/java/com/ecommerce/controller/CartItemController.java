package com.ecommerce.controller;

import com.ecommerce.bean.GlobalResponseHandler;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;


    @GetMapping("/cart")
    public ResponseEntity<Object> getAllCartItems(){
        return GlobalResponseHandler.createResponse("Found CartItems!",cartItemService.getAllCartItems(), HttpStatus.OK);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Object> getCartItemsById(@PathVariable Long id){
        return GlobalResponseHandler.createResponse("Found CartItem!",cartItemService.getCartItemById(id), HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<Object> saveCartItem(@RequestBody CartItem cartItem){
        return GlobalResponseHandler.createResponse("Saved CartItems!",cartItemService.saveCartItem(cartItem),HttpStatus.CREATED);
    }
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        return GlobalResponseHandler.createResponse("Deleted CartItem!",cartItemService.deleteCartItem(id),HttpStatus.OK);
    }
}
