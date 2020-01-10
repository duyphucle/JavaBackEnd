var user = user || {};

user.openAddEditUser = function() {
    $("#addEditUser").modal("show");
};

user.save = function() {
    if ($("#frmAddEditUser").valid()) {
        var a = $("#firstname").val();
        var b = $("#lastname").val();
        var c = $("#province").val();
        abc2(a,b,c);
    }
};

$(document).ready(function() {

    abc();
    setInterval(function() {
        abc();
    }, 300);
});

function abc() {
    $.ajax({
        url: "http://localhost:8080/customers/",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function(data) {
            $("#tbUser").html("");
            $.each(data, function(index, value) {
                //len truoc
                $("#tbUser").prepend(
                    "<tr>" +
                    "<td><a href='/view-customer/" + value.id + "'>" + value.firstName + "</a></td>" +
                    "<td><a href='/view-customer/" + value.id + "'>" + value.lastName + "</a></td>" +
                    "<td><a href='/view-customer/" + value.id + "'>" + value.province.name + "</a></td>" +
                    "<td><a href='/edit-customer/" + value.id + "' class='btn btn-block btn-primary btn-flat'>Edit</a></td>" +
                    "<td><a href='/delete-customer/" + value.id + "' class='btn btn-block btn-primary btn-flat' onclick='return confirm('Are you sure?')'>Delete</a></td>" +
                    "</tr>"
                );
            });
        }
    });
}

function abc2(a,b,c) {
    $.ajax({
        url: "http://localhost:8080/customers/",
        method: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            firstName: a,
            lastName: b,
            province:{id:c}
        }),
        success: function(data) {
            console.log("fgfdg");
        }
    });
}