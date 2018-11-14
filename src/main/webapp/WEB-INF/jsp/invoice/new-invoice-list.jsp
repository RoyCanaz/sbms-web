<%-- 
    Document   : new-invoice
    Created on : 18-Jul-2018, 15:10:20
    Author     : user
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>
         <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/invoice/cancel">Cancel</a></li>
         <li class="breadcrumb-item active">Item List</li>

    </ol>
        
    <section class="mb-4">

                <div class="card">
                    <div class="card-body">

                        <div class="row">
                         
                            
                            <div class="col-md-2">
                                <sec:authorize access="hasAnyAuthority('GLOBAL','SUPER_ADMIN', 'ADMIN')">
                                                    <h6 class="blue-text font-bold"><u>Currency</u></h6>
                                                <select name="curRate" id="currency-id" class="mdb-select colorful-select dropdown-primary" searchable="Search here.." required="true">
                                                 <option value="" disabled selected>Select Currency</option>
                                                  <c:forEach var="c" items="${currencyList}">

                                                      <c:choose>
                                                           <c:when test="${c.id eq invoiceCurrency.id}">
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
                                <select name="bankName" id="bankDetail-id" class="mdb-select con colorful-select dropdown-primary" searchable="Search here.." required="true">
                                     <option value="" disabled selected>Select Bank</option>
                                      <c:forEach var="bank" items="${bankingDetailList}">
                                                <c:choose>
                                                    <c:when test="${bank.id eq invoiceBank.id}">
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
                               <select name="name" id="client-id" class="mdb-select colorful-select dropdown-primary" searchable="Search here.." required="true">
                                <option value="" disabled selected>Select Client</option>
                                 <c:forEach var="client" items="${clientList}">
                                      <c:choose>
                                                    <c:when test="${invoiceClient.id eq client.id}">
                                                              <option value="${invoiceClient.id}" selected="">${invoiceClient.name}</option>
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
                             <select name="contact"  id="contact-id" class="mdb-select colorful-select dropdown-primary" searchable="Search here.." required="true">
                                <option value="" disabled selected>Sent To :</option>
                                 <c:forEach var="contact" items="${invoiceContacts}">                                   
                                        <c:choose>
                                                    <c:when test="${ not empty invoiceContact && invoiceContact.id eq contact.id}">
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
                                 <p><strong>${invoiceBank.bank}</strong></p>
                                <p>${invoiceBank.name}</p>
                               
                                <p>${invoiceBank.branch}</p>
                                <p>${invoiceBank.accountNumber}</p>

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
                                <p><strong>${invoiceClient.name}</strong></p>
                                <p>${invoiceClient.address}</p>
                                <p>${invoiceClient.email}</p>

                            </div>
                                             
                             <div class="col-md-3">     
                                   
                                <p><small>Sending To :</small></p>
                                <c:if test="${not empty invoiceContact}">  
                                <p><strong>${invoiceContact.firstName} ${invoiceContact.lastName}</strong></p>
                                <p>${invoiceContact.department}</p>
                                <p>${invoiceContact.jobPosition}</p>
                                
                                <c:choose>
                                    <c:when test="${empty invoiceContact.email}">
                                        <p class="red-text"><i>No email found for this contact</i></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="green-text">${invoiceContact.email} <i class="fa fa-check green-ic"></i></p>
                                    </c:otherwise>
                                </c:choose>
                                
                                
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
                                    
                                <c:forEach var="item" items="${listInvoiceItems}" varStatus="loop" >
                                
                                    <tr>
                                      
                                        <td>
                                            <h5 class="mt-3">
                                                <strong>${item.product.brand}</strong>
                                            </h5>
                                            <p class="text-muted">${item.product.description}</p>
                                        </td>
                                        <td>${item.product.model}</td>
                                        <td><p class="text-muted small "><i>${item.note}</i></p></td>
                                        <td><i class="fa fa-dollar"></i><fmt:formatNumber type="number" maxFractionDigits="2" value="${(item.product.sellingPrice*invoiceCurrency.rate)/ 1.15}"/></td>
                                        <td class="text-center text-md-left">
                                            <span class="qty">${item.quantity} </span>
                                              <div class="btn-group btn-group-sm" role="group" aria-label="Basic example">
                                                  <c:if test="${item.quantity!=1}">
                                                       <a href="${contextPath}/invoice/reduce-quantity/${item.product.id}" class="btn btn-primary btn-sm"><i class="fa fa-minus"></i></a>
                                                  </c:if>                                               
                                                 <a href="${contextPath}/invoice/add/${item.product.id}" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i></a>
    
                                             </div>
                                        </td>
                                        <td class="font-weight-bold">
                                            <strong>
                                                <fmt:formatNumber type="number" maxFractionDigits="2" value="${((item.product.sellingPrice*invoiceCurrency.rate)/ 1.15) * item.quantity}"/>
                                                 
                                            </strong>
                                        </td>
                                        <td>
                                            
                                            <a href="${contextPath}/invoice/remove/${item.product.id}"
                                               class="btn-floating btn-sm red darken-2" onclick="return confirm('Are You Sure?')" 
                                              data-toggle="tooltip" data-placement="top" title="Remove item"><i class="fa fa-remove"></i></a>
                                        </td>
                                    </tr>
                                 </c:forEach>
                               
                           

                                </tbody>
                               
                            </table>
                                                        
                                                        

                        </div>
                                               
                                                 
                                   <div class="row text-md-right">
                                                            <div class="col-md-4">
                                                                <h5 class="mt-2"><strong>Sub Total :</strong></h5>
                                                            </div>
                                                             <div class="col-md-4">
                                                                <h5 class="mt-2"><strong>$ 
                                                                      <c:if test="${invoiceTotal!=0}">
                                                                          ${invoiceTotal}
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
                                                                        <c:if test="${invoiceTotal!=0}">
                                                                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${invoiceTotal*0.15}"/>

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
                                                                        <c:if test="${invoiceTotal!=0}">
                                                                             <fmt:formatNumber type="number" maxFractionDigits="2" value="${invoiceTotal*0.15 + invoiceTotal}"/>

                                                                        </c:if> </strong>                                               
                                                                </h4>
                                                            </div>
                                                             <div class="col-md-4">
                                                                       
                                                                           
                                                                                     <c:choose>
                                                                                        <c:when test="${invoiceTotal==0 || empty invoiceTotal || empty invoiceContact || empty invoiceContact.email || empty invoiceClient}">
                                                                                            <p class="text-danger">Invalid Invoice SetUp(Select Recipient & Client)</p>
                                                                                         </c:when>
                                                                                        <c:otherwise> 
                                                                                            <form action="${contextPath}/invoice/send" method="GET">
                                                                                                <button type="submit" class="btn btn-primary btn-rounded" data-toggle="modal" data-target="#modalLoginForm">Send Invoice<i class="fa fa-angle-right right"></i></button>
                                                                                            </form>
                                                                                        </c:otherwise>
                                                                                  </c:choose>  
                                                                       
                                                            </div>
                                                        </div>
                                                   
                    </div>

                </div>

            </section>
                    
                        
                        
                        <div class="modal fade" id="productsList" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-fluid modal-notify modal-primary" role="document">
                        <!--Content-->
                        <div class="modal-content">
                            <!--Header-->
                            <div class="modal-header">
                                <p class="heading lead">Select Product To Add to Invoice</p>

                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true" class="white-text">&times;</span>
                                                        </button>
                            </div>

                            <!--Body-->
                            <div class="modal-body">
                                <div class="text-center">
                                    <a id="add-invoice-products"><i class="fa fa-cart-plus fa-4x mb-3 animated rotateIn"></i></a>
                                    
                                </div>
                                <div class="table-responsive">
                            <!--Table-->
                            <table id="productsTable" class="table table-hover table-responsive-md">

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
                                                                                         <input type="text" id="${inv.id}-note"  class="md-textarea form-control" style="width: 300px"  placeholder="Note Here......">                                                                                                                 
                                                                            </div>
                                                                    </td>
                                                                    <td class="overflow" data-toggle="tooltip" data-placement="top" title="${inv.description}"><span>${inv.description}</span></td>
                                                                     <td><i class="fa fa-dollar"></i><fmt:formatNumber type="number" maxFractionDigits="2" value="${(inv.sellingPrice*invoiceCurrency.rate)/ 1.15}"/></td>
                                                                    <td>${inv.availableStock}</td>
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
                <h4 class="modal-title w-100 font-weight-bold">Sending Invoice...</h4>
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


                

