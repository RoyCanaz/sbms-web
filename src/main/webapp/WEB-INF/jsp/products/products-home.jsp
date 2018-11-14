
<ol class="breadcrumb white">
    <li class="breadcrumb-item"><strong>${company.companyName}</strong></li>        
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/sales">Sales</a></li>
        <li class="breadcrumb-item active">Inventory</li>
    </ol>

<section class="mt-lg-5">

                <div class="row">
                 <c:forEach var="cat" items="${category}" varStatus="loop" >
                    <div class="col-xl-4 col-md-6 mb-4">

                        <div class="card card-cascade cascading-admin-card">

                            <div class="admin-up">
                                <a href="<c:url value="/inventory/add/${cat.id}/${cat.name}/${0}"/>"><i class="fa fa-plus primary-color"></i></a>
                                <div class="data">
                                    <h3 data-toggle="tooltip" data-placement="top" title="${cat.name}"><a href="<c:url value="/inventory/list?id=${cat.id}&name=${cat.name}"/>">${cat.name}</a></h3>
                                    <h6 class="font-weight-bold dark-grey-text">${cat.getStockSize()}</h6>
                                </div>
                            </div>

                            <div class="card-body">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-striped progress-bar-animated bg-primary" role="progressbar" style="width: ${cat.getStockPercentage()}%" aria-valuenow="${cat.getStockPercentage()}" aria-valuemin="0" aria-valuemax="100"></div>
                                    <%--  <c:choose>
                                        <c:when test="${cat.getStockSize()>5}">
                                         <div class="progress-bar progress-bar-striped progress-bar-animated bg-primary" role="progressbar" style="width: ${cat.getStockPercentage()}%" aria-valuenow="${cat.getStockPercentage()}" aria-valuemin="0" aria-valuemax="100"></div>
                                        </c:when>
                                       
                                        <c:otherwise>
                                          <div class="progress-bar progress-bar-striped progress-bar-animated bg-danger" role="progressbar" style="width: ${cat.getStockPercentage()}%" aria-valuenow="${cat.getStockPercentage()}" aria-valuemin="0" aria-valuemax="100"></div>
                                        </c:otherwise>
                                      </c:choose>--%>
                                    
                                </div>
                              
                                <p class="card-text bg">Stock level (${cat.getStockPercentage()}%)</p>
                            </div>

                        </div>
  
                    </div>
                </c:forEach>
                </div>
               

            </section>
