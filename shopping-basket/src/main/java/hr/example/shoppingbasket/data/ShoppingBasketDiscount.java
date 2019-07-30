package hr.example.shoppingbasket.data;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class ShoppingBasketDiscount {

	private Discount discount;
	private Integer quantity;
	private BigDecimal amount;
	
	public BigDecimal getAmountTotal() {
		return amount.multiply(new BigDecimal(quantity));
	}
}
