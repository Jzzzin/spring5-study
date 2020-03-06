package ch9.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableJpaRepositories
public class XAJpaConfig {

    private static Logger logger = LoggerFactory.getLogger(XAJpaConfig.class);

    @SuppressWarnings("unchecked")
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceA() {
        try {
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSA");
            dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
            dataSource.setXaProperties(xaAProperties());
            dataSource.setPoolSize(1);
            return dataSource;
        } catch (Exception e) {
            logger.error("Populator DataSource 빈을 생성할 수 없습니다!", e);
            return null;
        }
    }

    @Bean
    public Properties xaAProperties() {
        Properties xaProp = new Properties();
//        xaProp.put("databaseName", "musicdb_a");
        xaProp.put("user", "prospring5_A");
        xaProp.put("password", "prospring5_A");
        xaProp.put("url", "jdbc:mysql://localhost:3306/musicdb_a?useSSL=false"); // useSSL 사용하기 위하여 url 세팅
        return xaProp;
    }

    @SuppressWarnings("unchecked")
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceB() {
        try {
            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSB");
            dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
            dataSource.setXaProperties(xaBProperties());
            dataSource.setPoolSize(1);
            return dataSource;
        } catch (Exception e) {
            logger.error("Populator DataSource 빈을 생성할 수 없습니다!", e);
            return null;
        }
    }

    @Bean
    public Properties xaBProperties() {
        Properties xaProp = new Properties();
//        xaProp.put("databaseName", "musicdb_b");
        xaProp.put("user", "prospring5_B");
        xaProp.put("password", "prospring5_B");
        xaProp.put("url", "jdbc:mysql://localhost:3306/musicdb_b?useSSL=false"); // useSSL 사용하기 위하여 url 세팅
        return xaProp;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
        hibernateProp.put(JTA_PLATFORM, "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
        // 하이버네이트 5에서 필요함
        hibernateProp.put(TRANSACTION_COORDINATOR_STRATEGY, "jta");
        hibernateProp.put(CURRENT_SESSION_CONTEXT_CLASS, "jta");

        hibernateProp.put(AUTOCOMMIT, false);
        hibernateProp.put(FLUSH_BEFORE_COMPLETION, false);

//        hibernateProp.put(DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        hibernateProp.put(DIALECT, "org.hibernate.dialect.MySQL8Dialect"); // MySQL8Dialect 로 변경해야 제대로 롤백됨
        // users/schemas가 먼저 생성돼 있을 경우에만 동작하며, 이를 위해 use ddl.sql을 사용함
        hibernateProp.put(HBM2DDL_AUTO, "create-drop");
        hibernateProp.put(SHOW_SQL, true);
        hibernateProp.put(MAX_FETCH_DEPTH, 3);
        hibernateProp.put(STATEMENT_BATCH_SIZE, 10);
        hibernateProp.put(STATEMENT_FETCH_SIZE, 50);
        return hibernateProp;
    }

    @Bean
    public EntityManagerFactory emfA() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ch9.ch16");
        factoryBean.setDataSource(dataSourceA());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName("emfA");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public EntityManagerFactory emfB() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ch9.ch16");
        factoryBean.setDataSource(dataSourceB());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName("emfB");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}
