$(document)
		.ready(
				function() {
					$("#getcab").click(function() {
						getCab();
					});
					
					

					var driverId = localStorage.getItem("userid");
					
					console.log(driverId);
					var Lat;
					var Lon;
					if (navigator.geolocation) {
						navigator.geolocation.getCurrentPosition(function(p) {
							 Lat = p.coords.latitude;
							 Lon = p.coords.longitude;

						});
					}

					var ajax_call = function() {
						console.log("Entered");
						$
								.ajax({
									url : "http://localhost:8080/GoatRunner/application/driver/get_rides?driver_Id="
											+ driverId + "&latitude=" + Lat + "&longitude=" + Lon,
									type : "POST",
									contentType : "application/json",
									success : function(result) {
										$("#rides tbody").remove();
										
										var data = JSON.parse(result);
										console.log(data);
										console.log("Data is : " + data[0].bookingid);
										var tr;
									    for (var i = 0; i < data.length; i++) {
									    	var counter = data[i];
									    	console.log(counter);
									        tr = $('<tr/>');
									        tr.append("<td>" + counter.bookingId + "</td>");
									        tr.append("<td>" + counter.studentId + "</td>");
									        tr.append("<td>" + counter.source + "</td>");
									        tr.append("<td>" + counter.destination + "</td>");
									        tr.append('<td><select id="mySelect"> <option value="0">In Progress<option value="1">Completed<option value="1">Cancelled</select></td></tr>');
									        $('table').append(tr);
									        
									       
									    }
									    
									    
									    if($('#mySelect').val() == 1) {
									    	console.log("Entered");
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
					var interval = 1000*60*1;  
						//1000 * 60 * 1; // where X is your
										// every X
					// minutes
					setInterval(ajax_call, interval);
					
					

				});

function myFunction() {
	var x = document.getElementById("mySelect").value;
	document.getElementById("demo").innerHTML = "You selected: " + x;
}

function getCab() {

	var driverId = localStorage.getItem("userid");
	var password = localStorage.getItem("driverPassword");
	console.log(password);
	$
			.ajax({
				url : "http://localhost:8080/GoatRunner/application/driver/get_cab?driver_Id="
						+ driverId + "&password=" + password,
				type : "POST",
				contentType : "application/json",
				success : function(result) {
					var data = JSON.parse(result);
					// console.log(data.driverid);
					// localStorage.setItem("bookingId",resultData.bookingId);
					localStorage.setItem("cabno", data.cabno);
					console.log(localStorage.getItem("cabno"));
					document.getElementById("Cabid").value = localStorage
							.getItem("cabno");
					console.log("success");
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


function getStatus() {
	console.log("getStatus");
	var bookingId = localStorage.getItem("bookingId");
	$
	.ajax({
		url : "http://localhost:8080/GoatRunner/application/ride/complete?bookingId="
				+ bookingId,
		type : "POST",
		contentType : "application/json",
		success : function(result){
			console.log("success");
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

function Locationfunction() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(p) {
			var Lat = p.coords.latitude;
			var Lon = p.coords.longitude;

		});
	} else {
		alert('Geo Location feature is not supported in this browser.');
	}
}