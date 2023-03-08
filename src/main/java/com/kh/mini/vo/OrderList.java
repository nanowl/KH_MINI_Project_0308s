package com.kh.mini.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {
    int no ;
    Date date;
    int pdtNo;
    String pdtName;
    String userId;
    String loc;
    int price;
}
