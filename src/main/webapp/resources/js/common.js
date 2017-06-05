/**
 * Created by Oleg on 27.05.2017.
 */
$(document).ready(function () {
    var x;
    $(".loginpopup").magnificPopup();
    $(document).on('click', 'a[name=formref]', function (e) {
        x = $(this).data("id");
        $.magnificPopup.open({
            items: {
                src: "#forma",
                type: 'inline',
            },
            closeBtnInside: true
        });

    });
    $(document).on('click', '#senders', function (e) {
        var file = $("#forma").serialize();
        $.ajax({
            url: "/" + x + "/addapplication",
            type: "POST",
            data: file,
            success: function (result) {
                $.magnificPopup.close();
                if (result.status == "error") {
                    $.magnificPopup.open({
                        items: {
                            src: "#forma",
                            type: 'inline',
                        },
                        closeBtnInside: true
                    });
                    for (var i = 0; i < result.fieldErrors.length; i++) {
                        alertMessage(result.fieldErrors[i].defaultMessage, "danger", 10000);
                    }
                }
                else {
                    $("#forma")[0].reset();
                    alertMessage(result.status, "info", 4000);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });

        return false;
    });
    $(document).on('click', '.appnav', function (e) {
        $.ajax({
            url: "/admin",
            type: "GET",
            success: function (result) {
                applicationTable(result);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == 901) {
                    alertMessage("you session was expired,you will redirect to home page", "danger", 4000);
                    setTimeout(function () {
                        window.location.href = "http://localhost:8087/";
                    }, 2000);
                }
            }
        });
        return false;
    });
    $(document).on('click', '.ordnav', function (e) {
        $.ajax({
            url: "/admin/orders",
            type: "GET",
            success: function (result) {
                orderTable(result);

            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == 901) {
                    alertMessage("you session was expired,you will redirect to home page", "danger", 4000);
                    setTimeout(function () {
                        window.location.href = "http://localhost:8087/";
                    }, 2000);
                }
            }
        });
        return false;
    });
    $(document).on('click', '#send', function (e) {
        var form = $("#loginform").serialize();
        $.ajax({
            url: "/login",
            type: "POST",
            data: form,
            success: function (result) {
                $(".collapse").append('<ul class="nav navbar-nav navbar-left"> <li><a href="#" class="appnav">Applications</a></li> </ul>')
                    .append('<ul class="nav navbar-nav navbar-left"> <li><a href="#some" class="ordnav">Orders</a></li></ul>')
                    .append('<ul class="nav navbar-nav navbar-left"> <li><a href="#some" class="blnav">Black List</a></li></ul>');
                $(".navbar-right").empty();
                $(".navbar-right").append(' <li><a href="#logout" class="logout">Log out</a></li>');
                applicationTable(result);
                $.magnificPopup.close();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });

        return false;
    });
    $(document).on('click', '.blnav', function (e) {
        $.ajax({
            url: "/admin/blacklist",
            type: "GET",
            success: function (result) {
                blackListTable(result);

            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == 901) {
                    alertMessage("you session was expired,you will redirect to home page", "danger", 4000);
                    setTimeout(function () {
                        window.location.href = "http://localhost:8087/";
                    }, 2000);


                }
            }
        });
        return false;
    });
    $(document).on('click', 'a[name=addorder]', function () {
        x = $(this).data('id');
        $.ajax({
            url: "/admin/" + x + "/addorder",
            type: "GET",
            success: function (result) {
                if(result.status=="error"){
                    alertMessage("you cannot add this order","danger",4000)
                }else {
                    applicationTable(result.applicationList);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
        return false;
    });
    $(document).on('click', 'a[name=closeapp]', function () {
        x = $(this).data('id');
        $.ajax({
            url: "/admin/" + x + "/closeapp",
            type: "GET",
            success: function (result) {
                applicationTable(result);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
        return false;
    });
    $(document).on('click', 'a[name=closeord]', function () {
        x = $(this).data('id');
        $.ajax({
            url: "/admin/" + x + "/closeord",
            type: "GET",
            success: function (result) {
                orderTable(result)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
        return false;
    });
    $(document).on('click', 'a[name=updateord]', function () {
        x = $(this).data('id');
        $.magnificPopup.open({
            items: {
                src: "#updateordform",
                type: 'inline',
            },
            closeBtnInside: true
        });
        return false;
    });
    $(document).on('click', '#sendupdate', function (e) {
        var form = $("#updateordform").serialize();
        $.ajax({
            url: "/admin/" + x + "/updateord",
            type: "GET",
            data: form,
            success: function (result) {
                orderTable(result);
                $.magnificPopup.close();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });

        return false;
    });
    $(document).on('click', '.logout', function (e) {

        $(".logout1").submit();

        return false;
    });

});
function applicationTable(result) {
    $(".table").empty();
    $(".table").addClass("table-bordered");
    $(".table").append(' <thead> <tr class="table-hover"><td>Name</td> <td>LastName</td> <td>Passnum</td><td>Carname</td><td>Reject</td><td>Accept</td></tr> </thead>')
        .append('<tbody class="appresult"></tbody>');
    for (var i = 0; i < result.length; i++) {
        $(".appresult").append('<tr><td>' + result[i].firstname + '</td><td>' + result[i].lastname + '</td><td>' + result[i].passnum + '</td><td>' + result[i].carByCarId.make + '</td><td><a href="#" class=" btn btn-danger" data-id="' + result[i].id + '" name="closeapp">Submit</a></td><td><a href="#" class="btn btn-info" data-id="' + result[i].id + '"name="addorder">Submit</a></td></tr>');
    }
}
function orderTable(result) {
    $(".table").empty();
    $(".table").addClass("table-bordered");
    $(".table").append(' <thead> <tr class="table-hover"><td>Name</td> <td>LastName</td><td>retdate</td><td>RepCost</td><td>Close</td><td>Update</td></tr></thead>')
        .append('<tbody class="ordresult"></tbody>');
    for (var i = 0; i < result.length; i++) {
        $(".ordresult").append('<tr><td>' + result[i].appByAppId.firstname + '</td><td>' + result[i].appByAppId.lastname + '</td><td>' + result[i].retdate + '</td><td>' + result[i].repaircost + '</td><td><a href="#" class=" btn btn-danger" data-id="' + result[i].id + '" name="closeord">Submit</a></td><td><a href="#" class="btn btn-info" data-id="' + result[i].id + '" name="updateord">Submit</a></td></tr>');
    }
}
function blackListTable(result) {
    $(".table").addClass("table-bordered");
    $(".table").empty();
    $(".table").append(' <thead> <tr class="table-hover"><td>Id</td> <td>PassNum</td></tr></thead>')
        .append('<tbody class="blresult"></tbody>');
    for (var i = 0; i < result.length; i++) {
        $(".blresult").append('<tr><td>' + result[i].id + '</td><td>' + result[i].passnum + '</td></tr>');
    }
}
function alertMessage(message, type, delay) {
    $.bootstrapGrowl(message, // Messages
        { // options
            type: type, // info, success, warning and danger
            ele: "body", // parent container
            offset: {
                from: "top",
                amount: 55
            },
            align: "right", // right, left or center
            width: 400,
            delay: delay,
            allow_dismiss: true, // add a close button to the message
            stackup_spacing: 10
        });
}