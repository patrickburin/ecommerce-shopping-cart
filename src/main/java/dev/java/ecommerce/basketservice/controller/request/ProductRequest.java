package dev.java.ecommerce.basketservice.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product item informed in the basket payload")
public record ProductRequest(
    @Schema(description = "Product ID in the external API", example = "1")
    Long id,
    @Schema(description = "Requested quantity for the product", example = "2")
    Integer quantity) {
}
