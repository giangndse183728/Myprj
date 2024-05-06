/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.sp24.t2s1.weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.sp24.t2s1.util.DBUtil;

/**
 *
 * @author nguye
 */
public class Weapon {
    private static final String PRODUCT_QUANTITY = "SELECT quantity FROM [dbo].[product] WHERE productID like ?";
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String img;

    public Weapon() {
    }

    public Weapon(String id, String name, double price, int quantity, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
    }

    public String getImg() {
        return img;
    }
   
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setImg(String img) {
        this.img = img;
    }

    
    public int getProductQuantity(String wpid) throws SQLException {
        int quantitydtb = 0;
         Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
         try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRODUCT_QUANTITY);
                ptm.setString(1, wpid);
          
                rs = ptm.executeQuery();
                
             if (rs.next()) {  // Check if there is a result
                quantitydtb = rs.getInt("quantity");
            }
                
            }
            
         }
         catch (Exception e ){
             
         }
         
          finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quantitydtb;
    }   
}
