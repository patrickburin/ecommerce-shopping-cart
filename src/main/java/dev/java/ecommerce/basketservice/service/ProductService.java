package dev.java.ecommerce.basketservice.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dev.java.ecommerce.basketservice.client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final PlatziStoreClient platziStoreClient;

  @Cacheable(value = "products")
  public List<PlatziProductResponse> getAllProducts() {
    log.info("Getting all products");
    return platziStoreClient.getAllProducts();
  }

  @Cacheable(value = "product", key = "#id")
  public PlatziProductResponse getProductById(Long id) {
    log.info("Getting product with id: {}", id);
    return platziStoreClient.getProductById(id);
  }

}
