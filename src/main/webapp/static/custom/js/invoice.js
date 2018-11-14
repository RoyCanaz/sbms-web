/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    
   //   $("#listTable").DataTable();
      $("#add-invoice-products").click(function(){
        getInvoiceProductIds();
   });
   
   
   
     function productTable(){ 
    var table =  $("#productsTable").DataTable({
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
   var all_rows = [];    
   $.ajax({
                            type: 'GET',
                           url: "/sbms/rest/web/invoice/ids",
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
    function getInvoiceProductIds(){
        
      var rows_selected = table.column(0).checkboxes.selected();
  
      $.each(rows_selected, function (index, rowwId){
          var text = $("#"+rowwId+"-note").val();
         var this_row={};         
           this_row["productId"] = rowwId;
           this_row["note"] = text;
           all_rows.push(this_row);
      });
    console.log(all_rows);
    var data = JSON.stringify(all_rows);
    console.log(data);
    if(all_rows.length>0){
                $.ajax({      
                        type : "POST",
                         url : "/sbms/rest/web/invoice/product_id_note",
                        data : data,
                        contentType : "application/json",
                        success: function (result) {
                            console.log(result);
                         window.location = "/sbms/invoice/new";   
                         toastr["success"]("Product(s) Added", "Notification");
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            toastr["error"]("Error, Failed to Add Product(s)", "Notification");
                            console.log(errorThrown);
                        }
                    });
            }
} 




$("#currency-id").on('change', function(e){ 
                        e.preventDefault();
                       var id = $(this).val();
                       var link = "/sbms/rest/web/invoice/currency/" + id;
                       $.ajax({
                           type: 'GET',
                           url: "/sbms/rest/web/invoice/currency/" + id,
                           success: function(data){
                               window.location = "new";                           
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
                           url: "/sbms/rest/web/invoice/bank/" + id,
                           success: function(data){
                                 window.location = "new";                             
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
                           url: "/sbms/rest/web/invoice/client/" + id,
                           success: function(data){
                                 window.location = "new";                             
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
                           url: "/sbms/rest/web/invoice/contact/" + id,
                           success: function(data){
                                 window.location = "new";                             
                           },
                           error:function(){
                               alert("error");
                           }

                       });
           });
    
});


