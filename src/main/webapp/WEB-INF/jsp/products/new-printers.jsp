<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
         <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/inventory/">Inventory</a></li>
        <li class="breadcrumb-item active">${name}</li>

    </ol>
      <div class="card">
     <div class="card-body">

    <h4 class="h4 text-center py-4 blue">${name}</h4>
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
                                  
                                  <c:choose>
                                   <c:when test="${not empty spec.catridge}">
                                      <form:select path="catridge" class="mdb-select">
                                           <form:option value="${spec.catridge}" label="${spec.catridge}" selected="true"/>
                                            <form:options items="${catridgeList}"/>                                
                                       </form:select>
                                     </c:when>
                                   <c:otherwise> 
                                      <form:select path="catridge" class="mdb-select">
                                           <form:option value="" label="Select Catridge"/>
                                            <form:options items="${catridgeList}"/>                                
                                       </form:select>
                                       </c:otherwise>
                               </c:choose>
                                      <p class="help-block">
                                          <form:errors path="catridge" class="alert-danger"/>
                                      </p>
                               </div>
                        </div> 
                        <div class="col-md-6">              
                              <div class="md-form">
                                   <c:choose>
                                   <c:when test="${not empty spec.color}">
                                      <form:select path="color" class="mdb-select">
                                          <form:option value="${spec.color}" label="${spec.color}" selected="true"/>
                                            <form:options items="${colorList}"/>                                
                                       </form:select>
                                      </c:when>
                                   <c:otherwise> 
                                    <form:select path="color" class="mdb-select">
                                           <form:option value="" label="Select Color"/>
                                            <form:options items="${colorList}"/>                                
                                       </form:select>
                                     </c:otherwise>
                               </c:choose>
                                      <p class="help-block">
                                          <form:errors path="color" class="alert-danger"/>
                                      </p>
                             </div>
                        </div>
                </div>
                 <div class="form-row">
       
                  <div class="col-md-6">
                               
                        <div class="md-form"> 
                            <c:choose>
                                   <c:when test="${not empty spec.duplex}">
                                <form:select path="duplex" class="mdb-select">
                                      <form:option value="${spec.duplex}" label="${spec.duplex}" selected="true"/>
                                      <form:options items="${yesNoList}"/>                                
                                 </form:select>
                                </c:when>
                                   <c:otherwise> 
                                  <form:select path="duplex" class="mdb-select">
                                     <form:option value="" label="Select Duplex"/>
                                      <form:options items="${yesNoList}"/>                                
                                 </form:select>
                                  </c:otherwise>
                               </c:choose>
                                <p class="help-block">
                                    <form:errors path="duplex" class="alert-danger"/>
                                </p>
                         </div>
                  </div> 
                  <div class="col-md-6">              
                        <div class="md-form">  
                             <c:choose>
                                   <c:when test="${not empty spec.scanner}">
                                <form:select path="scanner" class="mdb-select">
                                   <form:option value="${spec.scanner}" label="${spec.scanner}" selected="true"/>
                                      <form:options items="${yesNoList}"/>                                
                                 </form:select>
                                </c:when>
                                   <c:otherwise> 
                                 <form:select path="scanner" class="mdb-select">
                                     <form:option value="" label="Scanner"/>
                                      <form:options items="${yesNoList}"/>                                
                                 </form:select>
                                  </c:otherwise>
                               </c:choose>
                                <p class="help-block">
                                    <form:errors path="scanner" class="alert-danger"/>
                                </p>
                       </div>
                  </div>
                </div>
                  <div class="form-row">      
                      <div class="col-md-6">
                            <div class="md-form"> 
                                <c:choose>
                                   <c:when test="${not empty spec.ethernet}">
                                     <form:select path="ethernet" class="mdb-select">
                                          <form:option value="${spec.ethernet}" label="${spec.ethernet}" selected="true"/>
                                           <form:options items="${yesNoList}"/>                                
                                      </form:select>
                                     </c:when>
                                   <c:otherwise> 
                                       <form:select path="ethernet" class="mdb-select">
                                          <form:option value="" label="Ethernet"/>
                                           <form:options items="${yesNoList}"/>                                
                                      </form:select>
                                    </c:otherwise>
                                  </c:choose>
                                     <p class="help-block">
                                         <form:errors path="ethernet" class="alert-danger"/>
                                     </p>
                            </div>
                      </div>
                      <div class="col-md-6">
                            <div class="md-form">  
                                <c:choose>
                                   <c:when test="${not empty spec.wireless}">
                                     <form:select path="wireless" class="mdb-select">
                                         <form:option value="${spec.wireless}" label="${spec.wireless}" selected="true"/>
                                           <form:options items="${yesNoList}"/>                                
                                      </form:select>
                                     </c:when>
                                   <c:otherwise> 
                                     <form:select path="wireless" class="mdb-select">
                                          <form:option value="" label="Wireless"/>
                                           <form:options items="${yesNoList}"/>                                
                                      </form:select>
                                     </c:otherwise>
                                  </c:choose>
                                     <p class="help-block">
                                         <form:errors path="wireless" class="alert-danger"/>
                                     </p>
                            </div>
                      </div>
                  </div>
                  <div class="form-row">      
                      <div class="col-md-6">
                            <div class="md-form"> 
                                <c:choose>
                                   <c:when test="${not empty spec.fax}">
                                     <form:select path="fax" class="mdb-select">
                                          <form:option value="${spec.fax}" label="${spec.fax}" selected="true"/>
                                           <form:options items="${yesNoList}"/>                                
                                      </form:select>
                                     </c:when>
                                   <c:otherwise> 
                                       <form:select path="fax" class="mdb-select">
                                          <form:option value="" label="Fax"/>
                                           <form:options items="${yesNoList}"/>                                
                                      </form:select>
                                    </c:otherwise>
                                  </c:choose>
                                     <p class="help-block">
                                         <form:errors path="fax" class="alert-danger"/>
                                     </p>
                            </div>
                      </div>
                      <div class="col-md-6">
                            <div class="md-form">  
                                 <i class="fa fa-tv prefix grey-text"></i>
                             <c:choose>
                               <c:when test="${not empty spec.dutyCycle}">
                                  <form:input path="dutyCycle" id="dutyCycle" value="${spec.dutyCycle}" class="form-control"/>
                              </c:when>
                             <c:otherwise>
                                 <form:input path="dutyCycle" id="dutyCycle" class="form-control"/>
                                  </c:otherwise>
                             </c:choose> 
                             <label for="dutyCycle" class="font-weight-light">Duty Cycle</label>
                             <p class="help-block">
                              <form:errors path="dutyCycle" class="alert-danger"/>
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

                      


