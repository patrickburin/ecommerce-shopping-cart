package dev.java.ecommerce.basketservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.exceptions.CustomErrorDecoder;

@FeignClient(name = "platziStoreClient", url = "${basket.client.platzi}", configuration = { CustomErrorDecoder.class })
public interface PlatziStoreClient {

  @GetMapping("/products")
  List<PlatziProductResponse> getAllProducts();

  @GetMapping("/products/{id}")
  PlatziProductResponse getProductById(@PathVariable Long id);

}
