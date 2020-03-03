package ch13;

import ch13.config.DataConfig;
import ch13.config.ServiceConfig;
import ch13.config.SimpleTestConfig;
import ch13.entities.Singer;
import ch13.services.SingerService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = {SimpleTestConfig.class, ServiceConfig.class, DataConfig.class})
@DisplayName("Integration SingerService Test")
@ActiveProfiles("test")
public class SingerServiceTest {

    private static Logger logger = LoggerFactory.getLogger(SingerServiceTest.class);

    @Autowired
    SingerService singerService;

    @BeforeAll
    static void setUp() {
        logger.info("--> @BeforeAll - 이 클래스의 모든 테스트 메서드를 실행하기 전에 실행합니다.");
    }

    @AfterAll
    static void tearDown() {
        logger.info("--> @AfterAll - 이 클래스의 모든 테스트 메서드를 실행한 다음에 실행합니다.");
    }

    @BeforeEach
    void init() {
        logger.info("--> @BeforeEach - 이 클래스의 각 테스트 메서드 실행하기 전에 실행합니다.");
    }

    @AfterEach
    void dispoes() {
        logger.info("--> @AfterEach - 이 클래스의 각 테스트 메서드 실행한 다음에 실행합니다.");
    }

    @Test
    @DisplayName("should return all singers")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/clean-up.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    public void findAll() {
        List<Singer> result = singerService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("should return singer 'John Mayer'")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/clean-up.sql",
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    public void testFindByFirstNameAndLastName() throws Exception {
        Singer result = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertNotNull(result);
    }
}
