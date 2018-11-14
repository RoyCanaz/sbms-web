
  <ol class="breadcrumb white">
      <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">Add/Edit Rate</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/currency/list">List</a></li>
    </ol>
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add/Edit Rate</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/admin/currency/add" method="POST">  
            <c:if test="${not empty item.id}">
             <form:hidden path="id"/>
             <form:hidden path="version"/>  
             <%-- <form:hidden path="dateCreated"/>--%>
             <form:hidden path="createdBy"/>
            </c:if>
            <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="name" id="name" class="form-control"/>
                <label for="name" class="font-weight-light">Name</label>
                <p class="help-block">
                 <form:errors path="name" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-pencil-square prefix grey-text"></i>
                <form:input path="rate" id="rate" class="form-control"/>
                <label for="rate" class="font-weight-light">Rate</label>
                 <p class="help-block">
                 <form:errors path="rate" class="alert-danger"/>
                 </p>
            </div>            
                <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Save</form:button>
            </div>
        </form:form>
    </div>
</div>
</div>

                      



