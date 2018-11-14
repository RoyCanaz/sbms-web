
  <ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Admin Dashboard</a></li>  
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/category/list">List</a></li>
        <li class="breadcrumb-item active">Add Category</li>
    </ol>
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add Category</h3>
    <div class="card-body mx-1">
      
         <form:form modelAttribute="category" action="${contextPath}/admin/category/edit" method="POST">  
                                    <form:hidden path="id"/>
                                <div class="md-form form-sm">
                                    <i class="fa fa-list prefix"></i>
                                    <form:input path="name" class="form-control  form-control-sm"/>
                                    <label for="name">Category</label>
                                    <p class="help-block">
                                      <form:errors path="name" class="alert-danger"/>
                                    </p>
                                </div>


                                <div class="md-form form-sm">
                                    <i class="fa fa-pencil prefix"></i>
                              <!--      <textarea type="text" id="form8" class="md-textarea form-control form-control-sm" rows="3"></textarea>-->
                                  <form:input path="description" class="form-control form-control-sm"/>
                                    <label for="description">Description</label>
                                     <p class="help-block">
                                      <form:errors path="description" class="alert-danger"/>
                                    </p>
                                </div>

                                <div class="text-center mt-1-half">
                                     <form:button class="btn btn-info mb-2">Save<i class="fa fa-check ml-1"></i></form:button>
                                    
                                </div>
                              </form:form>
      
    </div>
</div>
</div>

                      



