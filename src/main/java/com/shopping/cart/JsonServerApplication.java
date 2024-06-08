package com.shopping.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The JsonServerApplication class is the entry point for the JSON Server application.
 */
@SpringBootApplication
public class JsonServerApplication {

    /**
     * The main method is the entry point for the JSON Server application. It starts the server.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(JsonServerApplication.class, args);
    }
}
