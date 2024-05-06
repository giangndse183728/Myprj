/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.sp24.t2s1.weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.sp24.t2s1.util.DBUtil;

/**
 *
 * @author nguye
 */
public class WeaponDAO {
    private static final String LIST_WEAPON = "SELECT productID, fullName, price, quantity, img FROM product";
    private static final String INSERT = "INSERT INTO product(productID, fullName, price, quantity, img) VALUES (?, ?, ?, ?, ?)";
    
    public List<Weapon> getListWeapon() throws SQLException {
        List<Weapon> listWeapon = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_WEAPON);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("productID");
                    String fullName = rs.getString("fullName");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String img = rs.getString("img");
                    listWeapon.add(new Weapon(userID, fullName, price, quantity, img));
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
        return listWeapon;
    }

    public boolean insert(Weapon dto) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, dto.getId());
                ptm.setString(2, dto.getName());
                ptm.setDouble(3, dto.getPrice());
                ptm.setInt(4, dto.getQuantity());
                ptm.setString(5, dto.getImg());
                check = ptm.executeUpdate() > 0;
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

}
