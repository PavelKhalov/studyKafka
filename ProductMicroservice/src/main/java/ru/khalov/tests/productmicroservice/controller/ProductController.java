package ru.khalov.tests.productmicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khalov.tests.productmicroservice.service.dto.CreateProductDto;
import ru.khalov.tests.productmicroservice.service.ProductServiceImpl;
import ru.khalov.tests.productmicroservice.util.ExceptionMessage;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDto dto){

        try {
            String prodId = productService.createProduct(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(prodId);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionMessage(LocalDateTime.now(), "send to kafka error"));
        }

    }
}
