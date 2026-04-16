package dev.java.ecommerce.basketservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Operations for browsing products from the external API")
public class ProductController {

  private final ProductService productService;

  @GetMapping
  @Operation(summary = "List products", description = "Fetches all products available from the external Platzi API.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Product list returned successfully",
          content = @Content(array = @ArraySchema(schema = @Schema(implementation = PlatziProductResponse.class))))
  })
  public ResponseEntity<List<PlatziProductResponse>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get product by ID", description = "Fetches a specific product from the external Platzi API.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Product found",
          content = @Content(schema = @Schema(implementation = PlatziProductResponse.class)))
  })
  public ResponseEntity<PlatziProductResponse> getProductById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

}
