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
		<div align="left" class="menu_class_1" onclick="childMenu(childMenu1)">店铺</div>
		<ul id="childMenu1" class="box" style="display: none;">
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/shopKeeper/listAllSuperMarkets">店铺管理</a>
				</div>
			</li>
		</ul>
	</li>
	
	<li>
		<div class="menu_class_1" onclick="childMenu(childMenu2)">订单</div>
		<ul id="childMenu2" class="box" style="display: none;">
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/shopKeeper/passenger_showpage">订单管理</a>
				</div>
			</li>
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/shopKeeper/listAllStates">订单状态管理</a>
				</div>
			</li>
		</ul>
	</li>
	
	<li>
		<div class="menu_class_1" onclick="childMenu(childMenu3)">顾客</div>
		<ul id="childMenu3" class="box" style="display: none;">
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/shopKeeper/passenger_showpage">顾客管理</a>
				</div>
			</li>
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/shopKeeper/passenger_showpage">会员管理</a>
				</div>
			</li>
		</ul>
	</li>
	<li>
		<div class="menu_class_1" onclick="childMenu(childMenu5)">商品</div>
		<ul id="childMenu5" class="box" style="display: none;">
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/shopKeeper/passenger_showpage">商品管理</a>
				</div>
			</li>
			
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/shopKeeper/getAllTags">商品标签管理</a>
				</div>
			</li>
		</ul>
	</li>
	
	<li>
		<div class="menu_class_1" onclick="childMenu(childMenu6)">派送员</div>
		<ul id="childMenu6" class="box" style="display: none;">
			<li>
				<div class="menu_class_2">
					<a href="${pageContext.request.contextPath }/shopKeeper/passenger_showpage">派送员管理</a>
				</div>
			</li>
		</ul>
	</li>
</ul>