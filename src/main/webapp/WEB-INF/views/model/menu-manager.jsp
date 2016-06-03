<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
	$(function(){
		var location = <%= request.getAttribute("location") %>;
		var boxs = $(".box");
		for(var i = 0; i < boxs.length; i ++) {
			if(i == location) {
				boxs[i].style.display = 'block';
			}
		}
		
		$(".menu_class_2").click(function(){
			var url = $(this).find('a').attr('href');
			window.location=url;
		});
	});
//-->
</script>
<ul>
	<li>
		<div class="menu_class_1"><a href="toManagePage">首页</a></div>
	</li>
	<li>
		<div align="left" class="menu_class_1" onclick="childMenu(childMenu1)">商家</div>
		<ul id="childMenu1" class="box" style="display: none;">
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/manager/listAllSuperMarkets">店铺审核</a>
				</div>
			</li>
		</ul>
	</li>
	<li>
		<div class="menu_class_1" onclick="childMenu(childMenu2)">活动</div>
		<ul id="childMenu2" class="box" style="display: none;">
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/manager/passenger_showpage">活动审核</a>
				</div>
			</li>
		</ul>
	</li>
	<li>
		<div class="menu_class_1" onclick="childMenu(childMenu3)">系统设置</div>
		<ul id="childMenu3" class="box" style="display: none;">
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/manager/listAllParameters">系统参数</a>
				</div>
			</li>
		</ul>
	</li>
	
</ul>