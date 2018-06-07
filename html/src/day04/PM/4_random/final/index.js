//得到随机数
function getRandomNumber() {
    var min = parseInt(document.getElementById("txtMin").value);
    var max = parseInt(document.getElementById("txtMax").value);
    var ran = Math.random();
    var data = Math.floor(ran * (max - min)) + min;
    alert(data);
}
