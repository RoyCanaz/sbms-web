<%@include file="../template/headercss.jspf" %>
<body class="fixed-sn white-skin">

    <%@include file="../template/header.jsp" %>
    
 <main>
        <div class="container-fluid">

        <c:if test="${clickClientVisitPlansList == true}">
            <%@include file="../visitPlans/client-visit-plans-list.jsp"%>
        </c:if>
        
       
    </div>
  
    </main>
    
    <%@include file="../template/footer.jsp" %>
  

    <script src="<c:url value="/static/mdb/js/jQuery-3.2.1.min.js" />"></script>
     <script src="<c:url value="/static/mdb/js/popper.min.js" />"></script>
     <script src="<c:url value="/static/mdb/js/bootstrap.js" />"></script>  
     <script src="<c:url value="/static/mdb/js/mdb.min.js" />"></script>
     <script src="<c:url value="/static/custom/js/ajaxcallcategory.js" />"></script>
     <script src="<c:url value="/static/custom/js/jquery.dataTables.min.js" />"></script>
     <script src="<c:url value="/static/custom/js/dataTables.bootstrap4.js" />"></script>
     <script src="<c:url value="/static/custom/js/init-datatables.js" />"></script>
    <script src="<c:url value="/static/custom/js/setting.checkbox.js"/>"></script>

   

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
           <%@include file="../template/notify.jspf" %>
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

