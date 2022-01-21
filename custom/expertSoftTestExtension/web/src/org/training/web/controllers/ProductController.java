package org.training.web.controllers;

import de.hybris.platform.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    public static final String PRODUCT = "product";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product")
    public String getProduct(@RequestParam String code, Model model) {
        ProductModel productModel = productService.getProductForCode(code);
        if (productModel != null){
            model.addAttribute(PRODUCT, productModel);
        } else {
            model.addAttribute(MESSAGE, ERROR);
        }
        return PRODUCT;
    }
}
