package hr.example.shoppingbasket.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.example.shoppingbasket.data.Discount;
import hr.example.shoppingbasket.data.ShoppingBasketDiscount;
import hr.example.shoppingbasket.data.ShoppingBasketProduct;

@Service
public class PriceCalculatorService {
	@Autowired
	private DiscountService discountService;
	
	public List<ShoppingBasketDiscount> getShoppingBasketDiscounts(List<ShoppingBasketProduct> basketProducts) {
		List<Discount> possibleDiscounts = new ArrayList<>();
		basketProducts.stream()
			.forEach(bp -> possibleDiscounts.addAll(discountService.getDiscountsForProduct(bp.getProduct(), bp.getQuantity())));
		
		List<ShoppingBasketDiscount> shoppingBasketDiscounts = new ArrayList<>();
		
		possibleDiscounts.stream().forEach(discount -> {
			// check if appliesTo product exists
			if(basketProducts.stream().filter(bp -> bp.getProduct().equals(discount.getAppliesToProduct())).findAny().isPresent()) {
				Integer requiredProductBasketQuantity = basketProducts.stream()
						.filter(bp -> bp.getProduct().equals(discount.getRequiredProduct()))
						.map(bp -> bp.getQuantity())
						.findFirst().get(); 
				
				Integer appliesToProductBasketQuantity = basketProducts.stream()
						.filter(bp -> bp.getProduct().equals(discount.getAppliesToProduct()))
						.map(bp -> bp.getQuantity())
						.findFirst().get(); 
				
				Integer quantity = Math.min((requiredProductBasketQuantity / discount.getRequiredProductQuantity()), appliesToProductBasketQuantity);
				
				BigDecimal discountAmount = discount.getAppliesToProduct().getPrice().multiply(discount.getDiscountPercentage().divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
				shoppingBasketDiscounts.add(new ShoppingBasketDiscount(discount, quantity, discountAmount));
			}
		});
		
		return shoppingBasketDiscounts;
	}
}
