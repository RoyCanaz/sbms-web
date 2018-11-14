
  <ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item active">Add Role</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/list-roles">List</a></li>
    </ol>
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add Role</h3>
    <div class="card-body mx-1">
       
        <form:form modelAttribute="item" action="${contextPath}/admin/gb/addRole/form" method="POST">         
            <c:set var="id" value="${item.id}"/>           
            <div class="md-form">
                <i class="fa fa-user-circle-o prefix grey-text"></i>
                <form:input path="name" id="name" class="form-control"/>
                <label for="name" class="font-weight-light">Role Name</label>
                <p class="help-block">
                 <form:errors path="name" class="alert-danger"/>
                </p>
            </div>
             <div class="md-form">
                <i class="fa fa-pencil-square prefix grey-text"></i>
                <form:input path="description" id="description" class="form-control"/>
                <label for="description" class="font-weight-light">Description</label>
                 <p class="help-block">
                 <form:errors path="description" class="alert-danger"/>
                 </p>
            </div>            
                <div class="text-center py-4 mt-3">
                <form:button class="btn btn-blue">Add Role</form:button>
            </div>
        </form:form>
    </div>
</div>
</div>

                      



