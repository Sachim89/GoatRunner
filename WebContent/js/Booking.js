//Display the map on page load
if (navigator.geolocation) 
	{
		//var x = document.cookie;
		//document.getElementById("log").innerHTML = "Logged in as: "+x+"  ";
	
		navigator.geolocation.getCurrentPosition(function(p) 
		{
			var LatLng = new google.maps.LatLng(p.coords.latitude, p.coords.longitude);
			var mapOptions = 
			{
				center : LatLng,
				zoom : 13,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
				
			var map = new google.maps.Map(document.getElementById("map"), mapOptions);
			google.maps.event.addListener(marker,"click",
			function(e) {
				var infoWindow = new google.maps.InfoWindow();
				infoWindow.setContent(marker.title);
				infoWindow.open(map, marker);
				});
			});
		} else {
			alert('Geo Location feature is not supported in this browser.');
	}

//Get the current location id "Use current location" is requested
function Locationfunction() 
{
	if (navigator.geolocation) 
	{
		navigator.geolocation.getCurrentPosition(function(p) {
		var LatLng = new google.maps.LatLng(p.coords.latitude,p.coords.longitude);
		var mapOptions = {
		center : LatLng,
		zoom : 13,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var geocoder = geocoder = new google.maps.Geocoder();
	geocoder.geocode({'latLng' : LatLng },
	function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if (results[0]) {
					document.getElementById("fromAddress").value = results[0].formatted_address;
			}
		}
	});
	var map = new google.maps.Map(document.getElementById("map"), mapOptions);
	var marker = new google.maps.Marker({
			position : LatLng,
			map : map,
	});
	
	google.maps.event.addListener(marker,"click",function(e) 
	{
		var infoWindow = new google.maps.InfoWindow();
		infoWindow.open(map,marker);
	});
	});
	} else {
			alert('Geo Location feature is not supported in this browser.');
		}
	}
		
//Check Route option shows the routes
function direction()
{
	var directionsService = new google.maps.DirectionsService();
     var directionsDisplay = new google.maps.DirectionsRenderer();

     var map = new google.maps.Map(document.getElementById('map'), {
       zoom:7,
       mapTypeId: google.maps.MapTypeId.ROADMAP
     });
    
     directionsDisplay.setMap(map);
     directionsDisplay.setPanel(document.getElementById('panel'));

     var request = {
       origin: document.getElementById("toAddress").value, 
       destination:document.getElementById("fromAddress").value,
       travelMode: google.maps.DirectionsTravelMode.DRIVING
     };

     directionsService.route(request, function(response, status) {
       if (status == google.maps.DirectionsStatus.OK) {
         directionsDisplay.setDirections(response);
       }
     });
}

//On Enter key get To-Address and point in the Map
function searchTo()	
{
	var lat_to, lng_to;
	if (event.keyCode == 13) 
	{
		var to = document.getElementById("toAddress").value;
		var geocoder = geocoder = new google.maps.Geocoder();
		geocoder.geocode({'address' : to}, function(results, status){
		if (status == google.maps.GeocoderStatus.OK) {
				lat_to = results[0].geometry.location.lat();
				lng_to = results[0].geometry.location.lng();
		}
		
		var myLatLng = {lat : lat_to, lng : lng_to};
		var map = new google.maps.Map(document.getElementById("map"),{
		zoom : 13,
		center : myLatLng
		});
		var markerTo = new google.maps.Marker({
		position : myLatLng,
		map : map,
		});
				
		google.maps.event.addListener(marker,"click",function(e) 
		{
			var infoWindow = new google.maps.InfoWindow();
			infoWindow.open(map,markerTo);
		});		
		});
	}
}

//On Enter key get From-Address and point in the Map
function searchFrom() 
{
	var lat_from, lng_from;
	if (event.keyCode == 13) 
	{
		var from = document.getElementById("fromAddress").value;
		var geocoder = geocoder = new google.maps.Geocoder();
		geocoder.geocode({'address' : from}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				lat_from = results[0].geometry.location.lat();
				lng_from = results[0].geometry.location.lng();
			}
			var myLatLng = {lat : lat_from, lng : lng_from};
			var map = new google.maps.Map(document.getElementById("map"), {
			zoom : 13,
			center : myLatLng
		});
			var markerFrom = new google.maps.Marker({
			position : myLatLng,
			map : map,
		});
		google.maps.event.addListener(marker,"click",function(e) 
		{
			var infoWindow = new google.maps.InfoWindow();
			infoWindow.open(map,markerFrom);
		});				
		});
		document.getElementById("direction").innerHTML = 
			'<a href="#" onclick="direction()">Check Route</a>'
	}
}

//Get the current location id "Use current location" is requested
function Locationfunction() 
{
if (navigator.geolocation) 
{
	navigator.geolocation.getCurrentPosition(function(p) {
	var LatLng = new google.maps.LatLng(p.coords.latitude,p.coords.longitude);
	var mapOptions = {
	center : LatLng,
	zoom : 13,
	mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var geocoder = geocoder = new google.maps.Geocoder();
	geocoder.geocode({'latLng' : LatLng },
		function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
		if (results[0]) {
			document.getElementById("fromAddress").value = results[0].formatted_address;
		}
		}
	});
	var map = new google.maps.Map(document.getElementById("map"), mapOptions);
	var marker = new google.maps.Marker({
			position : LatLng,
			map : map,
	});

	google.maps.event.addListener(marker,"click",function(e) 
	{
		var infoWindow = new google.maps.InfoWindow();
		infoWindow.open(map,marker);
	});
	});
} else {
		alert('Geo Location feature is not supported in this browser.');
	}
}

//Request the cab
function request()
{
	console.log("Request created");
	var to = document.getElementById("toAddress").value;
	var from = document.getElementById("fromAddress").value;
	var numPass = document.getElementById("numPass").value;

	
	var j = 
	{
		"toAddress": to,
		"fromAddress": from,
		"numberPass": numPass
	}
	
	$.ajax({
		url: "http://localhost:8080/GoatRunner/application/user/booking",
		type: "POST",
		data : JSON.stringify(j),
		contentType :"application/json",
		success: function(resultData) {console.log("Entered")/*window.location.href = "ConfirmedBooking.html"*/}
	});
}