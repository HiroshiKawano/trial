package com.example.db;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
    public List<Item> findByNameLikeOrderByIdAsc(String name);
    public List<Item>
        findByPriceGreaterThanAndPriceLessThanOrderByPriceAsc(Integer low, Integer high);
    @Query(value="select it from Item it where it.name like :name")
    public List<Item> myQuery(@Param("name") String name);
}