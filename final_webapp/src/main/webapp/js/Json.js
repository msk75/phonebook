$(document).ready(function(){
	loadcalls();
});

        // Get the modal
		var modal1 = document.getElementById('id01');
		var modal2 = document.getElementById('id02');
		var modal3 = document.getElementById('id03');

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal1 || event.target == modal2 || event.target == modal3) {
				modal1.style.display = "none";
				modal2.style.display = "none";
				modal3.style.display = "none";
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
			user = {fname,lname,username,password,email,address};
			return user;
		}
		
		function adduser() {
			var user = fromUser();
			if(user.username==""||user.password==""){
				alert("please fit username or password field");				
			}else {
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
// error: function(xhr, status , error){
// // alert("Check your inputs");
// }
			});
			}
		}
		function login(){
			
			var user = document.getElementById("usrname").value;
			var pass = document.getElementById("password").value;
			if(user=="" || pass=="") {
				alert("please check your input");
			}
			else{
				var param = "api/users/login?username=" + user + "&password=" + pass;
				$.ajax({
					type: "GET",
					url: param,
					async : true,
					crossDomain : true,
					cache : false,
				    success: function(response) {
				    	document.getElementById('id01').style.display='none';
				    		loadpage(response,user);
	 			    },
				    error: function(xhr, status , error){
				    	 alert("data is not correct");
											 }
				});
			}

		}
		function logout(){
			$.ajax({
				type: "GET",
				url: "api/users/logout",
				async : true,
				crossDomain : true,
				cache : false,
			    success: function(response) {
			    	 window.location.href = "index.html";
 			    },
			    error: function(xhr, status , error){
					 alert("The informaion Not Current");
					 }
			});	
		}
		function chat(){
			 window.location.href = "http://localhost:8091";
		}
		function pers(){
			window.location.href = "chief.html";
			
		}
		function eng(){
			window.location.href = "index.html";
			
		}
		function loadpage(response,username){
			switch (response) {
			case "3":
                 document.getElementById('userinf').style.display='block';
                 document.getElementById('useract').style.display='block';
                 document.getElementById('cont').style.display='block';
                 document.getElementById('ex').style.display='block';
                 document.getElementById('chat').style.display='block';
                 document.getElementById('search').style.display='block';
                 document.getElementById('log').style.display='none';
                 document.getElementById('reg').style.display='none';
                 document.getElementById('eng').style.display='none';
                 document.getElementById('pers').style.display='none';
				 break;
			case "1":
				document.getElementById('search').style.display='block';
				document.getElementById('ex').style.display='block';
				document.getElementById('chat').style.display='block';
				document.getElementById('cont').style.display='block';
			     document.getElementById('userinf').style.display='none';
                 document.getElementById('useract').style.display='none';
                 document.getElementById('log').style.display='none';
                 document.getElementById('reg').style.display='none';
         		document.getElementById('updatebtn').style.display='none';
         		document.getElementById('deletebtn').style.display='none';
         		document.getElementById('eng').style.display='none';
                document.getElementById('pers').style.display='none';

				 break;
		    case "2":
		    	document.getElementById('search').style.display='block';
                 document.getElementById('useract').style.display='block';
				 document.getElementById('cont').style.display='block';
                 document.getElementById('ex').style.display='block';
                 document.getElementById('chat').style.display='block';
                 document.getElementById('userinf').style.display='none';
                 document.getElementById('log').style.display='none';
                 document.getElementById('reg').style.display='none';
                 document.getElementById('eng').style.display='none';
                 document.getElementById('pers').style.display='none';
		    	 break;
			default:
				break;
			}
		}

		
		
		function fromSearch(){
			var search=[];
			var fname=document.getElementById("fnsearch").value;
			var lname=document.getElementById("lnsearch").value;
			var mobileNumber=document.getElementById("mnsearch").value;
			var phoneNumber=document.getElementById("phnsearch").value;
			var email=document.getElementById("mailsearch").value;
			var address=document.getElementById("addsearch").value;
			search=[["fname",fname],["lname",lname],["mobileNumber",mobileNumber],["phoneNumber",phoneNumber],["email",email],["address",address]];
			return search;
			
		}
		
		function searchContact(){
			var search=fromSearch();
             $.ajax({
 				crossDomain : true,
 				type : "POST",
 				url : "api/calls/search",
 				data : JSON.stringify(search),
 				async : true,
 				cache : false,
 				contentType : "Application/JSON",
 				success : function(response) {
 					document.getElementById('id03').style.display='none';
 					DeleteStableRows();
 					adminSearch();
 					$.each(response, function(call, value) {
						$("#stable").append(
								$("<tr>").append(
										"<td>"   + value.id           + "</td>"  +
										"<td>"   + value.fname        + "</td>"  +
										"<td>"   + value.lname        + "</td>"  +
										"<td>"   + value.phoneNumber  + "</td>"  +
										"<td>"   + value.mobileNumber + "</td>"  +
										"<td>"   + value.email        + "</td>"  +
										"<td>"   + value.address      + "</td>"   ));
					});
 					selectedContacts();
 				}
 			});
		}
		
		function DeleteStableRows() {
 var table=document.getElementById("stable");
 var rowCount=table.rows.length;
 for (var i = rowCount - 1; i > 1; i--) {
 document.getElementById("stable").deleteRow(i);
 }
	    }
		function loadcalls() {
			document.getElementById("stableform").reset();

			$.ajax({
				type : "GET",
				url : "api/calls",
				async : true,
				crossDomain : true,
				cache : false,
				success : function(response) { 	
					DeleteStableRows();
					$.each(response, function(call, value) {
						$("#stable").append(
								$("<tr>").append(
										"<td>"   + value.id           + "</td>"  +
										"<td>"   + value.fname        + "</td>"  +
										"<td>"   + value.lname        + "</td>"  +
										"<td>"   + value.phoneNumber  + "</td>"  +
										"<td>"   + value.mobileNumber + "</td>"  +
										"<td>"   + value.email        + "</td>"  +
										"<td>"   + value.address      + "</td>"   ));
					});
					selectedContacts();
				},
			 error: function(xhr, status , error){
			 alert("Does Not Any Data");
			 }
			})
		}
		
		function selectedContacts(){
			var rIndex,table=document.getElementById("stable");
			
			for(var i=2;i<table.rows.length;i++){
				table.rows[i].onclick=function(){
					rIndex=this.rowIndex;
					document.getElementById("idcall").value=this.cells[0].innerHTML;
					document.getElementById("fnam").value=this.cells[1].innerHTML;
					document.getElementById("lnam").value=this.cells[2].innerHTML;
				    document.getElementById("mobile").value=this.cells[3].innerHTML;
					document.getElementById("phone").value=this.cells[4].innerHTML;
				    document.getElementById("mail").value=this.cells[5].innerHTML;
					document.getElementById("addr").value=this.cells[6].innerHTML;
					
				};
			}
		}
		selectedContacts();
				
		function fromCall() {
			var call = [];			
			var id = document.getElementById("idcall").value;
			var fname = document.getElementById("fnam").value;
			var lname = document.getElementById("lnam").value;
			var mobileNumber = document.getElementById("mobile").value;
			var phoneNumber = document.getElementById("phone").value;
			var email = document.getElementById("mail").value;
			var address = document.getElementById("addr").value; 
			  call={id,fname,lname,mobileNumber,phoneNumber,email,address};
			return call;
		}
		function addcall() {
			var call = fromCall();
			$.ajax({
				crossDomain : true,
				type : "POST",
				url : "api/calls",
				data : JSON.stringify(call),
				async : true,
				cache : false,
				contentType : "Application/JSON",
				success : function(response) {
					loadcalls();
						alert("YOUR DATA ADDED IN DATABASE");					
					},
					error: function(xhr, status , error){
						 alert("Duplicate Data");
						 }
			});
		}
		
		
		function DeleteUtableRows() {
 var table=document.getElementById("utable");
 var rowCount=table.rows.length;
 for (var i = rowCount - 1; i > 1; i--) {
 document.getElementById("utable").deleteRow(i);
 }
	    }
		
		function selectedUsers(){
			var rIndex,table=document.getElementById("utable");
			for(var i=2;i<table.rows.length;i++){
				table.rows[i].onclick=function(){
					rIndex=this.rowIndex;
					document.getElementById("idlb").value=this.cells[0].innerHTML;
					document.getElementById("fnamlb").value=this.cells[1].innerHTML;
					document.getElementById("lnamlb").value=this.cells[2].innerHTML;
					document.getElementById("unamelb").value=this.cells[3].innerHTML;
					document.getElementById("passlb").value=this.cells[4].innerHTML;
					document.getElementById("maillb").value=this.cells[5].innerHTML;
					document.getElementById("addrlb").value=this.cells[6].innerHTML;
					document.getElementById("rolelb").value=this.cells[7].innerHTML;
				};
			}
		}
		selectedUsers();
		
		
		function loadUsers() {
			document.getElementById("utableform").reset();
			$.ajax({
				type : "GET",
				url : "api/users",
				async : true,
				crossDomain : true,
				cache : false,
				success : function(response) {
					DeleteUtableRows();
					$.each(response, function(user, value) {
						$("#utable").append(
								$("<tr>").append(
										"<td>"  + value.id        +      "</td>" + 
										"<td>"  + value.fname     +      "</td>" +
										"<td>"  + value.lname     +      "</td>" +
										"<td>"  + value.username  +      "</td>" +
										"<td>"  + value.password  +      "</td>" +
										"<td>"  + value.email     +      "</td>" + 
										"<td>"  + value.address   +      "</td>" +
										"<td>"  + value.role      +      "</td>"));
					});
					selectedUsers();
				},
 error: function(xhr, status , error){
 alert("Does Not Any Data");
 }
			})
		}
		
		function adminSearch(){
			selectedContacts();
			document.getElementById('stable').value=[];
			document.getElementById('stable').style.display='block';
			document.getElementById('btable').style.display='block';
			document.getElementById('fnam').style.display='block';
			document.getElementById('idcall').style.display='block';
			document.getElementById('lnam').style.display='block';
			document.getElementById('mobile').style.display='block';
			document.getElementById('phone').style.display='block';
			document.getElementById('mail').style.display='block';
			document.getElementById('addr').style.display='block';
			document.getElementById('utable').style.display='none';
			document.getElementById('ubtable').style.display='none';
			document.getElementById('activ').style.display='none';
			document.getElementById('insertbtn').style.display='none';
		}
		
		function adminContact(){
			selectedContacts();
			document.getElementById('stableform').reset();
			document.getElementById('stable').value=[];
			document.getElementById('insertbtn').style.display='block';
			document.getElementById('stable').style.display='block';
			document.getElementById('btable').style.display='block';
			document.getElementById('fnam').style.display='block';
			document.getElementById('idcall').style.display='block';
			document.getElementById('lnam').style.display='block';
			document.getElementById('mobile').style.display='block';
			document.getElementById('phone').style.display='block';
			document.getElementById('mail').style.display='block';
			document.getElementById('addr').style.display='block';
			loadcalls();
			document.getElementById('utable').style.display='none';
			document.getElementById('ubtable').style.display='none';
			document.getElementById('activ').style.display='none';
			
		}
		
		function adminUser(){
			document.getElementById('utable').value=[];
			document.getElementById('utable').style.display='block';
			document.getElementById('ubtable').style.display='block';
			loadUsers();
			selectedUsers();
			document.getElementById('stable').style.display='none';
			document.getElementById('btable').style.display='none';			
			document.getElementById('activ').style.display='none';
		}
		function activeUser(){
			
			document.getElementById('activ').style.display='block';
			userActivity();
			document.getElementById('utable').style.display='none';
			document.getElementById('ubtable').style.display='none';
			document.getElementById('stable').style.display='none';
			document.getElementById('btable').style.display='none';	
		}
		
		
		function fromUser2() {
			var user = [];
			var id = document.getElementById("idlb").value;
			var fname = document.getElementById("fnamlb").value;
			var lname = document.getElementById("lnamlb").value;
			var username = document.getElementById("unamelb").value;
			var password = document.getElementById("passlb").value;
			var email = document.getElementById("maillb").value;
			var address = document.getElementById("addrlb").value;
			var role = document.getElementById("rolelb").value;
			var role_id;
			if(role==="normal")
				role_id=1;
			else if(role==="manager")
				role_id=2;
			else
				role_id=3;
			user = {"id":id,"fname":fname,"lname":lname,"username":username,"password":password,"email":email,"address":address,"role":{"id":role_id,"name":role}};
			return user;
		}
	    function editcall(){
	    	var call = fromCall();
	    	$.ajax({
	    		crossDomain:true,
	    		type:"PUT",
	    		url:"api/calls",
	    		data: JSON.stringify(call),
	    		async:true,
	    		chache:false,
	    		contentType:"Application/JSON",
	    		success:function(response){
	    			alert('YOUR DATA IS UPDATED');
	    			loadcalls();
	    			
	    		},
	     error:function(xhr, status, error){
	     alert("Duplicate Data");
	     }
	    	});
	    }
	    
	    function editUser(){
	    	var user = fromUser2();
	    	$.ajax({
	    		crossDomain:true,
	    		type:"PUT",
	    		url:"api/users",
	    		data: JSON.stringify(user),
	    		async:true,
	    		chache:false,
	    		contentType:"Application/JSON",
	    		success:function(response){
	    			loadUsers();
	    			alert('YOUR DATA IS UPDATED');
	    		},
	     error:function(xhr, status, error){
	     alert("Duplicate Data");
	     }
	    	});
	    }
	    
	    function removecall(){
	    	var call = fromCall();
	    	$.ajax({
	    		crossDomain:true,
	    		type:"DELETE",
	    		url:"api/calls",
	    		data: JSON.stringify(call),	
	    		async:true,
	    		cache:false,
	    		contentType: "Application/JSON",
	    		success:function(response){
	    			alert("DELETED");
	    			loadcalls();
	    		},
	     error: function(xhr, status , error){
	     alert("Thid Data does not exist");
	     }
	    	});
	    }
	    
	    function removeUser(){
	    	var user = fromUser2();
	    	$.ajax({
	    		crossDomain:true,
	    		type:"DELETE",
	    		url:"api/users",
	    		data: JSON.stringify(user),	
	    		async:true,
	    		cache:false,
	    		contentType: "Application/JSON",
	    		success:function(response){
	    			loadUsers();
	    			alert("DELETED");
	    		},
	     error: function(xhr, status , error){
	     alert("Thid Data does not exist");
	     }
	    	});
	    }
	    
	    function DeleteActivRows() {
	        var table=document.getElementById("activ");
	        var rowCount=table.rows.length;
	        for (var i = rowCount - 1; i > 1; i--) {
	        	document.getElementById("activ").deleteRow(i);
	        }
	    }
	    
	    
	    function userActivity(){
	    	$.ajax({
				type : "GET",
				url : "api/describes",
				async : true,
				crossDomain : true,
				cache : false,
				success : function(response) {
					DeleteActivRows();
					$.each(response, function(describes, value) {
						$("#activ").append(
								$("<tr>").append(
										"<td>"  + value.id        +      "</td>" + 
										"<td>"  + value.describes   +      "</td>" +
										"<td>"  + value.user     +      "</td>" ));
					});
				},
 error: function(xhr, status , error){
 alert("Does Not Any Data");
 }
			})
		}
		
		
		
		