package com.kh.mini.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    String userId;
    String userPwd;
    String userName;
    String phone;
    String eMail;
    String address;

}
