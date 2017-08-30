function submittest() {
	var username = $("#username").val();
	var password = $("#password").val();
	$.ajax({
		type : "GET",
		url : "/basic/userlogin",
		data : {username:username,
			password:password
		},
		dataType : "text",
		success : function(data) {
			window.location.href="http://192.168.7.31:8080/basic/index"
//			console.log("return ...." + data);
		}
	});
};

