package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Postavshik;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PostavshikRepository extends CrudRepository<Postavshik, Long> {
    List<Postavshik> findByNamepostavshikContaining(String namepostavshik);
}
