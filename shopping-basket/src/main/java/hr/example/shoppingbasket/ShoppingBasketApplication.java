package hr.example.shoppingbasket;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hr.example.shoppingbasket.data.Discount;
import hr.example.shoppingbasket.data.Product;
import hr.example.shoppingbasket.repository.DiscountRepository;
import hr.example.shoppingbasket.repository.ProductRepository;

@SpringBootApplication
public class ShoppingBasketApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingBasketApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(ProductRepository productRepository, DiscountRepository discountRepository) throws Exception{
		return (String[] args) -> {
			
			Product productButter = new Product("Butter", new BigDecimal(0.80));
			Product productMilk = new Product("Milk", new BigDecimal(1.15));
			Product productBread = new Product("Bread", new BigDecimal(1.00));
        	productRepository.save(productButter);
        	productRepository.save(productMilk);
        	productRepository.save(productBread);
        	System.out.println("Available products:");
        	productRepository.findAll().forEach(product -> System.out.println(product));
        	
        	
        	Discount discountBread = new Discount("Bread discount", productButter, 2, productBread, new BigDecimal(50.0));
        	Discount discountMilk = new Discount("Milk discount", productMilk, 4, productMilk, new BigDecimal(100.0));
        	discountRepository.save(discountBread);
        	discountRepository.save(discountMilk);
        	System.out.println("Available discounts:");
        	discountRepository.findAll().forEach(discount -> System.out.println(discount));
		};
    }

}
