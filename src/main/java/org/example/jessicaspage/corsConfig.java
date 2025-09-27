package org.example.jessicaspage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsConfig implements WebMvcConfigurer {
    @Value("${NETLIFY_URL}")
    private String netlififyUrl;
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") // Tillåt CORS för alla dina API-vägar
                .allowedOrigins(netlififyUrl) // ENDAST tillåt anrop från din Netlify-domän
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Tillåt dessa HTTP-metoder
                .allowedHeaders("*") // Tillåt alla headers
                .allowCredentials(true); // Viktigt om du använder cookies eller sessions
    }

}
