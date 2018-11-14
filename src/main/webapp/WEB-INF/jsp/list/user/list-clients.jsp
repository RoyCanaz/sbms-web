
<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>  
        <li class="breadcrumb-item active">Clients</li>       
</ol>

<div class="card">
     <div class="card-body">
         
                
                                     <!-- setting.checkbox.js -->
                                     <c:choose>
                                        <c:when test="${all}">
                                             <div class="form-check">
                                                <input type="checkbox" class="form-check-input" id="view-all-clients" checked>
                                                <label class="form-check-label" for="view-all-clients">View All</label>
                                              </div>
                                        </c:when>
                                        <c:otherwise>
                                             <div class="form-check">
                                                <input type="checkbox" class="form-check-input" id="view-all-clients">
                                                <label class="form-check-label" for="view-all-clients">View All</label>
                                              </div>
                                          </c:otherwise>
                    
                                    </c:choose>
               
              
         <hr>
         <table id="listTable" class="table table-striped table-bordered text-center table-responsive-lg w-auto table-responsive">
                <thead>
                <tr>
                    <th>#</th>
                   
                    <th>Name</th>
                  
                    <th>Email</th>
                    <th>Phone</th>
                    <th>More</th>
                    <th>Action</th>
                  
                    
                </tr>
                </thead>
            <tbody>
                <c:forEach var="client" items="${clients}" varStatus="loop" >
                <tr>
                    <th>${loop.count}</th>
                  
                    <td>${client.name}</td>
                   
                    <td>${client.email}</td>
                    <td>${client.phone}</td>
                    <td><a class="text-info" href="${contextPath}/client/details/${client.id}"><u>More Details</u></a></td>
                    <td>
                        <a href="<c:url value="/client/addClient/form?id=${client.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit Client"><i class="fa fa-pencil blue-text fa-1x"></i></a> 
                        <a href="<c:url value="/client/contact/addContact?id=${client.id}"/>" data-toggle="tooltip" data-placement="top" title="Contacts"><i class="fa fa-address-card blue-text fa-1x"></i></a> 
                    <a href="" id="getProcs" data-toggle="tooltip" data-placement="top" title="Procurement" 
                           data-toggle="modal"
                           data-target="#procurement"
                           data-cname="${client.name}"
                           data-link="${contextPath}/ajax/procurementDocs/${client.id}">
                            <i class="fa fa-product-hunt grey-text fa-1x"></i>
                        </a>
                    
                        <a href="${contextPath}/client/inventory/addClientinventory?id=${client.id}" data-toggle="tooltip" data-placement="top" title="Inventory"><i class="fa fa-shopping-basket blue-text fa-1x"></i></a>
                    
                        <a href="${contextPath}/visitplan/client-plans/${client.id}" data-toggle="tooltip" data-placement="top" title="Visit Plans"><i class="fa fa-street-view blue-text fa-1x"></i></a>
                        <sec:authorize access="hasAnyAuthority('GLOBAL', 'SUPER_ADMIN', 'ADMIN')">
                            <a onclick="return confirm('Delete Client?')" href="${contextPath}/client/delete/${client.id}" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash red-text fa-1x"></i></a>
                        </sec:authorize>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
         </table>
     </div>
 </div>

<div class="modal fade right" id="procurement" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                    aria-hidden="true" data-backdrop="false">
                    <div class="modal-dialog modal-full-height modal-right modal-notify modal-primary" role="document">
                       
                        <div class="modal-content">
                           
                            <div class="modal-header">
                                <p class="heading lead">Supporting Documents</p>

                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true" class="white-text">&times;</span>
                                                        </button>
                            </div>

                            <!--Body-->
                            <div class="modal-body">
                                
                                <div id="companyname" class="text-center"></div>
                                <ul class="docs list-group z-depth-0">
                                    
                                </ul>
                            </div>

                            <!--Footer-->
                            <div class="modal-footer justify-content-center">
                                <center><a type="button" class="btn btn-blue">Edit <i class="fa fa-pencil-square-o ml-1"></i></a></center>
                                
                            </div>
                        </div>
                        <!--/.Content-->
                    </div>
                </div>
