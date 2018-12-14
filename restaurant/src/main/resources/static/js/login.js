;
! function() {
	var layer = layui.layer,
		form = layui.form,
		carousel = layui.carousel;

	// 背景图片轮播
	carousel.render({
		elem: '#login_carousel',
		width: '100%',
		height: '100%',
		interval: 3000,
		arrow: 'none',
		anim: 'fade',
		indicator: 'none'
	});
	// 自定义验证规则
	form.verify({
		account: function(value) {
			var regPhone = /^1[34578]\d{9}$/;
			if(!regPhone.test(value.trim())){
				return "请输入正确的账号";
			}
		},
		pwd:function (value) {
			if(value.trim()==''||value.trim()==null){
				return'密码不能为空';
			}
        }
	});
	//监听提交  
	form.on("submit(login)", function() {
		$.ajax({
			url: "/restaurant/sysuser/login.do",
			type: "post",
            dataType: "JSON",
			data: {
				loginCode: $("#loginCode").val(),
				password: $("#password").val()
			},
			success: function(result) {
				if(result.code == 200) {
					location = "/restaurant/sysuser/admin.html";
				} else {
					$("#password").val("");
					layer.msg(result.msg,{icon:2,anim:6});
				}
			}
		});
		return false;
	});
}();