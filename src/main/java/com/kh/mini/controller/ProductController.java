package com.kh.mini.controller;

import com.kh.mini.dao.ProductDAO;
import com.kh.mini.vo.Products;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/list")
    public String productSelect(Model model) {
        ProductDAO dao = new ProductDAO();
        List<Products> product = dao.listProducts();
        model.addAttribute("product", product);
        return "thymeleaf/product";
    }

}
