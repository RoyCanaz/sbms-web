/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
   
    $(document).ready(function(){
        $("#modalNewQuote").modal('show');       
        
      
                $("#pwd").hide();
                $("#cpwd").hide();
      
        
        $("#changePassword").click(function () {             
                $("#pwd").toggle();
                $("#cpwd").toggle();
        });
   
            $("#company-id").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                     //  $(".mdb-select").material_select("destroy");
                       var link = "/sbms/rest/gb/company/" + id;
                       $.ajax({
                           type: 'GET',
                           url: "/sbms/rest/gb/company/" + id,
                           success: function(data){
                               window.location = "/sbms/admin/dashboard";                           
                           },
                           error:function(){
                             //  $(".mdb-select").material_select();
                               alert("error");
                           }

                       });
           });
            $("#inactiveEmail").on('click', function(e){ 
             e.preventDefault();
            var id = $(this).val();
            $.ajax({
                type: 'GET',
                url: "/sbms/rest/admin/email/default/enable/" + id,
                success: function(data){
                    window.location = "/sbms/admin/email/default/list";                           
                },
                error:function(){
                  //  $(".mdb-select").material_select();
                    alert("error");
                }
 });
            });
             $("#activeEmail").on('click', function(e){ 
             e.preventDefault();
                var id = $(this).val();
                $.ajax({
                type: 'GET',
                url: "/sbms/rest/admin/email/default/disable/" + id,
                success: function(data){
                    window.location = "/sbms/admin/email/default/list";                           
                },
                error:function(){
                  //  $(".mdb-select").material_select();
                    alert("error");
                }

            });
});
/*$("#disableAcc").on('click', function(e){ 
             e.preventDefault();
            var id = $(this).val();
            $.ajax({
                type: 'GET',
                url: "/rest/admin/email/enable/" + id,
                success: function(data){
                    console.log(id);
                    
                  //  window.location = "/admin/email/list";                           
                },
                error:function(){
                    
                    alert("error");
                }
 });
            });*/
       
        
        
             $("#enableAcc").on('click', function(e){ 
             e.preventDefault();
                var id = $(this).val();
                $.ajax({
                type: 'GET',
                url: "/sbms/rest/admin/email/disable/" + id,
                success: function(data){
                    console.log(id);
                 //   window.location = "/admin/email/list";                           
                },
                error:function(){
                    alert("error");
                }

            });
});

    });
    


