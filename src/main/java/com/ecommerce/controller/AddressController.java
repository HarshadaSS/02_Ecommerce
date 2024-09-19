package com.ecommerce.controller;

import com.ecommerce.bean.GlobalResponseHandler;
import com.ecommerce.entity.UserAddress;
import com.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/address")
    public ResponseEntity<Object> createAddress(@RequestBody UserAddress address){
        UserAddress savedAddress = addressService.createAddress(address);
        return GlobalResponseHandler.createResponse("address created",savedAddress, HttpStatus.CREATED);
    }

    @GetMapping("/address")
    public ResponseEntity<Object> getAllAddress(){
        List<UserAddress> addressList = addressService.getAllAddresses();
        return GlobalResponseHandler.createResponse("Fetched Address",addressList,HttpStatus.OK);
    }
}
