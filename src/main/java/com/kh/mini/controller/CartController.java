package com.kh.mini.controller;

import com.kh.mini.dao.CartDAO;
import com.kh.mini.vo.CartList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class CartController {
    @Controller
    @RequestMapping( "/select")
    public class SelectController {
        @GetMapping("/cart")
        public String selectEmp(Model model) {
            CartDAO dao = new CartDAO();
            List<CartList> cart = dao.viewCart();
            model.addAttribute("cart", cart);
            return "thymeleaf/selectCart";
        }
    }
}
