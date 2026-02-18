package petfeeder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FeedingSchedulerTest {

    FeedingScheduler fs;
    PetFeeder pf;
    /*Before Each Test
    Initialize the instance of Feeding Scheduler
     */
    @BeforeEach
    public void setup() throws Exception{
        pf = new PetFeeder();
        fs = new FeedingScheduler(pf);
    }


    /*The Meal Plan Index should be <0 and > Length of Meal Plan List */
    @Test
    public void mealPlanIndexOutOfBoundTest(){
        int index = 4;
        assertEquals(pf, fs);
        

    }
}
