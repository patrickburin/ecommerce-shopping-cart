package dev.java.ecommerce.basketservice.controller.request;

import dev.java.ecommerce.basketservice.entity.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Payload used to register a basket payment")
public class PaymentRequest {

  @Schema(description = "Payment method used to complete the basket", example = "PIX")
  private PaymentMethod paymentMethod;
}
