package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Usluga;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UslugaRepository extends CrudRepository<Usluga, Long> {
    List<Usluga> findByNameuslugaContaining(String nameusluga);
}
