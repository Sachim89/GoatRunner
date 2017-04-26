$(document).ready(function(){

	//Get the values from backend and display in HTML
	   $('#StudentID').val(localStorage.getItem("StudentID"));
	   $('#Name').val(localStorage.getItem("Name"));
	   $('#Email').val(localStorage.getItem("Email"));
	   $('#Password').val(localStorage.getItem("password"));
	   $("#Address").val(localStorage.getItem("Address"));
	   $("#phone").val(localStorage.getItem("Phone"));
	   $("#fav").val(localStorage.getItem("Fav"));
	   $("#questions").val(localStorage.getItem("Security"));
	   $("#answer").val(localStorage.getItem("Answer"));
	  
	   //Check for new values and update
	   $("#update").click(function(){
			validate();
		});
	   
	 //Logout functionality
	   $("#logout").click(function(){
			logout();
		});
	   
	   //Back button
	   $('#back').click(function(){
			parent.history.back();
			return false;
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
			},
			error: function(data){
				if(code == 400){
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
				if(code == 500){
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
			}
		});
	}
	else{
		window.location.href = "http://localhost:8080/GoatRunner/HomePage.html"
	}
//	localStorage.removeItem("StudentID");
}

function validate()
{	
	var isValid = true;
	
	//Check the Password and Student ID
	$('input').filter('[required]').each(function(){
		if($(this).val() == ""){
		$('input').css("border","2px solid red");
		$('input').css("box-shadow","0 0 3px red");
		$('select').css("border","2px solid red");
		$('select').css("box-shadow","0 0 3px red");
		isValid = false;
		return false;
		}
	});
	
	if(password !="" || id != ""){	
			if(id.length < 9 && password.length < 6){
				alert("Student ID should be 9 digits and Password should be minimum 6 characters");
			}
			else if(password.length < 6){
				alert("Password should be minimum 6 characters");
			}
			else if(id.length < 9){
				alert("Student ID should be 9 digits");
			}
			else if(id.length > 9){
				alert("Student ID should be 9 digits");
			}
	}
	
	if(!isValid){alert("Some fields are missing");}
	
	//Send the valid data to Service layer
	if(isValid){
			  
		var studID = document.getElementById("StudentID").value;
		var name = document.getElementById("Name").value;
		var email = document.getElementById("Email").value;
		var password = document.getElementById("Password").value;
		var address = document.getElementById("Address").value;
		var phone = document.getElementById("phone").value;
		var fav_location = document.getElementById("fav").value;
		var quest;
		document.getElementById("questions").addEventListener("change", function() {
			quest = this.value;
		});
		var answer = document.getElementById("answer").value;
	
		var j = {
				"name" : name,
				"student_id" : studID,
				"password" : password,
				"phone_number" : phone,
				"address" : address,
				"favourite_location" : fav_location,
				"email_id" : email,
				"security_question" : quest,
				"answer" : answer
			};
		$.ajax({
			url: "http://localhost:8080/GoatRunner/application/user/signup",
			type: "POST",
			data : JSON.stringify(j),
			contentType :"application/json",
			success: function(resultData) {
				window.location.href = "http://localhost:8080/GoatRunner/BookingPage.html";
					},
			error: function(data){
				if(code == 400){
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
				if(code == 500){
					alert("Oops!! Somethings went wrong.. Please try after sometime");
				}
			}
		});
	}
}
