package guru.springframework.services;


import guru.springframework.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);
}
