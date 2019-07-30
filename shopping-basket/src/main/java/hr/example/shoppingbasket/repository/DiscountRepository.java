package hr.example.shoppingbasket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hr.example.shoppingbasket.data.Discount;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long>{

}
