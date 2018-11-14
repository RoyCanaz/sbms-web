 <ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>        
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/invoice/quote">Client List</a></li> 
        <li class="breadcrumb-item active">${clientName} - Quotes</li>       
    </ol>

<div class="card">
     <div class="card-body">
                                     <!-- setting.checkbox.js -->
                                     <c:choose>
                                        <c:when test="">
                                             <div class="form-check">
                                                <input type="checkbox" class="form-check-input" id="view-all-quotes" checked>
                                                <label class="form-check-label" for="view-all-quotes">View All</label>
                                              </div>
                                        </c:when>
                                        <c:otherwise>
                                             <div class="form-check">
                                                <input type="checkbox" class="form-check-input" id="view-all-quotes">
                                                <label class="form-check-label" for="view-all-quotes">View All</label>
                                              </div>
                                        </c:otherwise>                   
                                    </c:choose>
         <table id="listTable" class="table table-striped table-bordered text-center">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Date Created</th>
                    <th>Quote Number</th>
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
                    <td><i class="fa fa-dollar mr-1 blue-ic"></i>${q.totalIncVat}</td>
                    <td>
                               <c:choose>
                                        <c:when test="${q.lastSendMailStatus==1}">
                                            <p class="text-success">Send</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="text-danger">Not Send <a href="<c:url value="/quote/edit?quoteId=${q.id}&clientId=${q.client.id}&contactId=${q.contact.id}&bankId=${q.bankingDetail.id}"/>"><i class="fa fa-refresh mr-2"></i></a></p>
                                        </c:otherwise>                   
                                </c:choose>
                    </td>                
                    <td>${q.lastDateOfSent}</td>
                    <td>
                       <a class="waves-effect" href="<c:url value="/quote/view?quoteId=${q.id}"/>">View<i class="fa fa-eye mr-1"></i></a>
                       <a class="waves-effect" href="<c:url value="/quote/edit?quoteId=${q.id}&clientId=${q.client.id}&contactId=${q.contact.id}&bankId=${q.bankingDetail.id}"/>">Edit<i class="fa fa-pencil mr-1"></i></a>
                       <a class="waves-effect" href="<c:url value="/invoice/view?quoteId=${q.id}"/>">Invoice<i class="fa fa-spinner mr-1"></i></a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
         </table>
     </div>
 </div>


