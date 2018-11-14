
  <ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/inventory/">Inventory</a></li>
        <li class="breadcrumb-item active">Add Quantity</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/inventory/list?id=${cat.id}&name=${cat.name}">List</a></li>
    </ol>
    <div class="mx-auto" style="width: 500px;">
      <div class="card">
     <div class="card-body">

    <h3 class="h4 text-center py-4 blue">Add Stock</h3>
    <div class="card-body mx-1">
       
       <form:form modelAttribute="product" action="${contextPath}/inventory/add/form" method="POST">  
                               <form:hidden path="id"/>
                                   
                                <div class="md-form form-sm">
                                    <i class="fa fa-pencil prefix"></i>
                                    <form:input path="quantityDelivered" class="form-control  form-control-sm"/>
                                    <label>Quantity Delivered</label>
                                    <p class="help-block">
                                      <form:errors path="quantityDelivered" class="alert-danger"/>
                                    </p>
                                </div>


                                <div class="md-form form-sm">
                                    <i class="fa fa-pencil prefix"></i>
                             
                                        <form:input path="sellingPrice" class="form-control form-control-sm"/>
                                    <label for="sellingPrice">Selling Price</label>
                                     <p class="help-block">
                                      <form:errors path="sellingPrice" class="alert-danger"/>
                                    </p>
                                </div>
                                <div class="md-form form-sm">
                                    <i class="fa fa-pencil prefix"></i>
                             
                                  <form:input path="reOrderLevel" class="form-control form-control-sm"/>
                                    <label for="reOrderLevel">Re-Order Level</label>
                                     <p class="help-block">
                                    <form:errors path="reOrderLevel" class="alert-danger"/>
                                    </p>
                                </div>
                                <div class="md-form form-sm">
                                    <i class="fa fa-pencil prefix"></i>
                             
                                        <form:input path="reOrderQuantity" class="form-control form-control-sm"/>
                                    <label for="reOrderQuantity">Re-Order Quantity</label>
                                     <p class="help-block">
                                      <form:errors path="reOrderQuantity" class="alert-danger"/>
                                    </p>
                                </div>

                                <div class="text-center mt-1-half">
                                   <form:button  class="btn btn-info mb-2">Save<i class="fa fa-check ml-1"></i></form:button>
                                </div>
                              </form:form>
    </div>
</div>
</div>
    </div>
                      




