var Account = {
    id: "AccountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Account.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '账号名', field: 'username', visible: true, align: 'center', valign: 'middle'},
            {title: '网站', field: 'website', visible: true, align: 'center', valign: 'middle'},
            //{title: '领域', field: 'subject.name', visible: true, align: 'center', valign: 'middle'},
            //{title: '备注', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};
 

/**
 * 检查是否选中
 */
Account.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Account.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加论文
 */
Account.openAddAccount = function () {
    //Feng.error("add paper");
    //console.log("add account");
    var index = layer.open({
        type: 2,
        title: '添加账号',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/social/account/account_add'
    });
    this.layerIndex = index;
};

// paper detail
Account.openAccountDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '账号详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/social/account/account_update/' + Account.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除paper
 */
Account.delete = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/social/account/delete", function () {
                Feng.success("删除成功!");
                Account.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("socialAccountId",Account.seItem.id);
            ajax.start();
        };

        Feng.confirm("是否刪除该账号?", operation);
    }
};

/**
 * 查询论文列表
 */
Account.search = function () {
    var queryData = {};
    queryData['username'] = $("#condition").val();
    console.log(queryData['username'])
    Account.table.refresh({query: queryData});
};

$(function () {
    //Peng.error("init paper");
    //console.log("account");
    var defaultColunms = Account.initColumn();
    var table = new BSTable(Account.id, "/social/account/list", defaultColunms);
    table.setPaginationType("server");
    table.init();
    Account.table = table;
});
