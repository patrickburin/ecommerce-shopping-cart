package dev.java.ecommerce.basketservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI basketServiceOpenApi() {
    return new OpenAPI()
        .info(new Info()
            .title("Basket Service API")
            .description("API for browsing products, creating baskets, and completing basket payments.")
            .version("v1")
            .contact(new Contact().name("Portfolio Project")));
  }

}
