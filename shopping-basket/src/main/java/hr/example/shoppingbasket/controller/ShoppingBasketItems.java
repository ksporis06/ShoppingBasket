package hr.example.shoppingbasket.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
public class ShoppingBasketItems {
	@NotNull
	Long id;
	
	@NotNull
	@Min(value=1,message="Value must be positive")
	Integer quantity;
	
	public ShoppingBasketItems() {
		this.id = null;
		this.quantity = null;
	}
}
