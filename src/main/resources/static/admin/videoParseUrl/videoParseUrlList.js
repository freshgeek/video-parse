/***
*
* @todo  基础解析路径列表控制js
* @author cc 
    qq: 3570632401  
     淘宝链接:[https://m.tb.cn/h.ewA2rg5?sm=60123d]
* @version 2020-01-03
*/
layui.extend({
    admin: '/layui/js/extends/admin',
    js_tools: '/layui/js/extends/js_tools'
});

layui.use(['laydate','admin','form', 'jquery', 'table', 'js_tools'], function () {
    var laydate = layui.laydate,
        $ = layui.jquery,
        form = layui.form,
        js_tools = layui.js_tools,
        table = layui.table;

    var option = js_tools.getTableTemplate();
    option.url = "/admin/api/videoParseUrl/page";
    option.cols = [[
        {fixed: 'left',checkbox: true},
        {field: 'id', title: '解析id'},
        {field: 'parseUrl', title: '解析地址'},
        {field: 'status', title: '状态 0有效 1失效过度 2失效'},
        {field: 'mark', title: '备注'},
        {field: 'failWeight', title: '失败权重'},
        {fixed: 'right',title:'操作', width:150, align:'center', toolbar: '#list_btn'} //这里的toolbar值是模板元素的选择器
    ]];

    window.videoParseUrlListIns = table.render(option);

    //监听工具条
    table.on('tool(list)', function (obj) {
        var data = obj.data;

        if (obj.event === 'edit') {
            // 编辑功能
            WeAdminEdit('编辑','./videoParseUrlEdit.html',data.id);
        } else if (obj.event === 'del') {
            //删除功能
            js_tools.quick_post("/admin/api/videoParseUrl/delete",data,function (rs) {
               if (rs.code==js_tools.successCode){
                   table.reload('list');
               }else{
                   layer.alert(rs.msg);
               }
            });
        } else {
            //其他功能

        }
    });

    //表格筛选监听
    form.on('submit(search)', function(data){
        //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        videoParseUrlListIns.reload({where:data.field});
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});