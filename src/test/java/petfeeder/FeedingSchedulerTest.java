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
        int numTimesCalled = 0;

        @Override
        public boolean dispenseMeal(int index) {
            called = true;
            numTimesCalled++;
            return true;
        }

        @Override
        public MealPlan[] getMealPlans() {
            MealPlan m = new MealPlan();
            m.setName("TestMeal");

            MealPlan[] plans = new MealPlan[1];
            plans[0] = m;
            return plans;
        }
    }

    private static class FakePetFeederFail extends PetFeeder {
        @Override
        public boolean dispenseMeal(int index) {
            return false;
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

        Thread.sleep(1010);

        assertEquals(1, pf.numTimesCalled);
    }

    @Test
    void scheduleRecurringFeeding_whenOneSecondPeriodRecurring_executesDispenseMeal() throws InterruptedException {

        fs = new FeedingScheduler(pf);

        fs.scheduleRecurringFeeding(0, 1);

        Thread.sleep(10100);

        assertEquals(10, pf.numTimesCalled);
    }


    @Test
    void scheduleRecurringFeeding_whenDispenseFails_coversFailureBranch() throws InterruptedException {
        FeedingScheduler scheduler = new FeedingScheduler(new FakePetFeederFail());

        scheduler.scheduleRecurringFeeding(0, 1);

        Thread.sleep(1100);

        scheduler.shutdown(); // cleanup
    }

    @Test
    void scheduleRecurringFeeding_whenRescheduled_cancelsPreviousTask() throws InterruptedException {
        fs.scheduleRecurringFeeding(0, 1);
        Thread.sleep(100);

        // Reschedule again
        fs.scheduleRecurringFeeding(0, 1);

        Thread.sleep(100);

        assertTrue(fs.hasActiveSchedule());
    }



    @Test
    void stop_whenCalled_cancelsSchedule() throws InterruptedException {
        fs.scheduleRecurringFeeding(0, 1);

        Thread.sleep(100);

        fs.stop();

        assertFalse(fs.hasActiveSchedule());
    }


    @Test
    void hasActiveSchedule_whenScheduled_returnsTrue() throws InterruptedException {
        fs.scheduleRecurringFeeding(0, 1);

        Thread.sleep(100);

        assertTrue(fs.hasActiveSchedule());
    }

    @Test
    void shutdown_whenCalled_doesNotThrow() {
        assertDoesNotThrow(() -> fs.shutdown());
    }

}
