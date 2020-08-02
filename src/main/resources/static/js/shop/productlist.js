$(function() {
	// var shopId = 1;
	// var listUrl = '/o2o/shopadmin/getproductlist?pageIndex=1&pageSize=99&shopId='
	// 		+ shopId;

	var listUrl = '/o2o/shopadmin/getproductlist?pageIndex=1&pageSize=99';

	var deleteUrl = '/o2o/shopadmin/modifyproduct';

	function getList() {
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var productList = data.productList;
				var tempHtml = '';
				productList.map(function(item, index) {
					var textOp = "下架";
					var contraryStatus = 0;
					if (item.enableStatus == 0) {
						textOp = "上架";
						contraryStatus = 1;
					} else {
						contraryStatus = 0;
					}
					tempHtml += '<div class="row row-product"><div class="col-33">'
							+ item.productName + '</div><div class="col-33">'
							+ item.priority + '</div><div class="col-33"><a href="#" class="edit" data-id="'
							+ item.productId + '" data-status="'
							+ item.enableStatus + '">编辑</a><a href="#" class="delete" data-id="'
							+ item.productId + '" data-status="'
							+ contraryStatus + '">'
							+ textOp + '</a><a href="#" class="preview" data-id="'
							+ item.productId + '" data-status="'
							+ item.enableStatus + '">预览</a></div></div>';
				});
				$('.product-wrap').html(tempHtml);
			}
		});
	}

	getList();

	function deleteItem(id, enableStatus) {
		var product = {};
		product.productId = id;
		product.enableStatus = enableStatus;
		$.confirm('确定么?', function() {
			$.ajax({
				url : deleteUrl,
				type : 'POST',
				data : {
					productStr : JSON.stringify(product),
					statusChange : true
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.toast('操作成功！');
						getList();
					} else {
						$.toast('操作失败！');
					}
				}
			});
		});
	}

	$('.product-wrap').on('click','a', function(e) {
		var target = $(e.currentTarget);
		if (target.hasClass('edit')) {
			// 编辑商品信息
			window.location.href = '/o2o/shopadmin/productedit?productId='
					+ e.currentTarget.dataset.id;
		} else if (target.hasClass('delete')) {
			// 下架商品，设置enableStatus为0
			deleteItem(e.currentTarget.dataset.id,
					e.currentTarget.dataset.status);
		} else if (target.hasClass('preview')) {
			// 预览商品信息
			window.location.href = '/o2o/frontend/productdetail?productId='
					+ e.currentTarget.dataset.id;
		}
	});

	$('#new').click(function() {
		window.location.href = '/o2o/shopadmin/productedit';
	});
});