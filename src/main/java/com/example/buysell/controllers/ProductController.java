package com.example.buysell.controllers;

import com.example.buysell.dto.ProductDTO;
import com.example.buysell.entity.Product;
import com.example.buysell.entity.User;
import com.example.buysell.services.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;


    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        List<ProductDTO> productDTOList = productService.getListProducts(title);
        User userByPrincipal = productService.getUserByPrincipal(principal);

        model.addAttribute("products", productDTOList);
        model.addAttribute("user", userByPrincipal);
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = (Product) productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }
    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                ProductDTO productDTO, Principal principal) throws IOException {
        productService.saveProduct(principal, productDTO, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
