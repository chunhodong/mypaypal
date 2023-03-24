package com.example.dicontainer.repository;

import com.example.dicontainer.domain.Rent;

import java.util.HashMap;
import java.util.Map;

public class RentRepositoryImpl implements RentRepository{
    private static final Map<Long, Rent> rents = new HashMap<>();

    @Override
    public void save(Rent rent) {
        rents.put(rent.getId(),rent);
    }

    @Override
    public void remove(Long id) {
        rents.remove(id);
    }
}
