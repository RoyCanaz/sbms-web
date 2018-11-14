
<ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/dashboard">Admin Dashboard</a></li>
        
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/addAdmin">Add</a></li>
        <li class="breadcrumb-item active">List</li>
        
    </ol>
<div class="card">
     <div class="card-body">
<table id="listTable" class="table table-bordered text-center">

    <!--Table head-->
    <thead class="blue">
        <tr>
            <th>#</th>           
            <th>Company</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>           
            <th>Roles</th>
            <th>Created By</th>
            <th>Action</th>            
        </tr>
    </thead>
    
    <tbody>       
        <c:forEach var="u" items="${users}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${u.company.companyName}</td>
            <td>${u.firstName}</td>
            <td>${u.lastName}</td>
            <td>${u.userName}</td>
            <td>${u.userRoles}</td>
            <td>${u.createdBy.userRoles}</td>
            <td>
                <a href="<c:url value="/admin/gb/addAdmin?id=${u.id}"/>" data-toggle="tooltip" data-placement="top" title="Edit"><i class="fa fa-pencil-square fa-1x" ></i></a>
                <a onclick="return confirm('Deactivate Account?')" href="<c:url value="/admin/gb/users/deactivate?id=${u.id}"/>" data-toggle="tooltip" data-placement="top" title="Deactivate User"><i class="fa fa-minus-square red-text fa-1x"></i></a>
            </td>          
        </tr>
         </c:forEach>       
    </tbody>

</table>
     </div>
</div>


