package dev.java.ecommerce.basketservice.controller.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload used to create or update a basket")
public record BasketRequest(
    @Schema(description = "Identifier of the client who owns the basket", example = "123")
    Long clientId,
    @ArraySchema(schema = @Schema(implementation = ProductRequest.class),
        arraySchema = @Schema(description = "Products that will be included in the basket"))
    List<ProductRequest> products) {

}
