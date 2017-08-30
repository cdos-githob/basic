var URL_PARS = null;

$(function() {
	// InitLeftMenu();
	// tabClose();
	// tabCloseEven();
	$('#dg').datagrid({
		onLoadSuccess : function(data) {
			if (data) {
				$.each(data.rows, function(index, item) {
					if (item.ischeck) {
						$('#dg').datagrid('checkRow', index);
					}
				});
			}
		}
	});
	
	
	var url = location.search; //获取url中"?"符后的字串
	URL_PARS = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for(var i = 0; i < strs.length; i ++) {
			URL_PARS[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
		}
	}
});

function getexetask(){
	$.ajax({
	type : "GET",
	url : "/basic/getexetask",
	data : {
		data : {}
	},
	dataType : "json",
	success : function(data) {
		console.log(data);
		if (data == "SUCCESS") {
			alert("SUCCESS");
		}
	}
});
}

function savesubtask(ptaskid){
	var flag = 0;
	var checkedItems = $('#dg').datagrid('getChecked');
	var subtasks = [];
	$.each(checkedItems, function(index, item) {
		var task = {};
//		task["id"] = 1;
//		task["taskid"] = 1;
		task["ptaskid"] = ptaskid;
		task["pmid"] = URL_PARS["userid"];
		task["operatorid"] = $('#operator').combobox('getValue');
		task["caseid"] = item.id;
		task["testnum"] = item.times;
		task["ispass"] = 0;
		task["pcid"] = $('#pc').combobox('getValue');
//		task["time"] = $("#softver").textbox('getValue');
		$.ajax({
			type : "GET",
			url : "/basic/updatesubtask",
			data : {
				data : JSON.stringify(task)
			},
			dataType : "text",
			success : function(data) {
				
				console.log(data);
				if (data == "SUCCESS") {
					if(flag == 0){
						alert("SUCCESS");
						flag = 1;
					}
				}
			}
		});
	});
}

function updatetask() {
	
	var name = $('#i')
	var data = {};
//	data["id"] = 1;
//	data["taskid"] = 1;
	data["ptaskid"] = 1;
	data["pmid"] = URL_PARS["userid"];
	data["operatorid"] = $('#operator').combobox('getValue');
	data["name"] = $("#taskname").textbox('getValue');
	data["softver"] = $("#softver").textbox('getValue');
	data["hardver"] = $("#hardver").textbox('getValue');
	
	$.ajax({
		type : "GET",
		url : "/basic/savetask",
		data : {
			data : JSON.stringify(data)
		},
		dataType : "text",
		success : function(data) {
			
			var taskid = data;
			savesubtask(data);
			console.log(data);
			if (data == "SUCCESS") {
				alert("SUCCESS");
			}
		}
	});
	
}
