
<%@include file="../template/headercss.jspf" %>
<body class="fixed-sn white-skin">

    <%--  <%@include file="../template/header.jsp" %>--%>
    
    
 <main>
        <div class="container-fluid">

       
         <c:if test="${clickForgotPassword == true}">
           <%@include file="../passwords/forgot-password.jsp" %>
         </c:if>
           <c:if test="${clickResetPassword == true}">
           <%@include file="../passwords/reset-password.jsp" %>
         </c:if>
         
          
 
    </div>
  
    </main>
    
    <%--  <%@include file="../template/footer.jsp" %>--%>
    <%@include file="../template/javascript.jspf" %>