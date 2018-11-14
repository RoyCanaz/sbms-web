<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/inventory/">Inventory</a></li>
        <li class="breadcrumb-item active">Serial Numbers</li>
    </ol> 


<section>
   
                <div class="card">
                    <div class="card-body">

                        <div class="table-responsive">
                            <!-- Item list -->
                            <table id="listTable" class="table table-responsive w-auto">
                                <thead>
                                    <tr><th>#</th>
                                        <th>Product Code</th>
                                        <th>Serial Number</th>

                                        
                                      
                                    </tr>
                                </thead>
                                
                               
                                   
                                
                                            <tbody>
                                                <c:forEach var="s" items="${stocks}" varStatus="loop">
                                                <tr>
                                                    <td>${loop.count}</td>
                                                    <td>
                                                        <h6>Product Code : ${s.product.productCode}</h6>
                                                    </td>
                                                    <td>
                                                        <form class="form-inline" id="update-sn${loop.count}" action="${contextPath}/rest/client/stock/updateSerialNumber" method="POST">
                                                              <input type="hidden" id="uuid${loop.count}" name="uuid${loop.count}" value="${s.id}">
                                                            <div class="form-group mx-sm-3 mb-2">
                                                                <input type="text" id="serial${loop.count}" name="serial${loop.count}" class="form-control" placeholder="Serial Number"/>
                                                              
                                                            </div>
                                                         
                                                             <button type="submit" id="btn-submit-sn${loop.count}" class="btn btn-sm btn-blue mb-2"><i class="fa fa-barcode"></i>&nbsp;Add</button>
                                                         
                                                      </form>

                                                    </td>
                                                
                                                   

                                                </tr>
                                                  </c:forEach>
                                            </tbody>
                                
                              
                            </table>
                            <!-- /.Item list -->
                        </div>


                    </div>
                </div>

            </section>


   </form>                           


















                          

