<%-- 
    Document   : shopping
    Created on : Feb 26, 2024, 8:42:51 AM
    Author     : nguye
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        
        <link rel="stylesheet" href="css/styleShopping.css" type="text/css">

    </head>
    <body>

        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                
               
                
                <img class="navbar-brand" src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/AvantGarde_logo.svg/1200px-AvantGarde_logo.svg.png" alt="alt" style="height:100px; width:120px" />
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link" aria-current="page" href="MainController?action=User">User</a></li>
                        <li class="nav-item"><a class="nav-link" href="MainController?action=Logout">Logout</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                     
                    <form action="MainController" method="POST" class="d-flex">
                        <button class="btn btn-outline-dark" type="submit" name="action" value="View">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.CART.cart.size()}</span>
                        </button>
                    </form>
                    
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-blackgray py-5">
            <div class="container">
                <div class="mySlides fade">
                 <img src="https://images.indianexpress.com/2023/03/heliot-emil-1200.jpg" alt="..." style="width: 100%; height: 500px ">
                </div>
                <div class="mySlides fade">
                 <img src="https://www.esquireme.com/public/images/2020/03/03/Balenciaga.jpg" alt="..." style="width: 100%; height: 500px ">
                </div>
                <div class="mySlides fade">
                 <img src="https://media-assets.grailed.com/prd/article/2979/58b94d4e110a4ff381e883a5385b4dbf?fit=crop&width=2400" alt="..." style="width: 100%; height: 500px ">
                </div>
                <br>

                <div style="text-align:center">
                    <span class="dot"></span> 
                    <span class="dot"></span> 
                    <span class="dot"></span> 
                </div>
                
            </div>
        </header>
        <!-- Section-->

               <section class=" bg-whitegray py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                   
                    
                    <c:forEach var="weapon" varStatus="counter" items="${requestScope.LIST_WEAPON}">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="${weapon.img}" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${weapon.name}</h5>
                                   
                                    
                                    <!-- Product price-->
                                    $${weapon.price}
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center">
                                    <form action="MainController" >
                                    <button class="btn btn-outline-dark mt-auto"  type="submit" name="action" value="Add" id="myBtn">
                                        <input type="hidden" name="weapon" value="${weapon.id}-${weapon.name}-${weapon.price}-${weapon.img}">
                                        <input type="hidden" name="quantity" value="1">
                                        Add to cart
                                    </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
                   
                   
                    
                   
                   
        </section>
        
          

        </div>  
      
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; DnilbThrift</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        
        <script>
let slideIndex = 0;
showSlides();

function showSlides() {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}    
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
  setTimeout(showSlides, 3000); // Change image every 3 seconds
}
        </script>
        
    </body>
</html>




