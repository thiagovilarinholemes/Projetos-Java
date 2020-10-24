package academy.digitallab.store.product.service;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null); // Busca um produto caso não encontre retorna null
    }

    @Override
    public Product createProduct(Product product) {

        product.setStatus("CREATED");
        product.setCreateAt(new Date());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        Product productDB = getProduct(product.getId()); // Verifica se o produto existe
        if (null == productDB){
            return null;
        }

        // Caso o produto exista atualiza
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id); // Verifica se o produto existe
        if (null == productDB){
            return null;
        }

        // Caso o produto exista deleta
        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id); // Verifica se o produto existe
        if (null == productDB){
            return null;
        }
        Double stock =  productDB.getStock() + quantity;
        productDB.setStock(stock);
        return productRepository.save(productDB);
    }


}
