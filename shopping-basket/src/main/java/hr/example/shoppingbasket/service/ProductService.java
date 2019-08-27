package hr.example.shoppingbasket.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.example.shoppingbasket.data.Product;
import hr.example.shoppingbasket.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	
	public Optional<Product> getProduct(Long id){
		return productRepository.findById(id);
	}
	
	public Product addProduct(Product product){
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
}
