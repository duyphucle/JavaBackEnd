function myfunc() {
    var x = document.getElementById("form");
    var vl = [];
    var idDapan = [];
    var idCauhoi = [];

    cauhois = document.getElementsByClassName("mrQuestionText");
    var id_cauhoi;
    for (var i = 0; i < cauhois.length; i++) {
        id_cauhoi = cauhois[i].getAttribute("idCauHoi");
        idCauhoi.push(id_cauhoi);

        if (document.querySelectorAll('div[idCauHoi = "' + id_cauhoi + '"] input').length > 0 &&
            document.querySelectorAll('div[idCauHoi = "' + id_cauhoi + '"] input:checked').length === 0) {
            alert("Khong duoc de trong");
            return false;
        }
    }

    txts = document.querySelectorAll('input[type = "text"]');
    for (var i = 0; i < txts.length; i++) {
        if (txts[i].value === "") {
            alert("Khong duoc de trong");
            return false;
        }
    }

    for (var i = 0; i < x.length; i++) {
        if (x.elements[i].checked || x.elements[i].type === "text" || x.elements[i].type === "select-one") {
            vl.push(x.elements[i].value);
            idDapan.push(x.elements[i].getAttribute("idDapAn"));
            x.elements[i].disabled = true;
        }
    }


    document.getElementById("demo").value = vl;
    document.getElementById("demo2").value = idDapan;
    document.getElementById("demo3").value = idCauhoi;
}

$(document).ready(function () {
    hangXe();
    $('select[name ="_QB5__PAGE_QB5__MAKE_F"]').change(function () {
        var selectedXe = $(this).children("option:selected").val();
        dongXe(selectedXe);
    });
});

function hangXe() {
    $.ajax({
        url: "http://localhost:8080/hangxe/",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            $('select[name ="_QB5__PAGE_QB5__MAKE_F"]').html("");
            $.each(data, function (index, value) {
                $('select[name ="_QB5__PAGE_QB5__MAKE_F"]').append(
                    "<option value='" + value + "'>" + value + "</option>"
                );
            });
        }
    });
}

function dongXe(x) {
    $.ajax({
        url: "http://localhost:8080/hangxe/" + x,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            $('select[name ="_QB5__PAGE_QB5__MODEL_F"]').html("");
            $.each(data, function (index, value) {
                $('select[name ="_QB5__PAGE_QB5__MODEL_F"]').append(
                    "<option value='" + value + "'>" + value + "</option>"
                );
            });
        }
    });
}