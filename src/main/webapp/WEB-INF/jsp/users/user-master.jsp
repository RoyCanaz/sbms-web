
<%@include file="../template/headercss.jspf" %>
<body class="fixed-sn white-skin">

    <%@include file="../template/header.jsp" %>
    
    
 <main>
        <div class="container-fluid">

       
         <c:if test="${clickUserDashboard == true}">
           <%@include file="../users/user-dashboard.jsp" %>
         </c:if>
           <c:if test="${clickEditContact == true}">
           <%@include file="../forms/user/edit-contact.jsp" %>
         </c:if>
         <c:if test="${clickClientDetails == true}">
           <%@include file="../users/client-details.jsp" %>
         </c:if>
          <c:if test="${clickVisitPlans == true}">
            <%@include file="../users/visit-plans.jsp" %>
        </c:if>
             <c:if test="${clickVisitSummary == true}">
            <%@include file="../users/visit-summary.jsp" %>
        </c:if>
             <c:if test="${clickMyAccount == true}">
            <%@include file="../users/my-account.jsp" %>
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
     <script src="<c:url value="/static/custom/js/alljs.js" />"></script>
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
            $('.datepicker').pickadate({
                format: 'You selecte!d: dddd, dd mmm, yyyy',
                formatSubmit: 'dd/mm/yyyy',
                hiddenPrefix: 'prefix__',
                hiddenSuffix: '__suffix'
            });
        });
    </script>
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


