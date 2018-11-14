<ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/addCompany">Add Company</a></li>
        <li class="breadcrumb-item active">Companies List</li>
    </ol>

<div class="card">
     <div class="card-body">
    <table id="listTable" class="table table-bordered text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Description</th> 
            <th>Address</th>
            <th>Email</th>
            <th>Mobile Phone</th>
            <th>Office Phone</th>
            <th>Website</th>
            <th>Modules</th>  
            <th>Action</th>
        </tr>
        </thead>
    <tbody>
        <c:forEach var="c" items="${companies}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${c.companyName}</td>
            <td>${c.description}</td>
            <td>${c.address}</td>
            <td>${c.email}</td>
            <td>${c.mobilePhone}</td>
            <td>${c.officePhone}</td>
            <td>${c.website}</td>
            <td>${c.modules}</td>
            <td>
                <a href="<c:url value="/admin/gb/addCompany?id=${c.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                 <a onclick="return confirm('Deactivate Company?')" href="<c:url value="/admin/gb/company/deactivate?id=${c.id}"/>" data-toggle="tooltip" data-placement="top" title="Deactivate Company"><i class="fa fa-minus-square red-text fa-1x"></i></a>
            </td>
        </tr>
        </c:forEach>
    </tbody>
    </table>
     </div>
</div>
