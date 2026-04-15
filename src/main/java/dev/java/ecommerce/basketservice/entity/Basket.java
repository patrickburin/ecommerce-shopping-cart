package dev.java.ecommerce.basketservice.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Basket {

  @Id
  private String id;

  private Long client;

  private BigDecimal totalPrice;

  private List<Product> products;

  private Status status;

  public void calculateTotalPrice() {
    this.totalPrice = products.stream()
        .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
