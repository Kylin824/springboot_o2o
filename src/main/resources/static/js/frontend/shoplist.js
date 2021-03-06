$(function() {
	var loading = false;
	var maxItems = 999;
	var pageSize = 10;
	var listUrl = '/o2o/frontend/listshops';
	var searchDivUrl = '/o2o/frontend/listshopspageinfo';
	var pageNum = 1;
	var parentId = getQueryString('parentId');
	var areaId = '';
	var shopCategoryId = '';
	var shopName = '';

	function getSearchDivData() {
		// 如果parentId不为空则取出此parent类别下的所有二级类别
		var url = searchDivUrl + '?' + 'parentId=' + parentId;
		$.getJSON(url,function (data) {
			if (data.success) {
				// 获取后台返回的店铺类别列表
				var shopCategoryList = data.shopCategoryList;
				var html = '';
				html += '<a href="#" class="button" data-category-id=""> 全部类别  </a>';

				// 拼接a标签集
				shopCategoryList
					.map(function (item, index) {
						html += '<a href="#" class="button" data-category-id='
							+ item.shopCategoryId
							+ '>'
							+ item.shopCategoryName
							+ '</a>';
					});
				// 拼接好的html嵌入前台的html组件里
				$('#shoplist-search-div').html(html);
				var selectOptions = '<option value="">全部街道</option>';
				var areaList = data.areaList;

				// 拼接option标签集
				areaList.map(function (item, index) {
					selectOptions += '<option value="'
						+ item.areaId + '">'
						+ item.areaName + '</option>';
				});
				// 拼接好的html嵌入前台的html组件里
				$('#area-search').html(selectOptions);
			}
		});
	}

	// 渲染出店铺类别列表以及区域列表以供搜索
	getSearchDivData();

	function addItems(pageSize, pageIndex) {
		// 拼接查询的url，参数为查询的条件
		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize + '&parentId=' + parentId + '&areaId=' + areaId
				+ '&shopCategoryId=' + shopCategoryId + '&shopName=' + shopName;

		// 设定加载符，若还在后台取数据则不能再次访问后台，避免多次重    复加载
		loading = true;
		// 获取查询得到的店铺信息列表
		$.getJSON(url, function(data) {
			if (data.success) {
				maxItems = data.count;
				var html = '';
				data.shopList.map(function(item, index) {
					html += '' + '<div class="card" data-shop-id="'
							+ item.shopId + '">' + '<div class="card-header">'
							+ item.shopName + '</div>'
							+ '<div class="card-content">'
							+ '<div class="list-block media-list">' + '<ul>'
							+ '<li class="item-content">'
							+ '<div class="item-media">' + '<img src="'
							+ item.shopImg + '" width="44">' + '</div>'
							+ '<div class="item-inner">'
							+ '<div class="item-subtitle">' + item.shopDesc
							+ '</div>' + '</div>' + '</li>' + '</ul>'
							+ '</div>' + '</div>' + '<div class="card-footer">'
							+ '<p class="color-gray">'
							+ new Date(item.lastEditTime).Format("yyyy-MM-dd")
							+ '更新</p>' + '<span>点击查看</span>' + '</div>'
							+ '</div>';
				});
				$('.list-div').append(html);
				var total = $('.list-div .card').length;
				if (total >= maxItems) {
					// 加载完毕，则注销无限加载事件，以防不必要的加载
					$.detachInfiniteScroll($('.infinite-scroll'));
					// 删除加载提示符
					$('.infinite-scroll-preloader').remove();
				}
				pageNum += 1;
				loading = false;
				$.refreshScroller();
			}
		});
	}

	// 预先加载10条店铺信息
	addItems(pageSize, pageNum);

	$(document).on('infinite', '.infinite-scroll-bottom', function() {
		if (loading)
			return;
		addItems(pageSize, pageNum);
	});

	$('.shop-list').on('click', '.card', function(e) {
		var shopId = e.currentTarget.dataset.shopId;
		window.location.href = '/o2o/frontend/shopdetail?shopId=' + shopId;
	});

	$('#shoplist-search-div').on(
			'click',
			'.button',
			function(e) {
				if (parentId) {// 如果传递过来的是一个父类下的子类
					shopCategoryId = e.target.dataset.categoryId;
					if ($(e.target).hasClass('button-fill')) {
						$(e.target).removeClass('button-fill');
						shopCategoryId = '';
					} else {
						$(e.target).addClass('button-fill').siblings()
								.removeClass('button-fill');
					}
					$('.list-div').empty();
					pageNum = 1;
					addItems(pageSize, pageNum);
				} else {// 如果传递过来的父类为空，则按照父类查询
					parentId = e.target.dataset.categoryId;
					if ($(e.target).hasClass('button-fill')) {
						$(e.target).removeClass('button-fill');
						parentId = '';
					} else {
						$(e.target).addClass('button-fill').siblings()
								.removeClass('button-fill');
					}
					$('.list-div').empty();
					pageNum = 1;
					addItems(pageSize, pageNum);
					parentId = '';
				}

			});

	$('#search').on('change', function(e) {
		shopName = e.target.value;
		$('.list-div').empty();
		pageNum = 1;
		addItems(pageSize, pageNum);
	});

	$('#area-search').on('change', function() {
		areaId = $('#area-search').val();
		$('.list-div').empty();
		pageNum = 1;
		addItems(pageSize, pageNum);
	});

	$('#me').click(function() {
		$.openPanel('#panel-left-demo');
	});

	$.init();
});
