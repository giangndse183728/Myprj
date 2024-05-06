/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.sp24.t2s1.order;

import java.sql.SQLXML;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nguye
 */
public class OrderDTO {
    private String orderID;
    private String userID;
    private Timestamp orderDate;
    private Double total;

    public OrderDTO() {
        
    }

    public OrderDTO(String orderID, String userID, Timestamp orderDate, Double total) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getUserID() {
        return userID;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }
    
      public String getFormatedOrderDate() {
        String formattedOrderDate = new SimpleDateFormat("hh:mm dd-MM-yyyy").format(orderDate);
        return formattedOrderDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
   
    
    
    
}
