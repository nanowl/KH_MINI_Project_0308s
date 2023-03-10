package com.kh.mini.dao;

import com.kh.mini.util.Common;
import com.kh.mini.vo.OrderList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
장바구니 표시 데이터 : 상품이름 , 고객아이디, 개수
    select products.product_name, customer.user_id, cart.cnt
    from products
 */
public class OrderDAO {
    List<OrderList> list = new ArrayList<>();
    Scanner sc = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    // ORDERLIST 테이블의 데이터를 모두 조회하는 메소드
    public List<OrderList> listOrder() {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT O.ORDER_NO, O.ORDER_DATE, P.PRODUCT_NAME, O.USER_ID, O.LOC, O.PRICE " +
                            "FROM ORDERLIST O JOIN PRODUCTS P " +
                                "ON O.PDT_NO = P.PRODUCT_ID " +
                            "ORDER BY O.ORDER_NO";
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                int no = rs.getInt("ORDER_NO");
                Date date = rs.getDate("ORDER_DATE");
                String pdtName = rs.getString("PRODUCT_NAME");
                String userId = rs.getString("USER_ID");
                String loc = rs.getString("LOC");
                int price = rs.getInt("PRICE");

                OrderList vo = new OrderList();
                vo.setNo(no);
                vo.setDate(date);
                vo.setPdtName(pdtName);
                vo.setUserId(userId);
                vo.setLoc(loc);
                vo.setPrice(price);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //위 메소드를 오버로딩한 메소드 id를 파라미터로 받아와서 id값과 일치하는 데이터만을 조회한다.
    public List<OrderList> listOrder(String id) {
        try {
            conn = Common.getConnection();
            // 기존의 외래키 상품번호 열을 이름으로 바꾸기 위해 부모테이블 PRODUCTS와 자식테이블 ORDERLIST를 조인
            String query = "SELECT O.ORDER_NO, O.ORDER_DATE, P.PRODUCT_NAME, O.USER_ID, O.LOC, O.PRICE " +
                            "FROM ORDERLIST O JOIN PRODUCTS P " +
                                    "ON O.PDT_NO = P.PRODUCT_ID " +
                            "WHERE USER_ID = ?" +
                            "ORDER BY O.ORDER_NO" ;
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int no = rs.getInt("ORDER_NO");
                Date date = rs.getDate("ORDER_DATE");
                String pdtName = rs.getString("PRODUCT_NAME");
                String userId = rs.getString("USER_ID");
                String loc = rs.getString("LOC");
                int price = rs.getInt("PRICE");

                OrderList vo = new OrderList();
                vo.setNo(no);
                vo.setDate(date);
                vo.setPdtName(pdtName);
                vo.setUserId(userId);
                vo.setLoc(loc);
                vo.setPrice(price);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public int getPdtNo(String pdtName) {
        int pdtNo = 0;
        String sql = "SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pdtName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pdtNo = rs.getInt("PRODUCT_ID");
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
        return pdtNo;
    }
    public int getPrice(String pdtName, int cnt) {
        int price = 0;
        String sql = "SELECT PRICE FROM PRODUCTS WHERE PRODUCT_NAME = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pdtName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                price = rs.getInt("PRICE");
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
        return price * cnt;
    }

    public void insertList(OrderList orderList) {
        String sql = "INSERT INTO ORDERLIST VALUES (SEQ_ORDERLIST.NEXTVAL, SYSDATE, ?, ?, ?, ?)";
        int pdtNo = getPdtNo(orderList.getPdtName());
        int price = getPrice(orderList.getPdtName(), orderList.getCnt());
        if (pdtNo == 0 || price == 0) {
            System.out.print("값이 0임");
            return;
        }
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pdtNo);
            pstmt.setString(2, orderList.getUserId());
            pstmt.setString(3, orderList.getLoc());
            pstmt.setInt(4, price);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

    }
}
