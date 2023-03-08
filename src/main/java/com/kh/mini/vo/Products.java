package com.kh.mini.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private int productId;
    private String productName;
    private String color;
    private int price;
    private String madeIn;
}
