<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>
         <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/quote/newQuote">Cancel</a></li>
        <li class="breadcrumb-item active">Edit</li>

    </ol>
<section class="mb-4">

                <div class="card">
                    <div class="card-body">

                        <div class="row">
                         
                         <div class="col-md-2">
                                <sec:authorize access="hasAnyAuthority('GLOBAL','SUPER_ADMIN', 'ADMIN')">
                                                    <h6 class="blue-text font-bold"><u>Currency</u></h6>
                                                <select name="currencyRate" id="currency-id-edit" class="mdb-select colorful-select dropdown-primary" searchable="Search here.." required="true">
                                                 <option value="" disabled selected>Select Currency</option>
                                                  <c:forEach var="c" items="${currencyList}">

                                                      <c:choose>
                                                           <c:when test="${c.id eq editCurrency.id}">
                                                              <option value="${c.id}" selected="">${c.name} - ${c.rate}</option>
                                                           </c:when>
                                                         <c:otherwise> 
                                                              <option value="${c.id}">${c.name} - ${c.rate}</option>
                                                        </c:otherwise>
                                                </c:choose>
                                                 </c:forEach>
                                               </select>
                                   </sec:authorize>
                            </div>  
                             <div class="col-md-2">
                                <sec:authorize access="hasAnyAuthority('GLOBAL','SUPER_ADMIN', 'ADMIN')">
                                      <h6 class="blue-text font-bold"><u>Bank</u></h6>
                                <select name="bankNameEdit" id="bankDetail-id-edit" class="mdb-select con colorful-select dropdown-primary" searchable="Search here.." required="true">
                                     <option value="" disabled selected>Select Bank</option>
                                      <c:forEach var="bank" items="${bankList}">
                                                <c:choose>
                                                    <c:when test="${bank.id eq editBank.id}">
                                                        <option value="${bank.id}" selected="">${bank.bank}</option>
                                                     </c:when>
                                                    <c:otherwise> 
                                                        <option value="${bank.id}">${bank.bank}</option>
                                                    </c:otherwise>
                                               </c:choose>
                                     </c:forEach>
                               </select>
                               </sec:authorize>
                            </div>
                              <div class="col-md-3">
                                    <h6 class="blue-text font-bold"><u>Client</u></h6>
                               <select name="name" id="client-id" class="mdb-select colorful-select dropdown-primary disabled" searchable="Search here.." required="true">
                                <option value="" disabled selected>Select Client</option>
                                 <c:forEach var="client" items="${clientList}">
                                      <c:choose>
                                                    <c:when test="${client.id eq editClient.id}">
                                                              <option value="${client.id}" selected="">${client.name}</option>
                                                     </c:when>
                                                    <c:otherwise> 
                                                            <option value="${client.id}">${client.name}</option>
                                                    </c:otherwise>
                                               </c:choose>                                                                      
                                </c:forEach>
                              </select>
                            </div>
                               <div class="col-md-5">
                                    <h6 class="blue-text font-bold"><u>Contact</u></h6>  
                             <select name="contact"  id="contact-id-edit" class="mdb-select colorful-select dropdown-primary" searchable="Search here.." required="true">
                                <option value="" disabled selected>Sent To :</option>
                                 <c:forEach var="contact" items="${contactList}">                                   
                                        <c:choose>
                                                    <c:when test="${ contact.id eq editContact.id}">
                                                              <option value="${contact.id}" selected="">${contact.firstName} ${contact.lastName} [${contact.email}]</option>
                                                     </c:when>
                                                    <c:otherwise> 
                                                             <option value="${contact.id}">${contact.firstName} ${contact.lastName} [${contact.email}]</option>
                                                    </c:otherwise>
                                               </c:choose>  
                                </c:forEach>
                              </select>
                           
                            </div>
                              
                        
                            
                         

                        </div>

                    </div>
                </div>

            </section>
                                                    
  <section class="mb-4">

                <div class="card">
                    <div class="card-body">

                        <div class="row">
                            
                             <div class="col-md-3">
                                
                                <p><small>Banking Details :</small></p>
                                 <p><strong>${editBank.bank}</strong></p>
                                <p>${editBank.name}</p>
                               
                                <p>${editBank.branch}</p>
                                <p>${editBank.accountNumber}</p>

                            </div>
                            
                         
                            <!--Grid column-->
                            <div class="col-md-3">
                               
                                <p><small>From:</small></p>
                                <p><strong>${company.companyName}</strong></p>
                                <p>${company.address}</p>
                                <p>${company.email}</p>
                                <p>${company.mobilePhone}</p>
                               
                                

                            </div>

                            <div class="col-md-3">
                               
                                <p><small>To:</small></p>
                                <p><strong>${editClient.name}</strong></p>
                                <p>${editClient.address}</p>
                                <p>${editClient.email}</p>

                            </div>
                                             
                             <div class="col-md-3">     
                                   
                                <p><small>Sending To :</small></p>
                                <c:if test="${not empty editContact}">  
                                <p><strong>${editContact.firstName} ${editContact.lastName}</strong></p>
                                <p>${editContact.department}</p>
                                <p>${editContact.jobPosition}</p>
                                <p class="green-text">${editContact.email} <i class="fa fa-check green-ic"></i></p>
                                 </c:if>
                            </div>

                        </div>

                    </div>
                </div>

            </section>        
                                 
                                 
             <section class="section my-5 pb-5">
                
                <div class="card card-ecommerce">
                    <div class="card-body">

                        <!-- Shopping Cart table -->
                        <div class="table-responsive">

                            <table class="table product-table">

                                <thead class="mdb-color lighten-5">
                                    <tr>
                                      
                                        <th class="font-weight-bold">
                                            <strong>Product</strong>
                                        </th>
                                        <th class="font-weight-bold">
                                            <strong>Model</strong>
                                        </th>
                                        
                                        <th class="font-weight-bold">
                                            <strong>Note</strong>
                                        </th>
                                       
                                        <th class="font-weight-bold">
                                            <strong>Price</strong>
                                        </th>
                                        <th class="font-weight-bold">
                                            <strong>QTY</strong>
                                        </th>
                                        <th class="font-weight-bold">
                                            <strong>Amount</strong>
                                        </th>
                                        <th>
                                             <a class="btn btn-primary btn-sm btn-rounded waves-effect waves-light" data-toggle="modal" data-target="#productsList">
                                                Add<i class="fa fa-plus ml-1"></i>
                                             </a>
                                        </th>
                                    </tr>
                                </thead>
                              
                                <tbody>
                                    
                                <c:forEach var="item" items="${listQuoteItemsEdit}" varStatus="loop" >
                                
                                    <tr>
                                      
                                        <td>
                                            <h5 class="mt-3">
                                                <strong>${item.product.brand}</strong>
                                            </h5>
                                            <p class="text-muted">${item.product.description}</p>
                                        </td>
                                        <td>${item.product.model}</td>
                                        <td><p class="text-muted small "><i>${item.note}</i></p></td>
                                        <td><i class="fa fa-dollar"></i><fmt:formatNumber type="number" maxFractionDigits="2" value="${(item.product.sellingPrice*editCurrency.rate)/ 1.15}"/></td>
                                        <td class="text-center text-md-left">
                                            <span class="qty">${item.quantity} </span>
                                              <div class="btn-group btn-group-sm" role="group" aria-label="Basic example">
                                                  <c:if test="${item.quantity!=1}">
                                                       <a href="${contextPath}/quote/sub-item/${item.id}/${quoteId}" class="btn btn-primary btn-sm"><i class="fa fa-minus"></i></a>
                                                  </c:if>                                               
                                                 <a href="${contextPath}/quote/add-item/${item.id}/${quoteId}" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i></a>
    
                                             </div>
                                        </td>
                                        <td class="font-weight-bold">
                                            <strong>
                                                <fmt:formatNumber type="number" maxFractionDigits="2" value="${((item.product.sellingPrice*editCurrency.rate)/ 1.15) * item.quantity}"/>
                                                 
                                            </strong>
                                        </td>
                                        <td>
                                            
                                            <a href="${contextPath}/quote/delete-item/${item.id}/${quoteId}"
                                               class="btn-floating btn-sm red darken-2" onclick="return confirm('Are You Sure?')" 
                                              data-toggle="tooltip" data-placement="top" title="Remove item"><i class="fa fa-remove"></i></a>
                                        </td>
                                    </tr>
                                 </c:forEach>
                               
                           

                                </tbody>
                               
                            </table>
                                                        
                                                        

                        </div>
                                                <form:form modelAttribute="quote" action="${contextPath}/quote/create" method="POST">
                                                 
                                   <div class="row text-md-right">
                                                            <div class="col-md-4">
                                                                <h5 class="mt-2"><strong>Sub Total :</strong></h5>
                                                            </div>
                                                             <div class="col-md-4">
                                                                <h5 class="mt-2"><strong>$ 
                                                                      <c:if test="${editTotal!=0}">
                                                                          ${editTotal}
                                                                      </c:if> </strong>                                               
                                                                 </h5>
                                                            </div>
                                                             <div class="col-md-4">
                                                                
                                                            </div>
                                                        </div>
                                                        <div class="row text-md-right">
                                                            <div class="col-md-4">
                                                                <h6 class="mt-2"><strong>Vat :</strong></h6>
                                                            </div>
                                                             <div class="col-md-4">
                                                                  <h6 class="mt-2"><strong>$ 
                                                                        <c:if test="${editTotal!=0}">
                                                                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${editTotal*0.15}"/>

                                                                        </c:if> 
                                                                    </strong>                                               
                                                                </h6>
                                                            </div>
                                                             <div class="col-md-4">
                                                                
                                                            </div>
                                                        </div>
                                                        <div class="row text-md-right">
                                                            <div class="col-md-4">
                                                                <h4 class="mt-2"><strong>Total :</strong></h4>
                                                            </div>
                                                             <div class="col-md-4">
                                                                <h4 class="mt-2"><strong>$ 
                                                                        <c:if test="${editTotal!=0}">
                                                                             <fmt:formatNumber type="number" maxFractionDigits="2" value="${editTotal*0.15 + editTotal}"/>

                                                                        </c:if> </strong>                                               
                                                                </h4>
                                                            </div>
                                                             <div class="col-md-4">
                                                                       
                                                                            <form:hidden path="contact" value="${quoteContact.id}"/>
                                                                            <form:hidden path="client" value="${quoteClient.id}"/>
                                                                            <form:hidden path="bankingDetail" value="${quoteBank.id}"/>
                                                                            <form:hidden path="total" value="${total}"/>
                                                                             
                                                                                     <c:choose>
                                                                                        <c:when test="${editTotal==0 || empty editTotal}">
                                                                                            <p class="text-danger">Invalid Quote SetUp(Select Recipient & Client)</p>
                                                                                         </c:when>
                                                                                        <c:otherwise> 
                                                                                                <form:button class="btn btn-primary btn-rounded" data-toggle="modal" data-target="#modalLoginForm">Send Qoute<i class="fa fa-angle-right right"></i></form:button>
                                                                                        </c:otherwise>
                                                                                  </c:choose>  
                                                                       
                                                            </div>
                                                        </div>
                                                    </form:form>
                    </div>

                </div>

            </section>                    
                                 
                                 
                                 
                                 
     <div class="modal fade" id="productsList" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-fluid modal-notify modal-primary" role="document">
                        <!--Content-->
                        <div class="modal-content">
                            <!--Header-->
                            <div class="modal-header">
                                <p class="heading lead">Select Product To Add to Quote</p>

                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true" class="white-text">&times;</span>
                                                        </button>
                            </div>

                            <!--Body-->
                            <div class="modal-body">
                                <div class="text-center">
                                    <a id="add-products"><i class="fa fa-cart-plus fa-4x mb-3 animated rotateIn"></i></a>
                                    
                                </div>
                                <div class="table-responsive">
                            <!--Table-->
                            <table id="productsTableAll" class="table table-hover table-responsive-md">

                                <!--Table head-->
                                <thead>
                                    <tr>
                                        <th></th>
                                       
                                        <th class="th-lg">Brand<i class="fa fa-sort ml-1"></i></th>
                                        <th class="th-lg">Model<i class="fa fa-sort ml-1"></i></th>
                                         <th></th>
                                        <th class="th-lg">Description<i class="fa fa-sort ml-1"></i></th>
                                        <th class="th-lg">Unit Price<i class="fa fa-sort ml-1"></i></th>
                                        <th class="th-lg">Available<i class="fa fa-sort ml-1"></i></th>
                                      
                                    </tr>
                                </thead>
                                <!--Table head-->

                                <!--Table body-->
                                <tbody>
                                    <c:forEach var="inv" items="${inventory}" varStatus="loop" >
                                    <tr>
                                       
                                        
                                       
                                        <td>${inv.id}</td>
                                       
                                        <td>${inv.brand}</td>
                                        <td>${inv.model}</td>
                                         <td>
                                            <div class="md-form">
                                                <c:if test="${not empty listQuoteItems}">
                                                  <c:forEach var="item" items="${listQuoteItems}" varStatus="loop" >
                                                      <c:choose>
                                                         <c:when test="${item.product.id eq inv.id}">
                                                             <input type="text" id="${inv.id}-note" value="${item.note}" class="md-textarea form-control" style="width: 300px"  placeholder="Note Here......">                                                           
                                                         </c:when>

                                                          <c:otherwise>
                                                             <c:set var="textarea" value="true"/>
                                                           </c:otherwise>
                                                      </c:choose>


                                                 </c:forEach>  
                                                  </c:if>
                                                   <c:if test="${ empty listQuoteItems || textarea eq true}">
                                                     <input type="text" id="${inv.id}-note" class="form-control" style="width: 300px"  placeholder="Short Note">

                                                 </c:if> 
                                                </div>
                                        </td>
                                        <td class="overflow" data-toggle="tooltip" data-placement="top" title="${inv.description}"><span>${inv.description}</span></td>
                                        <td><i class="fa fa-dollar"></i><fmt:formatNumber type="number" maxFractionDigits="2" value="${(inv.sellingPrice*editCurrency.rate)/ 1.15}"/></td>
                                        
                                        
                                        <td>${inv.availableStock}</td>
                                
                                    </tr>
                                   </c:forEach>
                                   
                                </tbody>
                             
                            </table>
     
                        </div>
                               
                            </div>
                           
                        </div>

                    </div>
                </div>                            
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                                 
                              
<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Sending Quote...</h4>
              <!--  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>-->
            </div>
            <div class="modal-body mx-3">
             
                <center><img src="${contextPath}/resources/img/loading.gif" class="img-fluid flex-center"></center>
                

            </div>
           
        </div>
    </div>
</div>

          
