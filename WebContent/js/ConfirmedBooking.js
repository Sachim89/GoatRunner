$(document).ready(function(){
	
	//Get the values from backend and display in HTML
   $.getJSON('http://localhost:8080/GoatRunner/application/user/booking', function(c) {
       $('#bookingID').val(c.booking_id);
       $('#driverName').val(c.driver_name);
       $('#driverNumber').val(c.driver_number);
       $('#cabNumber').val(c.cab_number);
       $("#rate").val(c.rate);
       localStorage.setItem("BookingId",c.booking_id);
    });
   //Logout functionality
   $("#logout").click(function(){
		logout();
	})
	
	//Details needed for processing
	var id = localStorage.getItem("StudentID");
	var to = localStorage.getItem("toAddress");
	var from = localStorage.getItem("fromAddress");
	var bookid = localStorage.getItem("BookingId");
	var numpass= localStorage.getItem("numPass");
	//Cancelling the request
	$("#cancel").click(function(){
		alert("You have cancelled the booking");
		
		$.ajax({
			url: "http://localhost:8080/GoatRunner/application/user/Cancel?bookingid="+bookid,
			type: "GET",
			contentType :"application/json",
			success: function(resultData) 
			{
				window.location.href = "http://localhost:8080/GoatRunner/BookingPage.html"
			}
		});
	}); 	
	
	var time = setTimeout(disable, 8000);
	document.getElementById("log").innerHTML = "Logged in as: " +x+ "  ";
	
	function disable() {
		p = document.getElementById("count");
	    x = document.getElementById("cancel");
	    x.disabled = true; p.disabled = true;
	    x.parentNode.removeChild(x);
	    p.parentNode.removeChild(p);
	}

     var directionsService = new google.maps.DirectionsService();
     var directionsDisplay = new google.maps.DirectionsRenderer();

     var map = new google.maps.Map(document.getElementById('map'), {
       zoom:7,
       mapTypeId: google.maps.MapTypeId.ROADMAP
     });
    
     directionsDisplay.setMap(map);
     directionsDisplay.setPanel(document.getElementById('panel'));

     var request = {
       origin: from, 
       destination: to,
       travelMode: google.maps.DirectionsTravelMode.DRIVING
     };

     directionsService.route(request, function(response, status) {
       if (status == google.maps.DirectionsStatus.OK) {
         directionsDisplay.setDirections(response);
       }
     });
});

//Logout functionality
function logout(){
	if(!confirm("Do you want to logout?")){
		var id = localStorage.getItem("StudentID");
			
		$.ajax({
			url: "http://localhost:8080/GoatRunner/application/user/logout?userId="+id,
			type: "POST",
			data : JSON.stringify(j),
			contentType :"application/json",
			success: function(resultData) 
			{
				window.location.href = "http://localhost:8080/GoatRunner/ConfirmedBooking.html"
			}
		});
	}
	else{
		window.location.href = "http://localhost:8080/GoatRunner/HomePage.html"
	}
//	localStorage.removeItem("StudentID");
}