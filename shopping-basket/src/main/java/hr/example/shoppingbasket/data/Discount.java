package hr.example.shoppingbasket.data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
@Entity
@Table(name = "discounts")
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "required_product_id")
	private Product requiredProduct; 
	
	@NotNull
	private Integer requiredProductQuantity;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "applies_to_product_id")
	private Product appliesToProduct;
	
	@NotNull
	private BigDecimal discountPercentage;
	
	public Discount(String name, Product requiredProduct, Integer requiredProductQuantity,
			Product appliesToProduct, BigDecimal discountPercentage) {
		this.name=name;
		this.requiredProduct = requiredProduct;
		this.requiredProductQuantity = requiredProductQuantity;
		this.appliesToProduct = appliesToProduct;
		this.discountPercentage = discountPercentage;
	}
}
