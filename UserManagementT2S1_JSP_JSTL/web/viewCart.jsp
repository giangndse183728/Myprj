<%-- 
    Document   : viewCart
    Created on : Feb 26, 2024, 8:48:48 AM
    Author     : nguye
--%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="sample.sp24.t2s1.weapon.Weapon"%>
<%@page import="sample.sp24.t2s1.weapon.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/styleViewCart.css" type="text/css"/>
    </head>
    

    <body>
        
        <c:if test ="${sessionScope.CART == null || sessionScope.CART.cart.size() < 0}" >
        <c:redirect url = "shopping.jsp"></c:redirect>
    </c:if>
                     
        <div class="card">
            <div class="row">
                <div class="col-md-8 cart">
                    <div class="title">
                        <div class="row">
                            <div class="col"><h4><b>Shopping Cart</b></h4></div>
                            <div class="col align-self-center text-right text-muted">${sessionScope.CART.cart.size()} items</div>
                        </div>
                    </div>

                    <c:set var="total" value="0" />
                    <c:forEach var="weapon" varStatus="counter" items="${sessionScope.CART.cart}">
                        <c:set var="total" value = "${weapon.value.price * weapon.value.quantity}"/>
                        <c:set var="totalAll" value="${total + totalAll}"/>

                        <c:set var="currentQuantity" value="${weapon.value.quantity}"/>
                        <c:set var="totalQuantity" value="${totalQuantity + currentQuantity}"/>
                        <form action ="MainController">



                            <div class="row border-top border-bottom">
                                <div class="row main align-items-center">
                                  
                                    <div class="col">
                                        
                                        <div class="row text-muted">Shoes</div>
                                        <div class="row">${weapon.value.name}</div>
                                    </div>
                                    <div class="col">
                                        <input type="number" name="quantity" value="${weapon.value.quantity}" min="1" max="${weapon.value.getProductQuantity(weapon.value.id)}"required="" style="width: 80px"/>
                                        <input type ="submit" name ="action" value="Edit" style="width: 50px"/> 
                                        <input type="hidden" name="id" value="${weapon.value.id}" />
                                    </div>
                                    <div class="col"> $${weapon.value.price * weapon.value.quantity } 
                                        <span class="close" >
                                            <a href="MainController?action=Remove&id=${weapon.value.id}">&#10005;</a>

                                        </span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:forEach>

                    <div class="back-to-shop"><a href="MainController?action=Shopping_Page">&leftarrow;<span class="text-muted">Back to shop</span> </a> </div>
                </div>
                <div class="col-md-4 summary">
                    <div><h5><b>Summary</b></h5></div>
                    <hr>
                    <div class="row">
                        <div class="col" style="padding-left:0;">Total quantity: ${totalQuantity}</div>
                        <div class="col text-right">${totalAll}</div>
                    </div>

                    <div class="coupon">
                        <form>

                            <p>COUPON</p>
                            <input id="code" placeholder="Enter your coupond">
                        </form>
                    </div>
                    <div class="card-body">
                        <p><strong>Payment</strong></p>
                        <img class="me-2" width="45px"
                             src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
                             alt="Visa" />
                        <img class="me-2" width="45px"
                             src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
                             alt="American Express" />
                        <img class="me-2" width="45px"
                             src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
                             alt="Mastercard" />
                    </div>
                    <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                        <div class="col">TOTAL PRICE</div>
                        <div class="col text-right">${totalAll}</div>
                    </div>
                    <form action="MainController" method="POST">
                        <button class="btn btn-success" type="submit" name="action" value="Checkout">
                            CHECKOUT
                        </button>
                    </form>
                </div>
            </div>

        </div>
    </body>
</html>
