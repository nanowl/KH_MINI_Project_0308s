package com.kh.mini.controller;


import com.kh.mini.dao.CartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
    @RequestMapping("/insert")
    public class InsertController {

        @Autowired
        private CartDAO cartDAO;

        @GetMapping("/cart")
        public String showCartInsertForm() {
            return "thymeleaf/insertCart";
        }

        @PostMapping("/cart")
        public String insertCart(@RequestParam("userId") String userId,
                                 @RequestParam("productId") int productId,
                                 @RequestParam("quantity") int quantity) {
            cartDAO.insertList(userId, productId, quantity);
            return "redirect:/select/cart";
        }
    }

