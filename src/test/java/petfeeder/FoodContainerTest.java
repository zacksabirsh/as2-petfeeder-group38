package petfeeder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoodContainerTest {
    private FoodContainer fc;
    @BeforeEach
    public void setup() throws Exception{
        fc = new FoodContainer();
    }



    /* Test for useIngredients Success*/
    @Test
    public void useIngredientsSuccessTest(){
        MealPlan m = new MealPlan();
        assertTrue(fc.useIngredients(m));
    }
    

    /* Test for useIngredients Failure*/
    @Test
    public void useIngredientsFailTest(){
        MealPlan m = new MealPlan();
        assertFalse(fc.useIngredients(m));
    }
    

    /* Test for Enough Ingredients to make a Meal */

    @Test
    public void enoughIngredientsTest() throws Exception{
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
    public void notEnoughIngredientsTest() throws Exception{
        MealPlan m = new MealPlan();
        //Setting Ingredients count
        m.setAmtKibble("0");
        m.setAmtTreats("0");
        m.setAmtWater("0");
        m.setAmtWetFood("0");
        assertFalse(fc.enoughIngredients(m));
    }

    /* Valid Number of TREATS */
    @Test
    public void AddValidTreatsAmount() throws Exception{
        String newAmount = "10";
        fc.addTreats(newAmount);
        assertEquals(25, fc.getTreats());
    }

    /* Valid Number of Kibble */
    @Test
    public void AddValidKibbleAmount() throws Exception{
        String newAmount = "10";
        fc.addKibble(newAmount);
        assertEquals(25, fc.getKibble());
    }
    

    /* Valid Number of Water */
    @Test
    public void AddValidWaterAmount() throws Exception{
        String newAmount = "10";
        fc.addTreats(newAmount);
        assertEquals(25, fc.getTreats());
    }
    

    /* Valid Number of Wet Food */
    @Test
    public void AddValidWetFoodAmount() throws Exception{
        String newAmount = "10";
        fc.addWetFood(newAmount);
        assertEquals(25, fc.getWetFood());
    }

    /* Testing for Null Value Exception for TREATS , KIBBLES, WATER & WET FOOD*/
    @Test
    public void AddInvalidTreatsAmount () {
        String NullAmount = null;
        assertThrows(IllegalArgumentException.class, ()->{
            fc.addTreats(NullAmount);
        });

    }
    @Test
    public void AddInvalidKibbleAmount (){
        String NullAmount = null;
        assertThrows(IllegalArgumentException.class, ()->{
            fc.addKibble(NullAmount);
        });

    }
    @Test
    public void AddInvalidWaterAmount (){
        String NullAmount = null;
        assertThrows(IllegalArgumentException.class, ()->{
            fc.addWater(NullAmount);
        });

    }
    @Test
    public void AddInvalidWetFoodAmount () {
        String NullAmount = null;
        assertThrows(IllegalArgumentException.class, ()->{
            fc.addWetFood(NullAmount);
        });

    }

    /* Testing for Negative Value Exception for TREATS , KIBBLES, WATER & WET FOOD*/
    @Test
    public void AddNegetiveTreatsAmount () {
        String negativeAmount = "-10";
        assertThrows(IllegalArgumentException.class, ()->{
            fc.addTreats(negativeAmount);
        });

    }
    @Test
    public void AddNegativeKibbleAmount (){
        String negativeAmount = "-10";
        assertThrows(IllegalArgumentException.class, ()->{
            fc.addKibble(negativeAmount);
        });

    }
    @Test
    public void AddNegetiveWaterAmount (){
        String negativeAmount = "-10";
        assertThrows(IllegalArgumentException.class, ()->{
            fc.addWater(negativeAmount);
        });

    }
    @Test
    public void AddNegetiveWetFoodAmount () {
        String negativeAmount = "-10";
        assertThrows(IllegalArgumentException.class, ()->{
            fc.addWetFood(negativeAmount);
        });

    }
    
    
}
