<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
         <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/inventory">Inventory</a></li>
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
                                 <c:if test="${empty item.id}">
                                  <option value="" disabled selected>Select Brand</option>
                                  </c:if> 
                                 <c:forEach var="br" items="${brands}">
                                     <c:if test="${item.brand eq br.name}">
                                         <option value="${br.name}" selected="">${br.name}</option>
                                    </c:if>
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
                <form:textarea rows="3" path="description" id="description" class="form-control"/>
                <label for="description" class="font-weight-light">Description</label>
                <p class="help-block">
                 <form:errors path="description" class="alert-danger"/>
                </p>
            </div> 
                <hr>
                <center><h6 class="bg-primary "><strong><i>Specifications</i></strong></h6></center>
                <hr>
                <div class="form-row">
       
                  <div class="col-md-6">
                               
                       <div class="md-form">
                            <i class="fa fa-pencil prefix grey-text"></i>
                            
                             <c:choose>
                               <c:when test="${not empty spec.monitorSize}">
                            <form:input path="monitorSize" id="monitorSize" value="${spec.monitorSize}" class="form-control"/>
                            </c:when>
                             <c:otherwise>
                                  <form:input path="monitorSize" id="monitorSize" class="form-control"/>
                              </c:otherwise>
                             </c:choose>    
                            <label for="monitorSize" class="font-weight-light">Size</label>
                            <p class="help-block">
                             <form:errors path="monitorSize" class="alert-danger"/>
                            </p>
                        </div> 
                  </div> 
                  <div class="col-md-6">              
                       <div class="md-form">
                            <i class="fa fa-pencil prefix grey-text"></i>
                             <c:choose>
                               <c:when test="${not empty spec.resolution}">
                                 <form:input path="resolution" id="resolution" value="${spec.resolution}" class="form-control"/>
                              </c:when>
                              <c:otherwise>
                              <form:input path="resolution" id="resolution" class="form-control"/>
                               </c:otherwise>
                             </c:choose> 
                            <label for="resolution" class="font-weight-light">Resolution</label>
                            <p class="help-block">
                             <form:errors path="resolution" class="alert-danger"/>
                            </p>
                        </div> 
                  </div>
                </div>
                  <div class="form-row">
       
                      <div class="col-md-6">
                            <div class="md-form">
                            <i class="fa fa-pencil prefix grey-text"></i>
                            <c:choose>
                               <c:when test="${not empty spec.videoInput}">
                                <form:input path="videoInput" id="videoInput" value="${spec.videoInput}" class="form-control"/>
                             </c:when>
                             <c:otherwise>
                              <form:input path="videoInput" id="videoInput" class="form-control"/>
                                </c:otherwise>
                             </c:choose> 
                            <label for="videoInput" class="font-weight-light">Video Input</label>
                            <p class="help-block">
                             <form:errors path="videoInput" class="alert-danger"/>
                            </p>
                        </div> 
                      </div>
                      <div class="col-md-6">               
                         <div class="md-form">
                             <i class="fa fa-tv prefix grey-text"></i>
                             <c:choose>
                               <c:when test="${not empty spec.display}">
                                  <form:input path="display" id="display" value="${spec.display}" class="form-control"/>
                              </c:when>
                             <c:otherwise>
                                 <form:input path="display" id="display" class="form-control"/>
                                  </c:otherwise>
                             </c:choose> 
                             <label for="display" class="font-weight-light">Display</label>
                             <p class="help-block">
                              <form:errors path="display" class="alert-danger"/>
                             </p>
                         </div> 
                      </div>      
                  </div>
                    <hr>
                <center><h6 class="bg-primary "><strong><i>Price/Suppliers</i></strong></h6></center>
                <hr>           
                  <%@include file="../template/pricesuppliers.jspf" %>                   
                 <hr>
                <%--<center><h6 class="bg-primary "><strong><i>Recommended Addons</i></strong></h6></center>
                <hr> 
                 <div class="md-form">
                <div class="form-check" style="margin-left: 100px">
                 <form:checkboxes class="test" path="addons" items="${addons}"  itemLabel="name" itemValue="id" />
                </div>
                <p class="help-block" style="margin-top: 10px">
               <form:errors path="addons" class="alert-danger "/>
                </p>
            </div> --%>
           
                <div class="text-center py-4 mt-3">
                    <form:button class="btn btn-block btn-lg btn-blue"><i class="fa fa-laptop"></i>&nbsp;Save ${name}</form:button>
                </div>
        </form:form>
    </div>
</div>
</div>

                      


