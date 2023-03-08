package com.kh.mini.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartList {
    private int PDT_NO_NUMBER;
    private String USER_ID_CART;
    int price;
    String PRODUCT_NAME;
    private int cnt;
}
