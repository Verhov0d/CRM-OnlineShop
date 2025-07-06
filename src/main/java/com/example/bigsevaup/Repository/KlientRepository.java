package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Klient;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface KlientRepository extends CrudRepository<Klient, Long> {
    List<Klient> findByNameklientContaining(String nameklient);
}
