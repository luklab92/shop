package com.spring.exercises.shop.repository;

import com.spring.exercises.shop.model.ProductDto;
import com.spring.exercises.shop.model.UserDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductRepository {
    private List<ProductDto> productList;

    public ProductRepository() {
        this.productList = new ArrayList<>();
    }

    public void addProductToList(ProductDto productDto) {
        ProductDto product = new ProductDto();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        productList.add(productDto);
    }

    public List<ProductDto> findAllProducts(){
        return productList;
    }

    @PostMapping("/deleteproduct")
    public String deleteProduct(@ModelAttribute("product") ProductDto productDto) {
        productList.remove(productDto);
        return "productdeleteresult";
    }
}

