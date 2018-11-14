<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">Add</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/users/list">List</a></li>

    </ol>
        
<div class="card">
    <h3 class="h4 text-center py-4 blue">CREATE/EDIT USERS</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/admin/addUsers/form" method="POST">     
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
              <c:choose>
                                <c:when test="${not empty userId}">
                                    
                                </c:when>
                                 <c:otherwise>
                       <%--    <div class="md-form ">
                                                      <i class="fa fa-key prefix grey-text"></i>
                                                      <label class="font-weight-light">Roles</label>
                                                      <div class="form-check" style="margin-left: 100px">
                                                       <form:checkboxes class="test" path="userRoles" items="${userRoles}"  itemLabel="printName" itemValue="id" />
                                                      </div>
                                                      <p class="help-block" style="margin-top: 10px">
                                                           <form:errors path="userRoles" class="alert-danger "/>
                                                      </p>
                                 </div>  --%>
                             <div class="row">
                                           <div class="col-md-2">
                                                <div class="md-form ">
                                              <i class="fa fa-key fa-2x prefix grey-text"></i>   
                                               <label class="font-weight-light">Roles</label>
                                                </div>
                                           </div>
                                           
                                           <c:forEach var="ro" items="${userRoles}" varStatus="l" >
                                               
                                             
                                                <div class="col-md-2">
                                                   <div class="md-form ">
                                                    <div class="form-check">
                                                       <%--  <form:checkbox id="userRole${loop.count}" class="form-check-input" path="userRoles" value="${role.id}"/>--%>
                                                        <input type="checkbox" id="userRole${l.count}" class="form-check-input" name="userRoles" value="${ro.id}">
                                                      <label class="form-check-label" for="userRole${l.count}">${ro.printName}</label>
                                                    </div>
                                                   </div>
                                                </div>
                                               
                                           </c:forEach>
                                        </div>           
                                         <p class="help-block" style="margin-top: 10px">
                                            <form:errors path="userRoles" class="alert-danger "/>
                                        </p>
                             <hr>
                            
                            
                           
                                 </c:otherwise>
                    
                </c:choose>
                                       
            <!-- Material input password -->
            <div id="pwd" class="md-form">
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
                    <p class="help-block">
                <form:errors path="confirmPassword" class="alert-danger"/>
                </p>
                </div>
                </div>  
            <div class="text-center py-4 mt-3">
                <form:button id="save-cat" class="btn btn-blue">Save</form:button>
            </div>
        </form:form>
    </div>
</div>
                      
