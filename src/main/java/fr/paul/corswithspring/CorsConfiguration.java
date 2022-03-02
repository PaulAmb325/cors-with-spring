package fr.paul.corswithspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:cors.properties")
public class CorsConfiguration {

    /*
    The origins are directly loaded from properties
    */
    @Value("${cors.allowedOriginsInConfig}")
    private String origins;

    /*
    This CORS Mapping automatically resolve cors for the path specified
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/conf-api/**")
                        .allowedOrigins(origins.split(" "))
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}
