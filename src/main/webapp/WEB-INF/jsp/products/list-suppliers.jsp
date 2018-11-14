<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/supplier/add">Add</a></li>
        <li class="breadcrumb-item active">List</li>

    </ol>
<div class="card">
     <div class="card-body">
    <table id="listTable" class="table table-bordered text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>          
            <th>Address</th>
            <th>Email</th>
            <th>Mobile Phone</th>
            <th>Office Phone</th>
            <th>Website</th>
            <th>Categories</th>  
            <th>Action</th>
        </tr>
        </thead>
    <tbody>
        <c:forEach var="s" items="${suppliers}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${s.name}</td>
            <td>${s.address}</td>
            
            <td>${s.email}</td>
            <td>${s.mobilePhone}</td>
            <td>${s.officePhone}</td>
            <td>${s.website}</td>
            <td>${s.categories}</td>
            <td>
                <a href="<c:url value="/admin/supplier/add/${s.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x"></i></a>
                 
            </td>
        </tr>
        </c:forEach>
    </tbody>
    </table>
     </div>
</div>

