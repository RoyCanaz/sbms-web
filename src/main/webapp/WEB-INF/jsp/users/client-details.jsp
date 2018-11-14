

<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>  
        <li class="breadcrumb-item active">${client.name}</li>       
</ol>
<div class="card mb-4 text-center py-3 blue accent-2 white-text">
                        
                        <h4 class="h4-responsive">${client.name}</h4>
                    </div>

<div class="row">
         <div class="col-lg-6 col-md-12">
                        <div class="card mb-4">
                                                <div class="card-header white-text primary-color">
                                                    Client Details
                                                </div>
                                                <div class="card-body">

                                                    <table class="table no-header mt-1">
                                                        <tbody>
                                                            <tr>
                                                                <td>Client Type</td>
                                                                <td>${client.clientType}</td>                                      
                                                            </tr>
                                                            <tr>
                                                                <td>Name</td>
                                                                <td>${client.name}</td>                                       
                                                            </tr>
                                                            <tr>
                                                                <td>Description</td>
                                                                <td>${client.description}</td>                                    
                                                            </tr>
                                                             <tr>
                                                                <td>Address</td>
                                                                <td>${client.address}</td>                                    
                                                            </tr>
                                                             <tr>
                                                                <td>Email</td>
                                                                <td>${client.email}</td>                                    
                                                            </tr>
                                                             <tr>
                                                                 <td>Phone</td>
                                                                <td>${client.phone}</td>                                    
                                                            </tr>
                                                            <tr>
                                                                 <td>Website</td>
                                                                <td>${client.website}</td>                                    
                                                            </tr>
                                                        </tbody>
                                                    </table>

                                                    <a href="<c:url value="/client/addClient/form?id=${client.id}"/>" class="btn btn btn-flat grey lighten-3 btn-rounded waves-effect font-weight-bold dark-grey-text float-right">Edit</a>
                                                   
                                                </div>
                                            </div>

        </div>
        <div class="col-lg-6 col-md-12">
                        <div class="card mb-4">
                                                <div class="card-header white-text primary-color">
                                                    Other
                                                </div>
                                                <div class="card-body">

                                                       
                                                                <a href="<c:url value="/client/contact/addContact?id=${client.id}"/>" class="btn btn-blue-grey btn-md w-100" role="button"><i class="fa fa-users mr-1"></i><br>Contacts</a>
                                                                <a href="${contextPath}/client/inventory/addClientinventory?id=${client.id}" class="btn btn-purple btn-md w-100" role="button"><i class="fa fa-shopping-basket mr-1"></i><br>Inventory</a>
                                                            
                                                </div>
                                            </div>

        </div>
</div>