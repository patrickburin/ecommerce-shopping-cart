package dev.java.ecommerce.basketservice.client.response;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product returned by the external Platzi API")
public record PlatziProductResponse(
    @Schema(description = "Product ID", example = "1")
    Long id,
    @Schema(description = "Product title", example = "Classic Red Pullover Hoodie")
    String title,
    @Schema(description = "Product price", example = "79.95")
    BigDecimal price) implements Serializable {
}
