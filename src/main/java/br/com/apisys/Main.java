package br.com.apisys;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> {
            System.out.println("Beans utilizados nesta instância Spring Boot:");

            String[] beansNames = applicationContext.getBeanDefinitionNames();

            Arrays.sort(beansNames);

            for (String beanName : beansNames) {
                System.out.println(beanName);
            }
        };
    }

//    @Bean
//    public AuditorAware<String> auditorAware() {
//        return new AuditorAwareImpl();
//    }


}
