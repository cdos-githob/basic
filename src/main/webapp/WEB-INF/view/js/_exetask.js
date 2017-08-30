var data = [
		{
			"pname" : "显示器",
			"check" : 1,
			"id" : 1,
			"name" : "开机DDC识别",
			"casenum" : 1,
			"step" : "在操作系统的\"开始菜单-系统设置面板-显示\"查看DDC识别是否为产品一致",
			"result" : "认别出的与硬件一致",
			"comment" : ""
		},
		{
			"pname" : "显示器",
			"check" : 1,
			"id" : 1,
			"name" : "睡眠/休眠唤醒后DDC识别",
			"casenum" : 2,
			"step" : "进入s3,s4状态,唤醒计算机.\"开始菜单-系统设置面板-显示\"查看DDC识别是否为产品一致",
			"result" : "认别出的与硬件一致",
			"comment" : "1. 使用脚本前先修改权限：chmod a+x pm-action_auto_pm-action_auto_s3.sh 使用超级用户权限运行脚本：sudo  ./pm-action_auto_pm-action_auto_s3.sh 输入需要运行的次数， 还需输入密码 唤醒通过键盘或是按电源按钮2. 使用脚本前先修改权限：chmod a+x pm-action_auto_pm-action_auto_s4.sh  使用超级用户权限运行脚本：sudo  ./pm-action_auto_pm-action_auto_s4.sh 输入需要运行的次数   还需输入密码    唤醒通过按电源按钮"
		},
		{
			"pname" : "显示器",
			"check" : 1,
			"id" : 1,
			"name" : "背景测试",
			"casenum" : 1,
			"step" : "进入linux右击鼠标,点击\"更改桌面背景\”  然后再选择其中的图画",
			"result" : "可以正常修改桌面背景",
			"comment" : ""
		},
		{
			"pname" : "显示器",
			"check" : 1,
			"id" : 1,
			"name" : "分辨率测试",
			"casenum" : 2,
			"step" : "在操作系统的\"开始菜单-系统设置面板-显示\"随机选择不同的分辨率与旋转",
			"result" : "不会花屏，蓝屏等无其它异常现象 ",
			"comment" : ""
		},
		{
			"pname" : "显卡",
			"check" : 1,
			"id" : 1,
			"name" : "系统休眠 ",
			"casenum" : 2,
			"step" : "进入s3,s4状态,唤醒计算机.",
			"result" : "1.S3/S4正常，无死机、蓝屏现象",
			"comment" : ""
		},
		{
			"pname" : "显卡",
			"check" : 1,
			"id" : 1,
			"name" : "分辨率、旋转测试",
			"casenum" : 1,
			"step" : "1.去除锁屏设置中所有的对勾，并将电源选项均选择“从不”2.打开终端运行resolution_rotate.sh脚本让屏幕自动旋转测试",
			"result" : "无异常显示现象",
			"comment" : ""
		} ];

var editIndex = null;
var taskid = null;

function formatPrice(val, row) {
	if (val == '1') {
		return '通过';
	} else if (val == '0') {
		return '未测试';
	}
};

$(function() {
	
	var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
	      }
	   }
	taskid = theRequest["taskid"];
	
	$.ajax({
		type : "GET",
		url : "/basic/getexetask",
		data : {
			ptaskid : taskid
		},
		dataType : "json",
		success : function(data) {
			console.log("exetask........"+data);
			$('#dg').datagrid('loadData', data);
			
		}
	});
	
	$('#dg')
			.datagrid(
					{
						view : detailview,
						detailFormatter : function(index, row) {
							return '<div style="padding:2px;position:relative;"><table class="ddv"></table></div>';
						},
						onExpandRow : function(index, row) {
							var ddv = $(this).datagrid('getRowDetail', index)
									.find('table.ddv');

							var par = {"operatorid":2,"caseid":row.caseid,"ptaskid":taskid};
                    
                    ddv.datagrid({
								// data:[
								// {"testno":"第1次","pcno":"dell-pc-01","ispass":"true","comment":"无"},
								// {"testno":"第2次","pcno":"dell-pc-02","ispass":1,"comment":"无"},
								// {"testno":"第3次","pcno":"dell-pc-03","ispass":1,"comment":"无"},
								// ],
								// url:'/basic/getexetaskitem?data='+JSON.stringify(par),
								fitColumns : true,
								singleSelect : true,
								rownumbers : true,
								singleSelect : true,
								loadMsg : '',
								height : 'auto',
								width : '500px',
								columns : [ [ {
									field : 'testno',
									title : '测试次数',
									width : 100
								}, {
									field : 'pcid',
									title : '测试机编号',
									width : 100
								},
								{
									field : 'ispass',
									title : '是否通过测试',
									width : 50,
									align : 'center',
									formatter : formatPrice,
									editor : {
										type : 'checkbox',
										options : {
											on : '1',
											off : '0'
										}
									}
								},
								{
									field : 'comm',
									title : '备注',
									width : 100,
									align : 'left',
									editor : {
										type : 'textbox'
									}
								} ] ],
								onResize : function() {
									$('#dg').datagrid('fixDetailRowHeight',
											index);
								},
								onClickCell : function(index, field, value) {
									if (editIndex != null) {
										$(this).datagrid("endEdit", editIndex);
									}
									$(this).datagrid('beginEdit', index);
									editIndex = index;
									var ed = $(this).datagrid('getEditor', {
										index : index,
										field : field
									});
								},
								onEndEdit : function(index, row, changes) {
									console.log(index);
									console.log(row);
									console.log(changes);
									if ($.isEmptyObject(changes)) {
										$.ajax({
											type : "GET",
											url : "/basic/updatesubtaskitem",
											data : {
												data : JSON.stringify(row)
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
								},
								onLoadSuccess : function(data) {
									setTimeout(function() {
										$('#dg').datagrid('fixDetailRowHeight',
												index);
									}, 0);
									if (data) {
										$.each(data.rows,
												function(index, item) {
													if (item.ispass) {
														ddv.datagrid(
																'checkRow',
																index);
													}
												});
									}
								}
							});
							$('#dg').datagrid('fixDetailRowHeight', index);

							$.ajax({
								type : "GET",
								url : "/basic/getexetaskitem",
								data : {
									data : JSON.stringify(par)
								},
								dataType : "json",
								success : function(data) {
									console.log(data);
									ddv.datagrid('loadData', data);
									if (data == "SUCCESS") {
										alert("SUCCESS");
									}
								}
							});
						}
					});
	
	$.ajax({
		type : "GET",
		url : "/basic/getuser",
		data : {
			data : {}
		},
		dataType : "text",
		success : function(data) {
			console.log(data);
			$("#operator").html(data);
		}
	});
	
});