
            <ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item active">Add company</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/list-company">List</a></li>
    </ol>
           


     <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add Company</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/admin/gb/addCompany/form" method="POST">         
             <form:hidden path="id"/>
             <form:hidden path="version"/>
            <%-- <form:hidden path="dateCreated"/>--%>
             <form:hidden path="createdBy"/>
            <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="companyName" id="companyName" class="form-control"/>
                <label for="name" class="font-weight-light">Company Name</label>
                <p class="help-block">
                 <form:errors path="companyName" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-pencil-square prefix grey-text"></i>
                <form:input path="description" id="description" class="form-control"/>
                <label for="description" class="font-weight-light">Description</label>
                 <p class="help-block">
                 <form:errors path="description" class="alert-danger"/>
                 </p>
            </div>
                
             <div class="md-form">
                <i class="fa fa-address-book prefix grey-text"></i>
                <form:input path="address" id="address" class="form-control"/>
                <label for="address" class="font-weight-light">Address</label>
                 <p class="help-block">
                  <form:errors path="address" class="alert-danger"/>
                 </p>
            </div>
             <div class="md-form">
                <i class="fa fa-envelope prefix grey-text"></i>
                <form:input path="email" id="email" class="form-control"/>
                <label for="email" class="font-weight-light">Email</label>
                <p class="help-block" >
                <form:errors path="email" class="alert-danger"/>
                </p>
            </div>
            <div class="md-form">
                <i class="fa fa-phone prefix grey-text"></i>
                <form:input path="mobilePhone" id="mobilePhone" class="form-control"/>
                <label for="mobilePhone" class="font-weight-light">Mobile Phone</label>
                <p class="help-block" >
                <form:errors path="mobilePhone" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-phone prefix grey-text"></i>
                <form:input path="officePhone" id="officePhone" class="form-control"/>
                <label for="officePhone" class="font-weight-light">Office Phone</label>
                <p class="help-block" >
                <form:errors path="officePhone" class="alert-danger"/>
                </p>
            </div>
            <div class="md-form">
                <i class="fa fa-link prefix grey-text"></i>
                <form:input path="website" id="website" class="form-control"/>
                <label for="website" class="font-weight-light">Website</label>
                <p class="help-block" >
                <form:errors path="website" class="alert-danger"/>
                </p>
            </div>
                <form:select path="modules" class="mdb-select colorful-select dropdown-primary" multiple="true" searchable="Search here..">
                                                    <form:option value="" label="Select Module" selected="true" disabled="true"/>
                                                     <form:options items="${modules}" itemLabel="printName" itemValue="id"/>                                
                 </form:select>
            
                <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Add Company</form:button>
            </div>
        </form:form>
    </div>
</div>
</div>

                      


