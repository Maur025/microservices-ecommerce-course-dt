package com.ecommerce.product_service.dataloader;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String @NonNull ... args) throws Exception {
        Product product = Product.builder()
            .name("Samsung Galaxy S24")
            .description("Smartphone con IA")
            .price(BigDecimal.valueOf(1200))
            .build();

        productRepository.save(product);

        log.info("Datos de prueba cargados: " + product.getName());
    }
}
