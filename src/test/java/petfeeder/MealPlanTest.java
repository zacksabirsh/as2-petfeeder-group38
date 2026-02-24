package petfeeder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.FoodStockException;
import petfeeder.exceptions.MealPlanException;

/**
 * Unit tests for the MealPlan class.
 */
public class MealPlanTest {

    private MealPlan m;

    /**
     * Initialize a fresh MealPlan instance before each test.
     */
    @BeforeEach
    void setUp() throws Exception {
        m = new MealPlan();
    }

    @Test
    void setAmtTreats_whenValid_updatesTreatsAndEnergyCorrectly() throws MealPlanException {
        m.setAmtTreats("10");
        assertEquals(10,m.getAmtTreats());
        assertEquals(200, m.getEnergyCost()); //10*20
    }

    @Test
    void setAmtTreats_whenZero_updatesTreatsAndEnergyCorrectly() throws MealPlanException {
        m.setAmtTreats("0");
        assertEquals(0,m.getAmtTreats());
        assertEquals(0, m.getEnergyCost());
    }

    @Test
    void setAmtTreats_whenInvalid_throwsMealPlanException() throws MealPlanException {
        assertThrows(MealPlanException.class, ()->{
            m.setAmtTreats("aa");
        });

    }

    @Test
    void setAmtTreats_whenNegative_throwsMealPlanException() throws MealPlanException {
        assertThrows(MealPlanException.class, ()->{
            m.setAmtTreats("-10");
        });

    }


//-----------------

    @Test
    void setAmtKibble_whenValid_updatesKibbleAndEnergyCorrectly() throws MealPlanException {
        m.setAmtKibble("2");
        assertEquals(2, m.getAmtKibble());
        assertEquals(20, m.getEnergyCost()); // 2 * 20
    }

    @Test
    void setAmtKibble_whenZero_updatesKibbleAndEnergyCorrectly() throws MealPlanException {
        m.setAmtKibble("0");
        assertEquals(0, m.getAmtKibble());
        assertEquals(0, m.getEnergyCost());
    }

    @Test
    void setAmtKibble_whenInvalid_throwsMealPlanException() {
        assertThrows(MealPlanException.class, () -> {
            m.setAmtKibble("abc");
        });
    }

    @Test
    void setAmtKibble_whenNegative_throwsMealPlanException() {
        assertThrows(MealPlanException.class, () -> {
            m.setAmtKibble("-5");
        });
    }


    //--------------


    @Test
    void setAmtWater_whenValid_updatesWaterAndEnergyCorrectly() throws MealPlanException {
        m.setAmtWater("3");
        assertEquals(3, m.getAmtWater());
        assertEquals(15, m.getEnergyCost()); // 3 * 5
    }

    @Test
    void setAmtWater_whenZero_updatesWaterAndEnergyCorrectly() throws MealPlanException {
        m.setAmtWater("0");
        assertEquals(0, m.getAmtWater());
        assertEquals(0, m.getEnergyCost());
    }

    @Test
    void setAmtWater_whenInvalid_throwsMealPlanException() {
        assertThrows(MealPlanException.class, () -> {
            m.setAmtWater("xyz");
        });
    }

    @Test
    void setAmtWater_whenNegative_throwsMealPlanException() {
        assertThrows(MealPlanException.class, () -> {
            m.setAmtWater("-1");
        });
    }



    //-----------


    @Test
    void setAmtWetFood_whenValid_updatesWetFoodAndEnergyCorrectly() throws MealPlanException {
        m.setAmtWetFood("4");
        assertEquals(4, m.getAmtWetFood());
        assertEquals(60, m.getEnergyCost()); // 4 * 15
    }

    @Test
    void setAmtWetFood_whenZero_updatesWetFoodAndEnergyCorrectly() throws MealPlanException {
        m.setAmtWetFood("0");
        assertEquals(0, m.getAmtWetFood());
        assertEquals(0, m.getEnergyCost());
    }

    @Test
    void setAmtWetFood_whenInvalid_throwsMealPlanException() {
        assertThrows(MealPlanException.class, () -> {
            m.setAmtWetFood("food");
        });
    }

    @Test
    void setAmtWetFood_whenNegative_throwsMealPlanException() {
        assertThrows(MealPlanException.class, () -> {
            m.setAmtWetFood("-3");
        });
    }





}