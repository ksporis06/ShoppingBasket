package hr.example.shoppingbasket.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.Value;

@Value
public class ShoppingBasket {
	private List<ShoppingBasketProduct> basketProducts;
	private List<ShoppingBasketDiscount> basketDiscounts;
	private BigDecimal amountTotal;
	
	public ShoppingBasket(List<ShoppingBasketProduct> basketProducts, List<ShoppingBasketDiscount> basketDiscounts) {
		this.basketProducts = basketProducts;
		this.basketDiscounts = basketDiscounts;
		this.amountTotal = getAmountTotal();
	}
	
	public BigDecimal getAmountTotal() {
		BigDecimal productTotal = basketProducts.stream()
				.map(product -> product.getAmountTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal discountTotal = basketDiscounts.stream()
				.map(discount -> discount.getAmountTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.setScale(2, RoundingMode.HALF_UP);
		
		if(discountTotal.compareTo(productTotal)>0)
			return new BigDecimal(0);
		
		return productTotal.subtract(discountTotal);
	}
	
}
