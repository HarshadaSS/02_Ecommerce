package com.ecommerce.service;

import com.ecommerce.entity.UserAddress;
import com.ecommerce.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    public List<UserAddress> getAllAddresses(){
        return userAddressRepository.findAll();
    }

    public UserAddress createAddress(UserAddress userAddress){
        return userAddressRepository.save(userAddress);
    }
}
