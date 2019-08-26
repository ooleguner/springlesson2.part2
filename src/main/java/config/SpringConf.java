package config;

import lesson1.com.Student;
import lesson2_1.Route;
import lesson2_1.Service;
import lesson2_1.Step;
import lesson2_2.repository.ItemDao;
import lesson2_2.service.ItemService;
import lesson3.dao.FileDao;
import lesson3.dao.StorageDao;
import lesson3.service.FileService;
import lesson3.service.StorageService;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

@Configuration
//@EnableJpaRepositories("lesson3.dao")
//@ComponentScan(basePackages = "lesson1.com")
//@ComponentScan(basePackages = "lesson2_1")
@ComponentScan(basePackages = "lesson3")
public class SpringConf {
    /*
        lesson1
     */
    @Bean
    public Student student() {
        return new Student();
    }

    /*
        lesson2_1
     */
    @Bean
    public Service service1() {
        return new Service(1L, "NameService1", new ArrayList(Arrays.asList("Param1_1", "Param1_2")));
    }

    @Bean
    public Service service2() {
        return new Service(2L, "NameService2", new ArrayList(Arrays.asList("Param2_1", "Param2_2")));
    }

    @Bean
    public Service service3() {
        return new Service(2L, "NameService3", new ArrayList(Arrays.asList("Param3_1", "Param3_2")));
    }

    @Bean
    public Step step1() {
        return new Step(100L, service1(), service2());
    }

    @Bean
    public Step step2() {
        return new Step(101L, service2(), service2());
    }

    @Bean
    public Route route() {
        return new Route();
    }

    /*
        lesson2_2
     */
    @Bean
    public ItemService itemService() {
        return new ItemService();
    }

    @Bean
    public ItemDao itemDao() {
        return new ItemDao();
    }

    /*
            lesson3
    */
    @Bean
    public FileDao fileDao(){
        return new FileDao();
    }

    @Bean
    public StorageDao storageDao(){
        return new StorageDao();
    }

    @Bean
    public FileService fileService() {
        return new FileService(fileDao());
    }

    @Bean
    public StorageService storageService(){
        return new StorageService(storageDao());
    }
/*
    @Bean
    public DriverManagerDataSource conferenceDataSource () {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource((conferenceDataSource()));
        emFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        emFactory.setJpaProperties(jpaProperties);
        emFactory.setPackagesToScan(getClass().getPackage().getName());
        return emFactory;
    }
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
*/
}
