<%-- 
    Document   : new-service
    Created on : 17-Jul-2018, 14:42:31
    Author     : user
--%>
<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
         <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/inventory/">Inventory</a></li>
        <li class="breadcrumb-item active">${name}</li>

    </ol>
 <div class="card">
     <div class="card-body">

    <h4 class="h4 text-center py-4 blue">Add ${name}</h4>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/inventory/form" method="POST">         
             <c:if test="${not empty item.id}">
               <form:hidden path="id"/>
               <form:hidden path="version"/>             
               <form:hidden path="specification"/>
             </c:if>
             <c:if test="${not empty categoryId}">
               <form:hidden path="categoryId" value="${categoryId}"/>
             </c:if> 
             
              <form:hidden path="brand" value="Service"/>         
              <div class="md-form">
                                <i class="fa fa-laptop prefix grey-text"></i>
                                <form:input path="model" id="model" class="form-control"/>
                                <label for="model" class="font-weight-light">Name</label>
                                <p class="help-block">
                                 <form:errors path="model" class="alert-danger"/>
                                </p>
                            </div>
                        
                  
                        
              
                
                
                
             <div class="md-form">
                <i class="fa fa-pencil prefix grey-text"></i>
                <form:textarea rows="3" path="description" class="form-control"/>
                <label for="description" class="font-weight-light">Description</label>
                <p class="help-block">
                 <form:errors path="description" class="alert-danger"/>
                </p>
            </div> 
             
                    <div class="md-form">
                        <i class="fa fa-dollar prefix grey-text"></i>
                        <form:input path="sellingPrice" class="form-control"/>
                        <label for="sellingPrice" class="font-weight-light" data-error="Selling Price Is Invalid/Required" data-success="">Selling Price</label>
                           <p class="help-block">
                                <form:errors path="sellingPrice" class="alert-danger"/>
                           </p>
                     </div> 
                          
                    
                <div class="text-center py-4 mt-3">
                    <form:button class="btn btn-block btn-lg btn-blue"><i class="fa fa-laptop"></i>&nbsp;Add ${name}</form:button>
                </div>
        </form:form>
    </div>
</div>
</div>