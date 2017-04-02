function submitform()
				 	{				 
				 		var studID = document.getElementById("StudentID").value;
				 		var name = document.getElementById("Name").value;
				 		var email = document.getElementById("Email").value;
						var password = document.getElementById("Password").value;
						var address = document.getElementById("Address").value;
						var phone = document.getElementById("phone").value;
						
						var xhr = new XMLHttpRequest();
			      		xhr.open(form.method, form.action, true);
			       		xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
			       			var j = {
			       						"userid" : studID,
			       						"name" : name,
			       						"email": email,
			            			  	"password": password,
			            			  	"address" : address,
			            			  	"phone Number" : phone
			       					};
			       		xhr.send(JSON.stringify(j));
			       		xhr.addEventListener("readystatechange", processRequest, false);
			       		xhr.onreadystatechange = processRequest;
			       		
			       		function processRequest(e) 
			       		{
			       			if (xhr.readyState == 4 && xhr.status == 200) 
			       			{
			       		       window.location.href = "http://localhost:8080/GoatRunner/application/login";
			       		    }
		       			}
				 	}