<div class="jumbotron text-center">

    <h1 class="card-title h2-responsive mt-2 font-bold blue-text"><strong>Reset Password?</strong></h1>

     <form method="Post" action="${contextPath}/password/reset">
         <input type="hidden" name="token" value="${resetToken}">
    <div class="justify-content-center">
       
            <div class="row ">

                      <div class="col-md-3 mb-4">
                      </div>
                                   <div class="col-md-6 mb-4">
                                       <div class="md-form">
                                           <input type="password" name="password" id="form4" class="form-control">
                                           <label class="active" for="form4">New Password:</label>
                                       </div>
                                        <p class="help-block text-danger">

                                       </p>
                                   </div>
                <div class="col-md-3 mb-4">
                </div>
            </div>
            <div class="row ">

           <div class="col-md-3 mb-4">
           </div>
                        <div class="col-md-6 mb-4">
                            <div class="md-form">
                                           <input type="password" name="confirmPassword" id="form5" class="form-control">
                                           <label class="active" for="form5">Confirm Password:</label>
                                       </div>
                                        <p class="help-block text-danger">

                                       </p>
                        </div>
           <div class="col-md-3 mb-4">
           </div>
 </div>
                       
    </div>

    <hr class="my-4">

    <center> <button type="submit" class="btn btn-outline-primary waves-effect"><span class="fa fa-send ml-1"></span> Submit </button></center>
    </form>

</div>

