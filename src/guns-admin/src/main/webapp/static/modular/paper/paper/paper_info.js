/**
 * 初始化系统参数详情对话框
 */
var PaperInfoDlg = {
    paperInfoData : {}
};

/**
 * 清除数据
 */
PaperInfoDlg.clearData = function() {
    this.paperInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperInfoDlg.set = function(key, val) {
    this.paperInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaperInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PaperInfoDlg.close = function() {
    parent.layer.close(window.parent.Paper.layerIndex);
}

/**
 * 收集数据
 */
PaperInfoDlg.collectData = function() {
    this
    .set('id')
    .set('cfgName')
    .set('cfgValue')
    .set('cfgDesc');
}

/**
 * 提交添加
 */
PaperInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paper/add", function(data){
        Feng.success("添加成功!");
        window.parent.Paper.table.refresh();
        PaperInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PaperInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paper/paper/update", function(data){
        Feng.success("修改成功!");
        window.parent.Paper.table.refresh();
        PaperInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paperInfoData);
    ajax.start();
}

$(function() {

});
