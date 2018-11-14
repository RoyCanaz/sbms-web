<ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/bankingDetail/add">Add Bank</a></li>
        <li class="breadcrumb-item active">Bank List</li>

    </ol>

<div class="card">
     <div class="card-body">
    <table id="listTable" class="table table-bordered text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Acc Name</th>          
            <th>Bank Name</th>
            <th>Acc Number</th>
            <th>Branch</th> 
            <th>Check Default</th>
            <th>Action</th>
        </tr>
        </thead>
    <tbody>
        <c:forEach var="b" items="${banks}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${b.name}</td>
            <td>${b.bank}</td>
            
            <td>${b.accountNumber}</td>
            <td>${b.branch}</td>
           <td>
            <%--   <c:if test="${!b.active}">
                <div class="btn-group btn-group-toggle" role="group" aria-label="Basic example">
                    <a class="btn btn-sm btn-danger disabled" href="<c:url value="/admin/bankingDetail/disable/${b.id}"/>" >Disabled</a> 
                    <a class="btn btn-sm btn-success" href="<c:url value="/admin/bankingDetail/enable/${b.id}"/>" >Enable</a> 
                </div>
                 </c:if>
             <c:if test="${b.active}">
               <a class="btn btn-sm btn-danger" href="<c:url value="/admin/bankingDetail/disable/${b.id}"/>" >Disable</a> 
               <a class="btn btn-sm btn-success disabled" href="<c:url value="/admin/bankingDetail/enable/${b.id}"/>" >Enabled</a> 
                </c:if>--%>
            
              
                <div class="form-check text-center">
                    
                    <c:if test="${b.active}">
                        <input type="checkbox" value="${b.id}" id="bank-id${loop.count}" class="bank-id form-check-input" checked="true">
                    </c:if>
                    <c:if test="${!b.active}">
                        <input type="checkbox" value="${b.id}" id="bank-id${loop.count}" class="bank-id form-check-input">
                     </c:if>   
                    <label for="bank-id${loop.count}"  class="form-check-label"></label>
                
            
                 </div>
           </td>
            <td>
                <a href="<c:url value="/admin/bankingDetail/add/${b.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                 
            </td>
        </tr>
        </c:forEach>
    </tbody>
    </table>
     </div>
</div>


