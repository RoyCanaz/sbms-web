<%-- 
    Document   : emails
    Created on : Aug 7, 2018, 11:57:55 PM
    Author     : kanaz
--%>

<ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item active">Global Email</li>
        
 </ol>
        
        <ul class="nav nav-tabs nav-justified blue" role="tablist">
                <li class="nav-item">
                    <a class="nav-link  ${activeGbListEmail}" data-toggle="tab" href="#panel6" role="tab"><i class="fa fa-list"></i>&nbsp;List</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link ${activeGbAddEmail} " data-toggle="tab" href="#panel5" role="tab"><i class="fa fa-envelope-square"></i>&nbsp; Add </a>
                </li>
    
         </ul>
        
        <div class="tab-content">
            
                    <div class="tab-pane fade in show ${activeGbListEmail}" id="panel6" role="tabpanel">
                       <!--Start Global Email Accounts--> 
                        <div class="card">
                                    <div class="card-body">
                                   <table id="listTable" class="table table-bordered text-center">
                                       <thead>
                                       <tr>
                                           <th>#</th>
                                           <th>Host</th>
                                           <th>Port</th> 
                                           <th>Email</th>
                                           <th>Check Default</th>
                                           <th>Action</th>

                                       </tr>
                                       </thead>
                                   <tbody>
                                       <c:forEach var="c" items="${globalEmails}" varStatus="loop" >
                                       <tr>
                                           <th>${loop.count}</th>
                                           <td>${c.host}</td>
                                           <td>${c.port}</td>
                                           <td>${c.email}</td>
                                           <td>
                                               <%--       <c:if test="${!c.active}">
                                               <div class="btn-group btn-group-toggle" role="group" aria-label="Basic example">
                                                   <a class="btn btn-sm btn-danger disabled" href="<c:url value="/admin/email/disable/${c.id}"/>" >Disabled</a> 
                                                   <a class="btn btn-sm btn-success" href="<c:url value="/admin/email/enable/${c.id}"/>" >Enable</a> 
                                               </div>
                                                </c:if>
                                            <c:if test="${c.active}">
                                              <a class="btn btn-sm btn-danger" href="<c:url value="/admin/email/disable/${c.id}"/>" >Disable</a> 
                                              <a class="btn btn-sm btn-success disabled" href="<c:url value="/admin/email/enable/${c.id}"/>" >Enabled</a> 
                                               </c:if>--%>
                                               
                                                 <div class="form-check text-center">
                    
                                                            <c:if test="${c.active}">
                                                                <input type="checkbox" value="${c.id}" id="gb-email${loop.count}" class="gb-email form-check-input" checked="true">
                                                            </c:if>
                                                            <c:if test="${!c.active}">
                                                                <input type="checkbox" value="${c.id}" id="gb-email${loop.count}" class="gb-email form-check-input">
                                                             </c:if>   
                                                            <label for="gb-email${loop.count}"  class="form-check-label"></label>
                
            
                                                </div>
                                           </td>
                                           <td>
                                               <a href="<c:url value="/admin/gb/email/edit/${c.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                                                <a onclick="return confirm('Delete?')" href="<c:url value="/admin/gb/email/delete/${c.id}"/>" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-minus-square red-text fa-1x"></i></a>
                                           </td>

                                       </tr>
                                       </c:forEach>
                                   </tbody>
                                   </table>
                                    </div>
                       </div>
                         <!--End Global Email Accounts--> 
                        
                    </div>
                    <div class="tab-pane fade ${activeGbAddEmail}" id="panel5" role="tabpanel">     
                                    <div class="card">
                                        <div class="card-body">
                                                      <form:form modelAttribute="item" action="${contextPath}/admin/gb/email" method="POST">   
                                                                                                                        <c:if test="${not empty item.id}">
                                                                                                                                <form:hidden path="id"/>
                                                                                                                                <form:hidden path="version"/>  
                                                                                                                                <form:hidden path="createdBy"/>
                                                                                                                        </c:if>
                                                                                                                         <div class="md-form">
                                                                                                                            <i class="fa fa-user-circle-o prefix grey-text"></i>
                                                                                                                            <form:input path="host" class="form-control validate"/>
                                                                                                                            <label for="host" class="font-weight-light">Host</label>
                                                                                                                            <p class="help-block">
                                                                                                                             <form:errors path="host" class="alert-danger"/>
                                                                                                                            </p>
                                                                                                                        </div>
                                                                                                                          <div class="md-form">
                                                                                                                            <i class="fa fa-user-circle-o prefix grey-text"></i>
                                                                                                                            <form:input path="port" class="form-control validate"/>
                                                                                                                            <label for="port" class="font-weight-light">Port</label>
                                                                                                                            <p class="help-block">
                                                                                                                             <form:errors path="port" class="alert-danger"/>
                                                                                                                            </p>
                                                                                                                        </div>
                                                                                                                                
                                                                                                                                  <div class="md-form">
                                                                                                                                            <i class="fa fa-user-circle-o prefix grey-text"></i>
                                                                                                                                            <form:input path="accName" class="form-control validate"/>
                                                                                                                                            <label for="accName" class="font-weight-light">Account Name</label>
                                                                                                                                            <p class="help-block">
                                                                                                                                             <form:errors path="accName" class="alert-danger"/>
                                                                                                                                            </p>
                                                                                                                                        </div>

                                                                                                                        <div class="md-form">
                                                                                                                            <i class="fa fa-user-circle-o prefix grey-text"></i>
                                                                                                                            <form:input type="email" path="email" id="email" class="form-control validate"/>
                                                                                                                            <label for="email" class="font-weight-light">Email</label>
                                                                                                                            <p class="help-block">
                                                                                                                             <form:errors path="email" class="alert-danger"/>
                                                                                                                            </p>
                                                                                                                        </div>

                                                                                                                          <div class="md-form">
                                                                                                                            <i class="fa fa-lock prefix grey-text"></i>
                                                                                                                            <form:password id="password" path="password" class="form-control"/>
                                                                                                                            <label for="password" class="font-weight-light">Password</label>
                                                                                                                            <p class="help-block" >
                                                                                                                            <form:errors path="password" class="alert-danger"/>
                                                                                                                            </p>
                                                                                                                        </div>
                                                                                                                            <div class="md-form">
                                                                                                                            <i class="fa fa-lock prefix grey-text"></i>
                                                                                                                            <form:password id="confirmPassword" path="confirmPassword" class="form-control"/>
                                                                                                                            <label for="confirmPassword" class="font-weight-light">Confirm Password</label>
                                                                                                                            <p class="help-block">
                                                                                                                            <form:errors path="confirmPassword" class="alert-danger"/>
                                                                                                                            </p>
                                                                                                                        </div>

                                                                                                                            <div class="text-center py-4 mt-3">
                                                                                                                            <form:button class="btn btn-blue">Save</form:button>
                                                                                                                        </div>
        </form:form>
                                        </div>
                                    </div>
                    </div>
                 
        </div>