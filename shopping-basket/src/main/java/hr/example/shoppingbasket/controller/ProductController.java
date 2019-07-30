package hr.example.shoppingbasket.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hr.example.shoppingbasket.data.Product;
import hr.example.shoppingbasket.service.ProductService;

@Controller
public class ProductController {
	@Autowired
    private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
    	return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product){
		return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id){
		Optional<Product> product = productService.getProduct(id);
		if(product.isPresent())
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) {
		if(!productService.getProduct(id).isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Product>(productService.updateProduct(product.withId(id)), HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity deleteProduct(@PathVariable Long id) {
		if(!productService.getProduct(id).isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
