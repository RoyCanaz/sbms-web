
  <ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">Add Brand</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/brand/list">List</a></li>
    </ol>
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Edit Brand</h3>
    <div class="card-body mx-1">
       
       <form:form modelAttribute="brand" action="${contextPath}/admin/brand/add" method="POST">  
                                    <form:hidden path="id"/>
                                    <form:hidden path="version"/>  
                                    <form:hidden path="dateCreated"/>
                                    <form:hidden path="createdBy"/>
                                  <center><h5><a  href="" class="btn btn-primary btn-sm btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#modalBrandForm">Add Categories</a></h5></center>
                                  
                                   <div class="row">
                                          
                                       
                                           <c:forEach var="cat" items="${brand.categories}" varStatus="loop" >
                                               
                                             
                                                <div class="col-md-3">
                                                   <div class="md-form ">
                                                    <div class="form-check">
                                                        
                                                       <input type="checkbox" id="cat${loop.count}" class="form-check-input" name="categories" value="${cat.id}" checked="true">
                                                                      <label class="form-check-label" for="cat${loop.count}">${cat.name}</label>
                                                                 
                                      
                                                    </div>
                                                   </div>
                                                </div>
                                               
                                           </c:forEach>
                                             
                                    
                                        </div>
                                  
                                <div class="md-form form-sm">
                                    <i class="fa fa-windows prefix"></i>
                                    <form:input path="name" id="name" class="form-control  form-control-sm"/>
                                    <label>Brand</label>
                                    <p class="help-block">
                                      <form:errors path="name" class="alert-danger"/>
                                    </p>
                                </div>


                                <div class="md-form form-sm">
                                    <i class="fa fa-pencil prefix"></i>
                             
                                        <form:input path="description" id="description" class="form-control form-control-sm"/>
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
                                                                      
                                                                      
                                                                      <div class="modal fade" id="modalBrandForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog cascading-modal" role="document">
                        <!--Content-->
                        <div class="modal-content" style="width:160%;">

                            <!--Header-->
                            <div class="modal-header light-blue darken-3 white-text">
                                <h4 class=""><i class="fa fa-pencil"></i>Add Brand Categories</h4>
                                <button type="button" class="close waves-effect waves-light" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                            </div>
                            <!--Body-->
                            <div class="modal-body mb-0">
                              <form:form modelAttribute="brand" action="${contextPath}/admin/brand/add-brand-categories" method="POST">  
                             
                                <center><h5>Select Categories</h5></center>
                                 <div class="row">
                                          
                                     <form:hidden path="id"/>
                                           <c:forEach var="cate" items="${categories}" varStatus="lup" >
                                               
                                               
                                                <div class="col-md-4">
                                                   <div class="md-form ">
                                                    <div class="form-check">
                                                        <input type="checkbox" id="cate${lup.count}" class="form-check-input" name="categories" value="${cate.id}">
                                                      <label class="form-check-label" for="cate${lup.count}">${cate.name}</label>
                                                    </div>
                                                   </div>
                                                </div>
                                               
                                           </c:forEach>
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

                      



