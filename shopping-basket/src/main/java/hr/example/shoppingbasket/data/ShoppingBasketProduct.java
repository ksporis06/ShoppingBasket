package hr.example.shoppingbasket.data;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class ShoppingBasketProduct {

	private Product product;
	private Integer quantity;
	
	public BigDecimal getAmountTotal() {
		return product.getPrice().multiply(new BigDecimal(quantity));
	}
	
}
