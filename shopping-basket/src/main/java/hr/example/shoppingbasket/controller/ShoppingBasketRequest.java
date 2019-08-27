package hr.example.shoppingbasket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Value;

@Value
public class ShoppingBasketRequest {
	@NotEmpty
	@Valid
	List<ShoppingBasketItems> items;
	
	public ShoppingBasketRequest() {
		this.items = new ArrayList<ShoppingBasketItems>();
	}
}
