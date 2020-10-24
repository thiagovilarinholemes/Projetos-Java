package academy.digitallab.store.product.controller;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.service.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products") // Sempre no plural
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    // -------------------Retrieve All Products --------------------------------------------

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products = new ArrayList<>();

        if( null == categoryId){ // Caso não seja passado o id da categoria
            products = productService.listAllProduct();
            if(products.isEmpty()){ // Caso não exista produtos cadastrados
                return ResponseEntity.noContent().build();
            }
        }else{ // Caso seja passado o id da categoria
            products = productService.findByCategory(Category.builder().id(categoryId).build()); // Pesquisa os produtos do id da categoria passada
            if(products.isEmpty()){ // Caso não exista produtos na categoria passada
                ResponseEntity.noContent().build();
            }

        }
        return ResponseEntity.ok(products);
    }

    // -------------------Retrieve Single Product ------------------------------------------

    @GetMapping(value = "/{id}" )
    ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if(null == product){ // Caso não exista o produto pesquisado
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(product);
    }

    // -------------------Create a Product -------------------------------------------

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product productCreate =  productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    // ------------------- Update a Product ------------------------------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);
        Product productDB = productService.updateProduct(product);
        if(productDB == null){
            return ResponseEntity.notFound().build(); // Retorna não encontrado
        }

        return ResponseEntity.ok(productDB);
    }

    // ------------------- Delete a Product -----------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDelete = productService.deleteProduct(id);
        if(productDelete == null){
            return ResponseEntity.notFound().build(); // Retorna não encontrado
        }
        return ResponseEntity.ok(productDelete);
    }

    // ------------------- Update a Product Stock -----------------------------------------

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id,@RequestParam(name = "quantity", required = true) Double quantity){
        Product product = productService.updateStock(id, quantity);
        if(product == null){
            return ResponseEntity.notFound().build(); // Retorna não encontrado
        }
        return ResponseEntity.ok(product);
    }

    // ------------------- Message Error -----------------------------------------

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream() // Recebe todos os campos que estão gerando erros, cria um fluxo de mensagens
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage()); // Campo que está gerando o erro e a mensagem de erro
                    return error;

                }).collect(Collectors.toList()); // Com isso será gerado uma lista

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper(); // Converte em JSON
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
