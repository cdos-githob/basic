﻿var _menus = {
	"menus" : [ {
		"menuid" : "1",
		"icon" : "icon-sys",
		"menuname" : "任务管理",
		"menus" : [ {
			"menuid" : "13",
			"menuname" : "测试任务创建",
			"icon" : "icon-users",
			"url" : "createtask?taskid=1"
		}, {
			"menuid" : "14",
			"menuname" : "测试任务执行",
			"icon" : "icon-role",
			"url" : "exetask.html"
		}, {
			"menuid" : "15",
			"menuname" : "测试报告查看",
			"icon" : "icon-set",
			"url" : "login.html"
		}, {
			"menuid" : "16",
			"menuname" : "系统日志",
			"icon" : "icon-log",
			"url" : "demo1.html"
		} ]
	}, {
		"menuid" : "8",
		"icon" : "icon-sys",
		"menuname" : "xx管理",
		"menus" : [ {
			"menuid" : "21",
			"menuname" : "xx列表",
			"icon" : "icon-nav",
			"url" : "demo.html"
		}, {
			"menuid" : "22",
			"menuname" : "xx监控",
			"icon" : "icon-nav",
			"url" : "demo1.html"
		} ]
	}, {
		"menuid" : "56",
		"icon" : "icon-sys",
		"menuname" : "xx管理",
		"menus" : [ {
			"menuid" : "31",
			"menuname" : "xx部门",
			"icon" : "icon-nav",
			"url" : "demo1.html"
		}, {
			"menuid" : "32",
			"menuname" : "xx列表",
			"icon" : "icon-nav",
			"url" : "demo2.html"
		} ]
	}, {
		"menuid" : "28",
		"icon" : "icon-sys",
		"menuname" : "xx管理",
		"menus" : [ {
			"menuid" : "41",
			"menuname" : "xx分类",
			"icon" : "icon-nav",
			"url" : "demo.html"
		}, {
			"menuid" : "42",
			"menuname" : "xx统计",
			"icon" : "icon-nav",
			"url" : "demo1.html"
		}, {
			"menuid" : "43",
			"menuname" : "添加",
			"icon" : "icon-nav",
			"url" : "demo2.html"
		} ]
	}, {
		"menuid" : "39",
		"icon" : "icon-sys",
		"menuname" : "xx管理",
		"menus" : [ {
			"menuid" : "51",
			"menuname" : "分类",
			"icon" : "icon-nav",
			"url" : "demo.html"
		}, {
			"menuid" : "52",
			"menuname" : "列表",
			"icon" : "icon-nav",
			"url" : "demo1.html"
		}, {
			"menuid" : "53",
			"menuname" : "xx",
			"icon" : "icon-nav",
			"url" : "demo2.html"
		} ]
	} ]
};

$(function(){
	
	$.ajax({
		type : "GET",
		url : "/basic/getmenu",
		data : {
		},
		dataType : "json",
		success : function(data) {
			console.log("menudata:" + data);
			_menus = data;
			InitLeftMenu();
			tabClose();
			tabCloseEven();


			$('#tabs').tabs({
		        onSelect: function (title) {
		            var currTab = $('#tabs').tabs('getTab', title);
		            var iframe = $(currTab.panel('options').content);

					var src = iframe.attr('src');
					if(src)
						$('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src)} });

		        }
		    });
		}
	});
	
//	InitLeftMenu();
//	tabClose();
//	tabCloseEven();
//
//
//	$('#tabs').tabs({
//        onSelect: function (title) {
//            var currTab = $('#tabs').tabs('getTab', title);
//            var iframe = $(currTab.panel('options').content);
//
//			var src = iframe.attr('src');
//			if(src)
//				$('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src)} });
//
//        }
//    });

})

//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false});

    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<ul>';
        $.each(n.menus, function(j, o) {
			menulist += '<li><div><a ref="'+o.menuid+'" href="#" rel="' + o.url + '" ><span class="icon '+o.icon+'" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
        })
		menulist += '</ul>';

		$('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });

	$('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = getIcon(menuid,icon);

		addTab(tabTitle,url,icon);
		$('.easyui-accordion li div').removeClass("selected");
		$(this).parent().addClass("selected");
	}).hover(function(){
		$(this).parent().addClass("hover");
	},function(){
		$(this).parent().removeClass("hover");
	});

	//选中第一个
	var panels = $('#nav').accordion('panels');
	var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 })
	})

	return icon;
}

function addTab(subtitle,url,icon){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
