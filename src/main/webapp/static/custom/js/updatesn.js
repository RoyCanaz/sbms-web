/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
     $('#update-sn1').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid1").val(),
            serialNumber :  $("#serial1").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial1').prop('disabled', true);
                $('#btn-submit-sn1').prop('disabled', true);

              
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn2').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid2").val(),
            serialNumber :  $("#serial2").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial2').prop('disabled', true);
                $('#btn-submit-sn2').prop('disabled', true);

                
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn3').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid3").val(),
            serialNumber :  $("#serial3").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial3').prop('disabled', true);
                $('#btn-submit-sn3').prop('disabled', true);

                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn4').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid4").val(),
            serialNumber :  $("#serial4").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial4').prop('disabled', true);
                $('#btn-submit-sn4').prop('disabled', true);

                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn5').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid5").val(),
            serialNumber :  $("#serial5").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial5').prop('disabled', true);
                $('#btn-submit-sn5').prop('disabled', true);

                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn5').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid5").val(),
            serialNumber :  $("#serial5").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial5').prop('disabled', true);
                $('#btn-submit-sn5').prop('disabled', true);

                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn6').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid6").val(),
            serialNumber :  $("#serial6").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial6').prop('disabled', true);
                $('#btn-submit-sn6').prop('disabled', true);

               
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn7').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid7").val(),
            serialNumber :  $("#serial7").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial7').prop('disabled', true);
                $('#btn-submit-sn7').prop('disabled', true);

              
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn8').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid8").val(),
            serialNumber :  $("#serial8").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial8').prop('disabled', true);
                $('#btn-submit-sn8').prop('disabled', true);

                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn9').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid9").val(),
            serialNumber :  $("#serial9").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial9').prop('disabled', true);
                $('#btn-submit-sn9').prop('disabled', true);

                
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn10').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid10").val(),
            serialNumber :  $("#serial10").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial10').prop('disabled', true);
                $('#btn-submit-sn10').prop('disabled', true);

                c
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn11').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid11").val(),
            serialNumber :  $("#serial11").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial11').prop('disabled', true);
                $('#btn-submit-sn11').prop('disabled', true);

                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn12').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid12").val(),
            serialNumber :  $("#serial12").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial12').prop('disabled', true);
                $('#btn-submit-sn12').prop('disabled', true);

              
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn13').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid13").val(),
            serialNumber :  $("#serial13").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial13').prop('disabled', true);
                $('#btn-submit-sn13').prop('disabled', true);

                
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn14').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid14").val(),
            serialNumber :  $("#serial14").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial14').prop('disabled', true);
                $('#btn-submit-sn14').prop('disabled', true)

               
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn15').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid15").val(),
            serialNumber :  $("#serial15").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial15').prop('disabled', true);
                $('#btn-submit-sn15').prop('disabled', true);

                
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn16').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid16").val(),
            serialNumber :  $("#serial16").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial16').prop('disabled', true);
                $('#btn-submit-sn16').prop('disabled', true);

               
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn17').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid17").val(),
            serialNumber :  $("#serial17").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial17').prop('disabled', true);
                $('#btn-submit-sn17').prop('disabled', true);

                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
    $('#update-sn18').on('submit', function (e){
        e.preventDefault();
        var data = {
            uuid : $("#uuid18").val(),
            serialNumber :  $("#serial18").val()
        };
        var url = $(this).attr('action');
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(data),
            contentType : "application/json",
            success : function (result){
                $('#serial18').prop('disabled', true);
                $('#btn-submit-sn18').prop('disabled', true);

              
                toastr["success"]("Serial Number Saved Successfully", "Notification");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown+jqXHR+textStatus);
                toastr["error"]("Error, Category Not Saved!!!", "Notification");
            }
        });
    });
});

