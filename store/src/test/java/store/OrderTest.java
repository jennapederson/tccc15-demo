package store;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import order.Order;
import order.OrderItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderTest {

	@Mock
	private OrderItem orderItem1;
	@Mock
	private OrderItem orderItem2;
	private Order order;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		order = new Order();
	}

	@Test
	public void testOrderItemNotAddedWhenStateVoid() {
		order.markVoid();
		assertThat(order.getOrderItemCount(), is(0));
		order.addItem(orderItem1);
		assertThat(order.getOrderItemCount(), is(0));
	}

	@Test
	public void testItemMarkedVoidWhenOrderMarkedVoid() {
		order.addItem(orderItem1);
		order.markVoid();
		verify(orderItem1).markVoid();
	}

	@Test
	public void testGetTotalIsTotalOfNonVoidItems() {
		order.addItem(orderItem1);
		order.addItem(orderItem2);

		when(orderItem1.isVoid()).thenReturn(false);
		when(orderItem1.getPrice()).thenReturn(new BigDecimal("10.00"));
		when(orderItem2.isVoid()).thenReturn(true);

		assertThat(order.getTotal(), is(new BigDecimal("10.00")));
		verify(orderItem1).isVoid();
		verify(orderItem1).getPrice();
		verify(orderItem2).isVoid();
	}

	@Test
	public void testGetOrderItemBySku() {
		order.addItem(orderItem1);
		order.addItem(orderItem2);

		// expect orderItem2 sku to be 123

		order.getOrderItem("123");

		// expect get sku to be called
	}
}
