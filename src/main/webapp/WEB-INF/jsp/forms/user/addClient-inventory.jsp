<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>  
        <li class="breadcrumb-item active">Client Inventory</li>       
</ol>
 <div class="card">
     <div class="card-body">
<ul class="nav nav-tabs nav-justified blue" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#panel6" role="tab"><i class="fa fa-cart-plus"></i>&nbsp; Add New Inventory</a>
    </li>
    
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#panel5" role="tab"><i class="fa fa-list"></i>&nbsp;List Inventory</a>
    </li>
    
</ul>

<div class="tab-content">
  
    <div class="tab-pane fade" id="panel5" role="tabpanel">
        
                <div class="container">
                    <table class="table table-bordered text-center table-responsive-lg">
                        <thead>
                        <tr>
                            <th>#</th>
                            <td></td>
                            <th>Model</th>
                            <th>Quantity</th>
                            <th>Need Maintenence</th>
                            <th>Date Created</th>
                            
                            

                        </tr>
                        </thead>
                    <tbody id="clientInventories">
                        <c:forEach var="inventory" items="${inventories}" varStatus="loop" >
                        <tr>
                            <th>${loop.count}</th>                           
                            <td><strong>${inventory.category}</strong></td>
                            <td>${inventory.model}</td>
                            <td>${inventory.quantity}</td>
                            <td>${inventory.needMaintenence}</td>
                            <td>${inventory.dateCreated}</td>                          
                        </tr>
                        </c:forEach>
                    </tbody>
                    </table>
                </div>

    </div>
    
    <div class="tab-pane fade in show active" id="panel6" role="tabpanel">
        
        
     <div class="card">
    
       <div class="card-body mx-1">
       
        <form:form modelAttribute="inventoryForm" action="${contextPath}/ajax/inventory/add" method="POST">         
           
                    <%--  <c:if test="${not empty ci}">
                        <form:hidden path="ci" value="${ci}"/>
                      </c:if> --%>
                    <input type="hidden" id="ci" name="ci" value="${ci}">
             <div class="md-form">
                <form:select path="category" class="mdb-select">
                           <form:option value="" label="Select Category"/>
                            <c:forEach var="cat" items="${categories}" varStatus="loop" >
                              <form:option value="${cat.id}" label="${cat.name}"/>
                            </c:forEach>

                  </form:select>
                 </div>
            <div class="md-form">
                <i class="fa fa-drupal prefix grey-text"></i>
                <form:input path="model" id="model" class="form-control"/>
                <label for="model" class="font-weight-light">Brand</label>
                <p class="help-block">
                 <form:errors path="model" class="alert-danger"/>
                </p>
            </div>
                 <div class="md-form">
                <i class="fa fa-plus-square prefix grey-text"></i>
                <form:input path="quantity" class="form-control"/>
                <label for="quantity" class="font-weight-light">Quantity</label>
                <p class="help-block">
                 <form:errors path="quantity" class="alert-danger"/>
                </p>
            </div>
             
              <div class="md-form">               
                        <form:select path="needMaintenence" class="mdb-select">
                                    <form:option value="" label="Need Maintenance"/>                                   
                                    <form:option value="Yes" label="Yes"/>
                                    <form:option value="No" label="No"/>
                         </form:select>
                        <p class="help-block">
                            <form:errors path="needMaintenence" class="alert-danger"/>
                        </p>
            </div>
             
         
            <div class="text-center py-4 mt-3">
                <form:button id="save-inv" class="btn btn-blue">Add Inventory</form:button>
            </div>
        </form:form>
    </div>
</div>
        
    </div>
   
</div>
</div>
 </div>