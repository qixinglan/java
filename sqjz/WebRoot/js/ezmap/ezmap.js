if(_appServer&&_appServer=="/")
	_appServer="";
var mapApp;

function init()
{
	mapApp = new EzMap(document.getElementById("map"));
	//_MapApp.showMapControl();
	mapApp.showStandMapControl();
	mapApp.hideMapServer();
}
//地图居中
function Center(){
	mapApp.gotoCenter();
}
//定位到某点
function Location(x,y)
{
	var strpoint=convertpoint(x,y);
	var p=new Point(strpoint.split(",")[0],strpoint.split(",")[1]);
	mapApp.centerAtPoint(p);
}
function SetCenter(x,y,level)
{
	if(level)
		zoomTo(level);
	Location(x,y);
	
}
//地图放大
function ZoomIn()
{
   mapApp.zoomIn();	
}
//测距离
function measureLength()
{
   mapApp.measureLength();	
}
//测面积
function measureArea()
{
	mapApp.measureArea();
}


//地图缩小
function ZoomOut()
{
	 mapApp.zoomOut();	
}
//显示影像地图
function showSatelliteMap()
{
	mapApp.map.setSatelliteMap();	
}
//矢量地图
function showVectorMap(){
	mapApp.map.setVectorMap();
}
//影像加矢量
function showVectorSateMap(){
   mapApp.map.setVectorSateMap();
}
//清除地图上所有
function clearMaps()
{
   mapApp.clearOverlays(true);	
   //mapApp.clearVMLContainer();
}

//地图拉框放大
function ZoomBoxIn()
{
	mapApp.zoomInExt();
}
//移动到某个坐标点
function PanTo(x,y)
{ 
  mapApp.panTo(new OpenLayers.LonLat(x,y));	
}
//地图漫游
function pan()
{
	mapApp.pan();	
}
function zoomTo(level){
	mapApp.zoomTo(level);
}

function GetPostionHandler(result)
{
	mapApp.pan();
	points=result;
   	//alert(result);
}
var points;
var pPolygon;
var state="";
function deleteFxry(gid)
{
	var xmlhttp=XMLHttp.create();	
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4){
			try{
				alert(xmlhttp.responseText);
			}
			catch(b){
			
			}
			state="";
		}
	}
	xmlhttp.open("POST",_appServer+"/data/xtsz/deletefxry.action",false);
	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	var data="gid="+gid;
	xmlhttp.send(data);
}
function GetFxryDZWLAll(fxryid)
{   
	if(mapApp.getZoomLevel()<8)
		zoomTo(8);
	var xmlhttp=XMLHttp.create();	
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4){
			try{
				var fxrylist=eval(eval(xmlhttp.responseText));
				if(fxrylist){
					for(var i=0;i<fxrylist.length;i++)
					{
				       //mapApp.removeOverlay(pPolygon);
						var coler="red";
						if(fxrylist[i].geotype=="01"){
							coler="blue";
					    }
						var polygon=new Polygon(fxrylist[i].geom,coler, 3,0.3,coler);
						polygon.gid=fxrylist[i].gid;
						polygon.addListener("click",function(){
							if(state=="delete")
							 {
								deleteFxry(polygon.gid);
							 }
						});
						//mapApp.centerAndZoom(pPolygon.getMBR().centerPoint(),8);
						mapApp.addOverlay(polygon);
				 }
				//mapApp.pan();
			 } 
			}
			catch(b){
			
			}			
	 }
	}
	xmlhttp.open("POST",_appServer+"/data/xtsz/getfxrydzwl.action",true);
	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	var data="fxryid="+fxryid;
	xmlhttp.send(data);
	//var strMsg="msg"+iIndex;
	//iIndex++;
	//pPolygon.addListener("click",function(){pPolygon.openInfoWindowHtml(strMsg);});
	//pPolygon.show();
}
function getZoomLevel()
{
	return mapApp.getZoomLevel();
}
function getCenter()
{
	var point=mapApp.getCenterLatLng();
	return Convert54ToWgs84(point.x,point.y);
}

//获取区县默认电子围栏
function GetFxryDZWL(fxryid)
{   
	if(mapApp.getZoomLevel()<8)
		zoomTo(8);
	var xmlhttp=XMLHttp.create();	
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4){
			try{
				points=eval(xmlhttp.responseText);
			}
			catch(b){
			
			}			
		}
	}
	xmlhttp.open("POST",_appServer+"/data/xtsz/getfxrypolyon.action",false);
	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	var data="fxryid="+fxryid;
	xmlhttp.send(data);
	mapApp.removeOverlay(pPolygon);
    pPolygon=new Polygon(points,"#ff00FF", 3,0.2,"blue");
    mapApp.centerAndZoom(pPolygon.getMBR().centerPoint(),8);
	mapApp.addOverlay(pPolygon);
	mapApp.pan();
}
var polyline;
function DrawLSGJ(strps){
	if(mapApp.getZoomLevel()>1)
		zoomTo(mapApp.getZoomLevel());
	var lsgjpoints=convertpoints(strps);
	if(polyline)
	{
		mapApp.removeOverlay(polyline);
	}
	polyline=new Polyline(lsgjpoints,"red",3, 0.5,1);	
	//mapApp.centerAndZoom(pPolygon.getMBR().centerPoint(),8);
	mapApp.addOverlay(polyline);
	mapApp.pan();
	return lsgjpoints;
	
}
function convertpoint(x,y)
{
    var xmlhttp=XMLHttp.create();
    var result="";
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4){
			try{
			 
				result=eval(xmlhttp.responseText);		
			}
			catch(b){
			
			}			
		}
	}
	var data="x="+x+"&"+"y="+y;
	xmlhttp.open("POST",_appServer+"/data/xtsz/convertpoint.action",false);
	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlhttp.send(data);	
	return result;
}
function Convert54ToWgs84(x,y)
{
    var xmlhttp=XMLHttp.create();
    var result="";
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4){
			try{
			 
				result=eval(xmlhttp.responseText);		
			}
			catch(b){
			
			}			
		}
	}
	var data="x="+x+"&"+"y="+y;
	xmlhttp.open("POST",_appServer+"/data/xtsz/Convert54ToWgs84.action",false);
	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlhttp.send(data);	
	return result;
}

function convertpoints(strpoints)
{
    var xmlhttp=XMLHttp.create();
    var result="";
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4){
			try{
			 
				result=eval(xmlhttp.responseText);		
			}
			catch(b){
			
			}			
		}
	}
	var data="points="+strpoints;
	xmlhttp.open("POST",_appServer+"/data/xtsz/convertpoints.action",false);
	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlhttp.send(data);	
	return result;
}
function locationt(x,y){
	var strpoint=convertpoint(x,y);
	var p=new Point(strpoint.split(",")[0],strpoint.split(",")[1]);
	Location(p.x,p.y);
	mapApp.zoomTo(0);
	var url=_appServer+"/js/cmap/openlayer/img/marker.png";
	Marker1(p,url,"sdfsdfsfsf");
}
function drawdzwlhandler(result)
{
    var xmlhttp=XMLHttp.create();	
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4){
			try{
				//alert(xmlhttp.responseText);
				mapApp.pan();
				location.href=location.href;
			}
			catch(b){
			
			}			
		}
	}
	var data="points="+result+"&"+parent.location.href.split("?")[1];
	xmlhttp.open("POST",_appServer+"/data/xtsz/savefxry.action",false);
	xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlhttp.send(data);
	
}
//获取位置信息
function GetPostion()
{
	mapApp.changeDragMode('drawPoint',null,null,GetPostionHandler);
}
//标绘电子围栏
function DrawDZWL()
{      
	mapApp.changeDragMode('drawPolygon',null,null,drawdzwlhandler);
}
function DeleteDZWL(){
  state="delete";	
}
function addMarker(x,y,strMsg,detail,width,height,leftOffset,topOffset)
{	
	var point=new Point(x,y);
	//Location(p.x,p.y);
	//document.frames("ezmap").mapApp.zoomTo(5);							
	var url=_appServer+"/js/cmap/openlayer/img/marker.png";
	var pIcon=new Icon();
	pIcon.image=url;
	if(!width)
		width=20;
	if(!height)
		height=20;
	pIcon.width=width;
	pIcon.height=height;
	if(!topOffset)
		topOffset=-8;
	if(!leftOffset)
		leftOffset=-15;
	pIcon.leftOffset=leftOffset;
	pIcon.topOffset=topOffset;
	//添加到地图上的标注
	var t=new Title("");
   
	var pMarker = new Marker(point,pIcon,t);
	t.bIsTransparent=true;
	//t.div.innerHTML=strMsg;
	pMarker.div=document.createElement("div");
	
	pMarker.titleDiv.style.border=0;
	pMarker.titleDiv.style.backgroundColor="";
	pMarker.titleDiv.style.Opacity=0;
	pMarker.titleDiv.innerHTML=strMsg;
	//alert(pMarker.div.innerHTML);
	if(detail&&detail!=""){
		pMarker.addListener("click",function(){pMarker.openInfoWindowHtml(detail);});
	}
	//添加到地图上
	mapApp.addOverlay(pMarker);
	return pMarker;
}
//标点
function addMarkerBylayer(x,y,strMsg,detail,l,width,height,leftOffset,topOffset)
{
	var strpoint=convertpoint(x,y);
	return addMarker(strpoint.split(",")[0],strpoint.split(",")[1],strMsg,detail,width,height,leftOffset,topOffset);
}