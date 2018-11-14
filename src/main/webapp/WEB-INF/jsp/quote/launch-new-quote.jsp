

<table id="listTable" class="table table-borderless">

    <!--Table head-->
    <thead>
        <tr>
            <th>#</th>
            <th>Date Created</th>
            <th>Status</th>
            <th>Quote</th>
            <th>Client Name</th>
            <th>Number Of Items</th>
            <th>Amount</th>
            <th></th>
            <th></th>
            <th></th>
            
            
        </tr>
    </thead>
    
    <tbody>
        <c:forEach var="quote" items="${allquotes}" varStatus="loop" >
        <tr>
            <th scope="row">${loop.count}</th>
            <td>${quote.dateCreated}</td>
            <td></td>
            <td>${quote.quoteUuid}</td>
            <td>${quote.client.name}</td>
            <td>${quote.numOfItems}</td>
            <td><i class="fa fa-dollar"></i>&nbsp;${quote.totalIncVat}</td>
            <td><a href="<c:url value="/quote/view?quoteId=${quote.id}"/>"><i class="fa fa-download mr-2"></i></a></td>
            <td><a href="<c:url value="/quote/edit-quote/${quote.id}"/>"><i class="fa fa-pencil mr-2"></i></a></td>
           
        </tr>
       </c:forEach>
    </tbody>
    <!--Table body-->

</table>


<!--Modal: Login with Avatar Form-->
<div class="modal fade" id="modalNewQuote" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog cascading-modal modal-avatar modal-sm" role="document">
        <!--Content-->
        <div class="modal-content">

            <!--Header-->
            <div class="modal-header">
                <img src="${contextPath}/resources/img/quotepic.png" alt="avatar" class="rounded-circle img-responsive">
            </div>
            <!--Body-->
            <div class="modal-body text-center mb-1">

                <h5 class="mt-1 mb-2">Total : <i class="fa fa-dollar mr-1"></i> ${newquote.totalIncVat}</h5>
                <c:if test="${newquote.lastSendMailStatus==0}">
                       <div class="alert alert-danger" role="alert">
                        Quote Not Sent !!!,<br>
                      </div>                                 
                </c:if>
                 <c:if test="${newquote.lastSendMailStatus==1}">
                       <div class="alert alert-success" role="alert">
                        Quote Sent Successfully !!!
                      </div>                                 
                </c:if>
              

                <div class="text-center mt-4">
                 <%--   <a class="btn btn-sm  blue-gradient btn-rounded" href="${contextPath}/quote/send?quoteId=${newquote.id}"><i class="fa fa-send mr-1"></i>Send</a>--%>
                    <a class="btn btn-sm blue-gradient btn-rounded" href="${contextPath}/quote/view?quoteId=${newquote.id}">View</a>
                </div>
            </div>

        </div>
        <!--/.Content-->
    </div>
</div>

                
