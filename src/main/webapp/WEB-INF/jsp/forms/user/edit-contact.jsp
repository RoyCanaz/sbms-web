
  <ol class="breadcrumb white">
      <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active">Add Contact</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/currency/list">List</a></li>
    </ol>
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Edit Contact</h3>
    <div class="card-body mx-1">
         <form:form modelAttribute="item" action="${contextPath}/client/contact/edit" method="POST">    
             <c:if test="${not empty item.id}">
                  <form:hidden path="id"/>
                    <form:hidden path="version"/>  
                    <form:hidden path="createdBy"/>
             </c:if>
             

            <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="firstName" class="form-control"/>
                <label for="firstName" class="font-weight-light">First Name</label>
                <p class="help-block">
                 <form:errors path="firstName" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-pencil-square prefix grey-text"></i>
                <form:input path="lastName" class="form-control"/>
                <label for="lastName" class="font-weight-light">Last Name</label>
                 <p class="help-block">
                 <form:errors path="lastName" class="alert-danger"/>
                 </p>
            </div> 
                <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="jobPosition" class="form-control"/>
                <label for="jobPosition" class="font-weight-light">Job Position</label>
                <p class="help-block">
                 <form:errors path="jobPosition" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-pencil-square prefix grey-text"></i>
                <form:input path="department" class="form-control"/>
                <label for="department" class="font-weight-light">Department</label>
                 <p class="help-block">
                 <form:errors path="department" class="alert-danger"/>
                 </p>
            </div> 
                <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="officePhone" class="form-control"/>
                <label for="officePhone" class="font-weight-light">Office Phone</label>
                <p class="help-block">
                 <form:errors path="officePhone" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-pencil-square prefix grey-text"></i>
                <form:input path="mobilePhone" class="form-control"/>
                <label for="mobilePhone" class="font-weight-light">Mobile Phone</label>
                 <p class="help-block">
                 <form:errors path="mobilePhone" class="alert-danger"/>
                 </p>
            </div> 
                <div class="md-form">
                <i class="fa fa-pencil-square prefix grey-text"></i>
                <form:input path="email" class="form-control"/>
                <label for="email" class="font-weight-light">Email</label>
                 <p class="help-block">
                 <form:errors path="email" class="alert-danger"/>
                 </p>
            </div> 
                <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Save</form:button>
            </div>
        </form:form>
       
    </div>
</div>
</div>

                      




