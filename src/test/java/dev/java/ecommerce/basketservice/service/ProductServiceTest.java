package dev.java.ecommerce.basketservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.java.ecommerce.basketservice.client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private PlatziStoreClient platziStoreClient;

  @InjectMocks
  private ProductService productService;

  @Test
  void shouldReturnProductById() {
    var id = 1L;
    var expectedProduct = new PlatziProductResponse(id, "Notebook", BigDecimal.valueOf(199.99));

    when(platziStoreClient.getProductById(id)).thenReturn(expectedProduct);

    var product = productService.getProductById(id);

    assertThat(product).isEqualTo(expectedProduct);
    verify(platziStoreClient).getProductById(id);
  }
}
