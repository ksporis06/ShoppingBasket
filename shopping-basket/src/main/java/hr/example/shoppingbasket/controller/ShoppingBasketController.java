package hr.example.shoppingbasket.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hr.example.shoppingbasket.data.ShoppingBasket;
import hr.example.shoppingbasket.data.ShoppingBasketDiscount;
import hr.example.shoppingbasket.data.ShoppingBasketProduct;
import hr.example.shoppingbasket.service.PriceCalculatorService;
import hr.example.shoppingbasket.service.ProductService;

@Controller
public class ShoppingBasketController {
	@Autowired
    private ProductService productService;
	
	@Autowired
	private PriceCalculatorService priceCalculatorService;
	
	@PostMapping("/shopping_basket")
	public ResponseEntity<ShoppingBasket> addProduct(@RequestBody List<ShoppingBasketRequest> request){
		
		if(!checkIfProductsExist(request))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<ShoppingBasketProduct> products = request.stream()
			.map(pq -> new ShoppingBasketProduct(productService.getProduct(pq.getId()).get(), pq.getQuantity()))
			.collect(Collectors.toList());
		
		// check for discounts
		List<ShoppingBasketDiscount> discounts = priceCalculatorService.getShoppingBasketDiscounts(products);
		
		// create basket object
		ShoppingBasket shoppingBasket = new ShoppingBasket(products, discounts);
		
		// log request
		System.out.println(shoppingBasket.toString());
		
		// return result
		return new ResponseEntity<ShoppingBasket>(shoppingBasket, HttpStatus.OK);
	}
	
	private Boolean checkIfProductsExist(List<ShoppingBasketRequest> request) {
		Optional<Long> missingProductId = request.stream()
				.map(pq -> pq.getId())
				.filter(pId -> !productService.getProduct(pId).isPresent())
				.findFirst();
		if(missingProductId.isPresent())
			return false;
		return true;
	}
	
	
	
}
