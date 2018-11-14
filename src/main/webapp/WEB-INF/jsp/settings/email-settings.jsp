
          
    
 
            <div class="row">
                <div class="col-md-8">
                                 <ol class="breadcrumb white">
                                        <li class="breadcrumb-item">${company.companyName}</li>
                                            <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>

                                            <li class="breadcrumb-item active">List</li>
                                        </ol>
                </div>
                  <div class="col-md-4">
                       <a class="btn btn-rounded btn-email pull-right" href="${contextPath}/admin/email/default/list">DEFAULT EMAIL ACCOUNT</a>
                </div>
            </div>
     




<div class="card">
     <div class="card-body">
    <table id="listTable" class="table table-bordered text-center">
        <thead>
        <tr>
             <th>id</th>
            <th>#</th>
             <th>Account</th>
            <th>Type</th>
           
            <th></th>
            <th>                
               <a href="" class="btn btn-primary btn-sm btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#modalBrandForm">
                   <i class="fa fa-plus ml-1"></i>
               </a>
                
            </th>          
        </tr>
        </thead>
    <tbody>
        <c:forEach var="email" items="${emails}" varStatus="loop" >
        <tr>
            <td>${email.id}</td>
            <th>${loop.count}</th>
            <td>${email.email}</td>
                    <td>
                        <strong>
                            <c:forEach var="ty" items="${email.emailTypes}" varStatus="l" >
                                [${ty.printName}]
                            </c:forEach>
                       </strong>
                    </td>
            
            <td>
               <%--   <c:if test="${!email.active}">
                    <fieldset class="form-group">
                        <input type="checkbox" onchange="return confirm('${email.id}')" value="${email.id}">
                        <label for="enableAcc">Enable</label>
                    </fieldset>
                 </c:if>
             <c:if test="${email.active}">
                   <fieldset class="form-group">
                       <input type="checkbox" onchange="return confirm('${email.id}')" value="${email.id}" checked="">
                        <label for="disableAcc">Disable</label>
                    </fieldset>
                </c:if>--%>
               
                  <c:if test="${email.active}">
                        <input type="checkbox" value="${email.id}" id="email-id${loop.count}" class="email-id form-check-input" checked="true">
                    </c:if>
                    <c:if test="${!email.active}">
                        <input type="checkbox" value="${email.id}" id="email-id${loop.count}" class="email-id form-check-input">
                     </c:if>   
                    <label for="email-id${loop.count}"  class="form-check-label"></label>
               
            </td>
            <td>
                 <a href="<c:url value="/admin/email/edit?id=${email.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                 <a onclick="return confirm('Delete?')" href="<c:url value="/admin/email/delete-acc/${email.id}"/>" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-minus-square red-text fa-1x"></i></a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
    </table>
     </div>
</div>

<div class="modal fade" id="modalBrandForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog cascading-modal" role="document">
                        <!--Content-->
                        <div class="modal-content">

                            <!--Header-->
                            <div class="modal-header light-blue darken-3 white-text">
                                <h4 class=""><i class="fa fa-envelope"></i>Add Email Account</h4>
                                <button type="button" class="close waves-effect waves-light" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                            </div>
                            <!--Body-->
                            <div class="modal-body mb-0">
                              <form:form modelAttribute="emailAccount" action="${contextPath}/admin/email/add" method="POST">  
                             <%-- <div class="md-form ">
                                   <i class="fa fa-key prefix grey-text"></i>
                                   <label class="font-weight-light">Email Types</label><br>
                                   <div class="form-check" style="margin-left: 100px">
                                    <form:checkboxes class="test" path="emailTypes" items="${emailTypes}"  itemLabel="printName" itemValue="id"/>
                                   </div>
                                   <p class="help-block" style="margin-top: 10px">
                                  <form:errors path="emailTypes" class="alert-danger "/>
                                   </p>
                               </div> 
                               --%>
                               
                                <div class="row">
                                           <div class="col-md-4">
                                                <div class="md-form ">
                                              <i class="fa fa-send prefix grey-text"></i>   
                                               <label class="font-weight-light">BCC/CC</label>
                                                </div>
                                           </div>
                                           
                                           <c:forEach var="em" items="${emailTypes}" varStatus="l" >
                                               
                                             
                                                <div class="col-md-4">
                                                   <div class="md-form ">
                                                    <div class="form-check">
                                                       <%--  <form:checkbox id="userRole${loop.count}" class="form-check-input" path="userRoles" value="${role.id}"/>--%>
                                                        <input type="checkbox" id="type${l.count}" class="form-check-input" name="emailTypes" value="${em.id}">
                                                      <label class="form-check-label" for="type${l.count}">${em.printName}</label>
                                                    </div>
                                                   </div>
                                                </div>
                                               
                                           </c:forEach>
                                        </div>           
                                         <p class="help-block" style="margin-top: 10px">
                                            <form:errors path="emailTypes" class="alert-danger "/>
                                        </p>
                             <hr>
                                   
                                   
                                   
                                <div class="md-form form-sm">
                                    <i class="fa fa-envelope prefix"></i>
                                    <form:input path="email" type="email" id="email" class="form-control  form-control-sm" required="required"/>
                                    <br><label>Email</label>
                                    <p class="help-block">
                                      <form:errors path="email" class="alert-danger"/>
                                    </p>
                                </div>


                             

                                <div class="text-center mt-1-half">
                                    <button class="btn btn-info mb-2">Save<i class="fa fa-send ml-1"></i></button>
                                </div>
                              </form:form>
                            </div>
                        </div>
                        <!--/.Content-->
                    </div>
                </div>

        <script>
            function disableAcc(id){
            alert(id);
        }
            </script>