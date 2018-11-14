<%-- 
    Document   : new-invoice
    Created on : 18-Jul-2018, 16:04:08
    Author     : user
--%>
<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>
         
        <li class="breadcrumb-item active">New Invoice</li>

    </ol>


<form action="${contextPath}/create_invoice/new" method="GET">
    
<section class="mb-4">

                <div class="card card-cascade">
                    <div class="view gradient-card-header blue">
                     <div class="card-header-title mb-3">
                         <h3 class="header text-center">New Invoice</h3>
                     </div>
                    </div>
                    <div class="card-body">
                       
                        <div class="row">
                        <div class="col-md-2"></div>
                           <div class="col-md-8">
                               <sec:authorize access="hasAnyAuthority('GLOBAL','SUPER_ADMIN', 'ADMIN')">
                                <h6 class="blue-text font-bold"><u>Rate</u></h6>
                               <select name="curRate" class="mdb-select colorful-select dropdown-primary" searchable="Search here.." required="true">
                                <option value="" disabled selected>Select Rate</option>
                                 <c:forEach var="c" items="${currency}">
                                     <c:choose>
                                         <c:when test="${c.active}">
                                             <option value="${c.id}" selected="">${c.name} - ${c.rate}</option>
                                          </c:when>
                                        <c:otherwise> 
                                             <option value="${c.id}">${c.name} - ${c.rate}</option>
                                       </c:otherwise>
                               </c:choose>
                                </c:forEach>
                              </select>
                                <h6 class="blue-text font-bold"><u>Bank</u></h6>
                                <select name="bankName" class="mdb-select con colorful-select dropdown-primary" searchable="Search here.." required="true">
                                     <option value="" disabled selected>Select Bank</option>
                                      <c:forEach var="bank" items="${banks}">
                                                <c:choose>
                                                    <c:when test="${bank.active}">
                                                        <option value="${bank.id}" selected="">${bank.bank}</option>
                                                     </c:when>
                                                    <c:otherwise> 
                                                        <option value="${bank.id}">${bank.bank}</option>
                                                    </c:otherwise>
                                               </c:choose>
                                     </c:forEach>
                               </select>
                                </sec:authorize>
                                <h6 class="blue-text font-bold"><u>Client</u></h6>
                               <select name="name" id="client-id" class="mdb-select colorful-select dropdown-primary" searchable="Search here.." required="true">
                                <option value="" disabled selected>Select Client</option>
                                 <c:forEach var="client" items="${clients}">
                                    <option value="${client.id}">${client.name}</option>
                                </c:forEach>
                              </select>
                             
                          <!--     <select id="client-contacts" class="mdb-select colorful-select dropdown-primary" searchable="Search here..">
                                          <option value="" disabled selected>Sent To :</option>
                                         
                                    </select>-->
                                 
                          <h6 class="blue-text font-bold"><u>Contact</u></h6>  
                             <select name="contact" id="client-id" class="mdb-select colorful-select dropdown-primary" searchable="Search here.." required="true">
                                <option value="" disabled selected>Sent To :</option>
                                 <c:forEach var="contact" items="${contacts}">
                                    <option value="${contact.id}">${contact.firstName} ${contact.lastName} [${contact.email}]</option>
                                </c:forEach>
                              </select>
                           
                            
                               
                            
                            </div>
                           <div class="col-md-2"></div>
                        </div>
                    </div>
                </div>
 </section>
 
<section class="mb-4">

                <div class="card">
                    
                    <div class="card-body">                       
                           <button type="submit" class="btn btn-lg btn-block btn-primary"><i class="fa fa-check"></i> Create</button>                     
                    </div>
                </div>
 </section>
</form>

