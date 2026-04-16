package dev.java.ecommerce.basketservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@Tag(name = "Basket", description = "Operations for managing a customer's shopping basket")
public class BasketController {

  private final BasketService basketService;

  @GetMapping("/{id}")
  @Operation(summary = "Get basket by ID", description = "Returns the details of a basket stored in MongoDB.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Basket found",
          content = @Content(schema = @Schema(implementation = Basket.class))),
      @ApiResponse(responseCode = "404", description = "Basket not found",
          content = @Content(schema = @Schema(implementation = String.class)))
  })
  public ResponseEntity<Basket> getBasketById(@PathVariable String id) {
    return ResponseEntity.ok(basketService.getBasketById(id));

  }

  @PostMapping
  @Operation(summary = "Create basket", description = "Creates a new basket for a client using the provided list of products.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Basket created successfully",
          content = @Content(schema = @Schema(implementation = Basket.class))),
      @ApiResponse(responseCode = "400", description = "Client already has an open basket",
          content = @Content(schema = @Schema(implementation = String.class)))
  })
  public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(request));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update basket", description = "Replaces the products in an existing basket and recalculates the total price.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Basket updated",
          content = @Content(schema = @Schema(implementation = Basket.class))),
      @ApiResponse(responseCode = "404", description = "Basket not found",
          content = @Content(schema = @Schema(implementation = String.class)))
  })
  public ResponseEntity<Basket> updatedBasket(@PathVariable String id, @RequestBody BasketRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(basketService.updateBasket(id, request));
  }

  @PutMapping("/{id}/payment")
  @Operation(summary = "Pay basket",
      description = "Sets the payment method and changes the basket status to SOLD.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Payment registered successfully",
          content = @Content(schema = @Schema(implementation = Basket.class))),
      @ApiResponse(responseCode = "404", description = "Basket not found",
          content = @Content(schema = @Schema(implementation = String.class)))
  })
  public ResponseEntity<Basket> payBasket(@PathVariable String id, @RequestBody PaymentRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(basketService.payBasket(id, request));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete basket", description = "Removes an existing basket by its identifier.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Basket deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Basket not found",
          content = @Content(schema = @Schema(implementation = String.class)))
  })
  public ResponseEntity<Void> deleteBasket(@PathVariable String id) {
    basketService.deleteBasket(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
