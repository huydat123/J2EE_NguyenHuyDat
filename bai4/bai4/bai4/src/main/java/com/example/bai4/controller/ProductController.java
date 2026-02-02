package com.example.bai4.controller;

import com.example.bai4.model.Category;
import com.example.bai4.model.Product;
import com.example.bai4.service.ProductService;
import com.example.bai4.repository.CategoryRepository; // Dùng tạm để init dữ liệu
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "books";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productService.getAllCategories());
        return "add-book";
    }

    // Xử lý thêm mới
    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult result,
                             @RequestParam("imageProduct") MultipartFile imageFile,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", productService.getAllCategories());
            return "add-book";
        }

        if (!imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                Path dir = Paths.get("src/main/resources/static/images");
                if (!Files.exists(dir)) {
                    Files.createDirectories(dir);
                }
                Path path = Paths.get(dir.toString(), fileName);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                product.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }


    // Form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", productService.getAllCategories());
        return "edit-book"; // Trả về file edit-book.html
    }

    // Xử lý sửa
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                @RequestParam("imageProduct") MultipartFile imageFile,
                                Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            model.addAttribute("categories", productService.getAllCategories());
            return "edit-book";
        }

        // Logic update ảnh (giữ ảnh cũ nếu không upload mới)
        if (!imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                product.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Giữ lại ảnh cũ (Cần truy vấn lại DB để lấy ảnh cũ nếu model không truyền về)
            Product oldProduct = productService.getProductById(id).orElse(null);
            if(oldProduct != null) product.setImage(oldProduct.getImage());
        }

        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    // Dữ liệu mẫu (Khởi tạo 2 danh mục điện thoại)
    @Bean
    public CommandLineRunner initData(CategoryRepository categoryRepo) {
        return args -> {
            if (categoryRepo.count() == 0) {
                Category c1 = new Category(); c1.setName("iPhone");
                Category c2 = new Category(); c2.setName("Samsung");
                categoryRepo.save(c1);
                categoryRepo.save(c2);
            }
        };
    }
}