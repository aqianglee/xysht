<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div>
    	<div class="title">
			校园生活通后台管理系统
    	</div>
    	<div id="shopKeeperInfo_messages" class="shopKeeperInfo_messages">
			<div class="shopKeeperInfo">
				<span>${sessionScope.shopKeeper.username }</span>
			</div>
			<div class="message">
				消息
			</div>
			<div class="alerm">
				提醒
			</div>
    	</div>
		<div style="clear: both;"></div>
	</div>
	<script type="text/javascript">
		$(function(){
			if(${sessionScope.shopKeeper == null}) {
				$("#shopKeeperInfo_messages").hide();
			}
		});
	</script>