var Subject = {
    id: "SubjectTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Subject.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '领域名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '已选中', field: 'checked', visible: true, align: 'center', valign: 'middle'},
            //{title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};
 
// update and save
Subject.update = function () {
    if (this.check()){
        console.log("ok");
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/subject/update", function () {
                Feng.success("保存成功!");
                Subject.table.refresh();
            }, function (data) {
                Feng.error("保存失败!" + data.responseJSON.message + "!");
            });
            //console.log(Subject.seItem.name);
            var ids = Subject.seItem.map(a => a.id);
            console.log(ids);
            ajax.set("subjectIds", ids);
            ajax.start();
        };
        Feng.confirm("是否保存关注学科设置?", operation);
    }
};

/**
 * 检查是否选中
 */
Subject.check = function () {
    //var selected = $('#' + this.id).bootstrapTable('getSelections');
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    console.log(selected.length);
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        //Subject.seItem = selected[0];
        Subject.seItem = selected;
        console.log(selected);
        return true;
    }
};

/**
 * 点击添加关注的领域
 */
Subject.openAddSubject = function () {
    Feng.error("add paper");
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
Subject.openSubjectDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '论文详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/paper/paper_update/' + Subject.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除paper
 */
Subject.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/paper/delete", function () {
                Feng.success("删除成功!");
                Dept.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("deptId",Subject.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除该论文?", operation);
    }
};

/**
 * 查询论文列表
 */
Subject.search = function () {
    //console.log("searching");
    var queryData = {};
    queryData['subjectname'] = $("#condition").val();
    Subject.table.refresh({query: queryData});
};

/*
$(function () {
    var defaultColunms = Subject.initColumn();
    var table = new BSTable(Subject.id, "/subject/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    Subject.table = table;
});
*/


$(function () {
    var defaultColunms = Subject.initColumn();
    var table = new BSTreeTable(Subject.id, "/subject/list", defaultColunms);
    //table.setPaginationType("server"); 
    // 猜测：会提供分页的一些参数，比如页数限制，这个在controller的getPage中必须
    // 但是TreeTable不能分页，所以必须在controller中wrap起来再发给客户端
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pId");
    table.setExpandAll(true);
    table.init();
    
    //table.setPaginationType("server");
    Subject.table = table;
});