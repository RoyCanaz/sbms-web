 <ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>        
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active">Sales</li>
    </ol>
                          

<div class="row">
     
      <div class="col-md-3">
          <a href="${contextPath}/inventory/" class="btn btn-primary btn-lg w-100" role="button"><i class="fa fa-cubes w-100"></i>Inventory</a> 
      </div> 
       <div class="col-md-3">
          <a href="${contextPath}/admin/brand/list" class="btn btn-indigo btn-lg w-100" role="button"><i class="fa fa-gg w-100"></i>Brands</a>
       </div>
       <div class="col-md-3">
            <a href="${contextPath}/admin/supplier/list" class="btn btn-blue-grey btn-lg w-100" role="button"><i class="fa fa-car w-100"></i><br>Suppliers</a>
       </div>
       <div class="col-md-3">
           <a href="${contextPath}/admin/category/list" class="btn btn-info btn-lg w-100" role="button"><i class="fa fa-users mr-1"></i><br>Categories</a>
       </div>
                          
 </div>
 <div class="row">
     
      <div class="col-md-3">
          <%-- <a href="${contextPath}/admin/setup" class="btn btn-warning btn-lg w-100" role="button"><i class="fa fa-cog mr-1"></i><br>Invoice Setup</a>--%>
           <div class="dropdown dropdown">
             <a class="btn btn-teal btn-lg dropdown-toggle w-100" role="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                 <i class="fa fa-cogs mr-1"></i><br>Invoice Setup</a>
                <div class="dropdown-menu dropdown-primary">
                         <a class="dropdown-item" href="${contextPath}/admin/bankingDetail/list"><i class="fa fa-bank"></i>&nbsp;Banking Details</a>
                         <a class="dropdown-item" href="${contextPath}/admin/email/list"><i class="fa fa-envelope-square"></i>&nbsp;Emails</a>
                         <a class="dropdown-item" href="${contextPath}/admin/currency/list"><i class="fa fa-dollar"></i>&nbsp;Currencies</a>
                       
                  </div>
           </div>
      </div> 
       <div class="col-md-3">
            
       </div>
       <div class="col-md-3">
           
       </div>
       <div class="col-md-3">
         <%--  <a href="${contextPath}/admin/email/default/list" class="btn btn-primary btn-lg w-100" role="button"><i class="fa fa-envelope w-100"></i><br>Email Settings</a>--%>
          
       </div>
                          
 </div>
                       

