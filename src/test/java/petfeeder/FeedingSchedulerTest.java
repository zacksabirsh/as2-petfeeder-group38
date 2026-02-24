package petfeeder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petfeeder.exceptions.FoodStockException;

import static org.junit.jupiter.api.Assertions.*;


public class FeedingSchedulerTest {

    FeedingScheduler fs;
    FakePetFeeder pf;
    /*Before Each Test
    Initialize the instance of Feeding Scheduler
     */
    private static class FakePetFeeder extends PetFeeder {
        boolean called = false;

        @Override
        public boolean dispenseMeal(int index) {
            called = true;
            return true;
        }
    }

    @AfterEach
    void cleanup() {
        if (fs != null) {
            fs.shutdown();
        }
    }
    @BeforeEach
    public void setup() throws Exception{
        pf = new FakePetFeeder();
        fs = new FeedingScheduler(pf);
    }



    @Test
    void scheduleRecurringFeeding_whenOneSecondPeriod_executesDispenseMeal() throws InterruptedException {

        fs = new FeedingScheduler(pf);

        fs.scheduleRecurringFeeding(0, 1);

        Thread.sleep(1500);

        assertTrue(pf.called);
    }

    @Test
    void scheduleRecurringFeeding_whenNegativePeriod_throwsIllegalArgumentException() throws InterruptedException {

        fs = new FeedingScheduler(pf);

        assertThrows(IllegalArgumentException.class, ()->{
            fs.scheduleRecurringFeeding(0, -1);
        });


    }

    @Test
    void scheduleRecurringFeeding_whenInvalidMealPlanIndex_throwsIllegalArgumentException() {

        fs = new FeedingScheduler(pf);

        assertThrows(IllegalArgumentException.class, ()->{
            fs.scheduleRecurringFeeding(1, 1);
        });

    }

}
