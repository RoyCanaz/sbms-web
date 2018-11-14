<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>   
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">Branch</li>       
</ol>

<div class="card">
     <div class="card-body">
    <h3 class="h4 text-center py-4 blue">Add Branches</h3>
    <div class="card-body mx-1">
        
        
       
        
         <form:form modelAttribute="item" action="${contextPath}/client/branch/addBranch/form" method="POST">         
            <c:set var="id" value="${item.id}"/>
                      <c:if test="${not empty ci}">
                        <form:hidden path="ci" value="${ci}"/>
                      </c:if> 
               <div class="md-form">
                <i class="fa fa-building prefix grey-text"></i>
                <form:input path="name" id="name" class="form-control"/>
                <label for="name" class="font-weight-light">Branch Name</label>
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
           <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Add Branch</form:button>
                <a href="${contextPath}/client/requiredDocuments/form?id=${ci}" class="btn btn-link btn-blue-grey">Next</a>
            </div>
            
         </form:form>
        
        
    </div>
</div>
</div>