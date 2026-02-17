package petfeeder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.FoodStockException;

/**
 * Unit tests for the PetFeeder class.
 * These tests verify the behavior of food stock replenishment and checking.
 */
public class PetFeederTest {

    private PetFeeder pf;

    /**
     * Initialize a fresh PetFeeder instance before each test.
     */
    @BeforeEach
    public void setUp() throws Exception {
        pf = new PetFeeder();

    }

    /**
     * Test that initial food stock is set correctly.
     */
    @Test
    public void testInitialStock() {

        String initialStock = pf.checkFoodStock();
        String expectedInitial =
                        "Kibble: 15\n" +
                        "Water: 15\n" +
                        "Wet Food: 15\n" +
                        "Treats: 15\n";
        assertEquals(expectedInitial, initialStock);

    }

    /**
     *This test confirms the seeded bug in wet food: positive input throws exception
     */
    @Test
    public void testSeededWetFoodBug() {
        assertThrows(FoodStockException.class, () -> pf.replenishFood("1","1","1","1"));
    }

    /**
     * Test replenishing food with valid inputs.
     * Verifies that stock increases correctly.
     * Wet food = "0" avoids the seeded bug
     */
    @Test
    public void testReplenishFood_ValidInput() throws FoodStockException {

        pf.replenishFood("3", "5", "0", "4");

        String expected =
                        "Kibble: 18\n" +   // 15 + 3
                        "Water: 20\n" +    // 15 + 5
                        "Wet Food: 15\n" + // 15 + 0
                        "Treats: 19\n";    // 15 + 4

        assertEquals(expected, pf.checkFoodStock());
    }

    /**
     * Test that negative input values throw FoodStockException.
     */
    @Test
    public void testReplenishFood_NegativeInput() throws FoodStockException {

        assertThrows(FoodStockException.class, () -> pf.replenishFood("-2", "-3", "-50", "-1"));

    }

    /**
     * Test that non-numeric inputs throw FoodStockException.
     */
    @Test
    public void testReplenishFood_NonNumericInput() throws FoodStockException {

        assertThrows(FoodStockException.class, () -> pf.replenishFood("AA", "zz", "2024-12-08", "//&&"));

    }

    /**
     * Test replenishing food with zeros (no change to stock).
     */
    @Test
    public void testReplenishFood_Boundary_Zero() throws FoodStockException {

        pf.replenishFood("0", "0", "0", "0");

        String expected =
                        "Kibble: 15\n" +   // 15
                        "Water: 15\n" +    // 15
                        "Wet Food: 15\n" + // 15
                        "Treats: 15\n";    // 15

        assertEquals(expected, pf.checkFoodStock());
    }


    @Test
    public void testReplenishFood_Boundary_One() throws FoodStockException {

        pf.replenishFood("1", "1", "0", "1");

        String expected =
                        "Kibble: 16\n" +   // 16
                        "Water: 16\n" +    // 16
                        "Wet Food: 16\n" + // 16
                        "Treats: 16\n";    // 16

        assertEquals(expected, pf.checkFoodStock());
    }

    /**
     * Test cumulative replenishment using multiple sequential inputs.
     * Verifies that stock increases correctly after each replenishment.
     */
    @Test
    public void testReplenishFood_Cumulative() throws FoodStockException {

        String[][] replenishments = {
                //wetFood = 0 in order to correctly test other food items
                {"1", "2", "0", "4"}, // first replenishment
                {"4", "3", "0", "1"}, // second replenishment
                {"2", "2", "0", "2"}  // third replenishment
        };

        // Starting stock
        int kibble = 15;
        int water = 15;
        int wetFood = 15;
        int treats = 15;

        // Apply each replenishment and verify after each step
        for (String[] r : replenishments) {
            pf.replenishFood(r[0], r[1], r[2], r[3]);

            kibble += Integer.parseInt(r[0]);
            water += Integer.parseInt(r[1]);
            wetFood += Integer.parseInt(r[2]);
            treats += Integer.parseInt(r[3]);

            String expected =
                            "Kibble: " + kibble + "\n" +
                            "Water: " + water + "\n" +
                            "Wet Food: " + wetFood + "\n" +
                            "Treats: " + treats + "\n";

            assertEquals(expected, pf.checkFoodStock(),
                    "Stock should reflect cumulative replenishment after adding: " +
                            String.join(", ", r));
        }
    }


    }

