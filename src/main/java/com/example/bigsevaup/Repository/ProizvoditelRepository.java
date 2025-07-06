package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Proizvoditel;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProizvoditelRepository extends CrudRepository<Proizvoditel, Long> {
    List<Proizvoditel> findByNameproizvoditelContaining(String nameproizvoditel);
}
