package com.kh.mini.controller;

import com.kh.mini.dao.OrderDAO;
import com.kh.mini.vo.OrderList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/list")
    public String orderSelect(Model model) {
        model.addAttribute("order", new OrderList());
        return "thymeleaf/insertOrder";
    }
    @PostMapping("/list")
    public String orderInsert(@ModelAttribute("orderList") OrderList orderList) {
        OrderDAO dao = new OrderDAO();
        dao.listOrder(orderList.getUserId());
        return "thymeleaf/selectOrder";
    }
}
