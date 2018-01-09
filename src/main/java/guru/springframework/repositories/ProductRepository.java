package guru.springframework.repositories;

import guru.springframework.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer>{
    Page<Product> findAll(Pageable pageable);
    List<Product> findAll();
}
