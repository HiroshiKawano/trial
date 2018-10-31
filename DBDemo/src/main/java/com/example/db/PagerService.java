package com.example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class PagerService {

    @Autowired
    private ItemRepository pagerRepo;

    public Page<Item> getAllPager(Pageable pageable){
        return pagerRepo.findAll(pageable);
    }
}