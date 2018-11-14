<%--
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>${pageTitle}</title>
        <%-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="<c:url value="/static/mdb/font/fonts/font-awesome.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/mdb/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/mdb/css/mdb.min.css"/>" rel="stylesheet">
        <%-- <link href="<c:url value="/static/mdb/css/style.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/custom/css/toastr.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/custom/css/tableresponsive.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/custom/css/jquery.dataTables.min.css"/>" rel="stylesheet">
        	<style>
		.navbar .dropdown-menu.mega-menu {
                width: 100%;
                border: none;
                -webkit-border-radius: 0;
                border-radius: 0;
                 }
                    .w-auto {
                 width: auto;
             }
</style>
</head>--%>
<%@include file="./template/headercss.jspf" %>
<body class="fixed-sn white-skin">

     <%@include file="./template/header.jsp" %>
    
    
 <main>
        <div class="container-fluid">

       <c:if test="${clickAddStock == true}">
            <%@include file="./products/add-quantity.jsp" %>
        </c:if>
         <c:if test="${addStock == true}">
            <%@include file="./products/add-stock.jsp" %>
        </c:if>
         <c:if test="${clickProducts == true}">
            <%@include file="./products/products-home.jsp" %>
        </c:if>
       <c:if test="${clickListInventory == true}">
            <%@include file="./products/list-inventory.jsp" %>
        </c:if>
        <c:if test="${clickNewPrinter == true}">
          <%@include file="./products/new-printers.jsp" %>
        </c:if>
         <c:if test="${clickNewService == true}">
          <%@include file="./products/new-service.jsp" %>
        </c:if>
       
        
        
       

        

    </div>
  
    </main>
    
    <%@include file="./template/footer.jsp" %>
  

    <script src="<c:url value="/static/mdb/js/jQuery-3.2.1.min.js" />"></script>
     <script src="<c:url value="/static/mdb/js/popper.min.js" />"></script>
     <script src="<c:url value="/static/mdb/js/bootstrap.js" />"></script>  
     <script src="<c:url value="/static/mdb/js/mdb.min.js" />"></script>
     <script src="<c:url value="/static/custom/js/ajaxcallcategory.js" />"></script>
     <script src="<c:url value="/static/custom/js/jquery.dataTables.min.js" />"></script>
     <script src="<c:url value="/static/custom/js/dataTables.bootstrap4.js" />"></script>
     <script src="<c:url value="/static/custom/js/init-datatables.js" />"></script>
     <script src="<c:url value="/static/custom/js/alljs.js" />"></script>
       <script src="<c:url value="/static/custom/js/updatesn.js" />"></script>
   <%-- <script src="<c:url value="/static/custom/js/toastr.min.js"/>"></script>--%>

   <script type="text/javascript">
    var id = "<c:out value="${id}"/>";
    if (id !=="") {
        $('#userName').addClass("disabled");
        $('#password').attr('readonly', 'readonly');
        $('#confirmPassword').attr('readonly', 'readonly');
    }
</script>

    <script type="text/javascript">
        /* WOW.js init */
        new WOW().init();

        // Tooltips Initialization
        $(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
  

    <script>
        // Material Select Initialization
        $(document).ready(function () {
            $('.mdb-select').material_select();
        });
    </script>
     <script>
         
         toastr.options = {
                "closeButton": true,
                "debug": false,
                "newestOnTop": true,
                "progressBar": true,
                "positionClass": "toast-top-center",
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
               else{}
     
     </script>
    <script>
        // SideNav Initialization
        $(".button-collapse").sideNav();
         var container = document.querySelector('.custom-scrollbar');
        Ps.initialize(container, {
            wheelSpeed: 2,
            wheelPropagation: true,
            minScrollbarLength: 20
        });
        // Tooltips Initialization
        $(function () {
          $('[data-toggle="tooltip"]').tooltip();
        });
        

    </script>
</body>

</html>

