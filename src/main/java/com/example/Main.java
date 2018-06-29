package com.example;

import com.example.config.JdbcConfig;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        List<Customer> customers = customerRepository.findByCustomerName("%o%");
        for (Customer customer : customers) {
            System.out.println(customer.getId() + ":" + customer.getName());
        }
    }
}
