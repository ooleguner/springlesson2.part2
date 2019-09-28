package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lesson6.controller.ItemController;
import com.lesson6.helpers.ItemMapper;
import com.lesson6.repository.ItemDao;
import com.lesson6.service.ItemService;
import com.lesson6_1.controller.FlightController;
import com.lesson6_1.controller.PassengerController;
import com.lesson6_1.controller.PlaneController;
import com.lesson6_1.helpers.GeneralMapper;
import com.lesson6_1.model.Flight;
import com.lesson6_1.model.Passenger;
import com.lesson6_1.model.Plane;
import com.lesson6_1.repository.*;
import com.lesson6_1.service.FlightService;
import com.lesson6_1.service.PassengerService;
import com.lesson6_1.service.PlaneService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@gromcode-lessons.cmbqecodcoqo.us-east-2.rds.amazonaws.com:1521:ORCL");
        dataSource.setUsername("main");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean en = new LocalContainerEntityManagerFactoryBean();
        en.setDataSource(dataSource());
        en.setPackagesToScan(new String[]{"com.lesson6"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        en.setJpaVendorAdapter(vendorAdapter);
        return en;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public GeneralMapper generalMapper() {
        return new GeneralMapper();
    }

    @Bean
    public PlaneController planeController() {
        return new PlaneController(generalMapper(), planeService());
    }

    @Bean
    public PlaneService planeService() {
        return new PlaneService(planeRepository(), filterHelper());
    }

    @Bean
    public RepositoryInterface<Plane> planeRepository() {
        return new PlaneRepository();
    }

    @Bean
    public FilterHelper filterHelper() {
        return new FilterHelper();
    }

    @Bean
    public Passenger passenger() {
        return new Passenger();
    }

    @Bean
    public PassengerController passengerController() {
        return new PassengerController(generalMapper(), passengerService());
    }

    @Bean
    public PassengerService passengerService() {
        return new PassengerService(passengerRepository());
    }

    @Bean
    public RepositoryInterface<Passenger> passengerRepository() {
        return new PassengerRepository();
    }

    @Bean
    public Flight flight() {
        return new Flight();
    }

    @Bean
    public FlightController flightController() {
        return new FlightController(generalMapper(), flightService());
    }

    @Bean
    public FlightService flightService() {
        return new FlightService(flightRepository(), passengerRepository(), filterHelper());
    }

    @Bean
    public RepositoryInterface<Flight> flightRepository() {
        return new FlightRepository();
    }

     /*
        @Bean
        public FlightFilter oneDayFilter(String[] param) {
            return new OneDayFilter(param);
        }

        @Bean
        public FlightFilter cityFromFilter(String[] p) {
            return new CityFromFilter(p);
        }

        @Bean
        public FlightFilter cityToFilter(String[] param){
            return new CityToFilter(param);
        }

        @Bean
        public FlightFilter modelPlaneFilter(String[] param){
            return new ModelPlaneFilter(param);
        }

        @Bean
        public FlightFilter datesFlightFilter(String[] p) {
            return new DatesFlightFilter(p);
        }
        @Bean

        public Filter filter(FlightFilter oneDayFilter, FlightFilter datesFlightFilter , FlightFilter cityFromFilter, FlightFilter cityToFilter, FlightFilter modelPlaneFilter) {
            return new Filter(oneDayFilter,datesFlightFilter , cityFromFilter, cityToFilter,modelPlaneFilter);
        }
    */




    //_______________________LESSON6_________________________________


    @Bean
    public ItemMapper itemMapper() {
        return new ItemMapper();
    }

    @Bean
    public ItemController itemController() {
        return new ItemController(itemService(), itemMapper());
    }

    @Bean
    public ItemService itemService() {
        return new ItemService(dao());
    }

    @Bean
    public ItemDao dao() {
        return new ItemDao();
    }
}
