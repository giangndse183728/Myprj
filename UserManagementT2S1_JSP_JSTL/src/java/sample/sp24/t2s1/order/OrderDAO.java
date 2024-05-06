/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.sp24.t2s1.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import sample.sp24.t2s1.user.UserDTO;
import sample.sp24.t2s1.util.DBUtil;
import sample.sp24.t2s1.weapon.Weapon;

/**
 *
 * @author nguye
 */
public class OrderDAO {
    private static final String INSERT = "INSERT INTO [dbo].[order](orderID, userID, orderDate, total) VALUES (?, ?, ?, ?)";
    private static final String INSERT_DETAIL = "INSERT INTO [dbo].[orderDetail](orderID, productID, price, quantity) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE [dbo].[product] SET quantity= quantity - ? WHERE productID like ?";
    private static final String LIST_ORDER = "SELECT orderID, userID, orderDate, total FROM [dbo].[order] WHERE orderID like ? ORDER BY orderDate DESC";
    private static final String ORDER_QUANTITY = "SELECT quantity FROM [dbo].[orderDetail] WHERE productID like ? AND orderID like ?";
    private static final String PRODUCT_QUANTITY = "SELECT quantity FROM [dbo].[product] WHERE productID like ?";
    
    
  
    public String generateId() {
        // Create a Random object
        Random random = new Random();

        int randomDigit = random.nextInt(1000);

        String formattedDigit = String.format("%04d", randomDigit);

        String generatedId = "S" + formattedDigit;

        return generatedId;
    }

    
        public boolean insert(OrderDTO dto) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, dto.getOrderID());
                ptm.setString(2, dto.getUserID());
                ptm.setTimestamp(3, dto.getOrderDate());
                ptm.setDouble(4, dto.getTotal());
                check = ptm.executeUpdate() > 0 ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return check;
        
        
        
    }
   
        
        
        
    public boolean insertDetail (OrderDTO order, Weapon weapon) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_DETAIL);
                ptm.setString(1, order.getOrderID());
                ptm.setString(2, weapon.getId());
                ptm.setDouble(3, weapon.getPrice());
                ptm.setInt(4, weapon.getQuantity());
                check = ptm.executeUpdate() > 0 ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return check;
    }
   
   public List<OrderDTO> getListOrder(String search) throws SQLException {
        List<OrderDTO> listOrder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_ORDER);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String userID = rs.getString("userID");
                    Timestamp orderDate = rs.getTimestamp("orderDate");
                    Double total = rs.getDouble("total");
                    listOrder.add(new OrderDTO(orderID, userID, orderDate, total));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return listOrder;
    }
   
       public boolean update(String id, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setInt(1, quantity);
                ptm.setString(2, id);
                
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
   
    public int getOrderDetailQuantity(String wpid, String orid) throws SQLException {
        int quantity = 0;
         Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
         try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ORDER_QUANTITY);
                ptm.setString(1, wpid);
                ptm.setString(2, orid);
                rs = ptm.executeQuery();
                
             if (rs.next()) {  // Check if there is a result
                quantity = rs.getInt("quantity");
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
        return quantity;
    }   
       
       
       
}
