<section class="section">

               
<div class="row">
                    <div class="col-md-4">

                      <!--  <div class="card mb-4">
                            <div class="card-body">
                                <h4 class="h4-responsive text-center mb-3">
                                    Pending Call/Visit Plan
                                </h4>

                               
                            </div>
                        </div>-->
                      <sec:authorize access="hasAnyAuthority( 'ADMIN', 'USER')">
                        <div class="card mb-4">
                            <div class="card-header white-text primary-color">
                              Pending Call/Visit Plans 
                            </div>

                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table large-header">
                                        <thead>
                                            <tr>
                                                <th class="font-weight-bold dark-grey-text"><strong>Date Of Visit</strong></th>
                                                <th class="font-weight-bold dark-grey-text"><strong>Client Name</strong></th>
                                                <th class="font-weight-bold dark-grey-text"><strong>Remainaing</strong></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach var="v" items="${visitPlans}" varStatus="loop" >
                                            <tr>
                                                <td>${v.dateOfVisit}</td>
                                                <td>${v.client.name}</td>
                                                <td>
                                                    <c:if test="${v.daysRemaining<3 && v.daysRemaining>0}">
                                                        <span class="badge orange" data-toggle="tooltip" data-placement="top" title="${v.daysRemaining} days remaining">${v.daysRemaining}</span> days
                                                    </c:if>
                                                     <c:if test="${v.daysRemaining<1}">
                                                        <span class="badge red" data-toggle="tooltip" data-placement="top" title="${v.daysRemaining} days remaining">${v.daysRemaining}</span> days
                                                    </c:if>
                                                     <c:if test="${v.daysRemaining>3}">
                                                        <span class="badge green" data-toggle="tooltip" data-placement="top" title="${v.daysRemaining} days remaining">${v.daysRemaining}</span> days
                                                    </c:if>
                                                    
                                                </td>
                                                <td><a class="btn btn-sm btn-link btn-outline-green waves-effect" href="${contextPath}/visitplan/callvisit/${v.id}" data-toggle="tooltip" data-placement="top" title="Call/Visit"><i class="fa fa-street-view"></i></a></td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                                <a href="${contextPath}/visitplan/list" class="btn btn btn-flat grey lighten-3 btn-rounded waves-effect float-right font-weight-bold dark-grey-text">View All</a>

                            </div>

                        </div>
                      </sec:authorize>
                    </div>

                    <div class="col-md-8">
                        <sec:authorize access="hasAnyAuthority( 'ADMIN', 'USER')">
                        <div class="card mb-4">
                             <div class="card-header white-text primary-color">
                              For Today :
                             </div>
                            <div class="card-body">
                                <table class="table large-header">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th class="font-weight-bold dark-grey-text"><strong>Client</strong></th>
                                            <th class="font-weight-bold dark-grey-text"><strong>Status</strong></th>
                                            <th class="font-weight-bold dark-grey-text"><strong>Summary</strong></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         <c:forEach var="p" items="${plansToday}" varStatus="lop" >
                                            <tr>
                                                <td>${lop.count}</td>
                                                 <td>${p.client.name}</td>
                                                 <td>
                                                     <c:if test="${p.status eq '1'}">
                                                         <p class="text-success">Done</p> 
                                                     </c:if>
                                                      <c:if test="${p.status eq '0'}">
                                                         <p class="text-danger">Not Done</p> 
                                                     </c:if>
                                                   
                                                 </td>
                                                 <td>${p.visitResult}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                         </sec:authorize>

                      <!--  <div class="card mb-4">
                            <div class="card-body">
                                <table class="table large-header mb-1">
                                    <thead>
                                        <tr>
                                            <th class="font-weight-bold dark-grey-text"><strong>Browser</strong></th>
                                            <th class="font-weight-bold dark-grey-text"><strong>Visits</strong></th>
                                            <th class="font-weight-bold dark-grey-text"><strong>Pages</strong></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                           
                                        </tr>
                                       
                                    </tbody>
                                </table>

                              

                            </div>

                        </div>-->

                    </div>
                </div>

            </section>

