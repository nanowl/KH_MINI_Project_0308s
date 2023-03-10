

package com.kh.mini.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartList {
    private String userId;
    private String productName;
    private int quantity;
    private int price;
    public CartList(String userId, String productName, int quantity,int price) {
        this.userId = userId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}