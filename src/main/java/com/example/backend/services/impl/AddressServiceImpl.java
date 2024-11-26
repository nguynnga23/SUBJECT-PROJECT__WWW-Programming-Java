package com.example.backend.services.impl;

import com.example.backend.models.Address;
import com.example.backend.repositories.AddressRepository;
import com.example.backend.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void delete(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public void update(Address address, Long id) {
        if(addressRepository.findById(id).isPresent()) {
            addressRepository.save(address);
        }
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
