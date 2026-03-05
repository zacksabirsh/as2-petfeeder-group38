package petfeeder;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.FoodStockException;
import petfeeder.exceptions.MealPlanException;

public class FoodContainerTest {
    private FoodContainer fc;
    @BeforeEach
    public void setup() throws Exception{
        fc = new FoodContainer();
    }

    /* Treats */
    @Test
    void addTreats_whenValidAmount_updatesCorrectly() throws Exception{
        String newAmount = "10";
        fc.addTreats(newAmount);
        assertEquals(25, fc.getTreats());
    }

    @Test
    void addTreats_whenZero_updatesCorrectly() throws Exception{
        String newAmount = "0";
        fc.addTreats(newAmount);
        assertEquals(15, fc.getTreats());
    }

    @Test
    void addTreats_whenInvalidAmount_throwsFoodStockException () {
        String NullAmount = null;
        assertThrows(FoodStockException.class, ()->{
            fc.addTreats(NullAmount);
        });

    }

    @Test
    void addTreats_whenNegativeAmount_throwsFoodStockException () {
        String negativeAmount = "-10";
        assertThrows(FoodStockException.class, ()->{
            fc.addTreats(negativeAmount);
        });

    }

    /* Kibble */
    @Test
    void addKibble_whenValidAmount_updatesCorrectly() throws Exception{
        String newAmount = "10";
        fc.addKibble(newAmount);
        assertEquals(25, fc.getKibble());
    }

    @Test
    void addKibble_whenZero_updatesCorrectly() throws FoodStockException {
        fc.addKibble("0");
        assertEquals(15, fc.getKibble());
    }

    @Test
    void addKibble_whenInvalidAmount_throwsFoodStockException (){
        String invalidAmount = "abc";
        assertThrows(FoodStockException.class, ()->{
            fc.addKibble(invalidAmount);
        });

    }

    @Test
    void addKibble_whenNullAmount_throwsFoodStockException (){
        String NullAmount = null;
        assertThrows(FoodStockException.class, ()->{
            fc.addKibble(NullAmount);
        });

    }

    @Test
    void addKibble_whenNegativeAmount_throwsFoodStockException (){
        String negativeAmount = "-10";
        assertThrows(FoodStockException.class, ()->{
            fc.addKibble(negativeAmount);
        });

    }


    /* Water */
    @Test
    void addWater_whenValidAmount_updatesCorrectly() throws Exception{
        String newAmount = "10";
        fc.addTreats(newAmount);
        assertEquals(25, fc.getTreats());
    }

    @Test
    void addWater_whenZero_updatesCorrectly() throws FoodStockException {
        fc.addWater("0");
        assertEquals(15, fc.getWater());
    }



    @Test
    void addWater_whenInvalidAmount_throwsFoodStockException (){
        String NullAmount = null;
        assertThrows(FoodStockException.class, ()->{
            fc.addWater(NullAmount);
        });

    }


    @Test
    void addWater_whenNegativeAmount_throwsFoodStockException (){
        String negativeAmount = "-10";
        assertThrows(FoodStockException.class, ()->{
            fc.addWater(negativeAmount);
        });

    }


    /* Wet Food */
    @Test
    void addWetFood_whenValidAmount_updatesCorrectly() throws Exception{
        String newAmount = "10";
        fc.addWetFood(newAmount);
        assertEquals(25, fc.getWetFood());
    }

    @Test
    void addWetFood_whenZero_updatesCorrectly() throws FoodStockException {
        fc.addWetFood("0");
        assertEquals(15, fc.getWetFood());
    }

    @Test
    void addWetFood_whenInvalidAmount_throwsFoodStockException () {
        String NullAmount = null;
        assertThrows(FoodStockException.class, ()->{
            fc.addWetFood(NullAmount);
        });

    }

    @Test
    void addWetFood_whenNegativeAmount_throwsFoodStockException () {
        String negativeAmount = "-10";
        assertThrows(FoodStockException.class, ()->{
            fc.addWetFood(negativeAmount);
        });

    }

    @Test
    void enoughIngredients_whenEnough_returnsTrue() throws Exception{
        MealPlan m = new MealPlan();
        //Setting Ingredients count
        m.setAmtKibble("0");
        m.setAmtTreats("0");
        m.setAmtWater("0");
        m.setAmtWetFood("0");
        assertTrue(fc.enoughIngredients(m));
    }




    /* Test for Not Enough Ingredients to make a Meal */
    @Test
    void enoughIngredients_whenNotEnough_returnsFalse() throws Exception{
        MealPlan m = new MealPlan();
        //Setting Ingredients count
        m.setAmtKibble("100");
        m.setAmtTreats("100");
        m.setAmtWater("100");
        m.setAmtWetFood("100");
        assertFalse(fc.enoughIngredients(m));
    }




    /* Test for useIngredients Success*/
    @Test
    void useIngredients_whenEnough_returnsTrue(){
        MealPlan m = new MealPlan();
        assertTrue(fc.useIngredients(m));
    }
    

    /* Test for useIngredients Failure*/
    @Test
    void useIngredients_whenNotEnough_returnsFalse() throws Exception{
        MealPlan m = new MealPlan();
        m.setAmtKibble("100");
        m.setAmtTreats("100");
        m.setAmtWater("100");
        m.setAmtWetFood("100");
        assertFalse(fc.useIngredients(m));
    }

    /* Test that using ingredients updates stock correctly*/
    @Test
    void useIngredients_whenUsing_updatesStockCorrectly() throws Exception{
        MealPlan m = new MealPlan();
        m.setAmtKibble("5");
        m.setAmtTreats("5");
        m.setAmtWater("5");
        m.setAmtWetFood("5");

        fc.useIngredients(m);

        assertEquals(10, fc.getKibble());
        assertEquals(10, fc.getWater());
        assertEquals(10, fc.getWetFood());
        assertEquals(10, fc.getTreats());
    }
    





    
    
}
