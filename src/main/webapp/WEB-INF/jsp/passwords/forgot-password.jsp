<div class="jumbotron text-center">

    <!--Title-->
    <h1 class="card-title h2-responsive mt-2 font-bold blue-text"><strong>Forgot Password?</strong></h1>

    <!--Text-->
     <form method="GET" action="${contextPath}/password/send">
         
    <div class="justify-content-center">
       
 <div class="row ">

           <div class="col-md-3 mb-4">
           </div>
                        <div class="col-md-6 mb-4">
                            <div class="md-form">
                                <input type="email" name="email" id="form4" class="form-control" required="">
                                <label class="active" for="form4">Your Registered Email Address:</label>
                            </div>
                             <p class="help-block text-danger">
                                ${unknownEmail}
                            </p>
                        </div>
     <div class="col-md-3 mb-4">
           </div>
 </div>
                       
    </div>

    <hr class="my-4">

    <center> <button type="submit" class="btn btn-outline-primary waves-effect"><span class="fa fa-send ml-1"></span> Submit </button></center>
    </form>
                            <p class="font-small blue-text d-flex justify-content-center"><a href="${contextPath}/" class="blue-text ml-1">Login</a></p>

</div>
