package ch18;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class SingerJobResults {

    private static Logger logger = LoggerFactory.getLogger(SingerJobResults.class);

    @Configuration
    static class SingerConfig {

        @Bean
        JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }

        @Bean(destroyMethod = "close")
        public BasicDataSource dataSource() {
            try {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://localhost:3306/xd?useSSL=false");
                dataSource.setUsername("xd");
                dataSource.setPassword("xd");
                return dataSource;
            } catch (Exception e) {
                logger.error("DBCP DataSource 빈을 생성할 수 없습니다!", e);
                return null;
            }
        }
    }

    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(SingerConfig.class);

        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM singer");
        for (Map<String, Object> result : results) {
            logger.info(result.toString());
        }

        ctx.close();
    }

}
