/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.sp24.t2s1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;


import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.sp24.t2s1.order.OrderDAO;
import sample.sp24.t2s1.order.OrderDTO;
import sample.sp24.t2s1.user.UserDTO;
import sample.sp24.t2s1.weapon.Cart;
import sample.sp24.t2s1.weapon.Weapon;

/**
 *
 * @author nguye
 */


@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String SUCCESS = "checkout.jsp";
    private static final String ERROR = "checkout.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        OrderDAO order = new OrderDAO();
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("CART");
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

        try {
            String orderID = order.generateId();
            String userID = user.getUserID();
            

           // Create a java.sql.Date object using the current time
            Timestamp orderDate = new Timestamp(System.currentTimeMillis());
            
            double total = 0;
            for (Weapon weapon : cart.getCart().values()) {
                total += weapon.getQuantity() * weapon.getPrice();
                
            }
            
            OrderDTO dto = new OrderDTO(orderID, userID,orderDate, total);
            boolean checkInsert = order.insert(dto);
            
            boolean checkInserDetail = false;
            boolean checkQuantity = false;
            for (Weapon weapon : cart.getCart().values()) {
                checkInserDetail = order.insertDetail(dto, weapon);
                int orderQuantity = order.getOrderDetailQuantity(weapon.getId(), orderID);
                checkQuantity = order.update(weapon.getId(),orderQuantity);
            }
            

            if (checkInsert && checkInserDetail && checkQuantity) {
                url = SUCCESS;
                session.setAttribute("ORDER", order);
                request.setAttribute("MESSAGE", dto.getFormatedOrderDate() + " --- " + userID);
            }

        } catch (Exception e) {

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
