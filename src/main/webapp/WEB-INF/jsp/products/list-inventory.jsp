 <ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/inventory/">Inventory</a></li>
        <li class="breadcrumb-item active">List</li>
    </ol>

<div class="card card-cascade narrower z-depth-1">

                    <!--Card image-->
                    <div class="view gradient-card-header blue-gradient narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">

                       

                        <a href="" class="white-text mx-3">Inventory List</a>

                        <div>
                            
                           
                            <a href="${contextPath}/inventory/add/${inventoryCat.id}/${inventoryCat.name}/0" class="btn btn-outline-white btn-rounded btn-sm px-2 waves-effect waves-light"><i class="fa fa-plus-square mt-0"></i></a>
                        </div>

                    </div>
                    <!--/Card image-->

                    <div class="px-4">

                        <div class="table-responsive">
                            <!--Table-->
                            <table id="listTable" class="table table-hover table-responsive-md w-auto text-center">

                                <!--Table head-->
                                <thead>
                                    <tr>
                                        
                                        
                                        <th class="th-lg"><a>Brand<i class="fa fa-sort ml-1"></i></a></th>
                                        <th class="th-lg"><a href="">Model<i class="fa fa-sort ml-1"></i></a></th>
                                        <th class="th-lg"><a href="">Description<i class="fa fa-sort ml-1"></i></a></th>
                                        <th class="th-lg"><a href="">Price<i class="fa fa-sort ml-1"></i></a></th>
                                        <th class="th-lg"><a href="">Delivered<i class="fa fa-sort ml-1"></i></a></th>
                                        <th class="th-lg"><a href="">Stock<i class="fa fa-sort ml-1"></i></a></th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <!--Table head-->

                                <!--Table body-->
                                <tbody>
                                    <c:forEach var="inv" items="${inventory}" varStatus="loop" >
                                    <tr>
                                       
                                        
                                   
                                   
                                        <td>${inv.brand}</td>
                                        <td>${inv.model}</td>
                                        <td class="overflow" data-toggle="tooltip" data-placement="top" title="${inv.description}"><span>${inv.description}</span></td>
                                        <td><i class="fa fa-dollar"></i>&nbsp; ${inv.unitPrice}</td>
                                        <td>${inv.quantityDelivered}</td>
                                        <td>${inv.availableStock}</td>
                                        <td>
                                            <a href="<c:url value="/inventory/add/${inv.category.id}/${inv.category.name}/${inv.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x blue-ic"></i></a>
                                            <a href="<c:url value="/inventory/add?id=${inv.id}"/>" data-toggle="tooltip" data-placement="top" title="Add"><i class="fa fa-plus-square fa-1x green-ic"></i></a>
                                            <a onclick="return confirm('Delete Product?')" href="<c:url value="/inventory/delete/${inv.id}"/>" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash fa-1x red-ic"></i></a>
                                        </td>
                                    </tr>
                                   </c:forEach>
                                   
                                </tbody>
                             
                            </table>
     
                        </div>

                        <hr class="my-0">


                    </div>
                </div>