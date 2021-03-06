<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>       
        <li class="breadcrumb-item active">Client Quotes</li>       
</ol>

<div class="card">
     <div class="card-body">
         <table id="listTable" class="table table-striped table-bordered text-center">
                <thead>
                <tr>
                    <th>#</th>
               
                    <th>Name</th>
                    <th>Description</th>
                   
                    <th>Phone</th> 
                    <th></th>
                    
                   
                    
                </tr>
                </thead>
            <tbody>
                <c:forEach var="client" items="${clients}" varStatus="loop" >
                <tr>
                    <th>${loop.count}</th>
                 
                    <td>${client.name}</td>
                    <td>${client.description}</td>
                    
                    <td>${client.phone}</td>
                    <td><a class="btn btn-sm blue-gradient btn-rounded" href="<c:url value="/invoice/quote?id=${client.id}"/>">Client Quotes</a></td>
                    
                </tr>
                </c:forEach>
            </tbody>
         </table>
     </div>
 </div>

