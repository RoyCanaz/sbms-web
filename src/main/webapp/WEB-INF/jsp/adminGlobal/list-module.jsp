<ol class="breadcrumb white">
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/dashboard">Admin Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/gb/addModule">Add Module</a></li>
        <li class="breadcrumb-item active">Modules</li>
    </ol>

<div class="card">
     <div class="card-body">
    <table id="listTable" class="table table-bordered text-center">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Description</th> 
                                
        </tr>
        </thead>
    <tbody>
        <c:forEach var="m" items="${modules}" varStatus="loop" >
        <tr>
            <th>${loop.count}</th>
            <td>${m.name}</td>
            <td>${m.description}</td>
            
        </tr>
        </c:forEach>
    </tbody>
    </table>
     </div>
</div>
