package petfeeder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.FoodStockException;
import petfeeder.exceptions.MealPlanException;

/**
 * Unit tests for the PetFeeder class.
 * These tests verify the behavior of food stock replenishment and checking.
 */
public class PetFeederTest {

    private PetFeeder pf;
    private MealPlan m;

    /**
     * Initialize a fresh PetFeeder instance before each test.
     */
    @BeforeEach
    void setUp() throws Exception {
        pf = new PetFeeder();
        m = new MealPlan();

    }

    /**
     * Test that adding a valid meal plan returns true.
     */
    @Test
    void addMealPlan_whenValidMealPlan_returnsTrue() {
        assertTrue(pf.addMealPlan(m));
    }


    /**
     * Test that deleting a valid meal plan returns name of plan.
     */
    @Test
    void deleteMealPlan_whenValidMealPlan_returnsNameOfPlan() {
        pf.addMealPlan(m);
        assertEquals("", pf.deleteMealPlan(0));
    }

    /**
     * Test that deleting a invalid meal plan returns null.
     */
    @Test
    void deleteMealPlan_whenInvalidMealPlan_returnsNull() {
        pf.addMealPlan(m);
        assertNull(pf.deleteMealPlan(1));
    }


    /**
     * Test that editing a valid meal plan returns name of plan.
     */
    @Test
    void editMealPlan_whenValidMealPlan_returnsNameOfPlan() {
        pf.addMealPlan(m);
        assertEquals("", pf.editMealPlan(0, new MealPlan()));
    }

    /**
     * Test that editing a invalid meal plan returns null.
     */
    @Test
    void editMealPlan_whenInvalidMealPlan_returnsNull() {
        pf.addMealPlan(m);
        assertNull(pf.editMealPlan(1, new MealPlan()));
    }


    /**
     * This test confirms the seeded bug in wet food: positive input throws exception
     */
    @Test
    void replenishFood_whenPositiveWetFood_throwsFoodStockException() {
        assertThrows(FoodStockException.class, () -> pf.replenishFood("1", "1", "1", "1"));
    }

    /**
     * Test replenishing food with valid inputs.
     * Verifies that stock increases correctly.
     * Wet food = "0" avoids the seeded bug
     */
    @Test
    void replenishFood_whenValidInput_updatesStockCorrectly() throws FoodStockException {

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
    void replenishFood_whenNegativeInput_throwsFoodStockException() throws FoodStockException {

        assertThrows(FoodStockException.class, () -> pf.replenishFood("-2", "-3", "-50", "-1"));

    }

    /**
     * Test that non-numeric inputs throw FoodStockException.
     */
    @Test
    void replenishFood_whenNonNumericInput_throwsFoodStockException() throws FoodStockException {

        assertThrows(FoodStockException.class, () -> pf.replenishFood("AA", "zz", "2024-12-08", "//&&"));

    }

    /**
     * Test replenishing food with zeros (no change to stock).
     */
    @Test
    void replenishFood_whenInputZero_updatesStockCorrectly() throws FoodStockException {

        pf.replenishFood("0", "0", "0", "0");

        String expected =
                "Kibble: 15\n" +   // 15
                        "Water: 15\n" +    // 15
                        "Wet Food: 15\n" + // 15
                        "Treats: 15\n";    // 15

        assertEquals(expected, pf.checkFoodStock());
    }


    /**
     * Test replenishing food with one of each.
     */
    @Test
    void replenishFood_whenInputOne_updatesStockCorrectly() throws FoodStockException {

        pf.replenishFood("1", "1", "0", "1");

        String expected =
                        "Kibble: 16\n" +   // 16
                        "Water: 16\n" +    // 16
                        "Wet Food: 16\n" + // 16
                        "Treats: 16\n";    // 16

        assertEquals(expected, pf.checkFoodStock());
    }


    /**
     * Test replenishing food with large numbers of each.
     */
    @Test
    void replenishFood_whenLargeInput_updatesStockCorrectly() throws FoodStockException {

        pf.replenishFood("100000000000", "100000000000", "0", "100000000000");

        String expected =
                "Kibble: 100000000015\n" +
                        "Water: 100000000015\n" +
                        "Wet Food: 15\n" +
                        "Treats: 100000000015\n";

        assertEquals(expected, pf.checkFoodStock());
    }

    /**
     * Test cumulative replenishment using multiple sequential inputs.
     * Verifies that stock increases correctly after each replenishment.
     */
    @Test
    void replenishFood_whenCumulative_updatesStockCorrectly() throws FoodStockException {

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

    /**
     * Test that initial food stock is set correctly.
     */
    @Test
    void checkFoodStock_whenNewPetFeeder_returnsDefaultStock() {

        String initialStock = pf.checkFoodStock();
        String expectedInitial =
                "Kibble: 15\n" +
                        "Water: 15\n" +
                        "Wet Food: 15\n" +
                        "Treats: 15\n";
        assertEquals(expectedInitial, initialStock);

    }

    @Test
    void dispenseMeal_whenValidMealPlan_returnsTrue() {
        pf.addMealPlan(m);
        assertTrue(pf.dispenseMeal(0));
    }

    @Test
    void dispenseMeal_whenInvalidMealPlan_returnsFalse() {
        assertFalse(pf.dispenseMeal(0));    }

    @Test
    void dispenseMeal_whenValidMealPlanExpensive_returnsFalse() throws MealPlanException {

        m.setAmtKibble("10000");
        pf.addMealPlan(m);
        assertFalse(pf.dispenseMeal(0));
    }

}





