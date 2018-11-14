<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>  
        
        <li class="breadcrumb-item active">Client</li>
         
</ol>
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add/Edit Client</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/client/addClient/form" method="POST">         
             <c:if test="${not empty item.id}">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>  
                    <form:hidden path="createdBy"/>
            </c:if>
            <div class="md-form">
               
                
                        <form:select path="clientType" class="mdb-select">
                                    <form:option value="" label="Client Type"/>
                                   
                                    <form:option value="Company" label="Company"/>
                                    <form:option value="Individual" label="Individual"/>
                         </form:select>
                        <p class="help-block">
                            <form:errors path="clientType" class="alert-danger"/>
                        </p>
            </div>
            <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="name" id="name" class="form-control"/>
                <label for="name" class="font-weight-light">Name</label>
                <p class="help-block">
                 <form:errors path="name" class="alert-danger"/>
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
                <form:input path="phone" id="phone" class="form-control"/>
                <label for="phone" class="font-weight-light">Phone</label>
                <p class="help-block" >
                <form:errors path="phone" class="alert-danger"/>
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
             <div class="md-form">     
                        <form:select path="branch" class="mdb-select">
                                    <form:option value="No" label="No" selected="true"/>
                                    <form:option value="Yes" label="Yes"/>
                                    
                         </form:select>
                 <label>Branch(Yes/No)</label>
                        <p class="help-block">
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

                      

