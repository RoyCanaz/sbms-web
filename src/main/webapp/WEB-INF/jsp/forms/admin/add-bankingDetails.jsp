<ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">Add Bank</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/bankingDetail/list">List</a></li>

    </ol>


      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add Banking Details</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/admin/bankingDetail/form" method="POST">         
            <c:if test="${not empty item.id}">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>  
                    <form:hidden path="createdBy"/>
            </c:if>
            
            <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="name" id="name" class="form-control"/>
                <label for="name" class="font-weight-light">Account Holder Name</label>
                <p class="help-block">
                 <form:errors path="name" class="alert-danger"/>
                </p>
            </div>          
          
            
             <div class="md-form">
                <i class="fa fa-bank prefix grey-text"></i>
                <form:input path="bank" id="bank" class="form-control"/>
                <label for="bank" class="font-weight-light">Bank Name</label>
                 <p class="help-block">
                  <form:errors path="bank" class="alert-danger"/>
                 </p>
            </div>
             <div class="md-form">
                <i class="fa fa-adn prefix grey-text"></i>
                <form:input path="accountNumber" id="accountNumber" class="form-control"/>
                <label for="accountNumber" class="font-weight-light">Account Number</label>
                <p class="help-block" >
                <form:errors path="accountNumber" class="alert-danger"/>
                </p>
            </div>
            <div class="md-form">
                <i class="fa fa-map-marker prefix grey-text"></i>
                <form:input path="branch" id="branch" class="form-control"/>
                <label for="branch" class="font-weight-light">Branch</label>
                <p class="help-block" >
                <form:errors path="branch" class="alert-danger"/>
                </p>
            </div>
            
            
                <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Save</form:button>
            </div>
        </form:form>
    </div>
</div>
</div>

                      


