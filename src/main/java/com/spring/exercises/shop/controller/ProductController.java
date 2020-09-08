package com.spring.exercises.shop.controller;

import com.spring.exercises.shop.model.ProductDto;
import com.spring.exercises.shop.model.UserDto;
import com.spring.exercises.shop.repository.ProductRepository;
import com.spring.exercises.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class ProductController{

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/addProduct")
    public ModelAndView addUser(Model model) {
        return new ModelAndView("productForm","productToInsert",new ProductDto());
    }

    @PostMapping("/addProduct")
    public String addUser(@ModelAttribute ProductDto productDto){
        productRepository.addProductToList(productDto);
        productDto.setAddDate(new Date());
        return "productSaveResult";
    }

    @GetMapping("/allproducts")
    public String showAllProducts(Model model) {
        List<ProductDto> listOfProducts = productRepository.findAllProducts();
        model.addAttribute("products", listOfProducts);
        return "showAllProducts";
    }

    @PostMapping("/deleteproduct")
    public String deleteProduct(@ModelAttribute("product") ProductDto productDto) {
        productRepository.deleteProduct(productDto);
        return "productdeleteresult";
    }

}

