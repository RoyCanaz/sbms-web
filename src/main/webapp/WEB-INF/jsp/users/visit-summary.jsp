<div class="jumbotron text-center">
    <ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li> 
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/visitplan/list">Call/Visit Plans</a></li> 
        <li class="breadcrumb-item active">Call/Visit Summary</li>       
</ol>
<hr class="my-4">
    <!--Title-->
    <h1 class="card-title h2-responsive mt-2 font-bold blue-text"><strong><u>Call/Visit Summary For : ${visitForm.client.name}</u></strong></h1>

    <!--Text-->
     <form:form modelAttribute="visitForm" method="POST" action="${contextPath}/visitplan/summary">
         <form:hidden path="id"/>
         
    <div class="justify-content-center">
       
 <div class="row ">

           <div class="col-md-3 mb-4">
           </div>
                        <div class="col-md-6 mb-4">
                            <div class="md-form">
                                <form:textarea rows="6" path="visitResult" id="form4" class="form-control"/>
                                <label class="active" for="form4">Call/Visit Summary:</label>
                            </div>
                            
                        </div>
     <div class="col-md-3 mb-4">
           </div>
 </div>
                       
    </div>

    <hr class="my-4">

    <center> <form:button type="submit" class="btn btn-outline-primary waves-effect"><span class="fa fa-check ml-1"></span> Save </form:button></center>
    </form:form>
                            

</div>
