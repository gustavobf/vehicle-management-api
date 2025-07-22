package br.com.batista;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.security.servlet.*;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class VehicleManagementApi {

    public static void main (String[] args) {
        SpringApplication.run(VehicleManagementApi.class, args);
    }

}
