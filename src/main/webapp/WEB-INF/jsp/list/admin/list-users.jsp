<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>   
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/addUsers">Add</a></li>
        <li class="breadcrumb-item active">List</li>
</ol>

<div class="card">
     <div class="card-body">
<table id="listTable" class="table table-bordered text-center">

    <!--Table head-->
    <thead class="blue">
        <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
            <th>Date Created</th>
            <th></th>
            <th></th>
            
            
        </tr>
    </thead>
    <!--Table head-->

    <!--Table body-->
    <tbody>
        
        <c:forEach var="item" items="${items}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${item.firstName}</td>
            <td>${item.lastName}</td>
            <td>${item.userName}</td>
            <td>${item.dateCreated}</td>
            <td><a href="<c:url value="/admin/addUsers/form?id=${item.id}"/>"><i class="fa fa-pencil-square fa-1x"></i></a></td>
            <td><a class="text-danger" onclick="return confirm('Deactivate Account?')" href="<c:url value="/admin/account/deactivate/${item.id}"/> ">Deactivate</a></td>
        </tr>
         </c:forEach>
       
    </tbody>
    <!--Table body-->

</table>
     </div>
</div>

