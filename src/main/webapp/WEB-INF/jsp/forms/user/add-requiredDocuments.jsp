<ol class="breadcrumb white">
        <li class="breadcrumb-item">${company.companyName}</li>
        <li class="breadcrumb-item"><a class="blue-text" href="${contextPath}/user/dashboard">Dashboard</a></li>  
        <li class="breadcrumb-item active">Documents</li>       
</ol>
<div class="card">
     <div class="card-body">
    <h3 class="h4 text-center py-4 blue">Required Documents</h3>
    <div class="card-body mx-1">
           <form name="item" action="${contextPath}/client/requiredDocuments/form" method="POST"> 
                      <c:if test="${not empty ci}">
                          <input type="hidden" name="ci" value="${ci}"/>
                      </c:if> 
                    
                       
                        <div class="form-check">   
                             <label class="form-check-label" for="applicationLetter">1)  </label>
                             <input type="checkbox" class="filled-in form-check-input" value="Yes" id="applicationLetter">
                             <label class="form-check-label" for="applicationLetter">Application Letter</label>
                        </div>
                             

                        <div class="form-check">
                            <label class="form-check-label" for="companyProfile">2)</label>
                            <input type="checkbox" class="filled-in form-check-input" name="companyProfile" id="companyProfile" value="Yes">
                            <label class="form-check-label" for="companyProfile">Company Profile</label>
                        </div>
             
                        <div class="form-check">   
                             <label class="form-check-label" for="certOfIncorporation">3)  </label>
                             <input type="checkbox" class="filled-in form-check-input" value="Yes" id="certOfIncorporation">
                             <label class="form-check-label" for="certOfIncorporation">Certficate of Incorporation</label>
                        </div>
                             

                        <div class="form-check">
                            <label class="form-check-label" for="mou">4)</label>
                            <input type="checkbox" class="filled-in form-check-input" name="mou" id="mou" value="Yes">
                            <label class="form-check-label" for="mou">Memorundum Of Association</label>
                        </div>
                          <div class="form-check">
                            <label class="form-check-label" for="vat">5)</label>
                            <input type="checkbox" class="filled-in form-check-input" name="vat" id="vat" value="Yes">
                            <label class="form-check-label" for="vat">Vat Registration Certificate</label>
                        </div> 
                         <div class="form-check">
                            <label class="form-check-label" for="crFourteen">5)</label>
                            <input type="checkbox" class="filled-in form-check-input" name="crFourteen" id="crFourteen" value="Yes">
                            <label class="form-check-label" for="crFourteen">CR 14</label>
                        </div>
                          <div class="form-check">
                            <label class="form-check-label" for="itf">6)</label>
                            <input type="checkbox" class="filled-in form-check-input" name="itf" id="itf" value="Yes">
                            <label class="form-check-label" for="itf">ITF 263 Tax Clearance Cerficate</label>
                        </div> 
                         <div class="form-check">
                            <label class="form-check-label" for="tradeLicense">7)</label>
                            <input type="checkbox" class="filled-in form-check-input form-control-lg" name="tradeLicense" id="tradeLicense" value="Yes">
                            <label class="form-check-label" for="tradeLicense">Trade License</label>
                        </div>
                          <div class="form-check">
                            <label class="form-check-label" for="traceableReference">8)</label>
                            <input type="checkbox" class="filled-in form-check-input" name="traceableReference" id="traceableReference" value="Yes">
                            <label class="form-check-label" for="traceableReference">Traceable Reference</label>
                        </div> 
                          <div class="text text-md-right">
                          <button type="submit" class="btn btn-blue-grey">Save <i class="fa fa-arrow-right prefix grey-text"></i></button> 
                          </div>
                          
                  </form>
       
        
    </div>
    
</div>
</div>

