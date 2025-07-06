package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Dolj;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DoljRepository extends CrudRepository<Dolj, Long> {
    List<Dolj> findByNamedoljContaining(String namedolj);
}
