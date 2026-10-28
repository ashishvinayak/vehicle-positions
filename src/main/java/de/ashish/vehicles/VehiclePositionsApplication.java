package de.ashish.vehicles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VehiclePositionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclePositionsApplication.class, args);
    }

}
