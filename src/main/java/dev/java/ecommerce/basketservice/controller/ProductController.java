package dev.java.ecommerce.basketservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<PlatziProductResponse>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlatziProductResponse> getProductById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

}
