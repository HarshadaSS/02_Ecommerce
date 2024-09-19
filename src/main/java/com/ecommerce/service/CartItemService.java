package com.ecommerce.service;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.EntityNotFound;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long id){
        return cartItemRepository.findById(id).get();
    }
    public CartItem saveCartItem(CartItem cartItem){
        CartItem savedCart = cartItemRepository.save(cartItem);
        Product product = productRepository.findById(savedCart.getProduct().getId()).get();
        product.setQuantity(product.getQuantity()-savedCart.getQuantity());
        productRepository.save(product);
        return savedCart;
    }

    public String deleteCartItem(Long id){
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(cartItem.isPresent()){
            cartItemRepository.deleteById(id);
            return "Cart item deleted";
        }
        throw new EntityNotFound("Entity not found");
    }
}
