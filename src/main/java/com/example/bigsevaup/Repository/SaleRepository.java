package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Sale;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {
    List<Sale> findByNamesaleContaining(String namesale);
}
