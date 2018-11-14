 <ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>        
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/client/list">Client List</a></li> 
        <li class="breadcrumb-item active">Quotes</li>       
    </ol>

<div class="card">
     <div class="card-body">
         <table id="listTable" class="table table-striped table-bordered text-center">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Date Created</th>
                    <th>Quote Number</th>
                    <th>Client</th>
                    <th>Amount</th>
                    <th>Status</th>                  
                    <th>Last Date Of Sent</th> 
                    <th>Action</th>                   
                </tr>
                </thead>
            <tbody>
                <c:forEach var="q" items="${quotes}" varStatus="loop" >
                <tr>
                    <th>${loop.count}</th>
                    <td>${q.dateCreated}</td>
                    <td>${q.quoteUuid}</td>
                    <td>${q.client.name}</td>
                    <td><i class="fa fa-dollar mr-1 blue-ic"></i>${q.totalIncVat}</td>
                    <td>
                               <c:choose>
                                        <c:when test="${q.lastSendMailStatus==1}">
                                            <p class="text-success">Sent</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="text-danger">Not Sent <a href="<c:url value="/quote/edit?quoteId=${q.id}&clientId=${q.client.id}&contactId=${q.contact.id}&bankId=${q.bankingDetail.id}"/>"><i class="fa fa-refresh mr-2"></i></a></p>
                                        </c:otherwise>                   
                                </c:choose>
                    </td>                
                    <td>${q.lastDateOfSent}</td>
                    <td>
                       <a class="waves-effect" href="<c:url value="/quote/view?quoteId=${q.id}"/>">View<i class="fa fa-eye mr-1"></i></a>
                       <a class="waves-effect" href="<c:url value="/quote/edit-quote/${q.id}"/>">Edit<i class="fa fa-pencil mr-1"></i></a>
                       <a class="waves-effect" href="<c:url value="/invoice/view/${q.id}"/>">Invoice<i class="fa fa-spinner mr-1"></i></a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
         </table>
     </div>
 </div>



