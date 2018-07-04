function onblur1 (){
			//alert("绑定成功");
			var name=$("#adminCode").val().trim();
		
			$("#adminCode_span").html("");
			
			//alert(name+","+password);
			var ok=true;
			if(name==""){
				$("#adminCode_span").html("用户名不能为空");
				ok=false;
			}
	};			
	
			function onblur2 (){
			//alert("绑定成功");
			
			var password=$("#password").val().trim();
		
			$("#password_span").html("");
			//alert(name+","+password);
			var ok=true;

			if(password==""){
				$("#password_span").html("密码不能为空");
				ok=false;
			}
			};