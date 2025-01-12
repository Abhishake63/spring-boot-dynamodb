package com.abhishake.dynamodb.service;

import com.abhishake.dynamodb.model.Product;
import com.abhishake.dynamodb.model.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    DynamoDbEnhancedClient dynamoDbEnhancedClient;
    DynamoDbTable<Product> productTable;

    private static final TableSchema<Product> PRODUCT_TABLE_SCHEMA = TableSchema.fromClass(Product.class);

    public ProductServiceImpl(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        productTable = dynamoDbEnhancedClient.table("Product", PRODUCT_TABLE_SCHEMA);
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = productTable.getItem(Key.builder().partitionValue(id).build());
        return convertToDTO(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        PageIterable<Product> products = productTable.scan();
        return products.items().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDto createNewProduct(ProductDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        productTable.putItem(product);
        return convertToDTO(product);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto dto) {
        Product product = productTable.getItem(Key.builder().partitionValue(id).build());
        BeanUtils.copyProperties(dto, product);

        productTable.updateItem(product);
        return convertToDTO(product);
    }

    @Override
    public void deleteProduct(String id) {
        productTable.deleteItem(Key.builder().partitionValue(id).build());
    }

    private ProductDto convertToDTO(Product product) {
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }
}
