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
             <div class="form-row">
       
            <div class="col-md-6"> 
              <div class="md-form">     
                                  <form:select path="brand" class="mdb-select colorful-select dropdown-primary" searchable="Search Brand.." required="true">
                                <option value="" disabled selected>Select Brand</option>
                                 <c:forEach var="br" items="${brands}">
                                    <option value="${br.name}">${br.name}</option>
                                </c:forEach>
                              </form:select>
                               <p class="help-block">
                                    <form:errors path="brand" class="alert-danger"/>
                               </p>
                         </div>
            </div>
              <div class="col-md-6" style="margin-top: 9px"> 
                         <div class="md-form">
                                <i class="fa fa-laptop prefix grey-text"></i>
                                <form:input path="model" id="model" class="form-control"/>
                                <label for="model" class="font-weight-light">Model</label>
                                <p class="help-block">
                                 <form:errors path="model" class="alert-danger"/>
                                </p>
                            </div>
                        
                     
                     </div>
             </div>
                        
             <div class="form-row">
       
                  <div class="col form-group">            
                        
                        <div class="md-form">    
                                       
                                        <form:select path="monthYear" class="mdb-select">
                                             <form:option value="" label="Select Month/Year"/>
                                             <form:option value="Month" label="Month(s)"/>
                                             <form:option value="Year" label="Year(s)"/>
                                         </form:select>
                                        <p class="help-block">
                                            <form:errors path="monthYear" class="alert-danger"/>
                                        </p>
                                        
                                   </div>     
                  </div>
                                        <div class="col form-group" style="margin-top: 9px">               
                          <div class="md-form">
                                   
                                     <form:input type="number" path="warranty" class="form-control"/>
                                     <label for="warranty" class="font-weight-light">Warranty</label>
                                     <p class="help-block">
                                      <form:errors path="warranty" class="alert-danger"/>
                                     </p>
                          </div>
                  </div>
             </div>   
                
                
                
             <div class="md-form">
                <i class="fa fa-pencil prefix grey-text"></i>
                <form:textarea rows="3" path="description" class="form-control"/>
                <label for="description" class="font-weight-light">Description</label>
                <p class="help-block">
                 <form:errors path="description" class="alert-danger"/>
                </p>
            </div> 
                          
                    <hr>
                <center><h6 class="bg-primary "><strong><i>Price/Suppliers</i></strong></h6></center>
                <hr>           
                  <div class="form-row">      
                      <div class="col-md-6">
                          <div class="md-form">     
                                     <form:select path="suppliers" class="mdb-select">
                                          <form:option value="" label="Select Supplier"/>
                                           <form:options items="${suppliers}" itemLabel="printName" itemValue="id"/>                                
                                      </form:select>
                                     <p class="help-block">
                                         <form:errors path="suppliers" class="alert-danger"/>
                                     </p>
                            </div>
                      </div>    
                      <div class="col-md-6">
                             <div class="md-form">
                                    <i class="fa fa-leanpub prefix grey-text"></i>
                                    <form:input path="landingCost" id="landingCost" class="form-control"/>
                                    <label for="landingCost" class="font-weight-light">Landing Cost</label>
                                       <p class="help-block">
                                            <form:errors path="landingCost" class="alert-danger"/>
                                       </p>
                                 </div> 
                      </div>  
                  </div>  
                  <div class="form-row">    
                      <div class="col-md-6">
                           <div class="md-form">
                             <i class="fa fa-car prefix grey-text"></i>
                             <form:input path="quantityDelivered" id="quantityDelivered" class="form-control"/>
                             <label for="quantityDelivered" class="font-weight-light">Quantity Delivered</label>
                                <p class="help-block">
                                     <form:errors path="quantityDelivered" class="alert-danger"/>
                                </p>
                         </div> 
                      </div>    
                                <div class="col-md-6">
                                         <div class="md-form">
                                            <i class="fa fa-dollar prefix grey-text"></i>
                                            <form:input path="sellingPrice" id="sellingPrice" class="form-control"/>
                                            <label for="sellingPrice" class="font-weight-light">Selling Price</label>
                                               <p class="help-block">
                                                    <form:errors path="sellingPrice" class="alert-danger"/>
                                               </p>
                                         </div> 
                                </div>  
                  </div>
                
                  
                  <div class="form-row">    
                       <div class="col-md-6">
                           <div class="md-form">
                            <i class="fa fa-level-down prefix grey-text"></i>
                            <form:input path="reOrderLevel" id="reOrderLevel" class="form-control"/>
                            <label for="reOrderLevel" class="font-weight-light">ReOrder Level</label>
                               <p class="help-block">
                                  <form:errors path="reOrderLevel" class="alert-danger"/>
                               </p>
                       </div>
                       </div>    
                       <div class="col-md-6">
                         <div class="md-form">
                            <i class="fa fa-level-up prefix grey-text"></i>
                            <form:input path="reOrderQuantity" id="reOrderQuantity" class="form-control"/>
                            <label for="reOrderQuantity" class="font-weight-light">ReOrder Quantity</label>
                               <p class="help-block">
                                  <form:errors path="reOrderQuantity" class="alert-danger"/>
                               </p>
                       </div> 
                       </div>  
                  </div>  
                  
             
                <div class="text-center py-4 mt-3">
                    <form:button class="btn btn-block btn-lg btn-blue"><i class="fa fa-laptop"></i>&nbsp;Add ${name}</form:button>
                </div>
        </form:form>
    </div>
</div>
</div>

                      



