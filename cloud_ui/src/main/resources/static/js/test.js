
function test() {
    var parameterSet = {};
    parameterSet.url = "http://localhost:8090/mobile/queryRain"
    parameterSet.parameters={test:"ts", startTime:"2017-06-02", endTime:"2017-8-2"}
    post(parameterSet);
}

function post(parameterSet) {
    $.ajax({
        type: "post",
        url : parameterSet.url,
        dataType:"json",
        contentType : 'application/json; charset=UTF-8',
        data : JSON.stringify(parameterSet.parameters || {}),
        success : function (data) {
            console.log(data);
        },
        error : function (data) {
            console.log(data);
        },
        beforeSend : function (request) {
            request.setRequestHeader("authority", localStorage.getItem("authority"));
        }
    });
}

