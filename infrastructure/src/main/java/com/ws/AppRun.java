package com.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main entry point for the Spring Boot application.
 * <p>This class bootstraps the application using {@link SpringApplication}.</p>
 */
@SpringBootApplication
public class AppRun {

    /**Starts the Spring Boot application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }

}
