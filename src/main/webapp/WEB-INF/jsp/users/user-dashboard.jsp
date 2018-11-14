<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
            
</ol>
 <div class="row">
     
      <div class="col-md-3">      
          <div class="card testimonial-card">
              <div class="card-up blue">
                   
              </div>
              <div class="avatar mx-auto white">
                  <center><i class=" fa fa-users blue-ic fa-3x rounded-circle"></i></center>
              </div>
              <div class="card-body">
                  <h5 class="card-title blue-text"><strong>Client Management</strong></h5>
                  <hr>
                 <div class="list-group">
                     <a href="${contextPath}/client/addClient" class="list-group-item waves-light"><i class="fa fa-plus"></i>&nbsp;Add Client</a>
                     <a href="${contextPath}/client/list" class="list-group-item waves-effect"><i class="fa fa-list"></i>&nbsp;List Clients</a>
                   
                  </div>
              </div>

          </div>
      </div> 
       <div class="col-md-3">
             <div class="card testimonial-card">
              <div class="card-up blue">
                   
              </div>
              <div class="avatar mx-auto white">
                  <center><i class=" fa fa-file-text blue-ic fa-3x rounded-circle"></i></center>
              </div>
              <div class="card-body">
                  <h5 class="card-title blue-text"><strong>Quotes</strong></h5>
                  <hr>
                 <div class="list-group">
                     <a href="${contextPath}/quote/list" class="list-group-item waves-light"><i class="fa fa-plus"></i>&nbsp;New Quote</a>
                     <a href="${contextPath}/invoice/list-quotes" class="list-group-item waves-effect"><i class="fa fa-list"></i>&nbsp;List Quotes</a>
                  </div>
              </div>

          </div>
       </div>
       <div class="col-md-3">
           
             <div class="card testimonial-card">
              <div class="card-up blue">
                   
              </div>
              <div class="avatar mx-auto white">
                  <center><i class=" fa fa-dollar blue-ic fa-3x rounded-circle"></i></center>
              </div>
              <div class="card-body">
                  <h5 class="card-title blue-text"><strong>Invoices</strong></h5>
                  <hr>
                 <div class="list-group">
                    <%-- <a href="${contextPath}/invoice/clientsAll" class="list-group-item waves-light"><i class="fa fa-plus"></i>&nbsp;Generate Invoice</a>
                     <a href="${contextPath}/invoice/quotesAll" class="list-group-item waves-light"><i class="fa fa-plus"></i>&nbsp;Generate Invoice</a>--%>
                    <a href="${contextPath}/invoice/new" class="list-group-item waves-light"><i class="fa fa-plus"></i>&nbsp;New Invoice</a>
                     <a href="${contextPath}/invoice/clientsAll" class="list-group-item waves-light"><i class="fa fa-list"></i>&nbsp;List Invoices</a>
                   
                  </div>
              </div>

          </div>
       </div>
        <div class="col-md-3">
           
             <div class="card testimonial-card">
              <div class="card-up blue">
                   
              </div>
              <div class="avatar mx-auto white">
                  <center><i class=" fa fa-street-view blue-ic fa-3x rounded-circle"></i></center>
              </div>
              <div class="card-body">
                  <h5 class="card-title blue-text"><strong>Visit Plans</strong></h5>
                  <hr>
                 <div class="list-group">
                     <a href="${contextPath}/visitplan/new" class="list-group-item waves-light"><i class="fa fa-plus"></i>&nbsp;Visit Plan</a>
                     <a href="${contextPath}/visitplan/list" class="list-group-item waves-light"><i class="fa fa-list"></i>&nbsp;List Visit Plans</a>
                   
                  </div>
              </div>

          </div>
       </div>
       </div>
       
                          
 </div>