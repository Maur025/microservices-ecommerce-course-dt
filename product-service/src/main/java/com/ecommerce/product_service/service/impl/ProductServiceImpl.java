package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.ProductRequestDto;
import com.ecommerce.product_service.dto.ProductResponseDto;
import com.ecommerce.product_service.mapper.ProductMapper;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import com.ecommerce.product_service.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.toProduct(productRequestDto);

        Product savedProduct = repository.save(product);

        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> productList = repository.findAll();

        return productMapper.toResponseDtoList(productList);
    }

    @Override
    public ProductResponseDto getProductById(String id) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        return productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto updateProductById(String id, ProductRequestDto productRequestDto) {
        Product product = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productMapper.updateProductFromRequest(productRequestDto, product);

        Product updatedProduct = repository.save(product);

        return productMapper.toResponseDto(updatedProduct);
    }

    @Override
    public void deleteProductById(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        repository.deleteById(id);
    }
}
