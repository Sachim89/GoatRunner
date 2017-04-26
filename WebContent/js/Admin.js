$(document).ready(function() {

	//On click of the cab number, get the details
	$("#cab1").click(function(){
		cab_number(1);
	});
	
	$("#cab2").click(function(){
		cab_number(2);
	});
	
	$("#cab3").click(function(){
		cab_number(3);
	});
	
	$("#cab4").click(function(){
		cab_number(4);
	});
	
	$("#cab5").click(function(){
		cab_number(5);
	});
	
	$("#cab6").click(function(){
		cab_number(6);
	});
});

//Function call to the backend to retrive the details associated with the particular cab
function cab_number(cabNo){
	$.ajax({
		url : "http://localhost:8080/GoatRunner/application/admin="+cabNo,
		type : "GET",
		contentType : "application/json",
		success : function(result) 
		{
			$("#rides tbody").remove();
			var data = JSON.parse(result);
			var tr;
			for (var i = 0; i < data.length; i++) 
			{
				var counter = data[i];
				tr = $('<tr/>');
				tr.append("<td>"+ counter.bookingId+ "</td>");
				tr.append("<td>"+ counter.studentId+ "</td>");
				tr.append("<td>" + counter.source+ "</td>");
				tr.append("<td>"+ counter.destination+ "</td>");
				tr.append("<td>"+ counter.driverId+ "</td>");
				$('table').append(tr);
			}
		},
		error : function(data) {
			if (data == 400) {
				alert("Oops!! Somethings went wrong.. Please try after sometime");
			}
			if (data == 500) {
				alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
			}
	});	
}
