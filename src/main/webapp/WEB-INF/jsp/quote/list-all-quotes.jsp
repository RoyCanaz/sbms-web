<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>        
        <li class="breadcrumb-item active">Select Quote To Generate Invoice</li>       
    </ol>
<table id="listTable" class="table table-borderless text-center">
    <thead>
        <tr>
            <th>#</th>
            <th>Date Created</th>
            <th>Status</th>
            <th>Quote</th>
            <th>Client Name</th>
            <th>Number Of Items</th>
            <th>Amount</th>
            <th>Action</th>
        </tr>
    </thead>
    
    <tbody>
        <c:forEach var="quote" items="${quotes}" varStatus="loop" >
        <tr>
            <th scope="row">${loop.count}</th>
            <td>${quote.dateCreated}</td>
            <td>
                                   <c:choose>
                                        <c:when test="${quote.lastSendMailStatus==1}">
                                            <p class="text-success">Sent</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="text-danger">Not Sent <a href="<c:url value="/quote/edit?quoteId=${quote.id}&clientId=${quote.client.id}&contactId=${quote.contact.id}&bankId=${quote.bankingDetail.id}"/>"><i class="fa fa-refresh mr-2"></i></a></p>
                                        </c:otherwise>                   
                                </c:choose>
            </td>
            <td>${quote.quoteUuid}</td>
            <td>${quote.client.name}</td>
            <td>${quote.numOfItems}</td>
            <td><i class="fa fa-dollar"></i>&nbsp;${quote.totalIncVat}</td>
            
            <td>
                <a href="<c:url value="/quote/view?quoteId=${quote.id}"/>"><i class="fa fa-download mr-2"></i></a>
            <a href="<c:url value="/quote/edit?quoteId=${quote.id}&clientId=${quote.client.id}&contactId=${quote.contact.id}&bankId=${quote.bankingDetail.id}"/>"><i class="fa fa-pencil mr-2"></i></a>
            <a class="btn btn-sm btn-rounded btn-success waves-effect" href="<c:url value="/invoice/view?quoteId=${quote.id}"/>">Invoice<i class="fa fa-spinner mr-1"></i></a>
            </td>
        </tr>
       </c:forEach>
    </tbody>
    <!--Table body-->

</table>
<!--Table-->
