package hr.example.shoppingbasket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import hr.example.shoppingbasket.data.Discount;
import hr.example.shoppingbasket.service.DiscountService;

@Controller
public class DiscountController {
	
	@Autowired
    private DiscountService discountService;
	
	@GetMapping("/discounts")
	public ResponseEntity<List<Discount>> getAllDiscounts(){
		List<Discount> discounts = discountService.getAllDiscounts();
    	return new ResponseEntity<List<Discount>>(discounts, HttpStatus.OK);
	}
	
}
