<%-- 
    Document   : login
    Created on : Jan 22, 2024, 8:36:08 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">


        <link rel="stylesheet" href="css/styleLogin.css" type="text/css"/>


        </head>


        <body>
            <div class="container">

                <div class="cover">
                    <div class="front">
                        <img src="https://dspncdn.com/a1/media/692x/dc/29/58/dc295897d7f19852e9cdcdff823f7980.jpg" alt="">
                        <div class="text">
                            <span class="text-1">New items are coming <br> be fashioned </span>
                            <span class="text-2">Let's get fit </span>
                        </div>
                    </div>

                </div>
                <div class="forms">
                    <div class="form-content">
                        <div class="login-form">
                            <div class="title">Login</div>
                            <form action="MainController" method="POST" id="form">
                                <div class="input-boxes">
                                    <div class="input-box">
                                        <i class="fas fa-envelope"></i>


                                        <input type="text" name="userID" placeholder="Enter your UserID" required="">
                                    </div>
                                    <div class="input-box">
                                        <i class="fas fa-lock"></i>
                                        <input type="password" name="password" placeholder="Enter your password" required="">
                                    </div>
                                    <div style="color: red"> ${requestScope.ERROR} </div> </br>
                                    
                                    
                                    <div class="g-recaptcha" data-sitekey="6Ld7HpMpAAAAAMKSCbr3W6BG-3eU-Jlg9k-NNuPd"></div>
                                    <div id="error"></div>
                                    
                                   

                                    <div class="button input-box">
                                        <input type="submit" name="action" value="Login">
                                    </div>

                                    <div class="button input-box">
                                        <input type="reset" value="Reset">
                                    </div>

                                </div>
                            </form>
                        </div>


                    </div>
                </div>
            </div>
                                    
                                    <script>
			window.onload = function (){
				let isValid = false;
				const form = document.getElementById("form");
				const error = document.getElementById("error");
				
				form.addEventListener("submit", function (event){
					
					const response = grecaptcha.getResponse();
					if (!response){
						event.preventDefault();
						error.innerHTML = "Please check Recatpcha";
					} 
					
				});
			}
		</script>



 <script src="https://www.google.com/recaptcha/api.js" async defer></script>

        </body>
    </html>