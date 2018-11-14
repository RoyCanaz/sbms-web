
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active">Edit</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/email/list">List</a></li>
    </ol>
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Edit</h3>
    <div class="card-body mx-1">
       
        
          
            <form:form modelAttribute="emailAccount" action="${contextPath}/admin/email/add" method="POST">  
                  <c:if test="${not empty typeId}">
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>  
                    <form:hidden path="company"/>
                    <form:hidden path="createdBy"/>
                 </c:if>
                          <%--   <div class="md-form ">
                                   <i class="fa fa-th-list prefix grey-text"></i>
                                   <label class="font-weight-light">Email Types</label><br>
                             
                                   
                                   <fieldset class="form-group">
                                       <form:checkboxes class="test" path="emailTypes" items="${emailTypes}"  itemLabel="printName" itemValue="id"/><br>
                                    </fieldset>
                                   <p class="help-block" style="margin-top: 10px">
                                     <form:errors path="emailTypes" class="alert-danger "/>
                                   </p>
                              </div> 
                           --%>      
                               
                                            <div class=" form-check-inline">
                                                       <label class="font-weight-light">BCC/CC :  </label>
                                                        <form:checkboxes path="emailTypes" items="${emailTypes}"
                                                                         element="div class='form-check' style='padding-left:20px;margin-right:30px'"
                                                                         itemLabel="printName" itemValue="id"/>
                                                    
                                                    <p class="help-block">
                                                      <form:errors path="emailTypes" class="alert-danger "/>
                                                    </p>
                                            </div>
                              
                           
                            
                           
                             <hr>
                                   
                                   
                                   
                                <div class="md-form form-sm">
                                    <i class="fa fa-envelope prefix"></i>
                                    <form:input path="email" type="email" id="email" class="form-control  form-control-sm" required="required"/>
                                    <label>Email</label>
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
</div>

                      





