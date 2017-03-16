package tap.gianninicinquilli.tapproject;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class CartTest {
	private Cart cart;
	private Dish dish1;
	private Dish dish2;
	private Bank bank;
	private CreditCard creditCard;

	@Before
	public void setup() {
		dish1 = mock(Dish.class);
		dish2 = mock(Dish.class);
		bank = mock(Bank.class);
		creditCard = mock(CreditCard.class);
		cart = new Cart(bank, creditCard);
		cart.add(dish1);
		cart.add(dish2);
		when(dish1.getPrice()).thenReturn(10.5);
		when(dish2.getPrice()).thenReturn(6.0);
	}

	@Test
	public void testCalculateTotal() {
		assertEquals(16.5, cart.calculateTotal(), 0);
	}

	@Test
	public void testPayAccepted() {
		when(bank.acceptPayment(creditCard.getCardNumber())).thenReturn(true);
		when(bank.receivedPayment(16.5)).thenReturn(true);
		boolean accept = cart.pay();
		verify(bank, times(1)).acceptPayment(creditCard.getCardNumber());
		verify(bank, times(1)).receivedPayment(anyDouble());
		assertTrue(accept);
	}

	@Test
	public void testPayNotAccepted() {
		when(bank.acceptPayment(creditCard.getCardNumber())).thenReturn(false);
		boolean accept = cart.pay();
		verify(bank, never()).receivedPayment(anyDouble());
		verify(bank, times(1)).acceptPayment(creditCard.getCardNumber());
		assertFalse(accept);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetDishWithWrongIndexThrowException() {
		cart.getDish(5);
	}

	@Test
	public void testGetDish() {
		Dish returned = cart.getDish(0);
		assertEquals(dish1, returned);
	}

	@Test
	public void testSizeOfEmptyCart() {
		cart = new Cart(bank, creditCard);
		assertEquals(0, cart.size());
	}

	@Test
	public void testSizeOfNotEmptyCart() {
		assertEquals(2, cart.size());
	}

}
