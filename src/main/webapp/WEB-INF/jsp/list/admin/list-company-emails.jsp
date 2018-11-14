<ol class="breadcrumb white">
    <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/email/default/form">Add</a></li>
        <li class="breadcrumb-item active">List</li>
    </ol>

<div class="card">
     <div class="card-body">
    <table id="listTable" class="table table-bordered text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Host</th>
            <th>Port</th> 
            <th>Email</th>
            <th>Enable/Disable</th>
            <th>Action</th>
                                
        </tr>
        </thead>
    <tbody>
        <c:forEach var="c" items="${companyEmails}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${c.host}</td>
            <td>${c.port}</td>
            <td>${c.email}</td>
            <%-- <td>
               <c:if test="${!c.active}">
                    <fieldset class="form-group">
                        <input type="checkbox" id="inactiveEmail" value="${c.id}">
                        <label for="inactiveEmail">Enable</label>
                    </fieldset>
                 </c:if>
             <c:if test="${c.active}">
                   <fieldset class="form-group">
                       <input type="checkbox" id="activeEmail" value="${c.id}" checked="">
                        <label for="activeEmail">Disable</label>
                    </fieldset>
                </c:if>
           </td>--%>
            <td>
                <c:if test="${!c.active}">
                <div class="btn-group btn-group-toggle" role="group" aria-label="Basic example">
                    <a class="btn btn-sm btn-danger disabled" href="<c:url value="/admin/email/disable/${c.id}"/>" >Disabled</a> 
                    <a class="btn btn-sm btn-success" href="<c:url value="/admin/email/enable/${c.id}"/>" >Enable</a> 
                </div>
                 </c:if>
             <c:if test="${c.active}">
               <a class="btn btn-sm btn-danger" href="<c:url value="/admin/email/disable/${c.id}"/>" >Disable</a> 
               <a class="btn btn-sm btn-success disabled" href="<c:url value="/admin/email/enable/${c.id}"/>" >Enabled</a> 
                </c:if>
            </td>
            <td>
                <a href="<c:url value="/admin/email/default/form?id=${c.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                 <a onclick="return confirm('Delete?')" href="<c:url value="/admin/email/delete-email/${c.id}"/>" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-minus-square red-text fa-1x"></i></a>
            </td>
            
        </tr>
        </c:forEach>
    </tbody>
    </table>
     </div>
</div>


