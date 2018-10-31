package com.example.db;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
    public List<Item> findByNameLikeOrderByIdAsc(String name);
    public List<Item> findByPriceGreaterThanAndPriceLessThanOrderByPriceAsc(Integer low, Integer high);
    @Query(value="select it from Item it where it.name like :name")
    public List<Item> myQuery(@Param("name") String name);
    @Query(value="select i from Item i where id = ?1 and price = ?2")
    public List<Item> test(Integer a,Integer b);
    //ページング
    public Page<Item> findAll(Pageable pageable);

}
