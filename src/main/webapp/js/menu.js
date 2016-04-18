function childMenu(item) {
	var boxs = $(".box");
	for(var i = 0; i < boxs.length; i ++) {
		if(boxs[i].style.display == 'block') {
			$(boxs[i]).slideToggle("slow");
			if(item.id == boxs[i].id) {
				return false;
			}
		}
	}
	
	$(item).slideToggle("slow");
	item.style.display = 'block';
	return false;
}