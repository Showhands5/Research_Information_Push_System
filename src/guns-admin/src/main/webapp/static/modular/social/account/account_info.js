/**
 * 初始化系统参数详情对话框
 */
var AccountInfoDlg = {
    accountInfoData : {}
};

/**
 * 清除数据
 */
AccountInfoDlg.clearData = function() {
    this.accountInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccountInfoDlg.set = function(key, val) {
    this.accountInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccountInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AccountInfoDlg.close = function() {
    parent.layer.close(window.parent.Account.layerIndex);
}

/**
 * 收集数据
 */
AccountInfoDlg.collectData = function() {
    this
    .set('id') // 必须在，否则更新后新增一条数据（如果数据库设置了自增）
    .set('username')
    .set('website');
}

/**
 * 提交添加
 */
AccountInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/social/account/add", function(data){
        Feng.success("添加成功!");
        window.parent.Account.table.refresh();
        AccountInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accountInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AccountInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/social/account/update", function(data){
        Feng.success("修改成功!");
        window.parent.Account.table.refresh();
        AccountInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accountInfoData);
    ajax.start();
}

$(function() {

});
