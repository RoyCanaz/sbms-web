<ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item active">Add</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/list-users">List</a></li>
        
    </ol>
        
<div class="card">
    <h3 class="h4 text-center py-4 blue">CREATE/EDIT ADMINSTRATORS</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/admin/gb/addUsers/form" method="POST">   
            <c:if test="${not empty userId}">
             <form:hidden path="id"/>
             <form:hidden path="version"/>           
             <form:hidden path="createdBy"/>
             <form:hidden path="company"/>
            </c:if>
            <div class="md-form">
                <i class="fa fa-user prefix grey-text"></i>
                <form:input path="firstName" id="firstName" class="form-control"/>
                <label for="firstName" class="font-weight-light">First Name</label>
                <p class="help-block">
                <form:errors path="firstName" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-user prefix grey-text"></i>
                <form:input path="lastName" id="lastName" class="form-control"/>
                <label for="lastName" class="font-weight-light">Last Name</label>
                 <p class="help-block">
                 <form:errors path="lastName" class="alert-danger"/>
                 </p>
            </div>
            <div class="md-form">
                <i class="fa fa-envelope prefix grey-text"></i>
                <form:input path="userName" id="userName" class="form-control"/>
                <label for="userName" class="font-weight-light">Email</label>
                <p class="help-block" >
                <form:errors path="userName" class="alert-danger"/>
                </p>
            </div>
               <form:select path="company" class="mdb-select colorful-select dropdown-primary" searchable="Search here..">
                    <form:option value="" label="Select Company" selected="true" disabled="true"/>
                     <form:options items="${companies}" itemLabel="companyName" itemValue="id"/>                                
               </form:select>
              
                <c:choose>
                                <c:when test="${not empty userId}">
                                    
                                </c:when>
                                 <c:otherwise>
                                     <!--   <div class="md-form ">
                                        <i class="fa fa-key prefix grey-text"></i>
                                        <label class="font-weight-light">Roles</label>-->
                                      <%--  <div class="form-check" style="margin-left: 100px">
                                         <form:checkboxes class="test" path="userRoles" items="${userRole}"  itemLabel="printName" itemValue="id" />
                                        </div>--%>
                                       
                                       <div class="row">
                                           <div class="col-md-2">
                                                <div class="md-form ">
                                              <i class="fa fa-key fa-2x prefix grey-text"></i>   
                                               <label class="font-weight-light">Roles</label>
                                                </div>
                                           </div>
                                           
                                           <c:forEach var="role" items="${userRole}" varStatus="loop" >
                                               
                                             
                                                <div class="col-md-3">
                                                   <div class="md-form ">
                                                    <div class="form-check">
                                                       <%--  <form:checkbox id="userRole${loop.count}" class="form-check-input" path="userRoles" value="${role.id}"/>--%>
                                                        <input type="checkbox" id="userRole${loop.count}" class="form-check-input" name="userRoles" value="${role.id}">
                                                      <label class="form-check-label" for="userRole${loop.count}">${role.printName}</label>
                                                    </div>
                                                   </div>
                                                </div>
                                               
                                           </c:forEach>
                                        </div>           
                                         <p class="help-block" style="margin-top: 10px">
                                            <form:errors path="userRoles" class="alert-danger "/>
                                        </p>
                                       
                                       
                                  <!--  </div>   -->
                  </c:otherwise>
                    
                </c:choose>
                                  <hr>
            <!-- Material input password -->
            <div id="pwd"  class="md-form">
                <i class="fa fa-lock prefix grey-text"></i>
                <form:password id="password" path="password" class="form-control"/>
                <label for="password" class="font-weight-light">Password</label>
                <p class="help-block" >
                <form:errors path="password" class="alert-danger"/>
                </p>
            </div>
                <div id="cpwd" class="md-form">
                <i class="fa fa-lock prefix grey-text"></i>
                <form:password id="confirmPassword" path="confirmPassword" class="form-control"/>
                <label for="confirmPassword" class="font-weight-light">Confirm Password</label>
                <p class="help-block">
                <form:errors path="confirmPassword" class="alert-danger"/>
                </p>
            </div>
                 <div class="md-form">
                <div class="form-check text-center">
                    <input type="checkbox" id="changePassword" class="form-check-input">
                   <label class="form-check-label" for="changePassword">Grant/Change Password</label>
                   <form:errors path="confirmPassword" class="alert-danger"/>
                </div>
                 </div>
            <div class="text-center py-4 mt-3">
                <form:button id="save-cat" class="btn btn-blue">Save</form:button>
            </div>
           
        </form:form>
            
    </div>
</div>

<%--
<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Change Password</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
            <form:form modelAttribute="item" action="${contextPath}/admin/gb/addUsers/form" method="POST">  
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <i class="fa fa-envelope prefix grey-text"></i>
                    <input type="password" id="defaultForm-email" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="defaultForm-email">New Password</label>
                </div>

                <div class="md-form mb-4">
                    <i class="fa fa-lock prefix grey-text"></i>
                    <input type="password" id="defaultForm-pass" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="defaultForm-pass">Re-Enter Password</label>
                </div>

            </div>
            <div class="modal-footer d-flex justify-content-center">
                <button class="btn btn-default">Save</button>
            </div>
             </form:form>
        </div>
    </div>
</div>
 <div class="text-center">
               <a href="" class="btn btn-blue" data-toggle="modal" data-target="#modalLoginForm">Change Password</a>
             </div>               
--%>
