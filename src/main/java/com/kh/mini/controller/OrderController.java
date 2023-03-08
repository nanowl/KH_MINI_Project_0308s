package com.kh.mini.controller;

import com.kh.mini.dao.OrderDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/list")
    public String orderSelect(Model model) {
        OrderDAO dao = new OrderDAO();

        return "thymeleaf/";
    }
}
