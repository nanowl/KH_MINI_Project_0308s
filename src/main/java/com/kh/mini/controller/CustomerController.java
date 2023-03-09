package com.kh.mini.controller;

import com.kh.mini.dao.CustomerDAO;
import com.kh.mini.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")

public class CustomerController {
    @GetMapping("select")
    public String selectCustomer(Model model) {
        CustomerDAO dao = new CustomerDAO();
        List<Customer> customers = dao.CustomerSelect();
        model.addAttribute("member", customers);
        return "thymeleaf/selectCustomer";
    }


    @GetMapping("insert")
    public String insertCustomerForm(Model model) {
        model.addAttribute("member", new Customer());
        return "thymeleaf/insertCustomer";
    }

    @PostMapping("insert")
    public String saveCustomer(@ModelAttribute("member") Customer customer) {
        CustomerDAO dao = new CustomerDAO();
        dao.insertList(customer);
        return "thymeleaf/insertRst";
    }
}
