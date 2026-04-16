package dev.java.ecommerce.basketservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.java.ecommerce.basketservice.controller.request.ProductRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.entity.Product;
import dev.java.ecommerce.basketservice.entity.Status;
import dev.java.ecommerce.basketservice.exceptions.BusinessException;
import dev.java.ecommerce.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasketService {

  private final BasketRepository basketRepository;
  private final ProductService productService;

  public Basket getBasketById(String id) {
    return basketRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Basket not found"));
  }

  public Basket createBasket(BasketRequest basketRequest) {

    basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
        .ifPresent(basket -> {
          throw new BusinessException("There is already an open basket for this client");
        });

    Basket basket = Basket.builder()
        .client(basketRequest.clientId())
        .status(Status.OPEN)
        .products(buildProducts(basketRequest.products()))
        .build();

    basket.calculateTotalPrice();
    return basketRepository.save(basket);
  }

  public Basket updateBasket(String basketId, BasketRequest request) {
    Basket savedBasket = getBasketById(basketId);

    savedBasket.setProducts(buildProducts(request.products()));
    savedBasket.calculateTotalPrice();

    return basketRepository.save(savedBasket);
  }

  public Basket payBasket(String basketId, PaymentRequest request) {
    Basket savedBasket = getBasketById(basketId);

    savedBasket.setPaymentMethod(request.getPaymentMethod());
    savedBasket.setStatus(Status.SOLD);

    return basketRepository.save(savedBasket);
  }

  public void deleteBasket(String basketId) {

    basketRepository.delete(getBasketById(basketId));
  }

  private List<Product> buildProducts(List<ProductRequest> productRequests) {
    List<Product> products = new ArrayList<>();
    productRequests.forEach(productRequest -> products.add(buildProduct(productRequest)));
    return products;
  }

  private Product buildProduct(ProductRequest productRequest) {
    PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());

    return Product.builder()
        .id(platziProductResponse.id())
        .title(platziProductResponse.title())
        .price(platziProductResponse.price())
        .quantity(productRequest.quantity())
        .build();
  }

}
