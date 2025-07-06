package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.KategoryProduct;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface KategoryProductRepository extends CrudRepository<KategoryProduct, Long> {
    List<KategoryProduct> findByNazvprodContaining(String nazvprod);
}
