package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Check;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CheckRepository extends CrudRepository<Check, Long> {
    List<Check> findByAdressContaining(String adress);
}
