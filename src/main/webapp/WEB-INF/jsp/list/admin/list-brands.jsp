 <ol class="breadcrumb white">
     <li class="breadcrumb-item"><strong>${company.companyName}</strong></li>    
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">List</li>
    </ol>

<div class="card">
     <div class="card-body">
    <table id="listTable" class="table table-bordered text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Description</th>
         <!--   <th>Category</th>-->
            <th>Date Created</th>
            <th>Action</th>
            <th>                
               <a href="" class="btn btn-primary btn-sm btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#modalBrandForm">
                   New Brand<i class="fa fa-plus ml-1"></i>
               </a>
                
            </th>          
        </tr>
        </thead>
    <tbody>
        <c:forEach var="bran" items="${brands}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${bran.name}</td>
            <td>${bran.description}</td>
            <td>${bran.dateCreated}</td>
            <td>
                 <a href="<c:url value="/admin/brand/edit?id=${bran.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                 <a onclick="return confirm('Delete Brand?')" href="<c:url value="/admin/brand/delete?id=${bran.id}"/>" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash red-text fa-1x"></i></a>
            </td>
            
            <td></td>
        </tr>
        </c:forEach>
    </tbody>
    </table>
     </div>
</div>

<div class="modal fade" id="modalBrandForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog cascading-modal" role="document">
                        <!--Content-->
                        <div class="modal-content" style="width:160%;">

                            <!--Header-->
                            <div class="modal-header light-blue darken-3 white-text">
                                <h4 class=""><i class="fa fa-pencil"></i>Add Brand</h4>
                                <button type="button" class="close waves-effect waves-light" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                            </div>
                            <!--Body-->
                            <div class="modal-body mb-0">
                              <form:form modelAttribute="brand" action="${contextPath}/admin/brand/add" method="POST">  
                                <%-- <div class="md-form">
                                     <form:select path="category" class="mdb-select">
                                                <form:option value="" label="Select Category"/>
                                              <c:forEach var="cat" items="${category}" varStatus="loop" >
                                                <form:option value="${cat.id}" label="${cat.name}"/>
                                              </c:forEach>
                                                
                                       </form:select>
                                 </div>--%>
                                <center><h5>Select Categories</h5></center>
                                 <div class="row">
                                          
                                           
                                           <c:forEach var="cat" items="${category}" varStatus="loop" >
                                               
                                             
                                                <div class="col-md-4">
                                                   <div class="md-form ">
                                                    <div class="form-check">
                                                       <%--  <form:checkbox id="userRole${loop.count}" class="form-check-input" path="userRoles" value="${role.id}"/>--%>
                                                        <input type="checkbox" id="cat${loop.count}" class="form-check-input" name="categories" value="${cat.id}">
                                                      <label class="form-check-label" for="cat${loop.count}">${cat.name}</label>
                                                    </div>
                                                   </div>
                                                </div>
                                               
                                           </c:forEach>
                                        </div>
                                <div class="md-form form-sm">
                                    <i class="fa fa-windows prefix"></i>
                                    <form:input path="name" id="name" class="form-control  form-control-sm"/>
                                    <label>Brand Name</label>
                                    <p class="help-block">
                                      <form:errors path="name" class="alert-danger"/>
                                    </p>
                                </div>


                                <div class="md-form form-sm">
                                    <i class="fa fa-pencil prefix"></i>
                              <!--      <textarea type="text" id="form8" class="md-textarea form-control form-control-sm" rows="3"></textarea>-->
                                        <form:input path="description" id="description" class="form-control form-control-sm"/>
                                    <label for="description">Description</label>
                                     <p class="help-block">
                                      <form:errors path="description" class="alert-danger"/>
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

