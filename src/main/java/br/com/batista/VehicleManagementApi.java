package br.com.batista;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.security.servlet.*;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class VehicleManagementApi {

    private static final Logger logger = LoggerFactory.getLogger(VehicleManagementApi.class);

    public static void main (String[] args) {
        SpringApplication application = new SpringApplication(VehicleManagementApi.class);
        var environment = application.run(args).getEnvironment();

        logger.info("""
                Application '{}' is running!
                """, environment.getProperty("spring.application.name"));
    }

}
