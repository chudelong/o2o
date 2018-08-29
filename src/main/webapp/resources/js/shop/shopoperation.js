/**
 * 从后台获取区域信息以及店铺分类的信息
 * 并将信息填充到对应的空间里面去
 */
$(function() {
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	var registerShopUrl = '/o2o/shopadmin/registershop';
	alert(initUrl);
	getShopInitInfo();
	/**
	 * 从后台获取区域信息以及店铺分类的信息
	 * 并将信息填充到对应的空间里面去
	 */
	function getShopInitInfo(){
		$.getJSON(initUrl, function(data){
			if(data.success){
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index){
					tempHtml += '<option data-id="' + item.shopCategoryId + '">'
					+ item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index){
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
					+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
		
		/**
		 * 提交的时候，获取表单里面的内容
		 */
		$('#submit').click(function(){
			var shop = {};
			shop.shopName = $('#shop-name').val();
			shop.shopAddr = $('#shop-addr').val();
			shop.phone = $('#shop-phone').val();
			shop.shopDesc = $('#shop-desc').val();
			shop.shopCategory = {
					shopCategoryId : $('#shop-category').find('option').not(function(){
						return !this.selected;
					}).data('id')
			};
			shop.area = {
				areaId : $('#area').find('option').not(function(){
					return !this.selected;
				}).data('id')	
			};
			var shopImg = $('shop-img')[0].files[0];
			var formData = new FormData();
			formData.append('shopImg', shopImg);
			formData.append('shopStr', JSON.stringify(shop));
			/**
			 * 将数据提交到后台
			 */
			$ajax({
				url : registerShopUrl,
				type :'POST',
				data : formData,
				contentType : false,
				proceesData : false,
				cache : false,
				success : function(data){
					if(data.success){
						$.toast('提交成功！');
					} else {
						$.toast('提交失败' + data.errMsg);
					}
				}
			})
		});
	}
})



























