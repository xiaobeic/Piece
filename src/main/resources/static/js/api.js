function changeSidebarColor(sidebarName) {
    sidebarName == "sidebarUser" ? $("#sidebarUser").addClass("active") : $("#sidebarUser").removeClass("active");
    sidebarName == "sidebarJJG" ? $("#sidebarJJG").addClass("active") : $("#sidebarJJG").removeClass("active");
    sidebarName == "sidebarGamePiece" ? $("#sidebarGamePiece").addClass("active") : $("#sidebarGamePiece").removeClass("active");
}

function editUser() {
    $("#table").css("display", "none");
    $("#modifyOrCreate").css("display", "block");
}

function addUser() {
    $("#table").css("display", "none");
    $("#modifyOrCreate").css("display", "block");
}

function cancelAddUser() {
    $("#modifyOrCreate").css("display", "none");
    $("#table").css("display", "block");
}

function submitAddUser() {
    $("#modifyOrCreate").css("display", "none");
    $("#table").css("display", "block");
}

function editJJG(jumboJackpotId) {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/admin/jumboJackpot/getJumboJackpot',
        data: {'jumboJackpotId': jumboJackpotId },
        dataType: 'json',
        success: function(result){
            console.log(result);
            $("#jumboJackpotId").val(result.jumboJackpotId);
            $("#jjgName").val(result.name);
            $("#totalPieces").val(result.totalPieces);
            $("#pieceType").val(result.pieceType);
            $("#racePieces").val(result.racePieces);
            $("#raceRatio").val(result.raceRatio);
            $("#formDate").val(getFormatDate(result.formDate));
            $("#toDate").val(getFormatDate(result.toDate));

            $("#table").css("display", "none");
            $("#modifyOrCreate").css("display", "block");

            $("#jjgAdd").css("display", "none");
            $("#jjgUpdate").css("display", "block");
        },
        error: function(result) {
            alert('Request exception!');
        }
    });
}

function getFormatDate(formatDate){
    var formatDate= new Date(formatDate);
    var year = formatDate.getFullYear();
    var month = formatDate.getMonth() + 1 < 10 ? "0" + (formatDate.getMonth() + 1) : formatDate.getMonth() + 1;
    var date = formatDate.getDate() < 10 ? "0" + formatDate.getDate() : formatDate.getDate();
    return year + "-" + month + "-" + date;
}

function deleteJJG(jumboJackpotId) {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/admin/jumboJackpot/removeJumboJackpot',
        data: {'jumboJackpotId': jumboJackpotId },
        success: function(result){
            if (result == 0) {
                alert('invalied paramter!');
            } else if (result == 1) {
                alert('Jumbo jackpot in operation cannot be deleted.');
            } else {
                alert('Delete success!');
                $("#table").css("display", "none");
                $("#modifyOrCreate").css("display", "block");
                window.location.href = "http://localhost:8080/components/jjg.html";
            }
        },
        error: function(result) {
            alert('Request exception!');
        }
    });
}

function addJJG() {
    $("#table").css("display", "none");
    $("#modifyOrCreate").css("display", "block");
}

function cancelAddJJG() {
    $("#modifyOrCreate").css("display", "none");
    $("#table").css("display", "block");
}

function submitAddJJG() {
    $.ajax({
        type: 'post',
        url: 'http://localhost:8080/admin/jumboJackpot/save',
        data: $('#addJumboJackpot').serialize(),
        success: function(result){
            alert('Save success!');
            window.location.href = "http://localhost:8080/components/jjg.html";
        },
        error: function(result) {
            alert('invalid parameter!');
        }
    });
}

function submitUpdateJJG() {
    $.ajax({
        type: 'post',
        url: 'http://localhost:8080/admin/jumboJackpot/update',
        data: $('#addJumboJackpot').serialize(),
        success: function(result){
            alert('Update success!');
            window.location.href = "http://localhost:8080/components/jjg.html";
        },
        error: function(result) {
            alert('invalid parameter!');
        }
    });
}

function addNewRow() {
    var index = $("#gamePieceTable tr").length;

    var jjgOptions = $("#JJGSelector" + (index - 1) ).html();
    var lastSelectedValue = $("#JJGSelector" + (index - 1) + "  option:selected").text();

    jjgOptions = jjgOptions.replace('<option value="' + lastSelectedValue + '">' + lastSelectedValue + '</option>', '');
    var jjgSelector = '<td><select  class="form-control" id="JJGSelector' + index + '">' + jjgOptions + '</select></td>';

    var queryTime = '<td><input class="form-control" placeholder="100" id="queryTimeInput' + index + '"></td>';

    var userOptions = $('#userSelector1').html();
    var userSelector = '<td style="width: 40%">' + '<select id="userSelector' + index + '" multiple="multiple" style="visibility: hidden">' + userOptions + '</select></td>';

    $("#gamePieceTableBody").append('<tr>' + jjgSelector + userSelector + queryTime + '</tr>');
    $("#userSelector" + index).multipleSelect({
        placeholder: "Select one or more than one user"
    });

    var isLastJJGOption = jjgOptions.split('value=').length == 3 ? true : false;
    if (isLastJJGOption) {
        $("#anotherRow").attr("disabled", true);
    }
}

function requestGamePieces() {
    var index = $("#gamePieceTable tr").length;
    var params = new Array();
    var passed = true;
    var totalQueryTime = 0;

    //Collect params
    for (var i = 1; i < index; i ++) {
        var selectedUser = $("#userSelector" + i).val();
        var selectedJJG = $("#JJGSelector" + i + "  option:selected").text();
        var queryTimes = $("#queryTimeInput" + i).val();

        if (selectedUser == "") {
            $('.ms-choice:eq(' + (i - 1) + ')').css('border-color', 'red');
            passed = false;
        } else {
            $('.ms-choice:eq(' + (i - 1) + ')').css('border-color', '#d2d6de');
        }
        if (queryTimes == "" || queryTimes <= 0 || !/^\d+$/.test(queryTimes)) {
            $("#queryTimeInput" + i).css('border-color', 'red');
            passed = false;
        } else {
            $("#queryTimeInput" + i).css('border-color', '#d2d6de');
        }
        var param = new Array();
        param[0] = selectedJJG;
        param[1] = selectedUser;
        param[2] = queryTimes;
        params[i - 1] = param;

        totalQueryTime += selectedUser.toString().split(',').length * queryTimes;
    }

    //request
    var completeTimes = 0;
    var queryFailTimes = 0;

    for (var i = 0; i < params.length; i++) {
        var param = params[i];
        var JJGID = param[0];
        for (var index = 1; index <= param[2]; index ++) {
            var users = param[1].toString().split(',');
            for (var j = 0; j < users.length; j++) {
                $.ajax({
                    type: 'get',
                    url: 'http://localhost:8080/admin/jumboJackpot/getPiece',
                    dataType: 'json',
                    data:{jumboJackpotId : JJGID, playerId : users[j]},
                    success: function(result){
                        completeTimes ++;
                        console.log(result);
                    },
                    error: function(result) {
                        console.log(result);
                        queryFailTimes ++;
                    }
                });
            }
        }
    }
}


