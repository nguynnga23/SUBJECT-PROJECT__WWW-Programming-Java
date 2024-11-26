package com.example.backend.services;

import com.example.backend.models.Address;

import java.util.List;

public interface AddressService {
    void save(Address address);
    void delete(long id);
    void update(Address address, Long id);
    List<Address> findAll();
}
