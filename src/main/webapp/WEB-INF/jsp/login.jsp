<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vimbika :: ${pageTitle}</title>
       <link href="<c:url value="/static/mdb/font/fonts/font-awesome.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/mdb/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/mdb/css/mdb.min.css"/>" rel="stylesheet">
        
        <link href="<c:url value="/static/custom/css/toastr.min.css"/>" rel="stylesheet">
    </head>
    <style>
      /* .center_div{
            
           margin: 100px auto;
           width:30%
      
             }
             
.form-elegant .font-small {
  font-size: 0.8rem; }

.form-elegant .z-depth-1a {
  -webkit-box-shadow: 0 2px 5px 0 rgba(55, 161, 255, 0.26), 0 4px 12px 0 rgba(121, 155, 254, 0.25);
  box-shadow: 0 2px 5px 0 rgba(55, 161, 255, 0.26), 0 4px 12px 0 rgba(121, 155, 254, 0.25); }

.form-elegant .z-depth-1-half,
.form-elegant .btn:hover {
  -webkit-box-shadow: 0 5px 11px 0 rgba(85, 182, 255, 0.28), 0 4px 15px 0 rgba(36, 133, 255, 0.15);
  box-shadow: 0 5px 11px 0 rgba(85, 182, 255, 0.28), 0 4px 15px 0 rgba(36, 133, 255, 0.15); }
   */
   
        .md-form label {
            color: #0069d9;
        }

        h6 {
            line-height: 1.7;
        }

        @media (max-width: 740px) {
            .full-height,
            .full-height body,
            .full-height header,
            .full-height header .view {
                height: 750px;
            }
        }

        @media (min-width: 741px) and (max-height: 638px) {
            .full-height,
            .full-height body,
            .full-height header,
            .full-height header .view {
                height: 750px;
            }
        }

        .card {
            margin-top: 75px;
            /*margin-bottom: -45px;*/
        }

       

        .md-form .form-control {
        
            color: #000;
        }

    </style>
    <body>
        
     <div class="container center_div">
                 
            
<!-- Material form login -->

    
<%--   
<section class="form-elegant">
    
<form action="${contextPath}/login" method="POST">
   
    <div class="card">

        <div class="card-body mx-4">

            <!--Header-->
            <div class="logo-wrapper waves-light">
                <center><a href="#"><img src="resources/img/logo.png" class="img-fluid flex-center"></a></center>
                </div>
            <div class="text-center">
                <h3 class="dark-grey-text mb-5"><strong>Login</strong></h3>
                
                <h6 class="bg-light red-text"><strong>${message}</strong></h6>
            </div>

            <!--Body-->
            <div class="md-form">
                <input type="text" id="Form-email1" name="username" class="form-control">
                <label for="Form-email1">Your Username</label>
            </div>

            <div class="md-form pb-3">
                <input type="password" id="Form-pass1" name="password" class="form-control">
                <label for="Form-pass1">Your password</label>
    
            </div>

            <div class="text-center mb-3">
                <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a">Sign in</button>
                <p class="font-small blue-text d-flex justify-content-end">Forgot <a href="#" class="blue-text ml-1"> Password?</a></p>
            </div>
        </div>


    </div>
    <!--/Form without header-->
</form>
</section>
--%>
<header>
            
             <section class="">
                 
        <!--    <div class="full-bg-img">-->
                
                <div class="flex-center">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-5 col-lg-6 col-md-10 col-sm-12 mx-auto mt-lg-5">

                                <!--Form with header-->
                                <div class="card wow fadeIn" data-wow-delay="0.3s">
                                    <div class="card-body">

                                        <!--Header-->
                                        <div class="form-header blue-gradient">
                                            <h3><i class="fa fa-user mt-2 mb-2"></i> Log in:</h3>
                                              
                                        </div>

                                        <!--Body-->
                                        <h6 class="bg-light text-center red-text"><strong>${message}</strong></h6>
                                        <form action="${contextPath}/login" method="POST">
                                              <div class="md-form">
                                                <input type="text" id="Form-email1" name="username" class="form-control">
                                                <label for="Form-email1">Username</label>
                                            </div>

                                            <div class="md-form pb-3">
                                                <input type="password" id="Form-pass1" name="password" class="form-control">
                                                <label for="Form-pass1">Password</label>

                                            </div>

                                            <div class="text-center mb-3">
                                                <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a">Login</button>
                                                <p class="font-small blue-text d-flex justify-content-end">Forgot <a href="${contextPath}/password/forgot" class="blue-text ml-1"> Password?</a></p>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!--/Form with header-->

                            </div>
                        </div>
                    </div>
                </div>
           <!-- </div>-->
        </section>
</header>

            
            
  </div> 
            
    </body>
      <script src="<c:url value="/static/mdb/js/jQuery-3.2.1.min.js" />"></script>
     <script src="<c:url value="/static/mdb/js/popper.min.js" />"></script>
     <script src="<c:url value="/static/mdb/js/bootstrap.js" />"></script>  
     <script src="<c:url value="/static/mdb/js/mdb.min.js" />"></script>
     <script>
         
         toastr.options = {
                "closeButton": true,
                "debug": false,
                "newestOnTop": true,
                "progressBar": true,
                "positionClass": "toast-top-full-width",
                "preventDuplicates": false,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
          };
          var msg = "${message.message}";
          if(msg.length>0){
           <%@include file="./template/notify.jspf" %>
               }
               
     
     </script>
</html>
