package ch18.test;

import ch18.FluxGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTwoTest {

    private final Logger logger = LoggerFactory.getLogger(IntegrationTwoTest.class);

    @Autowired
    FluxGenerator fluxGenerator;

    @Test
    public void test1Two() {
        // Flux 를 block 하려면 먼저 collectList 해야함
        fluxGenerator.generate("a", "b", "c").collectList().block().forEach(logger::info);
    }

    @Test
    public void test2Two() {
        // Flux 를 block 하려면 먼저 collectList 해야함
        fluxGenerator.generate("aa", "bb", "cc").collectList().block().forEach(logger::info);
    }

}
