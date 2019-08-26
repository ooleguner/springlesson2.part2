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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

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

}
