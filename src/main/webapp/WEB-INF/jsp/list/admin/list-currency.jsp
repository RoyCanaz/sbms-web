<ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/currency/add/form">Add/Edit Rate</a></li>
        <li class="breadcrumb-item active">Currencies</li>
    </ol>

<div class="card">
     <div class="card-body">
    <table id="listTable" class="table table-bordered text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Rate</th> 
            <th>Check Default</th>
            <th>Action</th>
                                
        </tr>
        </thead>
    <tbody>
        <c:forEach var="c" items="${currencies}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${c.name}</td>
            <td>${c.rate}</td>
            <td>
             <%--  <c:if test="${!c.active}">
                <div class="btn-group btn-group-toggle" role="group" aria-label="Basic example">
                    <a class="btn btn-sm btn-danger disabled" href="<c:url value="/admin/currency/disable?id=${c.id}"/>" >Disabled</a> 
                    <a class="btn btn-sm btn-success" href="<c:url value="/admin/currency/enable?id=${c.id}"/>" >Enable</a> 
                </div>
                 </c:if>
             <c:if test="${c.active}">
               <a class="btn btn-sm btn-danger" href="<c:url value="/admin/currency/disable?id=${c.id}"/>" >Disable</a> 
               <a class="btn btn-sm btn-success disabled" href="<c:url value="/admin/currency/enable?id=${c.id}"/>" >Enabled</a> 
                </c:if>--%>
             
              <div class="form-check text-center">                   
                    <c:if test="${c.active}">
                        <input type="checkbox" value="${c.id}" id="currency-id${loop.count}" class="currency-id form-check-input" checked="true">
                    </c:if>
                    <c:if test="${!c.active}">
                        <input type="checkbox" value="${c.id}" id="currency-id${loop.count}" class="currency-id form-check-input">
                     </c:if>   
                    <label for="currency-id${loop.count}"  class="form-check-label"></label>            
                 </div>
             
           </td>
            <td>
                <a href="<c:url value="/admin/currency/add/form?id=${c.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                 <a onclick="return confirm('Delete?')" href="<c:url value="/admin/currency/delete?id=${c.id}"/>" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-minus-square red-text fa-1x"></i></a>
            </td>
            
        </tr>
        </c:forEach>
    </tbody>
    </table>
     </div>
</div>

