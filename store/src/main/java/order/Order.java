package order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

	List<OrderItem> items = new ArrayList<OrderItem>();
	private OrderState state = OrderState.NEW;

	public void addItem(OrderItem item) {
		if (this.state.equals(OrderState.VOID)) {
			return;
		}
		this.items.add(item);
	}

	public int getOrderItemCount() {
		return items.size();
	}

	public void markVoid() {
		for (OrderItem item : items) {
			item.markVoid();
		}
		this.state = OrderState.VOID;
	}

	public boolean isVoid() {
		return OrderState.VOID.equals(state);
	}

	public OrderItem getOrderItem(String sku) {
		for (OrderItem item : items) {
			if (item.getSku().equals(sku)) {
				return item;
			}
		}
		return null;
	}

	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal("0.00");
		for (OrderItem item : this.items) {
			if (!item.isVoid()) {
				total = total.add(item.getPrice());
			}
		}
		return total;
	}
}
