package dev.java.ecommerce.basketservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.service.BasketService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

  private final BasketService basketService;

  @GetMapping("/{id}")
  public ResponseEntity<Basket> getBasketById(@PathVariable String id) {
    return ResponseEntity.ok(basketService.getBasketById(id));

  }

  @PostMapping
  public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(request));
  }

}
