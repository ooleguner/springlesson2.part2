package config;

import lesson1.com.Student;
import lesson1.com.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by oleg on 21.08.2019.
 */


@Configuration
@ComponentScan(basePackages = "lesson1.com")
public class SpringConf {

    @Bean
    public Student student(){
        return new Student();
    }



//    @Bean
//    public Test Test(){
//        return new Test();
//    }
//    @Bean
//    public Service service1() {
//      return new Service(1L,"NameService1",new ArrayList(Arrays.asList("Param1_1", "Param1_2")));
//    }
//    @Bean
//    public Service service2() {
//        return new Service(2L,"NameService2",new ArrayList(Arrays.asList("Param2_1", "Param2_2")));
//    }
//
//    @Bean
//    public Service service3() {
//        return new Service(2L,"NameService3",new ArrayList(Arrays.asList("Param3_1", "Param3_2")));
//    }
//
//    @Bean
//    public Step step1() {
//        return new Step(100L, service1(),service2());
//    }
//
//    @Bean
//    public Step step2() {
//        return new Step(101L, service2(),service2());
//    }
//
//    @Bean
//    public Route route(){
//        return new Route("1000", Arrays.asList(step1(), step2()));
//    }

}
