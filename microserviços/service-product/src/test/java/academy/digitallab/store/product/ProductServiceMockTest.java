package academy.digitallab.store.product;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import academy.digitallab.store.product.service.ProductService;
import academy.digitallab.store.product.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock // Para trabalhar em memória volátil
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach // Indicar que deve ser executado antes do teste
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService =  new ProductServiceImpl(productRepository);
        Product computer =  Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);

    }

    // Método que valida a busca pelo produto computer
    // Nele faz uma busca pelo produto computer
    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
        // Foi encontrado um produto computer
        Assertions.assertThat(found.getName()).isEqualTo("computer");

    }

    // Verifica se a lógica de négocio está funcionando corretamente
    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L,Double.parseDouble("8"));

        // Verifica se com a soma do valor acima será igual a 13
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }
}
