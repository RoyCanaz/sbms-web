 
<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>       
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">List</li>
</ol>

<div class="card">
     <div class="card-body">

    <table id="listTable" class="table table-bordered text-center" >
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Description</th>
            <th>Date Created</th>
            <th>Action</th>
            <th>                
               <a href="" class="btn btn-primary btn-sm btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#modalContactForm">
                   New category<i class="fa fa-plus ml-1"></i>
               </a>
                
            </th>          
        </tr>
        </thead>
        
    <tbody id="catDetails">
        <c:forEach var="cat" items="${category}" varStatus="loop" >
        <tr id="${cat.id}">
            <th>${loop.count}</th>
            <td>${cat.name}</td>
            <td>${cat.description}</td>
            <td>${cat.dateCreated}</td>
            <td>
                 <a href="<c:url value="/admin/category/edit?id=${cat.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                 <a onclick="return confirm('Delete Category?')" href="<c:url value="/admin/category/delete?id=${cat.id}"/>" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash red-text fa-1x"></i></a>
            </td>
            <td></td>
        </tr>
         </c:forEach>
    </tbody>
    </table>
                        </div>
 </div>
<div class="modal fade" id="modalContactForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog cascading-modal" role="document">
                        <!--Content-->
                        <div class="modal-content">

                            <!--Header-->
                            <div class="modal-header light-blue darken-3 white-text">
                                <h4 class=""><i class="fa fa-pencil"></i>Add Category</h4>
                                <button type="button" class="close waves-effect waves-light" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                            </div>
                            <!--Body-->
                            <div class="modal-body mb-0">
                              <form:form modelAttribute="categories" action="${contextPath}/ajax/category/add" method="POST">  
                                <div class="md-form form-sm">
                                    <i class="fa fa-list prefix"></i>
                                    <form:input path="name" id="name" class="form-control  form-control-sm"/>
                                    <label for="name">Category</label>
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
                                     <form:button id="save-cat"  class="btn btn-info mb-2">Save<i class="fa fa-send ml-1"></i></form:button>
                                    
                                </div>
                              </form:form>
                            </div>
                        </div>
                        <!--/.Content-->
                    </div>
                </div>

