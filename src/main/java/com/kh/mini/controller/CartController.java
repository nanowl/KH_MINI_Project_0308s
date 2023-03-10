package com.kh.mini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.mini.dao.CartDAO;
import com.kh.mini.vo.CartList;

@Controller
@RequestMapping("/select")
public class CartController {

    @GetMapping("/cart")
    public String showCart() {
        return "thymeleaf/selectCart";
    }

    @PostMapping("/cart")
    public String getCartList(@RequestParam String userId, Model model) {
        CartDAO cartDAO = new CartDAO();
        List<CartList> cartList = cartDAO.getCartList(userId);
        model.addAttribute("cartList", cartList);
        return "thymeleaf/selectCart";
    }


    @Autowired
    private CartDAO cartDAO;


    @PostMapping("/deleteCart")
    public String deleteCartItem(@RequestParam("userId") String userId, @RequestParam("productId") int productId) {
        cartDAO.deleteCartItem(userId, productId);
        return "redirect:/select/cart?userId=" + userId;
    }
}


