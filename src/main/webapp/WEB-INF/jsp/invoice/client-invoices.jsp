
<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>          
        <li class="breadcrumb-item active">Client Invoices</li>       
</ol>
<div class="card">
     <div class="card-body">
         <table id="listTable" class="table table-striped table-bordered text-center">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Date Created</th>
                    <th>Amount</th>
                    <th>Number Of Items</th>                                                   
                    <th></th>                  
                    <th></th>                   
                </tr>
                </thead>
            <tbody>
                <c:forEach var="invoice" items="${invoices}">
                <tr>
                    <td>${invoice.invoiceUuid}</td>
                    <td>${invoice.dateCreated}</td>                   
                    <td><i class="fa fa-dollar"></i>${invoice.qoute.totalIncVat}</td>
                    <td>${invoice.qoute.numOfItems}</td>
                    
                                  
                   
                    <td><a href="<c:url value="/invoice/viewInvoice?invoiceId=${invoice.id}"/>"><i class="fa fa-download mr-1 primary-ic"></i></a></td>
                  
                    <td><a href="<c:url value="/invoice/view?quoteId=${invoice.id}"/>"><i class="fa fa-pencil mr-1 secondary-ic"></i></a></td>
                </tr>
                </c:forEach>
            </tbody>
         </table>
     </div>
 </div>



