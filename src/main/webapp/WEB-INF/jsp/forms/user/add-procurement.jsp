<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>  
        <li class="breadcrumb-item active">Procurement</li>       
</ol>
<div class="container-fluid" style="margin-top: 20px; margin-bottom: 20px">
        
<!--<div class="card">
    <h3 class="h4 text-center py-4 blue">Add Client Requirements.</h3>
    <div class="card-body mx-1">
         -->
            <table class="table table-bordered table-responsive-lg">
                <!--To Save Using Ajax-->
                    <thead>
                        <tr>
                            <th>#</th>
                            
                            <th>Brand</th>
                            <th>Specify Model etc</th>
                            <th>Quantity</th>
                            <th>Expected Date of Delivery</th>
                            <th>Add</th>
                            <th>Remove</th>
                        </tr>
                    </thead>

                   <tbody>
                     <c:forEach var="brand" items="${brands}" varStatus="loop" >
                       <form:form modelAttribute="item" action="${contextPath}/client/addProcurement/form" method="POST">
                           <c:set var="id" value="${item.id}"/>
                            <tr>
                            <th>${loop.count}</th>
                            
                            <td><h6>${brand.name}</h6><br><hr><p>${brand.description}</p></td>
                            <td>
                                <div class="md-form">
                                    <i class="fa fa-pencil-square prefix grey-text"></i>
                                    <form:input path="description" id="description${loop.count}" class="form-control form-control-sm"/>
                                    <label for="description${loop.count}" class="font-weight-light">Description</label>
                                     <p class="help-block">
                                     <form:errors path="description" class="alert-danger"/>
                                     </p>
                                </div>
                            </td>
                            <td>
                                 <div class="md-form">
                                    <i class="fa fa-th-list prefix grey-text"></i>
                                    <form:input path="quantity" id="quantity${loop.count}" class="form-control form-control-sm"/>
                                    <label for="quantity${loop.count}" class="font-weight-light">Quantity</label>
                                    <p class="help-block" >
                                    <form:errors path="quantity" class="alert-danger"/>
                                    </p>
                                </div>
                            </td>
                            <td>
                                   <div class="md-form">
                                        <i class="fa fa-clock-o fa-1x prefix grey-text"></i>
                                        
                                        <form:input path="laterDate" id="laterDate${loop.count}" class="form-control form-control-sm"/>
                                        <label for="laterDate${loop.count}" class="font-weight-light">Expected Date of Delivery</label>
                                         <p class="help-block">
                                         <form:errors path="laterDate" class="alert-danger"/>
                                         </p>
                                    </div>
                            </td>
                            <td>
                                 <div class="text-center py-4 mt-3">
                                    <form:button class="btn btn-sm btn-blue">Add</form:button>
                                </div>
                            </td>
                            <td>
                                 <div class="text-center py-4 mt-3">
                                    <form:button class="btn btn-sm btn-danger">Remove</form:button>
                                </div>
                            </td>
                        </tr>
                      </form:form>
                    </c:forEach>
                    </tbody>
             </table>      
    <!--       
    </div>
</div>-->
</div>
                      

