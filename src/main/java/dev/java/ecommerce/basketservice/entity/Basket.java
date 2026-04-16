package dev.java.ecommerce.basketservice.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Represents a persisted shopping basket")
public class Basket {

  @Id
  @Schema(description = "Basket identifier", example = "68002c2d8e9f5c4c49530e0c")
  private String id;

  @Schema(description = "Client identifier", example = "123")
  private Long client;

  @Schema(description = "Calculated total price for the basket", example = "159.90")
  private BigDecimal totalPrice;

  @Schema(description = "Products included in the basket")
  private List<Product> products;

  @Schema(description = "Current basket status", example = "OPEN")
  private Status status;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(description = "Payment method recorded after the basket is paid", example = "PIX")
  private PaymentMethod paymentMethod;

  public void calculateTotalPrice() {
    this.totalPrice = products.stream()
        .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
