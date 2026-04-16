package dev.java.ecommerce.basketservice.entity;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(description = "Product associated with a basket")
public class Product {

  @Schema(description = "Product ID in the external API", example = "1")
  private Long id;
  @Schema(description = "Product title", example = "Classic Red Pullover Hoodie")
  private String title;
  @Schema(description = "Unit price of the product", example = "79.95")
  private BigDecimal price;
  @Schema(description = "Quantity of the product in the basket", example = "2")
  private Integer quantity;

}
