/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.sp24.t2s1.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.sp24.t2s1.user.UserDAO;
import sample.sp24.t2s1.user.UserError;
import sample.sp24.t2s1.weapon.Weapon;
import sample.sp24.t2s1.weapon.WeaponDAO;

/**
 *
 * @author nguye
 */
@WebServlet(name = "InventoryController", urlPatterns = {"/InventoryController"})
public class InventoryController extends HttpServlet {
    
    private static final String ERROR = "inventory.jsp";
    private static final String SUCCESS = "inventory.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        
        boolean check = true;
        WeaponDAO dao = new WeaponDAO();
        try {
            
            String id = request.getParameter("productId");
            String name = request.getParameter("productName");
            String priceStr = request.getParameter("productPrice");
            double price = Double.parseDouble(priceStr);
            String quantityStr = request.getParameter("productQuantity");
            int quantity = Integer.parseInt(quantityStr);
            String img = request.getParameter("productImg");
            
            if (check) {
                Weapon dto = new Weapon(id, name, price, quantity, img);
                boolean checkInsert = dao.insert(dto);
                if (checkInsert) {
                    url = SUCCESS;
                }

 
        }
        
        
        }catch (Exception e ){
            
        }
        finally {
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
