<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>  
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">Add Supplier</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/supplier/list">List</a></li>  
        
    </ol>


      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add Supplier</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/admin/supplier/form" method="POST"> 
            
            <c:if test="${not empty item.id}">
                <form:hidden path="id"/>
                <form:hidden path="version"/>  
                <form:hidden path="dateCreated"/>
                <form:hidden path="createdBy"/>
            </c:if>
                                   
            
            <div class="md-form">
                <i class="fa fa-truck prefix grey-text"></i>
                <form:input path="name" id="name" class="form-control"/>
                <label for="name" class="font-weight-light">Supplier Name</label>
                <p class="help-block">
                 <form:errors path="name" class="alert-danger"/>
                </p>
            </div>
                
                
         <%--   <div class="md-form">
                <i class="fa fa-cubes prefix grey-text"></i>
                <label class="font-weight-light">Supplier Products</label>
                <div class="form-check" style="margin-left: 200px">
                 <form:checkboxes class="form-check-input" itemLabel="printName" path="categories" items="${categories}"  itemValue="id" />
                </div>
                <p class="help-block" style="margin-top: 10px">
               <form:errors path="categories" class="alert-danger "/>
                </p>
            </div>--%>
                 <div class="form-check-inline">
                                                       <label class="font-weight-light">Supplier Products :  </label>
                                                        <form:checkboxes path="categories" items="${categories}"
                                                                         element="div class='form-check' style='padding-left:20px;margin-right:30px'"
                                                                         itemLabel="printName" itemValue="id"/>
                                                    
                                                    <p class="help-block">
                                                      <form:errors path="categories" class="alert-danger "/>
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
                <form:input path="officePhone" id="officePhone" class="form-control"/>
                <label for="officePhone" class="font-weight-light">Office Phone</label>
                <p class="help-block" >
                <form:errors path="officePhone" class="alert-danger"/>
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
                <i class="fa fa-link prefix grey-text"></i>
                <form:input path="website" id="website" class="form-control"/>
                <label for="website" class="font-weight-light">Website</label>
                <p class="help-block" >
                <form:errors path="website" class="alert-danger"/>
                </p>
            </div>
            
                <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Add Supplier</form:button>
            </div>
        </form:form>
    </div>
</div>
</div>

                      

