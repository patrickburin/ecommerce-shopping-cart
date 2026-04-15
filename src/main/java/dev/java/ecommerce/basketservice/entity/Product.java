package dev.java.ecommerce.basketservice.entity;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {

  private Long id;
  private String title;
  private BigDecimal price;
  private Integer quantity;

}
