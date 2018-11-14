
  <ol class="breadcrumb white">
      <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/admin/dashboard">Dashboard</a></li>
        <li class="breadcrumb-item active">Setup</li>
        
    </ol>
    
    <!-- Nav tabs -->
<ul class="nav nav-tabs nav-justified blue-gradient" role="tablist">
     <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#panel8" role="tab"><i class="fa fa-dollar"></i>Invoicing</a>
    </li>
   
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#panel6" role="tab"><i class="fa fa-list-ol"></i> Quote/Invoice Footer</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#panel7" role="tab"><i class="fa fa-suitcase"></i> BPN/VAT NUMBER</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#panel5" role="tab"><i class="fa fa-image"></i> Logo SetUp</a>
    </li>
</ul>

<div class="tab-content">
     <div class="tab-pane fade in show active" id="panel8" role="tabpanel">
         
          <div class="card">
                    <div class="card-body">
                        <div class="card-body mx-1">
                            <div class="row">
                                <div class="col-md-3">
                                    <a href="${contextPath}/admin/bankingDetail/list" class="btn btn-success btn-lg w-100" role="button"><i class="fa fa-bank mr-1"></i><br>Banking Details</a>
                                </div>
                                 <div class="col-md-3">
                                     <a href="${contextPath}/admin/email/list" class="btn btn-primary btn-lg w-100" role="button"><i class="fa fa-envelope-square w-100"></i><br>Emails</a>
                                </div>
                                 <div class="col-md-3">
                                     <a href="${contextPath}/admin/currency/list" class="btn btn-brown btn-lg w-100" role="button"><i class="fa fa-dollar mr-1"></i><br>Currencies</a> 
                                </div>
                                 <div class="col-md-3">
                                    
                                </div>
                            </div> 
                        </div>
                    </div>
          </div>
        </div>
   
    <div class="tab-pane fade" id="panel5" role="tabpanel">
         <div class="card">
     <div class="card-body">

    <div class="card-body mx-1">
        <div class="row">
            <div class="col-md-6">
                <form:form modelAttribute="image" action="${contextPath}/admin/setup/logo" method="POST" enctype="multipart/form-data">         

                         <div class="file-field">
                                    <div class="btn btn-primary btn-sm float-left">
                                        <span>Choose Logo</span>
                                        <input name="file" type="file" accept="image/png"/>
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input name="name" class="form-control file-path validate" type="text" placeholder="Upload Logo">
                                    </div>
                                </div>           
                        <div class="text-center py-4 mt-3">
                        <form:button class="btn btn-blue">Save</form:button>
                    </div>
                </form:form>
            </div>
            <div class="col-md-6">
                  <img src="${logoname}" alt="placeholder" class="img-thumbnail">  
            </div>
        </div>
    </div>
</div>
</div>
    </div>
    
    <div class="tab-pane fade" id="panel6" role="tabpanel">
       </div>
    
    <div class="tab-pane fade" id="panel7" role="tabpanel">
        </div>
   
  
</div>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
                      



