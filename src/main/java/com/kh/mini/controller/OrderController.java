package com.kh.mini.controller;

import com.kh.mini.dao.CustomerDAO;
import com.kh.mini.dao.OrderDAO;
import com.kh.mini.vo.OrderList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/add")
    public String orderInput(Model model) {
        model.addAttribute("order", new OrderList());
        return "thymeleaf/insertOrder";
    }
    @PostMapping("/add")
    public String orderInsert(@ModelAttribute("orderList") OrderList orderList, Model model) {
        OrderDAO dao = new OrderDAO();
        dao.insertList(orderList);
        return "redirect:/order/list";
    }

    @GetMapping("/list")
    public String orderSelect(Model model ) {
        OrderDAO dao = new OrderDAO();
        CustomerDAO isLogin = new CustomerDAO();
        List<OrderList> orderList = dao.listOrder();
        model.addAttribute("order", orderList);
        return "thymeleaf/selectOrder";

    }

    @PostMapping("/list")
    public String orderSelect(Model model,@RequestParam(value= "userId", required=false)  String id,
                              @RequestParam(value = "pwd", required = false) String pwd) {
        OrderDAO dao = new OrderDAO();
        CustomerDAO isLogin = new CustomerDAO();
        if (isLogin.login(id, pwd)) {
            List<OrderList> orderList = dao.listOrder(id);
            model.addAttribute("order", orderList);
            return "thymeleaf/selectOrder";
        } else {
            return "redirect:/main";
        }
    }
}
