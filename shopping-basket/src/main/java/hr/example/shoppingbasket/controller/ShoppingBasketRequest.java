package hr.example.shoppingbasket.controller;

import lombok.Value;

@Value
public class ShoppingBasketRequest {
	Long id;
	Integer quantity;
}
