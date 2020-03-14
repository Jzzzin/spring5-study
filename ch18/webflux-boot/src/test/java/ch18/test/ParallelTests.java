package ch18.test;

import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;

public class ParallelTests {
    @Test
    void executeTwoInParallel() {
        final Class<?>[] calsses = {
                IntegrationOneTest.class, IntegrationTwoTest.class
        };

        JUnitCore.runClasses(new ParallelComputer(true, true), calsses);
    }
}
