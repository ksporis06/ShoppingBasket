package hr.example.shoppingbasket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.example.shoppingbasket.data.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
