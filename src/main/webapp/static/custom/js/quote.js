/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    //  $("#listTable").DataTable();
     $("#add-products").click(function(){
     getQuoteProductIds();
   });
   $("#add-products-edit").click(function(){
        getQuoteProductIdsEdit();
   });
    function productTable(){ 
    var table =  $("#productsTableAll").DataTable({
       "columnDefs" : [{
           "targets" :0,
           "render" : function(data, type, row, meta){
               if(type==='display'){
                   data= '<div class="checkbox"><input type="checkbox" class="dt-checkboxes"><label></label></div>';
               }
                 return  data;
           },
           "checkboxes":{
               "selectRow":false,
               "selectAllRender": '<div class="checkbox"><input type="checkbox" class="dt-checkboxes"><label></label></div>'
           }
       } ],
       "select":{
           "style":"multi"
       },
       "order":[[1, "asc"]]
    });
        return table;
}
 var table = productTable();  
    //rest mobile sbms
     var all_rows = [];    
   $.ajax({
                            type: 'GET',
                           url: "/sbms/rest/web/quote/ids",
                           success: function(data){
                                console.log(data);  
                                      $.each(data, function (index, elem){                                       
                                       var this_row={}; 
                                      
                                       console.log(elem.productId);   
                                       this_row["productId"] =elem.productId;
                                       this_row["note"] = elem.note;
                                       all_rows.push(this_row);
                                      });
                                console.log(all_rows);    
                           },
                           error:function(){
                            
                               alert("error");
                           }
    });
function getQuoteProductIds(){
     
      var rows_selected = table.column(0).checkboxes.selected();
     // var ids = new Array();
   //   var all_rows = [];
      $.each(rows_selected, function (index, rowwId){
          var text = $("#"+rowwId+"-note").val();
           var this_row={};         
           this_row["productId"] = rowwId;
           this_row["note"] = text;
           all_rows.push(this_row);
         // ids.push(rowwId);
      });
    //var data = ids.toString();
    console.log(all_rows);
    var data = JSON.stringify(all_rows);
    console.log(data);
    if(all_rows.length>0){
                $.ajax({      
                        type : "POST",
                        url : "/sbms/rest/web/quote/product_id_note",
                        data : data,
                        contentType : "application/json",
                        success: function (result) {
                            console.log(result);
                         window.location = "/sbms/quote/list";   
                         toastr["success"]("Product(s) Added", "Notification");
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            toastr["error"]("Error, Failed to Add Product(s)", "Notification");
                            console.log(errorThrown);
                        }
                    });
            }
} 
function getQuoteProductIdsEdit(){
     
      var rows_selected = table.column(0).checkboxes.selected();
     // var ids = new Array();
   //   var all_rows = [];
      $.each(rows_selected, function (index, rowwId){
          var text = $("#"+rowwId+"-note").val();
           var this_row={};         
           this_row["productId"] = rowwId;
           this_row["note"] = text;
           all_rows.push(this_row);
         // ids.push(rowwId);
      });
    //var data = ids.toString();
    console.log(all_rows);
    var data = JSON.stringify(all_rows);
    console.log(data);
    if(all_rows.length>0){
                $.ajax({      
                        type : "POST",
                        url : "/sbms/rest/web/quote/edit/product_id_note",
                        data : data,
                        contentType : "application/json",
                        success: function (data) {
                            console.log(data);                 
                               window.location = "/sbms/quote/edit/"+data;          
                         toastr["success"]("Product(s) Added", "Notification");
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            toastr["error"]("Error, Failed to Add Product(s)", "Notification");
                            console.log(errorThrown);
                        }
                    });
            }
} 


//Currency On Change
 $("#currency-id").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                       var link = "/sbms/rest/web/quote/currency/" + id;
                       $.ajax({
                           type: 'GET',
                           url: "/sbms/rest/web/quote/currency/" + id,
                           success: function(data){
                               window.location = "list";                           
                           },
                           error:function(){
                             //  $(".mdb-select").material_select();
                               alert("error");
                           }

                       });
           });
            $("#currency-id-edit").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                       $.ajax({
                           type: 'GET',
                           url: "/sbms/rest/web/quote/edit/currency/" + id,
                           success: function(data){
                              console.log(data);                 
                               window.location = "/sbms/quote/edit/"+data;  
                           },
                           error:function(){
                             //  $(".mdb-select").material_select();
                               alert("error");
                           }

                       });
           });
           
           
           $("#bankDetail-id").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                       $.ajax({
                           type: 'GET',
                           url: "/sbms/rest/web/quote/bank/" + id,
                           success: function(data){
                                 window.location = "list";                             
                           },
                           error:function(){
                               alert("error");
                           }

                       });
           });
           $("#bankDetail-id-edit").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                       $.ajax({
                           type: 'GET',
                           url: "/sbms/rest/web/quote/edit/bank/" + id,
                           success: function(data){
                                  console.log(data);                 
                               window.location = "/sbms/quote/edit/"+data;                            
                           },
                           error:function(){
                               alert("error");
                           }

                       });
           });
            $("#client-id").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                       $.ajax({
                           type: 'GET',
                           url: "/sbms/rest/web/quote/client/" + id,
                           success: function(data){
                                 window.location = "list";                             
                           },
                           error:function(){
                               alert("error");
                           }

                       });
           });
            $("#contact-id").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                       $.ajax({
                           type: 'GET',
                           url: "/sbms/rest/web/quote/contact/" + id,
                           success: function(data){
                                 window.location = "list";                             
                           },
                           error:function(){
                               alert("error");
                           }

                       });
           });
                $("#contact-id-edit").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                       $.ajax({
                           type: 'GET',
                            url: "/sbms/rest/web/quote/edit/contact/" + id,
                           success: function(data){
                                   console.log(data);                 
                               window.location = "/sbms/quote/edit/"+data;                             
                           },
                           error:function(){
                               alert("error");
                           }

                       });
           });
           
});


