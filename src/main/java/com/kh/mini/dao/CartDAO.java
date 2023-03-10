package com.kh.mini.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.kh.mini.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mini.vo.CartList;

@Repository
public class CartDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public List<CartList> getCartList(String userId) {
        List<CartList> cartList = new ArrayList<>();

        try {
            conn = Common.getConnection();
            String query = "SELECT * FROM CART C JOIN PRODUCTS P ON C.PDT_NO_CART = P.PRODUCT_ID WHERE USER_ID_CART = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("PRODUCT_NAME");
                int quantity = rs.getInt("CNT");
                int price = rs.getInt("PRICE");
                CartList cart = new CartList(userId, productName, quantity, price);
                cartList.add(cart);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        }

        return cartList;
    }

    public void insertList(String userId, int productId, int quantity) {
        try {
            conn = Common.getConnection();
            String query = "INSERT INTO CART ( PDT_NO_CART, USER_ID_CART , CNT) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(2, userId);
            pstmt.setInt(1, productId);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteCartItem(String userId, int productId) {
        try {
            conn = Common.getConnection();
            String query = "DELETE FROM CART WHERE USER_ID_CART = ? AND PDT_NO_CART = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setInt(2, productId);

            pstmt.executeUpdate();

            Common.close(pstmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

