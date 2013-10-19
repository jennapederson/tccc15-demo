package order;

import java.math.BigDecimal;

public class OrderItem {

	private final String sku;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderItemType getCategory() {
		return category;
	}

	public void setCategory(OrderItemType category) {
		this.category = category;
	}

	private final BigDecimal price;
	private OrderItemType category;
	private OrderItemState state = OrderItemState.NEW;

	public OrderItem(String sku, String description, OrderItemType category,
			BigDecimal price) {
		this.sku = sku;
		this.description = description;
		this.category = category;
		this.price = price;
	}

	public void markVoid() {
		this.state = OrderItemState.VOID;
	}

	public boolean isVoid() {
		return OrderItemState.VOID.equals(state);
	}

	public String getSku() {
		return sku;
	}

	public BigDecimal getPrice() {
		return price;
	}
}
