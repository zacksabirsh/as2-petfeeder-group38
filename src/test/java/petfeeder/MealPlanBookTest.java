package petfeeder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.MealPlanException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MealPlanBookTest {

    private MealPlanBook book;

    /**
     * Initialize a fresh MealPlanBook instance before each test.
     */
    @BeforeEach
    void setUp() throws Exception {
        book = new MealPlanBook();
    }

    @Test
    void addMealPlan_whenValidPlan_returnsTrue(){
        MealPlan m = new MealPlan();
        assertTrue(book.addMealPlan(m));
    }

    @Test
    void addMealPlan_whenAddingTwoValidPlans_returnsTrue(){
        MealPlan m1 = new MealPlan();
        m1.setName("m1");
        MealPlan m2 = new MealPlan();
        m1.setName("m2");


        assertTrue(book.addMealPlan(m1));
        assertTrue(book.addMealPlan(m2));
    }


    @Test
    void addMealPlan_whenAddedTwice_doesNotAddAgain() {
        MealPlan m = new MealPlan();

        book.addMealPlan(m);
        book.addMealPlan(m);

        MealPlan[] plans = book.getMealPlans();

        int count = 0;
        for (MealPlan plan : plans) {
            if (plan != null) {
                count++;
            }
        }

        assertEquals(1, count);
    }

    @Test
    void addMealPlan_whenBookFull_returnsFalse() {
        MealPlan m0 = new MealPlan();
        MealPlan m1 = new MealPlan();
        MealPlan m2 = new MealPlan();
        MealPlan m3 = new MealPlan();
        MealPlan m4 = new MealPlan();

        m0.setName("m0");
        m1.setName("m1");
        m2.setName("m2");
        m3.setName("m3");
        m4.setName("m4");

        assertTrue(book.addMealPlan(m0));
        assertTrue(book.addMealPlan(m1));
        assertTrue(book.addMealPlan(m2));
        assertTrue(book.addMealPlan(m3));
        assertFalse(book.addMealPlan(m4));

    }


    @Test
    void deleteMealPlan_whenValidPlan_returnsName(){
        MealPlan m = new MealPlan();
        m.setName("test");
        book.addMealPlan(m);
        assertEquals("test", book.deleteMealPlan(0));
    }


    @Test
    void deleteMealPlan_whenValidPlan_removesFromList(){
        MealPlan m = new MealPlan();
        m.setName("test");
        book.addMealPlan(m);
        book.deleteMealPlan(0);


        MealPlan[] plans = book.getMealPlans();

        int count = 0;
        for (MealPlan plan : plans) {
            if (plan != null) {
                count++;
            }
        }

        assertEquals(0, count);
    }


    @Test
    void deleteMealPlan_whenEmptyBook_returnsNull(){
        assertNull(book.deleteMealPlan(0));
    }

    @Test
    void deleteMealPlan_whenNegativeIndex_throwsArrayIndexOutOfBoundsException(){
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            book.deleteMealPlan(-2);
        });
    }

    @Test
    void deleteMealPlan_whenLargeIndex_throwsArrayIndexOutOfBoundsException(){
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->{
            book.deleteMealPlan(5);
        });
    }




}
