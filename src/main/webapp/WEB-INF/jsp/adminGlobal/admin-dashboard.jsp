<!-- Nav tabs -->
<ul class="nav nav-tabs nav-justified blue" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#panel5" role="tab"><i class="fa fa-dashboard"></i>Dashboard</a>
    </li>
   <!-- <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#panel6" role="tab"><i class="fa fa-building"></i>C</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#panel7" role="tab"><i class="fa fa-envelope"></i> Mail</a>
    </li>-->
</ul>
<!-- Tab panels -->
<div class="tab-content">
    <!--Panel 1-->
    <div class="tab-pane fade in show active" id="panel5" role="tabpanel">
        <br>                         
 <div class="row">
     
      <div class="col-md-3">
          <a href="${contextPath}/admin/gb/list-users" class="btn btn-danger btn-lg w-100" role="button"><i class="fa fa-users mr-1"></i><br>Users</a>  
      </div> 
       
       <div class="col-md-3">
            <a href="${contextPath}/admin/gb/list-company" class="btn btn-info btn-lg w-100" role="button"><i class="fa fa-building-o mr-1"></i><br>Companies</a>
       </div>
             <sec:authorize access="hasAuthority('GLOBAL')">
                <div class="col-md-3">
                     <a href="${contextPath}/admin/gb/list-module" class="btn btn-blue-grey btn-lg w-100" role="button"><i class="fa fa-plus w-100"></i><br>Modules</a>
                </div>

                <div class="col-md-3">
                     <a href="${contextPath}/admin/gb/list-roles" class="btn btn-purple btn-lg w-100" role="button"><i class="fa fa-user-secret w-100"></i><br>Roles</a>
                </div>
                
                  <div class="col-md-3">
                     <a href="${contextPath}/admin/gb/email" class="btn btn-yellow btn-lg w-100" role="button"><i class="fa fa-envelope w-100"></i><br>Global Email</a>
                </div>
                
             </sec:authorize>
                          
 </div>
   <div class="row">
     
      <div class="col-md-3">
            
      </div> 
       <div class="col-md-3">
            
       </div>
       <div class="col-md-3">
            
       </div>
       <div class="col-md-3">
            
       </div>
                          
 </div>
                   
    </div>
  
    <div class="tab-pane fade" id="panel6" role="tabpanel">
        <br>
        
        
       </div>
    
    <div class="tab-pane fade" id="panel7" role="tabpanel">
        <br>
        </div>
   
</div>
