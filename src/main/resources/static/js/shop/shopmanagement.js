$(function () {

    var shopId = getQueryString("shopId");
    var shopInfoUrl = "/o2o/shopadmin/setcurrentshopsession?shopId=" + shopId;
    // $('#shopInfo').attr('href', '/o2o/shopadmin/shopoperation?shopId=' + shopId);

    // var shopInfoUrl = '/o2o/shopadmin/getshopmanagementinfo?shopId=' + shopId;

    // 这一步会在后台将对应的shopId存到session中
    $.getJSON(shopInfoUrl, function(data) {
        // 如果shopInfoUrl
        if (data.redirect) {
            // 如果后台发送重定向，则执行重定向
            window.location.href = data.url;
        }
        else{
            if (data.shopId != undefined && data.shopId != null) {
                shopId = data.shopId;
            }
        }
        $('#shopInfo').attr('href', '/o2o/shopadmin/shopoperation?shopId=' + shopId);
    });
});
