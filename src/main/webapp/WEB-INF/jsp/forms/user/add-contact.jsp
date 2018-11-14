<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>  
        
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/client/details/${ci}">Client</a></li>  
        
        <li class="breadcrumb-item active">Contact</li>       
</ol>

<div class="card">
     <div class="card-body">
<ul class="nav nav-tabs nav-justified blue" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#panel6" role="tab"><i class="fa fa-list"></i>&nbsp;List</a>
    </li>
    
    <li class="nav-item">
        <a class="nav-link " data-toggle="tab" href="#panel5" role="tab"><i class="fa fa-user-plus"></i>&nbsp; Add New</a>
    </li>
    
</ul>

<div class="tab-content">
  
    <div class="tab-pane fade" id="panel5" role="tabpanel">
        
              <div class="card">
    
       <div class="card-body mx-1">
       
        <form:form modelAttribute="contactsForm" action="${contextPath}/ajax/contact/add" method="POST">         
           
                      <c:if test="${not empty ci}">
                        <form:hidden path="ci" value="${ci}"/>
                      </c:if> 
            <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="firstName" id="firstName" class="form-control"/>
                <label for="firstName" class="font-weight-light">First Name</label>
                <p class="help-block">
                 <form:errors path="firstName" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-pencil-square prefix grey-text"></i>
                <form:input path="lastName" id="lastName" class="form-control"/>
                <label for="lastName" class="font-weight-light">Last Name</label>
                 <p class="help-block">
                 <form:errors path="lastName" class="alert-danger"/>
                 </p>
            </div>
              <div class="md-form">               
                        <form:select path="gender" class="mdb-select">
                                    <form:option value="" label="Select Gender"/>                                   
                                    <form:option value="Male" label="Male"/>
                                    <form:option value="Female" label="Female"/>
                         </form:select>
                        <p class="help-block">
                            <form:errors path="gender" class="alert-danger"/>
                        </p>
            </div>
             <div class="md-form">
                <i class="fa fa-window-close-o prefix grey-text"></i>
                <form:input path="department" id="department" class="form-control"/>
                <label for="department" class="font-weight-light">Department</label>
                 <p class="help-block">
                 <form:errors path="department" class="alert-danger"/>
                 </p>
            </div>
              <div class="md-form">
                <i class="fa fa-window-close-o prefix grey-text"></i>
                <form:input path="jobPosition" id="jobPosition" class="form-control"/>
                <label for="jobPosition" class="font-weight-light">Job Position</label>
                 <p class="help-block">
                 <form:errors path="jobPosition" class="alert-danger"/>
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
                <i class="fa fa-mobile-phone prefix grey-text"></i>
                <form:input path="mobilePhone" id="mobilePhone" class="form-control"/>
                <label for="mobilePhone" class="font-weight-light">Phone</label>
                <p class="help-block" >
                <form:errors path="mobilePhone" class="alert-danger"/>
                </p>
            </div>
            <div class="md-form">
                <i class="fa fa-telegram prefix grey-text"></i>
                <form:input path="officePhone" id="officePhone" class="form-control"/>
                <label for="officePhone" class="font-weight-light">Office Phone</label>
                <p class="help-block" >
                <form:errors path="officePhone" class="alert-danger"/>
                </p>
            </div>
 

            <div class="text-center py-4 mt-3">
                <form:button id="save-con" class="btn btn-blue">Add Contact</form:button>
                <a href="${contextPath}/client/inventory/addClientinventory?id=${ci}" class="btn btn-link btn-blue-grey">Navigate To Client Inventory</a>
            </div>
        </form:form>
    </div>
</div>  

    </div>
    
    <div class="tab-pane fade in show active" id="panel6" role="tabpanel">
        
         <div class="container">
                    <table class="table table-bordered text-center table-responsive-lg">
                        <thead>
                        <tr>
                            <th>#</th>
                           
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Job Position</th>
                            <th>Department</th>
                            <th>Email</th>
                            <th>Mobile Phone</th>
                            <th>Office Phone</th>
                            <th>Action</th>
                            

                        </tr>
                        </thead>
                    <tbody id="conDetails">
                        <c:forEach var="contact" items="${contacts}" varStatus="loop" >
                        <tr>
                            <th>${loop.count}</th>
                            
                            <td>${contact.firstName}</td>
                            <td>${contact.lastName}</td>
                             <td>${contact.jobPosition}</td>
                            <td>${contact.department}</td>
                            
                            <td>${contact.email}</td>
                            <td>${contact.mobilePhone}</td>
                            <td>${contact.officePhone}</td>
                            <td>
                                <a href="<c:url value="/client/contact/edit/${contact.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square blue-ic fa-1x"></i></a>
                                 <a onclick="return confirm('Delete Contact?')" href="<c:url value="/client/contact/delete/${ci}/${contact.id}"/>" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-minus-square red-ic fa-1x"></i></a>
                            </td>

                        </tr>
                        </c:forEach>
                    </tbody>
                    </table>
                </div>
     
        
    </div>
   
</div>
</div>
 </div>
        
        
        
        
       