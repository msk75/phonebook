	
	//$(document).ready(function(){ loadcall(); });
	
// Get the modal
		var modal1 = document.getElementById('id01');
		var modal2 = document.getElementById('id02');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal1 || event.target == modal2) {
				modal1.style.display = "none";
				modal2.style.display = "none";
			}
		}

		function fromUser() {
			var user = [];
			var fname = document.getElementById("fname").value;
			var lname = document.getElementById("lname").value;
			var username = document.getElementById("uname").value;
			var password = document.getElementById("pass").value;
			var email = document.getElementById("email").value;
			var address = document.getElementById("address").value;
			user={fname,lname,username,password,email,address};
			return user;
		}
		function adduser() {
			var user = fromUser();
			$.ajax({
				crossDomain : true,
				type : "POST",
				url : "api/users",
				data : JSON.stringify(user),
				async : true,
				cache : false,
				contentType : "Application/JSON",
				success : function(response) {
					alert("YOUR DATA ADDED IN DATABASE");
				},
			});
		}

		function loadcall() {
			$.ajax({
				type : "GET",
				url : "calls",
				async : true,
				crossDomain : true,
				cache : false,
				success : function(response) {
					$.each(response, function(calls, value) {
						$("#stable").append(
								$("<tr>").append(
										"<td>" + value.id + "</td>" + "<td>"
												+ value.fname + "</td>"
												+ "<td>" + value.lname
												+ "</td>" + "<td>"
												+ value.phoneNumber + "</td>"
												+ "<td>" + value.mobileNumber
												+ "</td>" + "<td>"
												+ value.email + "</td>"
												+ "<td>" + value.address
												+ "</td>"));
					});
				},
			// error: function(xhr, status , error){
			// alert(xhr.responseText + status);
			// }
			})
		}
		
		function fromCall() {
			var call = [];
			var fname = document.getElementById("fname").value;
			var lname = document.getElementById("lname").value;
			var mobileNumber = document.getElementById("mobile").value;
			var phoneNumber = document.getElementById("phone").value;
			var email = document.getElementById("email").value;
			var address = document.getElementById("address").value; 
			  call={fname,lname,mobileNumber,phoneNumber,email,address};
			return call;
		}
		function addcall() {
			var call = fromCall();
			$.ajax({
				crossDomain : true,
				type : "POST",
				url : "api/callinfo",
				data : JSON.stringify(call),
				async : true,
				cache : false,
				contentType : "Application/JSON",
				success : function(response) {
					alert("YOUR DATA ADDED IN DATABASE");
				},
			});
		}
		
		
		function loadUsers() {
			$.ajax({
				type : "GET",
				url : "api/users",
				async : true,
				crossDomain : true,
				cache : false,
				success : function(response) {
					$.each(response, function(user, value) {
						$("#utable").append(
								$("<tr>").append(
										"<td>" + value.id + "</td>" + "<td>"
												+ value.fname + "</td>"
												+ "<td>" + value.lname
												+ "</td>" + "<td>"
												+ value.username + "</td>"
												+ "<td>" + value.password
												+ "</td>" + "<td>"
												+ value.email + "</td>"
												+ "<td>" + value.address
												+ "</td>"));
					});
				},
			// error: function(xhr, status , error){
			// alert(xhr.responseText + status);
			// }
			})
		}
		
		function adminContact(){
			
			document.getElementById('stable').style.display='block';
			document.getElementById('btable').style.display='block';
			document.getElementById('stable').value=[];
			loadcall();
			document.getElementById('utable').style.display='none';
			document.getElementById('ubtable').style.display='none';
			
		}
		function adminUser(){
			loadUsers();
			document.getElementById('stable').style.display='none';
			document.getElementById('btable').style.display='none';
			document.getElementById('utable').style.display='block';
			document.getElementById('ubtable').style.display='block';
			
		}