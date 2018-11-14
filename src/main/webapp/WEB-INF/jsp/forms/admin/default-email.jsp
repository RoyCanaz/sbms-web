
  <ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">Add </li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/email/default/list">List</a></li>
    </ol>
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add Account</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/admin/email/default" method="POST">   
            <c:if test="${not empty emailId}">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>  
                    <form:hidden path="createdBy"/>
            </c:if>
             <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="host" class="form-control validate"/>
                <label for="host" class="font-weight-light">Host</label>
                <p class="help-block">
                 <form:errors path="host" class="alert-danger"/>
                </p>
            </div>
              <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="port" class="form-control validate"/>
                <label for="port" class="font-weight-light">Port</label>
                <p class="help-block">
                 <form:errors path="port" class="alert-danger"/>
                </p>
            </div>
                    
             <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="accName" class="form-control validate"/>
                <label for="accName" class="font-weight-light">Account Name</label>
                <p class="help-block">
                 <form:errors path="accName" class="alert-danger"/>
                </p>
            </div>
             
            <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input type="email" path="email" id="email" class="form-control validate"/>
                <label for="email" class="font-weight-light">Email</label>
                <p class="help-block">
                 <form:errors path="email" class="alert-danger"/>
                </p>
            </div>
                
              <div class="md-form">
                <i class="fa fa-lock prefix grey-text"></i>
                <form:password id="password" path="password" class="form-control"/>
                <label for="password" class="font-weight-light">Password</label>
                <p class="help-block" >
                <form:errors path="password" class="alert-danger"/>
                </p>
            </div>
                <div class="md-form">
                <i class="fa fa-lock prefix grey-text"></i>
                <form:password id="confirmPassword" path="confirmPassword" class="form-control"/>
                <label for="confirmPassword" class="font-weight-light">Confirm Password</label>
                <p class="help-block">
                <form:errors path="confirmPassword" class="alert-danger"/>
                </p>
            </div>
                         
                <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Save</form:button>
            </div>
        </form:form>
    </div>
</div>
</div>

                      




