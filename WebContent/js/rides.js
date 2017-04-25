
$(document).ready(function () {


	var ajax_call = function() {
$.getJSON("http://localhost:8080/GoatRunner/application/",function (data) {
    var tr;
    for (var i = 0; i < data.length; i++) {
        tr = $('<tr/>');
        tr.append("<td>" + data[i].BookingID + "</td>");
        tr.append("<td>" + data[i].StudentID + "</td>");
        tr.append("<td>" + data[i].TO + "</td>");
        tr.append("<td>" + data[i].FROM + "</td>");
        tr.append('<td><select id="mySelect" onchange="myFunction()"> <option value="In Progress">In Progress<option value="completed">Completed<option value="Cancelled">Cancelled</select></td></tr>');
        $('table').append(tr);
    }
});



	}
var interval = 1000 * 60 * 1; // where X is your every X minutes

setInterval(ajax_call, interval);

});

function myFunction() {
    var x = document.getElementById("mySelect").value;
    document.getElementById("demo").innerHTML = "You selected: " + x;
}

function getcab(){
	$.getJSON("http://localhost:8080/GoatRunner/application/",function (data) {
		$('#getcab').val(data.cabid);
	});
}