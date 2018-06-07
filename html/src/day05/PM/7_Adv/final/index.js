var imageArray = ["rose.jpg", "daffodil.jpg", "apple.jpg", "daisy.jpg"];
var imageTimer;
var imageIndex = 1;

var rotateFunc = function () {
        var image = document.getElementById("img1");
        image.src = "images/" + imageArray[imageIndex];
        if (imageIndex == imageArray.length - 1)
            imageIndex = 0;
        else
            imageIndex++;
    };
//启动图片轮换
function startRotate() {    
    imageTimer = window.setInterval(rotateFunc, 2000);
}

window.addEventListener("load",startRotate,false);

//停止图片轮换
function stopRotate() {
    window.clearInterval(imageTimer);
}