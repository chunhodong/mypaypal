package com.example.dicontainer.repository;

import com.example.dicontainer.domain.Rent;

public interface RentRepository {
    void save(Rent rent);

    void remove(Long id);
}
