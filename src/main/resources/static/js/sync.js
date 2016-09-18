/**
 * Created by admin on 2016/9/13.
 */
var circle = "<div class='preloader-wrapper big active'>" +
    "<div class='spinner-layer spinner-blue-only'>" +
    "<div class='circle-clipper left'>" +
    "<div class='circle'></div>" +
    "</div>" +
    "<div class='gap-patch'>" +
    "<div class='circle'></div>" +
    "</div>" +
    "<div class='circle-clipper right'>" +
    "<div class='circle'></div> " +
    "</div>" +
    "</div>" +
    "</div>";

function comparison() {
    var data = {
        from_appid: $('#from_appid').val(),
        from_appsecret: $('#from_appsecret').val(),
        to_appid: $('#to_appid').val(),
        to_appsecret: $('#to_appsecret').val()
    }
    var compare_date = $("#compare_date");
    var circle_div = $("#circle");
    compare_date.empty();
    circle_div.append($(circle));
    $.ajax(
        {
            type: "post",
            url: "/sync/comparison",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (data) {
                circle_div.empty();
                var compare_date_div = $("<div class='card-panel'></div>")
                var table = $("<table class='highlight'>" +
                    "<tr>" +
                    "<td><input type='checkbox' id='checkbox_all' onchange='checkbox_all()'/><label for='checkbox_all'></label>选择</td>" +
                    "<td>主库</td>" +
                    "<td>从库</td>" +
                    "</tr></table>");
                table.append(detail(data, table));
                compare_date_div.append($(table));
                compare_date.append(compare_date_div);

                $('.collapsible').collapsible({
                    accordion: false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
                });
            }
        }
    )
}

function detail(comparison, table) {
    $.each(comparison, function (i, item) {
        var fromResource = item.fromResource || "";
        var toResource = item.toResource || "";
        var detailComparisons = item.detailComparisons;
        var flag = "" != toResource;
        var tr = $("<tr></tr>");
        var check_td = $("<td></td>");
        var checkbox = $("<input type='checkbox' id='" + fromResource.id + "checkbox" + "'  data-from='" + fromResource.id + "'  data-to='" + toResource.id + "'/><label for='" + fromResource.id + "checkbox" + "'></label>");
        check_td.append(checkbox);
        tr.append(check_td);

        var from_td = build_td(detailComparisons, fromResource, 0);
        tr.append(from_td);

        if (flag) {
            var to_td = build_td(detailComparisons, toResource, 1);
            tr.append(to_td);
        }

        table.append(tr);
    })

}
function build_td(detailComparisons, resource, type) {
    var flag = undefined != detailComparisons && detailComparisons != null;
    var td = $("<td></td>");
    //var to_td = $("<td></td>");
    var ul = $("<ul class='collapsible collapsible-accordion' data-collapsible='accordion'></ul>");
    var li = $("<li></li>");
    var head_div = $("<div class='collapsible-header'>" + resource.r_cn_name + "</div>");
    var body_div = $("<div class='collapsible-body'></div>");
    var div = $("<div class='card-panel'></div>");
    if (flag) {
        build_detail(detailComparisons, div, type);
    }
    body_div.append(div);
    li.append(head_div);
    li.append(body_div);
    ul.append(li);
    td.append(ul);
    return td;
}
function build_detail(detailComparisons, div, type) {
    var tabel = $("<tabel class='centered'></tabel>");
    var first_tr = $("<tr></tr>");
    var first_td = $("<td>字段</td><td>名称</td><td>长度</td><td>类型</td>");
    first_tr.append(first_td);
    tabel.append(first_tr);
    $.each(detailComparisons, function (i, item) {
        var Detail;
        if (type == 0) {
            Detail = item.fromDetail;
        } else {
            Detail = item.toDetail;
        }
        var from_flag = undefined != Detail && Detail != null;
        if (from_flag) {
            var tr = $("<tr></tr>");
            var td = $("<td>" + Detail.rd_name + "</td>" +
                "<td>" + Detail.rd_cn_name + "</td>" +
                "<td>" + Detail.rd_length + "</td>" +
                "<td>" + Detail.field_type + "</td>");
            tr.append(td);
            tabel.append(tr);
        }
    });
    div.append(tabel);
}

function sync() {
    var checkbox = $("input:checkbox:checked");
    if (checkbox.size() == 0) {
        alert("请先选择要同步数据")
    } else {

        var fromTo = [];
        checkbox.each(function (i, item) {
            var data = {
                from_appid: $('#from_appid').val(),
                from_appsecret: $('#from_appsecret').val(),
                to_appid: $('#to_appid').val(),
                to_appsecret: $('#to_appsecret').val()
            };
            data.from_resource = $(item).data("from") || "";
            data.to_resource = $(item).data("to") || "";
            fromTo.push(data);
        })

        $.ajax({
            type: "post",
            url: "/sync/sync",
            data: JSON.stringify(fromTo),
            contentType: "application/json",
            success: function (data) {
                console.info(data);
                comparison();
            }
        });
    }
}
function exchange() {
    var from_appid = $('#from_appid').val();
    var from_appsecret = $('#from_appsecret').val();
    var to_appid = $('#to_appid').val();
    var to_appsecret = $('#to_appsecret').val();
    $('#from_appid').val(to_appid);
    $('#from_appsecret').val(to_appsecret);
    $('#to_appid').val(from_appid);
    $('#to_appsecret').val(from_appsecret);
}
function checkbox_all() {
    var chcekbox_all = $("#checkbox_all");
    if ($("#checkbox_all").prop('checked') == true) {
        $("input:checkbox").prop('checked', true);
    } else {
        $("input:checkbox").prop('checked', false);
    }
}