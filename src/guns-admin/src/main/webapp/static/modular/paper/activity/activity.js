var Activity = {
    id: "ActivityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Activity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            //{title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '活动名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '链接', field: 'url', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'beginTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'}
    ];
};
 

/**
 * 检查是否选中
 */
Activity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Activity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加论文
 */
Activity.openAddActivity = function () {
    //Feng.error("add paper");
    var index = layer.open({
        type: 2,
        title: '添加论文',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paper/paper/paper_add'
    });
    this.layerIndex = index;
};

// paper detail
Activity.openActivityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '论文详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/paper/paper_update/' + Activity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除paper
 */
Activity.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/paper/delete", function () {
                Feng.success("删除成功!");
                Dept.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("deptId",Activity.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除该论文?", operation);
    }
};

/**
 * 查询论文列表
 */
Activity.search = function () {
    //console.log("????");
    var queryData = {};
    queryData['activityname'] = $("#condition").val();
    Activity.table.refresh({query: queryData});
};

$(function () {
    //Peng.error("init paper");
    //console.log("init paper");
    var defaultColunms = Activity.initColumn();
    var table = new BSTable(Activity.id, "/activity/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    Activity.table = table;
});
