<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>  
        <li class="breadcrumb-item active">List</li>       
</ol>

<div class="card">
     <div class="card-body">
<ul class="nav nav-tabs nav-justified blue" role="tablist">
     <li  class="nav-link ${list}" >&nbsp; ${clientName}</li>
<%--    <li class="nav-item">
        <a class="nav-link ${add}" data-toggle="tab" href="#panel6" role="tab"><i class="fa fa-street-view"></i>&nbsp; Add Call/Visit Plan</a>
    </li>--%>
    
   
    
</ul>

<div class="tab-content">
  
    <%--<div class="tab-pane fade ${showActiveList}" id="panel5" role="tabpanel">--%>
        
                <div class="container">
                    <table id="listTable" class="table table-bordered text-center table-responsive-lg">
                        <thead>
                        <tr>
                            <th>#</th>
                           
                            <th>Date Created</th>
                            
                            <!--<th>Client</th>-->
                            <th>Date of Visit</th>
                            <th>Days</th>
                            <th>Status</th>
                            <th>Call/Visit Summary</th>
                           
                           
                            

                        </tr>
                        </thead>
                    <tbody id="conDetails">
                        <c:forEach var="visit" items="${clientVisitPlans}" varStatus="loop" >
                        <tr>
                            <th>${loop.count}</th>
                            
                            <td>${visit.dateCreated}</td>
                            <%--<td>${visit.client.name}</td>--%>
                            <td>${visit.dateOfVisit}</td>
                            
                            <td >
                                <c:if test="${visit.status eq '0'}">
                                                    <c:if test="${visit.daysRemaining<3 && visit.daysRemaining>0}">
                                                        <span class="badge orange" data-toggle="tooltip" data-placement="top" title="${visit.daysRemaining} days remaining">${visit.daysRemaining}</span> days
                                                    </c:if>
                                                     <c:if test="${visit.daysRemaining<1}">
                                                        <span class="badge red" data-toggle="tooltip" data-placement="top" title="${visit.daysRemaining} days remaining">${visit.daysRemaining}</span> days
                                                    </c:if>
                                                     <c:if test="${visit.daysRemaining>3}">
                                                        <span class="badge green" data-toggle="tooltip" data-placement="top" title="${visit.daysRemaining} days remaining">${visit.daysRemaining}</span> days
                                                    </c:if>
                                                   </c:if> 
                                                </td>
                            
                            <td>
                                <c:if test="${visit.status eq '0'}">
                                    <a class="btn btn-sm btn-link btn-outline-danger" href="${contextPath}/visitplan/callvisit/${visit.id}" data-toggle="tooltip" data-placement="top" title="Call/Visit">Call/Visit</a>
                                </c:if>
                                 <c:if test="${visit.status eq '1'}">
                                    <a class="btn btn-sm btn-link btn-outline-success" data-toggle="tooltip" data-placement="top" title="Called/Visited on ${visit.dateCalledVisited}">Done</a>
                                </c:if>
                            </td>
                            <td>${visit.visitResult}</td>
                             
                        </tr>
                        </c:forEach>
                    </tbody>
                    </table>
                </div>

    <!--</div>-->
    
<%--    <div class="tab-pane fade ${showActiveAdd}" id="panel6" role="tabpanel">
        
        
     <div class="card">
    
       <div class="card-body mx-sm-3">
       
        <form:form modelAttribute="visitForm" action="${contextPath}/visitplan/add" method="POST">         
           
                      <c:if test="${not empty visitForm.id}">
                        <form:hidden path="id" value="${visitForm.id}"/>
                      </c:if> 
                        <div class="md-form">     
                            
                              <form:select path="client" class="mdb-select colorful-select dropdown-primary" searchable="Search Client.." required="true">
                                 <c:forEach var="c" items="${clients}">
                                     <c:if test="${visitForm.client.name eq c.name}">
                                         <option value="${c.id}" selected>${c.name}</option>
                                 </c:if>
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>
                              </form:select>
                               <p class="help-block">
                                    <form:errors path="client" class="alert-danger"/>
                               </p>
                         </div>
                         <div class="md-form">  
                             <form:input type="date" path="dateVisit"  class="form-control"/>
                            
                              <p class="help-block">
                                    <form:errors path="dateOfVisit" class="alert-danger"/>
                               </p>
                         </div>
                         
             
             
             
            
           
           
 

            <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Save</form:button>
            </div>
        </form:form>
    </div>
</div>
        
    </div>--%>
   
</div>
</div>
 </div>
