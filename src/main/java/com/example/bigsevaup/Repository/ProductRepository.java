package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByNameproductContaining(String nameproduct);
    long countBykategoryProduct_Id(long id);

    @Transactional
    @Query(value = "SELECT * FROM product WHERE kategory_product_id = :id", nativeQuery = true)
    List<Product> findBykategory(@Param("id") Integer kategoryproductid);
}