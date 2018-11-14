/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
         $("#note-second").hide();

        $(".bank-id").each(function (index) {
            var loop = index+1;
            $("#bank-id"+loop).change(function () {
               var id = $(this).val();
                if ($(this).is(':checked')) {
                       window.location = "/sbms/admin/bankingDetail/enable/"+id;  
                } else {
                      window.location = "/sbms/admin/bankingDetail/disable/"+id;  
                }
            });
        });
        
          $(".gb-email").each(function (index) {
            var loop = index+1;
            $("#gb-email"+loop).change(function () {
               var id = $(this).val();
                if ($(this).is(':checked')) {
                       window.location = "/sbms/admin/gb/email/enable/"+id;  
                } else {
                      window.location = "/sbms/admin/gb/email/disable/"+id;  
                }
            });
        });
        
        
         $(".currency-id").each(function (index) {
            var loop = index+1;
            $("#currency-id"+loop).change(function () {
               var id = $(this).val();
                if ($(this).is(':checked')) {
                       window.location = "/sbms/admin/currency/enable?id="+id;  
                } else {
                      window.location = "/sbms/admin/currency/disable?id="+id;  
                }
            });
        });
          $(".email-id").each(function (index) {
            var loop = index+1;
            $("#email-id"+loop).change(function () {
               var id = $(this).val();
                if ($(this).is(':checked')) {
                       window.location = "/sbms/admin/email/enable/type/"+id;  
                } else {
                      window.location = "/sbms/admin/email/disable/type/"+id;  
                }
            });
        });
           $("#view-all-clients").change(function () {              
                if ($(this).is(':checked')) {
                       window.location = "/sbms/client/view-all";  
                } else {
                      window.location = "/sbms/client/list";  
                }
            });
             $("#view-all-quotes").change(function () {              
                if ($(this).is(':checked')) {
                       window.location = "/sbms/invoice/allQuotes";  
                } else {
                      window.location = "/sbms/nvoice/all-quotes";  
                }
            });
            
             $("#btn-st").click(function () {             
                $("#note-second").toggle();              
            });
             $("#btn-nd").click(function () { 
                $("#note-nd").val("");
                $("#note-second").toggle();              
            });
});

