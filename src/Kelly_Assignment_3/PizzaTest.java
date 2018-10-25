package Kelly_Assignment_3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

class PizzaTest {

	// Some legal argument tests:
	// Default constructor.
	// Also tests for toString and getCost.
	@Test
	public void testPizza() throws Exception {
		Pizza pizza1 = new Pizza();
		System.out.println(pizza1);
		System.out.println(pizza1.getCost());
		assertEquals("small pizza, single cheese, ham. Cost: $8.50 each.", pizza1.toString());
//		              small pizza, single cheese, ham. Cost: $8.50 each.

		assertEquals(8.50, pizza1.getCost(), 0.01);
	}
	
	// Five parameter constructor
	@Test
	public void testPizza1() throws Exception {
		Pizza pizza1 = new Pizza("small", "single", "none", "none", "none");
		System.out.println(pizza1);
		System.out.println(pizza1.getCost());

		assertEquals("small pizza, single cheese. Cost: $7.00 each.", pizza1.toString());
//		              small pizza, single cheesenone pineapplenone peppersnone ham. Cost: $7.00 each.

		assertEquals(7.00, pizza1.getCost(), 0.01);		
	}
	@Test
	public void testPizza2() throws Exception {
		Pizza pizza1 = new Pizza("medium", "triple", "none", "none", "none");
		System.out.println(pizza1);
		System.out.println(pizza1.getCost());
		assertEquals("medium pizza, triple cheese. Cost: $12.00 each.", pizza1.toString());
		assertEquals(12.00, pizza1.getCost(), 0.01);		
	}
	@Test
	public void testPizza3() throws Exception {
		Pizza pizza1 = new Pizza("large", "double", "single", "single", "single");
		System.out.println(pizza1);
		System.out.println(pizza1.getCost());
//		assertEquals("large pizza, double cheese, pineapple, green pepper, ham. Cost: $17.00 each.", pizza1.toString());
		assertEquals("large pizza, double cheese, pineapple, peppers, ham. Cost: $17.00 each.", pizza1.toString());
//		              large pizza, double cheese, pineapple, peppers, ham. Cost: $17.00 each.
		assertEquals(17.00, pizza1.getCost(), 0.01);		
	}
	@Test
	public void testPizza4() throws Exception {
//		Pizza pizza1 = new Pizza("Large", "Single", "Single", "None", "Single");
		Pizza pizza1 = new Pizza("Large", "Single", "Single", "Single", "None");
		System.out.println(pizza1);
		System.out.println(pizza1.getCost());
		assertEquals("large pizza, single cheese, pineapple, ham. Cost: $14.00 each.", pizza1.toString());
		assertEquals(14.00, pizza1.getCost(), 0.01);		
	}

	// Some illegal argument tests
	// Five parameter constructor
	@Test
	public void testPizza5() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("bork", "single", "single", "single", "single"));
	}
	@Test
	public void testPizza6() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("", "single", "single", "single", "single"));
	}
	@Test
	public void testPizza7() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza(null, "single", "single", "single", "single"));
	}
	@Test
	public void testPizza8() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "bork", "single", "single", "single"));
	}
	@Test
	public void testPizza9() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "", "single", "single", "single"));
	}
	@Test
	public void testPizza10() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", null, "single", "single", "single"));
	}
	@Test
	public void testPizza11() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "bork", "single", "single"));
	}
	@Test
	public void testPizza12() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "", "single", "single"));
	}
	@Test
	public void testPizza13() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", null, "single", "single"));
	}
	@Test
	public void testPizza14() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "single", "bork", "single"));
	}
	@Test
	public void testPizza15() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "single", "", "single"));
	}
	@Test
	public void testPizza16() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "single", null, "single"));
	}
	@Test
	public void testPizza17() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "single", "single", "bork"));
	}
	@Test
	public void testPizza18() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "single", "single", ""));
	}
	@Test
	public void testPizza19() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "single", "single", null));
	}
	
	// Cannot have pineapple without ham
	@Test
	public void testPizza20() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "single", "none", "none"));
	}
	// Cannot have green pepper without ham
	@Test
	public void testPizza21() throws Exception {
		assertThrows(IllegalPizza.class, () -> new Pizza("small", "single", "none", "none", "single"));
	}

	@Test
	public void testEqualsObject() throws Exception {
		Pizza pizza1 = new Pizza("large", "single", "single", "single", "single");
		Pizza pizza2 = new Pizza("large", "single", "single", "single", "single");
		Pizza pizza3 = new Pizza("medium", "single", "single", "single", "single");
		Pizza pizza4 = new Pizza("large", "double", "single", "single", "single");
//		Pizza pizza5 = new Pizza("large", "single", "none", "none", "single");
		Pizza pizza5 = new Pizza("large", "single", "none", "single", "none");
		assertEquals(pizza1, pizza2);
		assertTrue(pizza1.equals(pizza2));
		assertFalse(pizza1.equals(pizza3));
		assertFalse(pizza1.equals(pizza4));
		assertFalse(pizza1.equals(pizza5));
	}

	@Test
	public void testClone() throws Exception {
		Pizza pizza1 = new Pizza("large", "single", "single", "single", "single");
//		Pizza pizza2 = pizza1.clone();
		Pizza pizza2 = pizza1.cloneMethod();
		assertEquals(pizza1, pizza2);
	}

	@Test
	public void testFileSave() throws Exception {
    	String testFile = "OnePizza.dat";
		Pizza pizza1 = new Pizza("large", "triple", "single", "single", "single");
		Pizza pizza2;
    	try (ObjectOutputStream binFileOut = new ObjectOutputStream(new FileOutputStream(testFile))) {
            binFileOut.writeObject(pizza1);
        }
        try (ObjectInputStream binFileIn = new ObjectInputStream(new FileInputStream(testFile))) {
            pizza2 = (Pizza)binFileIn.readObject();
        }
        assertEquals(pizza1, pizza2, "Comparing object from file to object saved.");
    }

} // end PizzaTest
