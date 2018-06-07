//全选
function selectAdmins(chkObj) {
    var trs = document.getElementById("datalist").getElementsByTagName("tr");
    for (var i = 1; i < trs.length; i++)
        trs[i].getElementsByTagName("input")[0].checked=chkObj.checked;
}

function buttonEvents(){
	var trs=document.getElementById("datalist").getElementsByTagName("tr");
	for(var i=1;i<trs.length;i++){
		var td=trs[i].getElementsByTagName("td")[4];
		var modify=td.getElementsByTagName("input")[0];
		modify.addEventListener("click",modiAdmin,false);
		var del=td.getElementsByTagName("input")[1];
		del.addEventListener("click",deleteAdmin,false);
	}
}
window.addEventListener("load",buttonEvents,false);

//修改
function modiAdmin() {
    var div = document.getElementById("operate_result_info");
    div.style.display = "block";
    var span=div.getElementsByTagName("span")[0];
    span.className = "success"
	span.innerHTML = "修改成功！";
}

//删除
function deleteAdmin() {
    var r = window.confirm("确定要删除此管理员吗？");
    if (r) {
        var div = document.getElementById("operate_result_info");
        div.style.display = "block";
		var span=div.getElementsByTagName("span")[0];
        span.className = "fail";
        span.innerHTML = "删除失败！";
    }
	return r;
}

