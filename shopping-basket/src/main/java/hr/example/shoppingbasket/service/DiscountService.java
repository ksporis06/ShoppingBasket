package hr.example.shoppingbasket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.example.shoppingbasket.data.Discount;
import hr.example.shoppingbasket.data.Product;
import hr.example.shoppingbasket.repository.DiscountRepository;

@Service
public class DiscountService {

	@Autowired
	private DiscountRepository discountRepository;
	
	public List<Discount> getAllDiscounts(){
		List<Discount> discounts = new ArrayList<>();
		discountRepository.findAll().forEach(discounts::add);
		return discounts;
	}
	
	public Optional<Discount> getDiscount(Long id){
		return discountRepository.findById(id);
	}
	
	public Discount addDiscount(Discount discount){
		return discountRepository.save(discount);
	}
	
	public Discount updateDiscount(Discount discount) {
		return discountRepository.save(discount);
	}
	
	public void deleteDiscount(Long id) {
		discountRepository.deleteById(id);
	}
	
	public List<Discount> getDiscountsForProduct(Product product, Integer quantity){
		List<Discount> discounts = new ArrayList<>();
		if(product==null || quantity==null)
			return discounts;
		
		discountRepository.findAll()
		.forEach(discount -> {
			if(discount.getRequiredProduct().equals(product) && discount.getRequiredProductQuantity()<=quantity)
				discounts.add(discount);
		});
		return discounts;
	}
}
