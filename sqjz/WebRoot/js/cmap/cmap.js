var proj='EPSG:4326'; //坐标系 wgs84          
var mapResolutions =[
		0.12500000233169614,
		0.062500001165848071,
		0.031250000582923911,
		0.015625000291462004,
		0.0078125001457310019,
		0.003906250072865501,
		0.0019531250364327505,
		0.00097656251821637394,
		0.00048828125910818827,
		0.00024414062955409386,
		0.0001220703147770468,
		6.1035157388523398e-005,
		3.0517578694261699e-005,
		1.525878934713085e-005,
		7.6293946735654383e-006
		];
var baseUrl="http://192.168.40.132:10590";
var tileSize = new OpenLayers.Size(256,256);  //切片大小                      
var agsTileOrigin = new OpenLayers.LonLat(-256,256); //坐标原点                
var mapExtent = new OpenLayers.Bounds(-180,-90,180,90);//切片范围        
var aerialsUrl = baseUrl+"/arcgiscache/DHMapService/Layers/_alllayers";//加载切片地图地址 
OpenLayers.Lang.code="zh-CN";//语言设置 
var markers;//用于标绘服刑人员位置
var map;//定义地图变量
var wfs;//用于标绘服刑人员电子围栏图层
var unitlayer;//区县标记图
var selectLayer;//查询标记层
var LSGJLayer;//历史轨迹层
var panPanel;//地图工具栏
var measureControls;//测量控件
var drawDZWL;//电子围栏标绘控件
var fxryId;//服刑人员ID
var deleteDZWL;//删除电子围栏控件
var modifyDZWL;//修改电子围栏
var layer_lsgj_point ;//历史轨迹点层
var vPath="/sqjz";
if(typeof CONTEXT_PATH!="undefined")
   vPath=CONTEXT_PATH;
OpenLayers.ProxyHost=vPath+"/proxy.jsp?url=";
//OpenLayers.ProxyHost="/sqjz/proxy.jsp?url=";
function init(){
	map = new OpenLayers.Map({
    		div:"map", //地图的div容器
    		panDuration:10,//移动的间距
    		maxExtent:mapExtent, //最大的视野范围
   		 	//displayProjection: new OpenLayers.Projection("EPSG:900913"),
    		controls: [
        	//new OpenLayers.Control.LayerSwitcher(), 
        	new OpenLayers.Control.PanZoomBar(),//滑动放大的杆子
        	new OpenLayers.Control.MousePosition(),//显示鼠标坐标的控件
        	new OpenLayers.Control.Navigation(),//导航,左右上下移动的控件
		    new OpenLayers.Control.OverviewMap({maximized: false}),//鹰眼
		    new OpenLayers.Control.ScaleLine(),//左下角标尺
		    new OpenLayers.Control.Attribution()//
	      ]
	   });
        var baseLayer = new OpenLayers.Layer.ArcGISCache('北京市', aerialsUrl, {
            tileOrigin: agsTileOrigin, //坐标原点 
            resolutions: mapResolutions, //切片
            //maxResolution:0.12500000233169614,
            //numZoomLevels:14,
            //sphericalMercator: true,
            maxExtent: mapExtent,//切片范围
            useArcGISServer: false, //是否使用ArcGIS
            isBaseLayer: true, //是否基础图层
            type: 'png',
            projection: proj, //坐标系
            transitionEffect: 'resize', //图片移动效果
            loading:true
        });
        //矢量图层 WFS 矢量图层一种标准服务  
        wfs=new OpenLayers.Layer.Vector("电子围栏", {
        	strategies: [new OpenLayers.Strategy.BBOX()], //图层策略
            protocol: new OpenLayers.Protocol.WFS({
                 url: baseUrl+"/geoserver/wfs",
                 featureType: "fxrywlsz",//对应图层名称
                 geometryName: "geom" //几何图形名称,点线面
                 }),
            styleMap: new OpenLayers.StyleMap(GetStyle("green",3,0)), //点线面的样式
            filter: new OpenLayers.Filter.Logical({
                type: OpenLayers.Filter.Logical.OR, //逻辑是或
                filters: [
                    new OpenLayers.Filter.FeatureId({
                        fids:['fxrywlsz.17'] //数据库里ID为17的图层,全北京市的
                    })
                ] //过滤数组
             })
        });
        //var format = new OpenLayers.Format.CQL();
        markers = new OpenLayers.Layer.Markers( "服刑人员位置" ); //位置图层
        unitlayer=new OpenLayers.Layer.Vector("区县边界"); //区县边界矢量图层
        selectLayer=new OpenLayers.Layer.Vector("查询人员标记"); 
        LSGJLayer=new OpenLayers.Layer.Vector("历史轨迹图层",{styleMap: new OpenLayers.StyleMap(GetStyle("red",3,0))});      
        layer_lsgj_point = new OpenLayers.Layer.Markers("lsgj_point");
        var beijing=new OpenLayers.Layer.Vector("beijing", {
            strategies: [new OpenLayers.Strategy.BBOX()], //图层策略
            protocol: new OpenLayers.Protocol.WFS({ 
            url: baseUrl+"/geoserver/wfs",
            featureType: "fxrywlsz",
            geometryName: "geom"
            }),
            styleMap: new OpenLayers.StyleMap(GetStyle("green",3,0.5)),
            filter: new OpenLayers.Filter.Logical({
                type: OpenLayers.Filter.Logical.OR,
                filters: [
                    new OpenLayers.Filter.FeatureId({
                        fids:['fxrywlsz.17']
                    }),
                    new OpenLayers.Filter.Comparison({
                        type: OpenLayers.Filter.Comparison.EQUAL_TO,
                        property: "fxryid",
                        value: "天津市"
                    })
                ]
             })
        });
   /*var wmsLayer = new OpenLayers.Layer.WMS( "test", 
                baseUrl+"/geoserver/wms", 
                {layers: 'beijing:fxrywlsz'},
                {
                	isBaseLayer: false,
                	opacity: 0.1
                }); 
                */
        //导航底图、区县边界、服刑人员电子围栏图层、没用到的图层、历史轨迹线图层、服刑人员位置点图层、历史轨迹点的图层的增加
	map.addLayers([baseLayer,unitlayer,wfs,selectLayer,LSGJLayer,markers,layer_lsgj_point]);
    InitDrawDZWL(); //初始化 "画"  电子围栏 在地图上操作时的  地图控件
    InitMeatrue();  //初始化  “量距离、测面积” 在地图上操作时显示的  地图控件
    deleteDZWL=new DeleteFeature(wfs);  //初始化   删除电子围栏时的  控件   
    //test();
    modifyDZWL = new OpenLayers.Control.ModifyFeature(wfs);  //初始化   修改电子围栏的  控件
    map.addControls([deleteDZWL,modifyDZWL]);
	var panel = new OpenLayers.Control.Panel({
        displayClass: 'customEditingToolbar',
        allowDepress: true
	}); 
	//GetFxryDZWL("北京市",'02','red');
	var measureLength = new OpenLayers.Control.Button(
	{
		title: "量距离",
		displayClass: "olControlLJLFeature",
		trigger:function(){
			measureControls.polygon.deactivate();
			measureControls.line.activate();
		}
	});
	var measureArea = new OpenLayers.Control.Button({
		title: "测面积",
		displayClass: "olControlCMJFeature",
		trigger:function(){
			measureControls.line.deactivate();
			measureControls.polygon.activate();
		}
	});
	var zoomInButton=new OpenLayers.Control.Button({
		title:"放大",
		displayClass: "olControlFDFeature",
		trigger:function(){
			ZoomIn();
		}
		
	});
	var clearButoon=new OpenLayers.Control.Button({
		title:"清除地图",
		displayClass: "olControlDeleteFeature",
		trigger:function(){
			clearMap();
		}
		
	});
	var zoomOutButton=new OpenLayers.Control.Button({
		title:"缩小",
		displayClass: "olControlSXFeature",
		trigger:function(){
			ZoomOut();
		}
		
	});
	var zoomBox=new OpenLayers.Control.ZoomBox({
		title:"拉框放大",
		displayClass: "olControlTKFDFeature"
	});
	panel.addControls([
	                   clearButoon,
	                   measureLength,
	                   measureArea,
	                   zoomBox,
	                   zoomInButton,
	                   zoomOutButton
	                   
	                   ]);
		
	map.addControl(panel);
	//
	var bound=new OpenLayers.Bounds(73.48748800017125, 3.820313000244937,135.08896900021935, 53.561513000424824);
	map.zoomToExtent(bound);
	SetCenter(117.10781180984,40.152701059375,8);
 }
var DeleteFeature = OpenLayers.Class(OpenLayers.Control, {
	    callback:null,//删除回调函数
		initialize: function(layer, options) {
		OpenLayers.Control.prototype.initialize.apply(this, [options]);
		this.layer = layer;
		this.handler = new OpenLayers.Handler.Feature(this, layer, {click: this.clickFeature});
		},
		clickFeature: function(feature) {     
		if(feature.fid == undefined) {
            	this.layer.destroyFeatures([feature]);
        	} else {      		   
            		feature.state = OpenLayers.State.DELETE;
            		//this.layer.removeFeatures([featrure]);
            		var gid=parseInt(feature.fid.split(".")[1]);
            		if(gid>17){
            		var data=feature.layer.protocol.format.write([feature]);
            		   OpenLayers.Request.POST({
            		            url:baseUrl+"/geoserver/wfs",
            		            callback: this.callback,
            		            //headers: options.headers,
            		            data: data
            		        });
            		   this.layer.destroyFeatures([feature]);
            		   
            		}
        }
		 this.deactivate();
    },
    setMap: function(map) {
        this.handler.setMap(map);
        OpenLayers.Control.prototype.setMap.apply(this, arguments);
    },
    CLASS_NAME: "OpenLayers.Control.DeleteFeature"
});

function DrawLSGJ(points)
{
	 var original = OpenLayers.Geometry.fromWKT("LINESTRING(" +points+")");
	 LSGJLayer.addFeatures([new OpenLayers.Feature.Vector(original)]);
}
//标点
function DrawLSGJPoint(points,times)
{
	 var pointArr = points.split(",");
	 var locate;
	 var lonLat;
	 var size = new OpenLayers.Size(11,11);
	 var timeArr = times.split(",");
	 var param ;
	 var timeParam;
	 var mapAlarm = {};
	 var timeMap ={};
	 var tF,tS;
	 if(pointArr.length>0){
		 var tt =timeArr[0].split(";");
		tF=tS = (tt[0].slice(0,-2));
	 }else{
		 tF=tS = null; 
	 }
	 for(var i=0;i<pointArr.length;i++){
		 param = timeArr[i].split(";");
		 //统计报警类型
		 if(!mapAlarm.hasOwnProperty(pointArr[i])){
			 if(param[1]!="null"){
				 var tempMap = {};
				 tempMap[param[1]] = 1;
				 mapAlarm[pointArr[i]] = tempMap;
			 }else{
				 mapAlarm[pointArr[i]] = {};
			 }
		 }else{
			 if(param[1]!="null"){
				 var tempMap = mapAlarm[pointArr[i]];
				 if(tempMap.hasOwnProperty(param[1])){
					 tempMap[param[1]] = tempMap[param[1]]+1;
				 }else{
					 tempMap[param[1]] = 1;
				 }
			 }
		 }
		 if(i==0){
		 	timeParam = param;
			 continue;
		 }
		 //统计时间段
		 if(pointArr[i]==pointArr[i-1]){
			 tS=(param[0].slice(0,-2));
		 }else{
			 var t_tempmap = timeMap[pointArr[i-1]];
			 if(t_tempmap==null){
				 t_tempmap={};
			 }
			 //只有一次定位情况
			 if(tF==tS){
				 for(var j=0;j<5;j++){
					 if(t_tempmap[j]!=null)
						 continue;
					 t_tempmap[j]=tF;
					 timeParam = param;
					 tF=tS=timeParam[0].slice(0,-2);
					 timeMap[pointArr[i-1]] =t_tempmap;
					 break;
				 }
			 }
			 //多次定位情况
			 else{
				 for(var j=0;j<7;j++){
					 if(t_tempmap[j]!=null)
						 continue;
					 t_tempmap[j]=tS+"至"+tF;
					 timeMap[pointArr[i-1]] =t_tempmap;
					 break;
				 }
				 tF=tS=(param[0].slice(0,-2));
				 timeParam = param;
			 }
		 }
	 }
	
	 if(pointArr.length>0){
		 var t_tempmap = timeMap[pointArr[pointArr.length-1]];
		 if(t_tempmap==null){
			 t_tempmap={};
		 }
		 param = timeArr[pointArr.length-1].split(";");
		 if(tF==tS){
			 for(var j=0;j<7;j++){
				 if(t_tempmap[j]!=null)
					 continue;
				 t_tempmap[j]=timeParam[0].slice(0,-2);
				 timeMap[pointArr[pointArr.length-1]] =t_tempmap;
				 break;
			 }
		 }
		 //多次定位情况
		 else{
			 for(var j=0;j<7;j++){
				 if(t_tempmap[j]!=null)
					 continue;
				 t_tempmap[j]=tS+"至"+tF;
				 timeMap[pointArr[pointArr.length-1]] =t_tempmap;
				 break;
			 }
			 tF=tS;
		 }
	 }
	 if($("input:checked[name='animate']").length>0){
		 animate(pointArr);
		 return;
	 }
	 var drawmap = {};
	 for(var i=0;i<pointArr.length;i++){
		 if(drawmap.hasOwnProperty(pointArr[i])){
			 continue;
		 }else{
			 drawmap[pointArr[i]]={};
		 }
		 
		 var alarmFlag = false;
		 locate = pointArr[i].split(" ");
		 lonLat = new OpenLayers.LonLat(locate[0],locate[1]);
		 
		 param = timeArr[i].split(";");
		 var popupContentHTML = "";
		 var map = timeMap[pointArr[i]];
		 for(var j=0;j<7;j++){
			if( map[j]!=null){
				popupContentHTML+=(map[j]+"<br/>");
			}else{
				break;
			}
		 }
		 var icon = new CMapIcon("", size,{x:-5.5,y:-5.5});
		 var tempMap = mapAlarm[pointArr[i]];
		 for(var j=0;j<7;j++){
			 if(tempMap.hasOwnProperty(j)){
				 if(!alarmFlag){
					 popupContentHTML +="报警情况：<br/>";
				 }
				 alarmFlag = true;
				 popupContentHTML+=("&nbsp;&nbsp;"+$.dictionary.formatter('BJLX')(j)+":"+tempMap[j]+"次；<br/>");
			 }
		 }
		 if(alarmFlag==true){
			 icon.imageDiv.innerHTML+="<div class='point_r'></div>";
		 }else{
			 icon.imageDiv.innerHTML+="<div class='point_g'></div>";
		 }
		 
		var feature= addMarker(lonLat.lon,lonLat.lat,icon,popupContentHTML,true,true);
			
	 }
}
var count =0;
var flag=true;
function animate(points){
	$("input").each(function(i){
		$(this).attr("disabled","disabled");
	 });
	$("#slider").css("display", "none");
	count=0;
	points = dealPoints(points);
	if(flag){
		var drawmap = {};
		 for(var i=points.length-1;i>-1;i--){
			 if(i==points.length-1){
				 flag=false;
				 setTimeout((function(obj,p,p2){
					return function(){
						 if(!flag){
							 clearTimeout(obj);
							 drawPointAnimate(obj,p,p2);
							 return;
						 }
				 	}})(drawmap,points[i],null),(points.length-i)*500);
			 }else{
				 distance = caculateDistance(points[i],points[i+1]);
				
				 setTimeout((function(obj,p,p2){
						return function(){
							if(!flag){
								 clearTimeout(obj);
								 drawPointAnimate(obj,p,p2);
								 return;
							 }
					 	}})(drawmap,points[i],points[i+1]),(points.length-i)*500);
			 }
		 }
		 setTimeout((function(obj){
				return function(){
					showDialog();
					$("input").each(function(i){
						$(this).removeAttr("disabled");
					 });
					$("#slider").css("display", "");
			 	}})(showDialog),points.length*500);
	}
	
}
function dealPoints(points){
	var p = new Array();
	var distance = 0;
	for(var i=0;i<points.length;i++){
		if(i==0){
			p.push(points[0]);
		}else{
			distance = caculateDistance(points[i],p[p.length-1]);
			if(distance==0)
				continue;
			else if(distance>0.001){
					 compensatePoints(p,points[i],p[p.length-1],distance);
			}else{
				p.push(points[i]);
			}
		}
	}
	if(points.length>1){
		p.push(points[points.length-1]);
	}
	return p;
}
function  compensatePoints(p,p1,p2,distance){
	var c = Math.floor(distance  / 0.001);
	var p1s = p1.split(" ");
	var p2s = p2.split(" ");
	var _x = p1s[0]-p2s[0];
	var _y = p1s[1]-p2s[1];
	var _nx = 0;
	var _ny = 0;
	for (var i = 1 ;i<c;i++){
		_nx = parseFloat(p2s[0])+i/c*_x;
		_ny = parseFloat(p2s[1])+i/c*_y;
		var newP = _nx+" " + _ny;
		p.push(newP);
	}
	p.push(p1);
	return p;
}
function caculateDistance(p1,p2){
	 var p1s = p1.split(" ");
	 var p2s = p2.split(" ");
	 var c = Math.sqrt((p1s[0]-p2s[0])*(p1s[0]-p2s[0]) + (p1s[1]-p2s[1])*(p1s[1]-p2s[1]));
	 return c;
}
function showDialog(){
	if(!flag){
		 clearTimeout(obj);
	 }
	flag=true;
	$.dialog({
		title : '播放完毕',
		icon : 'success.png',
		fixed : true,
		lock : true,
		zIndex:99999,
		width : 250,
		content : '播放完毕！',
		resize : false,
		okVal : '关闭',
		ok : function(){
			$("input:checked[name='animate']").attr("checked",false);
			drawGJ();
		},
		cancel : false
	});
}
function drawPoint(drawmap,point1,point2){
	var size = new OpenLayers.Size(11,11);
		 if(drawmap.hasOwnProperty(point1)){
			 return ;
		 }else{
			 drawmap[point1]={};
		 }
		 
		 var alarmFlag = false;
		 locate = point1.split(" ");
		 lonLat = new OpenLayers.LonLat(locate[0],locate[1]);
		 
		 var popupContentHTML = "";

		 var icon = new CMapIcon("", size,{x:-5.5,y:-5.5});
		
		 if(alarmFlag==true){
			 icon.imageDiv.innerHTML+="<div class='point_r'></div>";
		 }else{
			 icon.imageDiv.innerHTML+="<div class='point_g'></div>";
		 }
		 
		addMarker(lonLat.lon,lonLat.lat,icon,popupContentHTML,true,true);
		
		if(point2!=null)
			DrawLSGJ(point1+","+point2);
		
}
function drawPointAnimate(drawmap,point1,point2){
	var size = new OpenLayers.Size(20,20);
		 if(drawmap.hasOwnProperty(point1)){
			 return ;
		 }else{
			 drawmap[point1]={};
		 }
		 
		 var alarmFlag = false;
		 locate = point1.split(" ");
		 lonLat = new OpenLayers.LonLat(locate[0],locate[1]);
		 
		 var popupContentHTML = "";

		 var icon = new CMapIcon("", size,{x:-5.5,y:-5.5});
		
		/* if(alarmFlag==true){
			 icon.imageDiv.innerHTML+="<div class='point_r'></div>";
		 }else{
			 icon.imageDiv.innerHTML+="<div class='point_g'></div>";
		 }*/
		 var footHtml = getFootPic(point1,point2);
		 icon.imageDiv.innerHTML+=footHtml;
		 //icon.imageDiv.innerHTML+="<div class='point_start'></div>";
		addMarker(lonLat.lon,lonLat.lat,icon,popupContentHTML,true,true);
		SetCenter(lonLat.lon,lonLat.lat,GetCurrentZoom());
		if(point2!=null)
			DrawLSGJ(point1+","+point2);
		
}
function getFootPic(pointE,pointS){
	if(pointS == null){
		return "<div class='point_foot'></div>";
	}
	var xyS = pointS.split(" ");
	var xyE = pointE.split(" ");
	var x = parseFloat(xyS[0]);
	var y = parseFloat(xyS[1]);
	var x1 = parseFloat(xyE[0]);
	var y1 = parseFloat(xyE[1]);
	if(Math.abs((y-y1)/(x-x1))<1){
		if(x-x1<0){
			return "<div class='point_right'></div>";
		}else{
			return "<div class='point_left'></div>";
			
		}
	}else{
		if(y-y1<0){
			return "<div class='point_top'></div>";
		}else{
			
			return "<div class='point_down'></div>";
		}
	}
}
function ClearLSGJ()
{
	markers.clearMarkers();
   LSGJLayer.removeAllFeatures();	
}
function GetDzwl(fxryid){
	var result;
	 OpenLayers.Request.GET(
			{
			
	        url:vPath+"/data/xtsz/getdzwl.action",
	        async: false,
	        user: undefined,
	        password: undefined,
	        params: {"fxryid":fxryid},
	        proxy: null,
	        headers: {},
	        data: null,
	        callback: function() {},
	        success: function(resp){result=resp.responseText;},
	        failure: null,
	        scope: null	
			}
	);
	return result;
}
//获取服刑人员电子围栏
function GetFxryDZWL(name,geotype,color,level)
{
     setTimeout(function(){},50);
	 var protocol=new OpenLayers.Protocol.WFS({
 		 version: "1.1.0",
         srsName: "EPSG:4326",
         url: baseUrl+"/geoserver/wfs",
         featureType: "fxrywlsz",
 	     geometryName: "geom",
 		 filter: new OpenLayers.Filter.Logical({
         type: OpenLayers.Filter.Logical.OR,
          filters: [
                    new OpenLayers.Filter.FeatureId({
                    		fids:['fxrywlsz.17']
                    }), 
                    new OpenLayers.Filter.Comparison({
					    type: OpenLayers.Filter.Comparison.EQUAL_TO,
					    property: "fxryid",
					    value: name
					})
          ]
       })
      });
protocol.read({
callback: function(resp) {
	var features=resp.features;
	wfs.removeAllFeatures();
	var addfeatures=[];
	if(features&&features[0]){
	//markers.clearMarkers();
	for(var i=0;i<features.length;i++){		
		var id=parseInt(features[i].fid.split(".")[1]);
		if(id&&id<=17){
			features[i].style=GetStyle("blue",2,0);
		    wfs.addFeatures([features[i]]);
		}
		else{
			if(features[i].attributes.geotype=="01")
			    features[i].style=GetStyle("blue",2,0.5);
			else
				features[i].style=GetStyle("red",2,0.5);
	         addfeatures.push(features[i]);
			}
	}
	wfs.addFeatures(addfeatures);
  //var centerpoint=features[0].geometry.getCentroid();
  //if(level){  
  //	SetCenter(centerpoint.x,centerpoint.y,level);
  //}
  //else{
  //	SetCenter(centerpoint.x,centerpoint.y,map.getZoom());
  // }
  	
	}
}

});
}
//初始化在 地图上量距离、测面积 在地图上的渲染方式
function InitMeatrue()
{    
	 //素描符号
	 var sketchSymbolizers = {
             "Point": {
                 pointRadius: 4,  //点半径
                 graphicName: "square", //绘画的样式
                 fillColor: "white",  
                 fillOpacity: 1,
                 strokeWidth: 1,
                 strokeOpacity: 1,
                 strokeColor: "#333333"
             },
             "Line": {
                 strokeWidth: 3,
                 strokeOpacity: 1,
                 strokeColor: "#666666",
                 strokeDashstyle: "dash"
             },
             "Polygon": {
                 strokeWidth: 2,
                 strokeOpacity: 1,
                 strokeColor: "#666666",
                 fillColor: "white",
                 fillOpacity: 0.3
             }
         };
         var style = new OpenLayers.Style();
         //样式规则
         style.addRules([
             new OpenLayers.Rule({symbolizer: sketchSymbolizers})
         ]);
         //地图的样式
         var styleMap = new OpenLayers.StyleMap({"default": style});
        //渲染
         var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
         renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
         measureControls = {
             line: new OpenLayers.Control.Measure(
                 OpenLayers.Handler.Path, {
                     persist: true,
                     immediate:false,
                     handlerOptions: {
                         layerOptions: {
                             renderers: renderer,
                             styleMap: styleMap
                         }
                     }
                 }
             ),
             polygon: new OpenLayers.Control.Measure(
                 OpenLayers.Handler.Polygon, {
                     persist: true,
                     immediate:false,
                     handlerOptions: {
                         layerOptions: {
                             renderers: renderer,
                             styleMap: styleMap
                         }
                     }
                 }
             )
         };
         
         var control;
         for(var key in measureControls) {
             control = measureControls[key];
             control.events.on({
                 "measure": handleMeasurements
                 //"measurepartial": handleMeasurements
             });
             map.addControl(control);
         }
}
function DeleteDZWL()
{
	deleteDZWL.activate();
	deleteDZWL.callback=HandlerDeleteDZWL;
}
function HandlerDeleteDZWL(response)
{ 
	//deleteDZWL.deactivate();
	var data = response.responseText;
    if(data.indexOf("SUCCESS")!=-1)
        {
    	 //alert("删除成功！");
        }	
}
//测量回调函数
function handleMeasurements(event) {
    var geometry = event.geometry;
    var units = event.units;
    var order = event.order;
    var measure = event.measure;
    var out = "";
    if(order == 1) {
        out += "测量结果: " + measure.toFixed(3) + " " + units;
    } else {
        out += "测量结果: " + measure.toFixed(3) + " 平方公里";
    }
    alert(out);
}
function drawm()
{
	 var polygon="POLYGON((115.398632563727 39.9613113122079,115.39811695938 39.9640017471456,115.398204320169 39.9681965212282,115.399738319863 39.9803155216198,115.400565866229 39.984005832661,115.402073321337 39.9874743781291,115.404206781502 39.9905971295272,115.412551781424 40.0006251297193,115.41496907582 40.0030682740039,115.41776896727 40.005061480985,115.418327913677 40.0053909556741,115.418341169762 40.0053989092353,115.418460165904 40.005469746988,115.422327865953 40.0077540691581,115.422470323072 40.0112778802322,115.423500247006 40.0168539847887,115.427333247499 40.028233984376,115.428738736372 40.0314447085608,115.430683138952 40.0343607756675,115.434117139031 40.0386437762275,115.436533960648 40.0411696882784,115.439353687812 40.0432361822034,115.442490168986 40.044780120112,115.455681158441 40.0498952166669,115.462909161157 40.0567484182546,115.463018214974 40.0568510446327,115.470703215036 40.0640290446974,115.475060325363 40.067122009581,115.483026324894 40.0713030099338,115.483640173449 40.0716118597074,115.484939200349 40.0722377180079,115.48506308612 40.0725990578093,115.487108686776 40.0760727978292,115.489809351525 40.0790657498302,115.493055356736 40.0814563150229,115.51166735618 40.092457315119,115.515111715835 40.0940728534103,115.518799676066 40.0950069448094,115.522597791927 40.095225790206,115.531648820037 40.0948844178823,115.534456947501 40.0964179942579,115.534496085829 40.0964393598328,115.534540063302 40.0964827999407,115.534557422225 40.0964999258586,115.535646671822 40.0975732450935,115.546911924359 40.1086885601945,115.548627171187 40.1101976653691,115.55762117136 40.1172416655495,115.561030334555 40.1193953302774,115.564802218961 40.1208213574413,115.568783491204 40.1214617766645,115.572812305942 40.1212905538401,115.576724885154 40.1203146494609,115.580362175987 40.1185737356634,115.581114899371 40.1180034444862,115.581556470593 40.1200674300357,115.583313826496 40.1239554687018,115.585855923036 40.1273822586897,115.589067062126 40.1301918369827,115.592801095638 40.1322563316342,115.598088095733 40.1344803316718,115.602241238162 40.1357180095293,115.606563487251 40.1360320180117,115.619629487247 40.1355610178752,115.625729765404 40.1353376452715,115.630210763555 40.1351740528322,115.631056486806 40.1352437612571,115.632373073979 40.1376992975586,115.635151520864 40.1409544207343,115.638558901397 40.1435438901546,115.642439195084 40.1453491366431,115.670697283909 40.1549731238899,115.674890373106 40.1564041064437,115.678230708461 40.1572312527444,115.681663390521 40.1574735442219,115.685086794637 40.1571238078301,115.706633794531 40.1530258077075,115.709649987926 40.1522034384648,115.721216711135 40.1480542684308,115.724508191921 40.1484079899918,115.724565212932 40.1484295001149,115.724370609134 40.1491731327257,115.724104519335 40.152821162343,115.724507258029 40.1564566437986,115.72556535469 40.1599579800186,115.727243418832 40.1632080607261,115.733130411336 40.1724293856415,115.733731433858 40.1733746925361,115.736081582999 40.1763900597361,115.738962550897 40.1789031582814,115.742269069578 40.1808221618109,115.745880321822 40.1820769516611,115.748079320285 40.1823935065546,115.748643939779 40.1835735041697,115.750898256465 40.1872201983164,115.75387798674 40.1903026534103,115.757446209601 40.192679228159,115.761438962335 40.1942407170401,115.766445962108 40.1956017168504,115.770092141599 40.1962379082577,115.781930141623 40.1971879088997,115.786125588761 40.1970828580543,115.790206746863 40.1961046142662,115.793993914251 40.1942962515962,115.797320334252 40.1917373959254,115.800039537838 40.1885407188656,115.802031792952 40.1848469766,115.805369932449 40.176715079604,115.809680641174 40.1706328932517,115.818524030035 40.1692922634058,115.818578171693 40.1702313156925,115.819660212795 40.174222671291,115.821539997853 40.1779061669946,115.824137157913 40.1811243174082,115.840629158518 40.1977093178056,115.843470840067 40.2000812456775,115.846700956008 40.2018888813884,115.850208865213 40.2030703080111,115.852418283744 40.2035927291064,115.853176030292 40.2051143624722,115.85507459559 40.2081931256293,115.858304264344 40.2125026455272,115.858314302238 40.2150198989999,115.859018173215 40.218673734222,115.860386712835 40.2221339431019,115.863881713029 40.2290179430985,115.865822034617 40.23210540287,115.876913034367 40.2466234023761,115.879669445456 40.2495628136712,115.882959133022 40.2518900215631,115.886648552684 40.2535105533915,115.890587932998 40.2543586239063,115.894301659463 40.2543965793034,115.896079722906 40.2573820122549,115.898074263976 40.2601596223954,115.900512821615 40.2625568601407,115.903324108786 40.2645036463676,115.90645457767 40.2662963418819,115.908303045936 40.2673550368051,115.908940242815 40.2677194452005,115.910120909815 40.2683432972471,115.918467909829 40.272403297416,115.922678340642 40.2738964407462,115.925951482464 40.2742810186386,115.924174271343 40.2762902792787,115.922056996077 40.2801212151461,115.915800996262 40.2951162150655,115.914650401761 40.2988796322041,115.914317062152 40.3022147013696,115.91222633259 40.3040489704637,115.909646736045 40.307573389116,115.901185274673 40.3222689807889,115.900571625457 40.3233334940281,115.899310447011 40.325515422733,115.89768756152 40.3290947276597,115.896795945062 40.3329222857803,115.89667002558 40.3368503037741,115.896772013888 40.3374636524145,115.896037572544 40.3390589249666,115.8953535477 40.3390998798193,115.88346680197 40.3400889664657,115.881291609582 40.3391165276511,115.877906330665 40.3379539515673,115.874368037461 40.3374134169179,115.871452037888 40.3372324175643,115.867147683908 40.3374303003342,115.862985611338 40.3385455459101,115.861733954158 40.3390306377509,115.859882297358 40.3397477301102,115.851408963116 40.343035123832,115.847053907017 40.3453809106014,115.839223907462 40.3509479101188,115.838269023662 40.3516708162731,115.836261023693 40.3532878161977,115.833595561731 40.3558775837233,115.832803578767 40.356805067388,115.82429082331 40.357767645848,115.820677567648 40.3585188833966,115.817263859318 40.3599212277829,115.802805859881 40.3674882277999,115.79925759903 40.3698591878942,115.796293129501 40.3729289643101,115.794047420677 40.376557793145,115.792622717474 40.3805804573886,115.790084654652 40.3912521705694,115.78466007506 40.4015043387566,115.783995194597 40.4028867077943,115.782015783481 40.407441546701,115.781488942889 40.4086484020326,115.780084951285 40.4105141155053,115.777567070221 40.4138582066487,115.773519891762 40.4154660439671,115.77059891092 40.4169122552202,115.769269911686 40.4177112551941,115.765152791082 40.4201891394053,115.76211084697 40.4224374250916,115.759546623769 40.4252182472505,115.754863815763 40.4313757513579,115.752452565202 40.4335680885514,115.74964309924 40.4367261745842,115.747560085043 40.4404041710821,115.746296563599 40.4444377947559,115.745012563742 40.4508477944776,115.74498205103 40.4510033176064,115.744784606249 40.4520312341642,115.743765607435 40.4552549838072,115.743690404348 40.4556102342471,115.7436178157 40.4559107605451,115.743495126192 40.4564023911078,115.742937049881 40.4600281970081,115.743050622925 40.4636949419896,115.743832024183 40.4672792591727,115.744413575707 40.4691169346844,115.738779420627 40.4705401688028,115.73446514188 40.4716282302919,115.730340057967 40.4731684708278,115.726651991604 40.4755740528152,115.723579709437 40.4787283737369,115.717605709137 40.4863953738688,115.7165820217 40.4877095407573,115.71390897702 40.4911480179139,115.711836750165 40.4944263317279,115.710436209349 40.4980429532203,115.709760020036 40.5018618841718,115.709833609422 40.5057395187861,115.710654210278 40.5095300437939,115.712190965004 40.5130909215665,115.716760965309 40.5213669219036,115.720377559413 40.5279138088026,115.720495535926 40.5281243623456,115.726280403855 40.5383041289285,115.727114837528 40.5397725931102,115.727177216791 40.5399453953281,115.727820263403 40.5417299739208,115.72833548678 40.5430179121788,115.729960486174 40.5467039121876,115.732053940301 40.5503548408159,115.734865036135 40.5534868641634,115.738269299544 40.5559612975674,115.742115991193 40.557668574168,115.74623478121 40.5585330964829,115.749315183124 40.5585210101144,115.75633302868 40.5635738165019,115.77333986076 40.5761168392061,115.776473353387 40.5780113729864,115.779899732689 40.5793028679178,115.78350414614 40.5799480329797,115.787165773492 40.5799252421838,115.790761876682 40.5792352594796,115.794171914022 40.5779012131465,115.797736409227 40.5761148175825,115.799043344697 40.5828021288797,115.799951161384 40.5860037693551,115.801108488245 40.5890823201593,115.801585673697 40.5903534928436,115.802695536562 40.593318112388,115.804546799308 40.5970341202557,115.807125382114 40.6002878564073,115.810320174566 40.6029391182613,115.813993513951 40.6048736636851,115.8179871171 40.606008133657,115.840669116275 40.6099671336648,115.841043880508 40.6100288853371,115.865863881151 40.6138768856221,115.868050805525 40.6142154939504,115.869537889548 40.6143890374026,115.870770482581 40.6144862889619,115.871103191705 40.6149251982299,115.880619191669 40.6243591980092,115.88231978381 40.6258636491713,115.891326783684 40.6329626491902,115.894974901679 40.6352480668424,115.899027575564 40.6366998702493,115.903297045983 40.6372507977264,115.907585509454 40.6368753249433,115.911694282549 40.6355908474589,115.914032009304 40.6345725097666,115.925504074484 40.6313183803885,115.939587144476 40.6300698659429,115.943028523139 40.6294581452872,115.94631084726 40.6282566305616,115.949333864078 40.6265020198986,115.952005240919 40.6242479047633,115.955655309029 40.6205760043228,115.962191868507 40.6208495147531,115.966708429384 40.6205254448792,115.970041429302 40.6199014448815,115.973538300419 40.6189107897888,115.976798150081 40.6173036540444,115.979713325787 40.615133111318,115.982187557464 40.6124708410977,115.984139136674 40.6094047615639,115.985503614927 40.6060361261997,115.986734334528 40.6019693768468,115.989755336196 40.6041819674951,115.989960336657 40.6043009675869,115.990314883963 40.604501976015,115.997000166598 40.6082026570086,115.997269088184 40.608586422852,116.000103344038 40.6118243756755,116.003564627558 40.6143811042737,116.007492704294 40.6161382490844,116.011705730369 40.6170144659671,116.016008670642 40.6169691918967,116.02170653326 40.6162919186874,116.031930491475 40.6205656906314,116.033045273222 40.6209930638444,116.048584273047 40.6264240633854,116.052831971896 40.627405335735,116.069220972248 40.6293453361657,116.069538043605 40.6293803068783,116.084984015693 40.6309593154632,116.088270336706 40.6316426800578,116.087460712822 40.6331857603129,116.086064231432 40.6365677220912,116.085307245271 40.6401474982983,116.085215090335 40.643805275384,116.085790851009 40.6474186291429,116.087015256836 40.6508666221988,116.08929725685 40.6558056224334,116.091262810725 40.6591591365122,116.093839927101 40.6620691072851,116.096931258893 40.6644256145439,116.100420035227 40.6661396444159,116.111561034585 40.670324644054,116.112433991799 40.6706520046898,116.113298417143 40.6720828626528,116.115853860806 40.6754492083863,116.119059642411 40.6782033943307,116.123909642139 40.6815493948482,116.124789203726 40.6821556083959,116.128232770518 40.6840589428004,116.131982324887 40.6852512834179,116.135892751258 40.68568648418,116.139812707978 40.6853477019098,116.143590484546 40.6842480481876,116.144398532331 40.6839236333256,116.144910621904 40.6852856790916,116.145841418148 40.6885661095404,116.145700597442 40.6889183802231,116.144982995828 40.6927078266688,116.145006087848 40.6965645510821,116.145769014777 40.700345132908,116.147243405543 40.7039089831167,116.149374431769 40.7071235723077,116.153623920476 40.7123573482138,116.155985072354 40.7183673070965,116.157939938513 40.7221193670546,116.160640319769 40.7253762669159,116.163149868102 40.7273506605389,116.163485572914 40.728005360942,116.165927015097 40.73108345565,116.168923085855 40.7336248857548,116.172358172008 40.735531581872,116.176099719534 40.7367299679018,116.180003348595 40.7371738002036,116.183918424911 40.736845952058,116.187330717512 40.7362182369718,116.187265416212 40.7365226286677,116.187172528749 40.7402434424439,116.18777068404 40.7439170365267,116.188939683989 40.7484090359329,116.190328069269 40.7521577491116,116.19243425429 40.7555554614066,116.195174094516 40.7584664302689,116.196153094696 40.7593174308421,116.196180059839 40.7593408282015,116.201110059659 40.763610828162,116.201757800193 40.7641717808928,116.203689877401 40.7664067322426,116.20508961389 40.7677296234011,116.207145691253 40.7755353551036,116.208826869714 40.7798300953697,116.209043648287 40.7801441000016,116.209038693994 40.7837536435796,116.20979878403 40.7876012119809,116.211295118804 40.7912264708281,116.21347015856 40.7944900151474,116.216240264758 40.7972663492625,116.219498916291 40.7994487125805,116.226352786901 40.8031212573964,116.226731118751 40.8035520745524,116.229704696473 40.8063141277479,116.23317464337 40.8084190198732,116.236998074815 40.8097800761944,116.241017550482 40.8103412514667,116.245067557383 40.8100794377518,116.248981325324 40.8090054159508,116.252597694124 40.8071634118712,116.254689694369 40.805814411715,116.260833782168 40.801851580293,116.265505140853 40.7988438559287,116.268808543549 40.7961817177873,116.277359543978 40.7876447180046,116.279843429021 40.7846247664193,116.281110403798 40.7822635656968,116.285154794903 40.7819560921853,116.289092911771 40.7808185739721,116.290588093465 40.7800277507906,116.292418915783 40.7822312646158,116.294889491575 40.7847230851431,116.297757543883 40.7867447651974,116.300934787501 40.7882340728456,116.30432341961 40.7891451637942,116.312142419557 40.7905261636275,116.314830578567 40.7908153745078,116.330632578829 40.7914403745515,116.335441015315 40.7910482317427,116.34694310806 40.7886893588358,116.358867447147 40.7891724167603,116.365389364742 40.7902539323244,116.370572526498 40.7911154744599,116.381633426406 40.7954666776872,116.384638236131 40.7963835825327,116.387749557646 40.7968186393207,116.402886557818 40.7977326401012,116.406654128103 40.7976042090552,116.410330671876 40.7967710786489,116.413785569989 40.7952628480902,116.416896077854 40.7931331013433,116.419551686279 40.7904575034139,116.421658047602 40.7873311121451,116.423140327647 40.783865001031,116.423161030828 40.7837703518992,116.428022423034 40.7848957296892,116.428832777822 40.7849940607827,116.422694682998 40.7906397120763,116.419713922488 40.79408671211,116.417590832485 40.7981189858229,116.416498589418 40.8009311430243,116.411126341271 40.8028891547117,116.408235665592 40.8042115897164,116.395760921225 40.8111667565561,116.390459187165 40.8141129400866,116.390398171674 40.8141469861383,116.390257171467 40.8142259861842,116.387222672928 40.816315109163,116.384622848525 40.8189253829568,116.382545914305 40.8219682369546,116.381062343846 40.8253404224327,116.38022247698 40.8289275159429,116.379992477165 40.8305865160057,116.379985116004 40.8306401458969,116.379877490296 40.8314322103648,116.377398379931 40.8348775891736,116.375279738026 40.8360867021789,116.372072546897 40.8383621758181,116.369372587742 40.8412212098828,116.368071632087 40.8432969509457,116.367998994573 40.8433184102181,116.364316969949 40.8453160374458,116.361131813183 40.8480368087037,116.360487813162 40.8487158085397,116.360378913151 40.8488315444454,116.356707187344 40.8527649480493,116.355705004021 40.8536161394808,116.346892108127 40.8611010509626,116.34667640543 40.8612869092954,116.336714093979 40.8699951756985,116.325482658373 40.8787565039928,116.323689530897 40.8803363576841,116.313998530958 40.8899623582237,116.311409812696 40.893121530725,116.309516859808 40.8967407243507,116.308398617107 40.9006690021404,116.308101720436 40.9047425369077,116.308407651962 40.9150991528047,116.308262466306 40.9157440226464,116.308235651821 40.9200397406314,116.309126797129 40.9242420937892,116.310894789437 40.9281572074813,116.317500789204 40.9393862072997,116.320116507732 40.9428898787397,116.3234310972 40.9457414035303,116.327286139316 40.9478044952045,116.341463139285 40.9534974951365,116.344635654816 40.954460902233,116.345047945423 40.9548973479783,116.345356890155 40.9552283604188,116.348603911411 40.9580328394322,116.352374375328 40.9600802405697,116.356494762805 40.9612763411536,116.36077545104 40.9615660958886,116.365787451044 40.9613660956588,116.369409065822 40.9608876880403,116.372883249882 40.9597585234977,116.376094096042 40.9580162736912,116.378934482708 40.9557190642883,116.382584907216 40.9521685967481,116.386035331165 40.9488141288993,116.389556860251 40.9442232833927,116.392458726533 40.939025477369,116.395243059566 40.9340505112482,116.399434440996 40.9277788277078,116.400637915012 40.9259751303448,116.400751621426 40.9258050994208,116.406023560765 40.9212341660078,116.410911802307 40.9194390821198,116.414546329187 40.9200758971789,116.420733452013 40.9211599193426,116.424368014213 40.9214591627032,116.427996491367 40.9210934544568,116.431498240337 40.9200749540269,116.434756831569 40.9184375255062,116.43607550824 40.9176163912252,116.438033997756 40.9177108022324,116.438585621508 40.9177297687678,116.441336692787 40.9177863673349,116.439631842779 40.9196585899436,116.43758447556 40.9230834002316,116.433550517293 40.9316769216016,116.426687712221 40.9393165009333,116.424388381695 40.9424384927653,116.422734661378 40.9459454805012,116.421788705331 40.9497056561651,116.42158606673 40.9535776958684,116.422134361621 40.9574160713813,116.423412982679 40.9610765197222,116.428000167689 40.9709962349505,116.43006734932 40.9808230975787,116.431103283799 40.98421780568,116.432722963738 40.9873759444092,116.434875380544 40.9901980544769,116.437492748135 40.9925952591473,116.445426747989 40.998660259147,116.448761298206 41.0007252168271,116.452434730498 41.0020996878374,116.456305771316 41.0027308125131,116.460225547441 41.0025943189422,116.464043311385 41.0016954564204,116.467544311856 41.0004984569784,116.470339582985 40.9992982483616,116.471033770935 40.9988651362645,116.471183027794 40.9989321118523,116.474595828125 41.0001065785916,116.478164138842 41.0006485535365,116.48177175275 41.0005403864897,116.485301182687 40.9997856000674,116.488637487663 40.9984087749805,116.49317689665 40.996035444535,116.496463939446 40.9955972688269,116.500592878969 40.9950488836155,116.50307246546 40.9969094716511,116.506640313949 40.9986140681046,116.519882495433 41.003398192833,116.526001369807 41.0066185213186,116.530039431092 41.0082113922173,116.534326060683 41.0088954853314,116.538659324284 41.0086385744423,116.542390677821 41.0075789641595,116.542902813343 41.0079996686282,116.546157279915 41.009785298109,116.549685683741 41.0109387242012,116.553366471086 41.011420211303,116.557072838594 41.0112131721531,116.560677101668 41.0103247392634,116.574632102191 41.0054727394435,116.577341575082 41.0042999740863,116.579848875194 41.0027411065246,116.588814747619 40.9962022744468,116.590239667583 40.9957927245339,116.590160507737 40.9966562548876,116.590158062921 40.9966831239831,116.590021062898 40.9982001237928,116.58976078273 41.0010872353565,116.589793895656 41.0050143939518,116.590593800427 41.008859367743,116.592129653372 41.0124738977024,116.595688128704 41.0189855379764,116.592771533265 41.0224166550297,116.59039920838 41.0258905468458,116.588805978289 41.0297838060957,116.588062326893 41.0339241964529,116.588201153049 41.0381285486037,116.589216315141 41.0422108635886,116.590818866962 41.046613502511,116.590245685627 41.0499135691838,116.590345768221 41.0538532970736,116.591216453803 41.0576969124275,116.592823934801 41.0612951727312,116.595105794848 41.0645083622834,116.597973432328 41.0672117171724,116.60131550066 41.0693002696936,116.60500223174 41.0706929241076,116.609880031052 41.0720035503223,116.610922328616 41.0730275997513,116.611019904347 41.0731251749344,116.613881761975 41.0754983325932,116.617133411235 41.077300798528,116.620662490522 41.0784702880491,116.624347051564 41.0789663891352,116.630747051724 41.0792273894801,116.633943148827 41.0791017473185,116.637078343161 41.0784682024272,116.643060539344 41.0767516243449,116.648394688365 41.075244881131,116.651538611413 41.0740637997133,116.654440578258 41.0723732986407,116.662354953917 41.0668236324262,116.672315648339 41.0629441117049,116.676259319196 41.0615181678513,116.677391703551 41.0619988350306,116.681294361238 41.0627977249219,116.685277936308 41.0628073035491,116.689184390755 41.0620271909051,116.692858746137 41.0604883359347,116.696155231936 41.0582517887161,116.698943068633 41.0554062784566,116.701111656041 41.0520646933924,116.702574961081 41.0483596022402,116.703993212425 41.0433595093207,116.709267044278 41.0335883926412,116.710907949381 41.0295466447112,116.71163354831 41.025245272595,116.711409323787 41.0208888957459,116.710706323743 41.0165518953937,116.709865522948 41.0113729591015,116.708746844933 41.0072848755116,116.706791821116 41.0035243237155,116.704492584161 41.0007483574582,116.704080133241 40.9993712120751,116.70248565191 40.9962164043611,116.700360667257 40.9933915817742,116.697448643311 40.9901599914534,116.698344192798 40.9835635803063,116.698489980854 40.979673227089,116.697879328557 40.9758283335814,116.696681901667 40.9725729428447,116.698192492424 40.9706030828653,116.700017037015 40.9667202379218,116.702165657586 40.9604843416164,116.709044435389 40.9546965101827,116.709544230686 40.9541956338532,116.712365485399 40.9543621115896,116.716066418239 40.9538861558564,116.719614526807 40.9527310093999,116.722886306431 40.9509368812446,116.725767871062 40.9485662224696,116.732414870921 40.9419662229793,116.73506689116 40.9387121043684,116.736981238388 40.9349760939328,116.738073573648 40.9309227862756,116.738295772746 40.926730754925,116.737638046425 40.9225846850546,116.736129371637 40.9186672369479,116.734185723304 40.9156869213596,116.734215981755 40.9156539107554,116.734413006582 40.9156415871978,116.738770654987 40.9143394957321,116.741499883319 40.9127831606433,116.750591092016 40.9091676279699,116.752339144364 40.908961857006,116.755510885546 40.9085861160376,116.759369676094 40.9077359199645,116.762986007861 40.9061436755095,116.766218725493 40.9038715323438,116.768941647148 40.9010081784513,116.770665936646 40.8982723522861,116.772333127846 40.8977472664784,116.77534448373 40.8962001442315,116.787040483501 40.8888781438215,116.789746762989 40.8868461387101,116.80006076288 40.8776391386072,116.802715503262 40.8747542472052,116.804756451992 40.8714068940447,116.80705879864 40.8666329509527,116.807117263987 40.8666339177858,116.814284221612 40.8667675253848,116.818131106575 40.8664669532838,116.821848677761 40.8654332550775,116.825298559108 40.8637049072552,116.828352338551 40.861346242671,116.82931262086 40.8602511372577,116.833165474883 40.8598989741069,116.83885570681 40.8585181611193,116.849068460396 40.8543800336312,116.854215189455 40.8532532890615,116.858503458151 40.8517889892757,116.862352031436 40.8493969387037,116.86556334836 40.8461999298066,116.865869348082 40.8458169292315,116.865905114271 40.8457720309734,116.867037996469 40.8443456982282,116.870440877191 40.8400643635391,116.870492587793 40.8399833974215,116.874407773478 40.8393524258292,116.878161980023 40.837936124708,116.881557922668 40.8357989421697,116.884458914928 40.8330268996814,116.890198915229 40.8263168996149,116.8902835673 40.8262172850078,116.891074566905 40.8252802849652,116.893123496258 40.822359943908,116.894621002247 40.8191220465658,116.895519439834 40.8156696107172,116.895693586856 40.813381945532,116.8995756385 40.8106562391307,116.901242736052 40.8093485889924,116.903302735722 40.8075485896219,116.905974882706 40.8047089448166,116.90804526172 40.8014047833698,116.909435177905 40.7977616960627,116.910054669222 40.7941355052863,116.92130920131 40.7919858718025,116.925261810812 40.7907973238517,116.928886276873 40.7888227317738,116.932028235586 40.7861461923006,116.93455387275 40.7828816976966,116.936355622942 40.7791682808944,116.937356750668 40.7751640941628,116.9384683781 40.7673677026233,116.940139065327 40.7639430945767,116.941446129204 40.7604843189202,116.942056676379 40.7570545614946,116.942466166333 40.7568871846352,116.945897820098 40.7550780703433,116.948915666843 40.752640501245,116.951406166557 40.7496661856495,116.953275619529 40.7462670258018,116.954453691601 40.7425709078007,116.95467040245 40.7415611848313,116.954921183164 40.7414489792446,116.956348267657 40.7407409580666,116.959585267645 40.738970958281,116.963356624382 40.7363002763221,116.973480624504 40.727204275942,116.976094254326 40.7243532821443,116.976768752949 40.7232489396024,116.987358067889 40.7207675040234,116.99020370041 40.7198721676795,117.001098700114 40.7155271678577,117.002854682652 40.7148254141581,117.015120445537 40.7136402943346,117.018583083792 40.7129941035609,117.02190884531 40.712064103318,117.0226626295 40.7124374338241,117.036150629171 40.7174584333436,117.039519882366 40.7183868448969,117.042999307756 40.7187145857531,117.058605002994 40.7188150041622,117.066247533988 40.7197685862803,117.073000327836 40.7206140600558,117.075135200568 40.7205805128527,117.084200419275 40.723141629157,117.087799699682 40.7238103374949,117.103616699736 40.725270337374,117.10788408924 40.7252069402834,117.11204086631 40.724239553718,117.115897745529 40.7224122291546,117.119279097806 40.7198081767059,117.12011378098 40.7188186954788,117.122141605079 40.7184262735576,117.128775283312 40.7171518333303,117.132817322193 40.7163753101171,117.144445973324 40.7156535979431,117.15195925675 40.7173357962404,117.157611972148 40.7177778070057,117.159120971589 40.7176808070292,117.161577033233 40.7175226742966,117.16521789911 40.7169478980913,117.16869145346 40.7157147345976,117.170316401358 40.7149627719323,117.172878062334 40.7157068290294,117.176634864876 40.7160599941451,117.180391138284 40.7157012449108,117.187419246206 40.7143518412535,117.201498073473 40.7131073148323,117.206434258266 40.7120303373802,117.218590257666 40.7077103369126,117.219756548429 40.707254251995,117.23292254854 40.7016242522703,117.237221836753 40.6991115674511,117.240085091202 40.6969180645117,117.24490378567 40.6981870826937,117.245565966628 40.6983616032422,117.247636386367 40.6987916639067,117.252032386742 40.6994646643653,117.256367283593 40.6996521644002,117.260640604483 40.698900356216,117.264651220362 40.6972446245801,117.26821036671 40.6947628985178,117.272986003825 40.6905946890753,117.273342622718 40.6902844591731,117.273375531875 40.6902557676754,117.275395531499 40.6884907681203,117.275426650671 40.6884635205628,117.283036538096 40.6817864838956,117.290438860732 40.6782793723982,117.302958413253 40.6776615320133,117.303376577507 40.6776619833716,117.315494769764 40.6788056321283,117.318192244885 40.681224769975,117.320218531619 40.6827263488773,117.327671832638 40.6882679069216,117.33101045643 40.690277175462,117.334675319844 40.6916009371677,117.338527308614 40.692188943471,117.351817696169 40.6929061324135,117.362399853929 40.6961485102433,117.36424153347 40.6967109315222,117.369007029319 40.6982056222659,117.378082523482 40.7010578913881,117.380785473704 40.7017049528611,117.396358474198 40.7043049531509,117.399615881345 40.7045779672604,117.414012880667 40.7046039675601,117.418596568583 40.7040801295876,117.433065147971 40.7007017960102,117.448243723711 40.6971764494504,117.4510867452 40.6962884485326,117.455729734612 40.6944486412152,117.459410376591 40.6929915477772,117.468398789594 40.6953297910402,117.472715459486 40.6959610880289,117.47706631784 40.6956413906182,117.492243318058 40.6928383908595,117.496221122105 40.6916665681006,117.499871765221 40.689699616538,117.504680584058 40.6864234210012,117.509393566486 40.6832135036522,117.51046967109 40.6824856941205,117.513756264643 40.6797031669627,117.516371037581 40.676281600501,117.518192768019 40.6723796198787,117.519136999831 40.6681781224251,117.519190999623 40.6677031221041,117.519296940164 40.6663831002116,117.519305539547 40.6662001613595,117.519655492002 40.6594872416977,117.519790277871 40.6589585800521,117.519798277341 40.6589125797295,117.520083520294 40.656133357765,117.520149520639 40.6540953573116,117.519933452593 40.6504462368368,117.51905681452 40.6468973959935,117.517548892194 40.6435673906031,117.516304151477 40.6417797268396,117.51616952128 40.6404266751101,117.515232455193 40.6370812358332,117.514972891205 40.6365398016624,117.514474648334 40.6324439114959,117.513636659118 40.6286618466131,117.512085286014 40.6251122716816,117.509878729716 40.6219283511411,117.507099770512 40.6192295316865,117.50385266273 40.6171170611537,117.50025922357 40.615670190146,117.496454263067 40.6149431988999,117.495259262818 40.6148331983239,117.490045952539 40.6143547106675,117.488586763708 40.614274400165,117.472752763769 40.6139824001892,117.46862219558 40.6143359651268,117.464653258975 40.6155335226168,117.461016485283 40.6175236178001,117.457868133958 40.6202207432435,117.455921374191 40.6227563254448,117.455201542683 40.6226092141804,117.444863478552 40.6204954052049,117.441929725272 40.6201187888487,117.432333289327 40.6196033300185,117.431345721691 40.613541884501,117.430227999808 40.609462718491,117.429079459779 40.6065309452246,117.431039525254 40.6031655010452,117.432377815445 40.6003983026399,117.43327626299 40.5974587108898,117.434020408158 40.5941262655753,117.435119975568 40.5934637121564,117.437930018157 40.5909420702311,117.440216081306 40.5879372519959,117.441896695341 40.5845563417911,117.442911967307 40.5809198269508,117.443225715405 40.5771573039303,117.442826758423 40.5734028598069,117.441729314209 40.5697902937459,117.439972492977 40.5664483487271,117.436247492969 40.5607443482878,117.433850432819 40.5577472153057,117.430925038146 40.5552631119496,117.424600037848 40.5508621122432,117.421251915328 40.5489815755861,117.417606104137 40.5477755891493,117.413797010226 40.5472886126556,117.409965059105 40.5475385989104,117.406251518931 40.5485163319563,117.402669635337 40.5498384395927,117.400002929274 40.5487777655452,117.399229578347 40.5485457174994,117.398845299235 40.5480185042297,117.396342809061 40.5451904542113,117.393353335639 40.5428832106974,117.389983456116 40.541179028788,117.386353309428 40.5401386639485,117.382592313244 40.539799206028,117.378834550124 40.5401727569763,117.375213987367 40.5412459993996,117.367537033937 40.5443365648434,117.36204808136 40.5465421347895,117.358894228821 40.5481467839213,117.356072538095 40.5502821401261,117.354852374495 40.551388224296,117.349684253519 40.5560606671079,117.343210064538 40.5577747947665,117.332124769458 40.55531785312,117.326698359474 40.5548741980148,117.313287749174 40.5556119838848,117.31118094917 40.5554777695128,117.300070515841 40.548619358828,117.296322238911 40.5468140862018,117.281162238577 40.5413720861535,117.279111681403 40.5407577090236,117.267257086144 40.5378873021723,117.266585961026 40.5374912842081,117.266583561527 40.5374318973513,117.266393889852 40.5366954420127,117.267569064329 40.5345338903511,117.271387330924 40.5275085597147,117.272902955357 40.5239287592497,117.273697432606 40.5201233821735,117.273740747143 40.5162361964766,117.273031262537 40.5124140608975,117.271595783272 40.508801376567,117.269488542075 40.5055346315009,117.266789150985 40.502737244053,117.263599593596 40.500514900142,117.259784593035 40.4983749003808,117.253323238431 40.4947538972715,117.250761978539 40.4933178728918,117.24684073671 40.4916406704853,117.242653035876 40.4908330151588,117.238389302788 40.4909316333129,117.238366611497 40.4909371087544,117.239638854777 40.4889139389523,117.240993433046 40.485494877479,117.241320360112 40.4838226767724,117.242880085671 40.4824023103808,117.245379555631 40.4796607894061,117.247329679265 40.4765047942944,117.248663356294 40.4730429172146,117.249334697267 40.469394275189,117.2493315375 40.468562595171,117.25074477582 40.4678654670537,117.252963126256 40.4665885569625,117.25461912618 40.4654885574971,117.2610729145 40.4611967169966,117.26809238201 40.4565284057111,117.27101128363 40.4541634581373,117.27343643322 40.4512943793373,117.275282265659 40.4480223973234,117.276483655532 40.4444629555042,117.27699821491 40.4407416395624,117.276807788899 40.43698974649,117.275919096186 40.4333396521138,117.274363491993 40.4299201405573,117.267850492018 40.4185711406009,117.265670254795 40.4154881809937,117.262949413616 40.412869956869,117.259784953371 40.410809795312,117.256289671831 40.409381131164,117.252588158958 40.4086348894238,117.252124022402 40.4085862579416,117.250593799452 40.407204073351,117.252008105884 40.4042865782,117.252999222457 40.4004588274937,117.253678222293 40.3960948276949,117.253904900678 40.3923537852188,117.253429629736 40.3886361381901,117.252269099536 40.3850724390513,117.250640274943 40.3821084827463,117.255663465639 40.37679895112,117.257691714453 40.3742734118541,117.260120552639 40.3706891259483,117.264510390982 40.364213839565,117.266062867695 40.3614846053084,117.267169059746 40.3585460269585,117.268458836337 40.354085102747,117.269855688164 40.3518154571792,117.270924698649 40.3512916129975,117.271640656114 40.3508658407361,117.273134291466 40.3505175942857,117.276984972612 40.3491941951691,117.280487824734 40.3471183441331,117.283497662226 40.3443760806906,117.285889733865 40.3410810657006,117.287564893482 40.3373698703719,117.288453709353 40.3333963156837,117.288519341992 40.3293250968406,117.287759071068 40.3253249570138,117.286204408158 40.321561693304,117.285947554525 40.3210844399049,117.286026839584 40.3206914005204,117.291263913646 40.3179240789395,117.295125971655 40.3152610638633,117.298261465468 40.3117716771112,117.304174464753 40.303391677374,117.306287623985 40.2995698806852,117.306862307705 40.2976177009798,117.309836141511 40.297803908499,117.311376006813 40.2978408976438,117.316203007278 40.2977708971138,117.320178341875 40.2973128785846,117.326314015099 40.295973528331,117.328175556901 40.296144335931,117.33188708502 40.29613905744,117.335533727751 40.2954480685577,117.33898989956 40.2940951660235,117.34213657448 40.2921269420042,117.344865385205 40.2896111795215,117.347082355117 40.2866345180945,117.348711134716 40.2832994699902,117.349695630996 40.2797208898366,117.352501630893 40.2639008905431,117.352546832034 40.263635689919,117.354124501257 40.2539879931639,117.35492316932 40.2534034745795,117.367725424862 40.2514085062435,117.371298913151 40.2505080375124,117.374973592462 40.249211856396,117.380967745656 40.2470991781474,117.388865746319 40.2449401641265,117.392213423893 40.2436943582537,117.395286100082 40.2418729196859,117.395996076529 40.2412578005917,117.398734715279 40.239480460769,117.401615570506 40.2366403188306,117.403865953435 40.233278547015,117.40539379115 40.2295326899726,117.406136573191 40.2255560069445,117.406304572845 40.2235450068369,117.406341668756 40.2207432557609,117.406219669425 40.2186002558614,117.405664555432 40.2149252745464,117.404439046355 40.2114164618251,117.402585464044 40.2081949913801,117.401820313729 40.2073015902419,117.401851703317 40.2070704238489,117.403352732602 40.2067237149363,117.406795732197 40.2055937149868,117.410585564077 40.2038961442296,117.413943136434 40.2014525239592,117.416723699519 40.1983682021407,117.418807379283 40.1947761482996,117.420104345309 40.1908312210148,117.420558683537 40.1867034917444,117.420150806796 40.1825709128016,117.419739806728 40.1805679130629,117.418350438116 40.1763011891997,117.416044791631 40.1724516084495,117.412938929354 40.1692129517555,117.411397929337 40.1679309510917,117.411373513879 40.1679602991431,117.407626251577 40.1654360206436,117.403409513123 40.1639024281579,117.40286020399 40.163563958507,117.401277079242 40.1625496977482,117.40085530816 40.1622868151504,117.398778307707 40.1610278155473,117.396559137487 40.1598660652246,117.394555137702 40.1589720644671,117.391283965915 40.1578407317704,117.389024966062 40.1572727325566,117.387453373545 40.1569440286909,117.385430372884 40.1566050283462,117.380925158374 40.1563660229492,117.379941434221 40.1564251450799,117.378420058897 40.1561609370597,117.373717252932 40.1559070501798,117.371510467787 40.1560486575263,117.370789336954 40.1528473131229,117.370176337464 40.1509383123231,117.368219359128 40.1466563405996,117.366355951186 40.1442781729437,117.36629836545 40.1441373851859,117.364081800239 40.1401124707396,117.362837800429 40.1383644710691,117.360400494685 40.1355398403523,117.360021173796 40.1352380465634,117.359813787829 40.1349056830029,117.357337340069 40.1322014582274,117.356151064899 40.1313075997731,117.355054629948 40.1293722315626,117.352335441296 40.1262820194315,117.349041217382 40.1238139256836,117.345311496573 40.1220724950553,117.34130426423 40.1211314919269,117.339269263915 40.1208704924813,117.336333222556 40.1207118379747,117.335006222515 40.1207378373751,117.334944627589 40.1207391390897,117.334423051299 40.1207509655337,117.332124140365 40.1205271314743,117.328258560159 40.1205260925731,117.327638580544 40.1206474578801,117.325432545636 40.1202578297271,117.322496101168 40.1200256664152,117.322417916721 40.1199866858172,117.320446916659 40.1190616854287,117.317055634177 40.1178296654594,117.315590692306 40.1175814955196,117.314736833864 40.1161079009707,117.31453595433 40.1157689623635,117.314475954261 40.1156699622947,117.313017815449 40.1135777317178,117.311438815478 40.1115947320022,117.308923809567 40.1089671847074,117.306846205123 40.1074676971124,117.304421651184 40.1052557104374,117.300728859875 40.1031132567594,117.298041860069 40.1019172573635,117.294199094526 40.1006545412086,117.290180850184 40.1001908478707,117.288139242127 40.1003703634651,117.288111088011 40.10034576677,117.284520919997 40.0983290559234,117.283947392893 40.0981481499912,117.282850667154 40.0971256456303,117.281048667691 40.0958536454276,117.277480895784 40.0938478507714,117.273579508017 40.0926103605396,117.271900081144 40.0922617842626,117.270926323414 40.0920221799744,117.270126463364 40.0918250477968,117.268395225736 40.0913497205853,117.265753770331 40.0908128445875,117.261794770324 40.0902828444282,117.258299880913 40.0901236950634,117.254830728873 40.0905759832797,117.253337025086 40.0909056434494,117.251664473391 40.0911899068082,117.249801422153 40.0913754086065,117.245681297611 40.092230498504,117.244190458658 40.0928880946933,117.238518547581 40.0890986169892,117.237162328484 40.0878405100742,117.236620092857 40.0859142067776,117.23480450914 40.0823726093891,117.232323616893 40.0792606277126,117.229275654837 40.0767014904364,117.225781316601 40.0747965346387,117.221978971484 40.0736211930272,117.218019185282 40.0732220069444,117.214058758166 40.073614783417,117.2081676941 40.0747998460606,117.201267161072 40.0721567250138,117.200654249213 40.0716078596114,117.197954444443 40.0695736490619,117.194950612751 40.0680231802551,117.192019388814 40.0670930312982,117.191082783276 40.0657697170057,117.190567063493 40.0650735503989,117.18668201944 40.0600599461644,117.186006970744 40.0591873462362,117.183434398102 40.0564405766285,117.180392807815 40.0542243632358,117.176989894028 40.0526171759315,117.173346144332 40.0516759206911,117.16959057364 40.0514339247111,117.165856156132 40.0518997563877,117.162268035038 40.0526958120359,117.160913225598 40.0515255181363,117.157742310164 40.0496736173177,117.154286259993 40.0484326666546,117.153424128305 40.0482099578845,117.134643993795 40.0433402569756,117.130707252055 40.0427293577295,117.126727529218 40.0429108503153,117.122862732064 40.0438775335004,117.118249266235 40.0455347487137,117.11170580467 40.0478769767314,117.104194878379 40.0505687301719,117.089982065292 40.0493396202715,117.086872782991 40.0471666441424,117.0827732702 40.0449624043433,117.078275275194 40.0437654418793,117.067226274683 40.0421694411244,117.062858295061 40.0415409526147,117.061542144271 40.0399750634585,117.058363932444 40.037462682201,117.054744621581 40.0356423308925,117.050880592842 40.0341677822575,117.050057457169 40.0338485107135,117.049951053143 40.0338075880335,117.045883158713 40.0322563817152,117.042392767681 40.0270806494161,117.03986083017 40.0240291580591,117.039653920255 40.0238249230265,117.035814006344 40.0200078304721,117.032941085252 40.0171478870342,117.030027101442 40.0147495138668,117.026714438573 40.0129412660393,117.023121056391 40.0117875329761,117.019374910702 40.0113293976563,117.005363209707 40.0109482007834,117.005078473846 40.0109388294187,117.004896473547 40.0109348292342,117.004113470246 40.0109329507491,117.003007470504 40.010951950726,117.003007650485 40.010962427537,117.001271098277 40.0110496439827,116.997994098243 40.0113796439123,116.998004611343 40.0114840421975,116.996145328887 40.0115963830775,116.990156328795 40.0127003827274,116.984202114892 40.01379897569,116.982399625119 40.0142186194399,116.974613624896 40.0164156191317,116.972964838933 40.0168819484919,116.972660522464 40.016970629991,116.965894522513 40.0190006300755,116.963121905525 40.0200625810794,116.95850490575 40.0222365815092,116.955501949851 40.0239841562204,116.953924976923 40.0253150776336,116.952378647851 40.0242453141058,116.949206521788 40.0224542226194,116.945762136305 40.0212682204643,116.942159762269 40.0207266544254,116.938518911973 40.0208474914875,116.934960374199 40.0216267227626,116.931602206929 40.0230384964885,116.928555820656 40.0250359756867,116.928209820888 40.0253109757778,116.925601280466 40.0279301830553,116.922055219707 40.0265176305794,116.917931213143 40.0258118539561,116.91493721267 40.025617854003,116.911200393571 40.0257258418511,116.907548950892 40.0265273683464,116.906051951106 40.0270063679525,116.902785243399 40.0284859472564,116.89941001632 40.0276914313807,116.896179016609 40.0272224313364,116.895183742058 40.0271136553032,116.894074022556 40.0266182444182,116.890697514126 40.0254597505694,116.88989651406 40.0252627504779,116.885857201384 40.0246975908216,116.884535976894 40.0247839572077,116.881136310549 40.0241381679592,116.879012317607 40.0224935947282,116.875669473908 40.0207912553062,116.872067537644 40.0197431051934,116.868333230008 40.0193860197874,116.866916230313 40.0193840196952,116.866888000381 40.0193839997719,116.866795999736 40.0193839997719,116.862264173184 40.0199042013105,116.857968092946 40.021437744962,116.854131241777 40.0239048555497,116.853771615601 40.0241990952319,116.85156030446 40.0254719801745,116.849772304744 40.0268299798465,116.848465587985 40.0279128579659,116.846528587692 40.0296618585819,116.84642618638 40.0297624992171,116.846267329727 40.0298033711433,116.843563135365 40.031065431339,116.841180309147 40.0309136626319,116.837704700054 40.030592069566,116.837266473468 40.0305658761517,116.83722880495 40.0271203607154,116.836774526672 40.0230950067264,116.835518441837 40.0192437609049,116.833512080834 40.0157246191036,116.830837753839 40.0126819527381,116.827605174069 40.0102405860055,116.823946956846 40.0085006750207,116.820013179098 40.0075335989512,116.81596522252 40.0073790317142,116.815460222614 40.0074110313912,116.808582387784 40.0078519774562,116.804636549061 40.0085056989313,116.802422502351 40.0093504622862,116.801819325449 40.0091761590639,116.799424213659 40.0086407720978,116.799130213591 40.008593771729,116.796500187744 40.0083499490087,116.796007187492 40.0083369493086,116.790820740601 40.0088802877545,116.790102740766 40.0090522875924,116.789779975758 40.0091324506043,116.789150796719 40.0092942811754,116.788473049151 40.0094509687912,116.787572169788 40.0096856369745,116.78840202211 40.0076403473845,116.789122701888 40.0044254009442,116.789340702049 40.0028564005351,116.789513037376 39.9992565395497,116.789216980959 39.9970370669498,116.789577108488 39.9953450017667,116.789667496671 39.9916459316486,116.78958449734 39.9904309322759,116.788954112482 39.9866348013777,116.787608386257 39.9830296639609,116.785848906174 39.9801596589127,116.785456978565 39.9792962845594,116.782679712141 39.9756782629121,116.781198182393 39.9744615603213,116.782008367568 39.9738674013107,116.782497679512 39.9734260615992,116.783320938766 39.9726876639699,116.784901115104 39.970950692197,116.785561701739 39.9705163770518,116.788319535979 39.9678158374509,116.790507256702 39.9646358308671,116.792043379165 39.961094801128,116.792870688322 39.9573246388778,116.7929666165 39.9565248065629,116.793885927884 39.9550341315029,116.795152832029 39.9518881370466,116.795308832028 39.9513801370025,116.795461845856 39.9507006651862,116.796059801413 39.949310927194,116.796514800836 39.9474714977308,116.797251992536 39.9452332193529,116.797278992882 39.9451212195839,116.797630023353 39.9432969774291,116.79768802333 39.9428959778227,116.797850618268 39.9413495820287,116.797869618244 39.9410615822373,116.797876026217 39.9409605594163,116.797902026517 39.9405335586108,116.797786436084 39.9381897030688,116.797807908611 39.9380615505366,116.797726851819 39.9343150452404,116.797665851704 39.9337890452655,116.796782697135 39.9297986179529,116.79572831969 39.9274534272435,116.796059626951 39.925543810864,116.79634620694 39.9215667319781,116.796098606319 39.9196393907103,116.796854517244 39.9174506935201,116.797343999826 39.9130529997963,116.797343999826 39.9120589998232,116.797183877205 39.910185610023,116.797442977833 39.9096990425132,116.797476978501 39.9096080428134,116.797911215637 39.9080493465286,116.801451057086 39.9083287147646,116.807768189319 39.908983439579,116.810724917234 39.9090699680348,116.814832917817 39.9088859676435,116.81839986982 39.9084019407242,116.821822285931 39.9072863706402,116.824989301869 39.9055753946908,116.828027698686 39.9035610300696,116.832799501926 39.9014284145829,116.836807926238 39.8996363303299,116.840144736678 39.8977412530849,116.843058195202 39.895243778139,116.843671351968 39.8946064141494,116.847471737085 39.892239338713,116.847792694301 39.8920351682528,116.857209695095 39.885918168553,116.860591472952 39.8831525106501,116.863292074439 39.879718557138,116.867718317832 39.8726110369126,116.871856250843 39.8702922602194,116.873371505101 39.8694451499233,116.873389131951 39.8694352838597,116.878846131791 39.8663772831336,116.88136240908 39.8649660108831,116.881375193351 39.8649629782658,116.884227477531 39.8639166865852,116.885369858583 39.8650961896398,116.887131858899 39.8665361903953,116.891521528367 39.869261684515,116.89223752811 39.8695866842139,116.89633602343 39.8709358786148,116.900628517116 39.8713746124408,116.901913517019 39.8713666120719,116.906655400848 39.870765920879,116.911119336642 39.8690572461333,116.912425467691 39.8683683556567,116.913594417028 39.8678249090026,116.914147807054 39.8675572184598,116.915028807212 39.8671142187151,116.916513761538 39.866286660048,116.916852722694 39.866078402717,116.920650885553 39.8639814944765,116.921701885457 39.8632394944333,116.923361457742 39.8619311785623,116.927332458303 39.8584451787832,116.930026544939 39.8555621867484,116.931763545001 39.8532901871953,116.931960427356 39.8530282448753,116.933389426704 39.8510942447214,116.934621618452 39.8492140029475,116.935825619116 39.8471300030708,116.936144362857 39.846556793219,116.938734362686 39.8417137929148,116.939608158722 39.8398565647204,116.940843158118 39.8368385649395,116.942221207348 39.8313756799736,116.943289947415 39.8213100795305,116.944948308238 39.819910411838,116.945972307796 39.818767411289,116.949109414459 39.8140704653593,116.949520414527 39.8132134654087,116.9508281394 39.8096562004008,116.951096540028 39.8080190660084,116.951664212681 39.8074679586182,116.951804415621 39.807330503707,116.952814928306 39.8063300224488,116.958099213478 39.8018798036977,116.960987646246 39.7988805838107,116.963192442661 39.7953482706085,116.964618033815 39.7914359754193,116.964966371216 39.7900436211111,116.96572570413 39.7870172658657,116.966310555456 39.7829608683798,116.966056276718 39.7788704217096,116.964973545344 39.7749176879204,116.963107826337 39.7712686463716,116.960537463158 39.7680765240732,116.957370388003 39.7654753615255,116.953739589617 39.7635743842206,116.949742589655 39.7619793835118,116.946030876791 39.7609003942894,116.942180926646 39.7605555551925,116.932923926935 39.7606245556765,116.931870918096 39.760660157818,116.927784228711 39.7609062295002,116.927722066723 39.760903257068,116.927304513163 39.7595081841488,116.925714129495 39.7563578264481,116.923594786136 39.7535360437526,116.922817967369 39.7526724051009,116.923082954804 39.751657451134,116.924524954752 39.7416894510109,116.925701082695 39.7335366449817,116.925887830504 39.7298286761444,116.925386009054 39.7261500781949,116.924212910995 39.722627614769,116.922408961016 39.7193826691431,116.920036322816 39.7165270613942,116.91717675696 39.7141591951061,116.913928803428 39.7123606664042,116.906253303905 39.7089994272714,116.907754338265 39.7069862998159,116.909025338422 39.704909300262,116.909172568026 39.7046686426863,116.911953011191 39.7019275426723,116.911968620502 39.7019121373475,116.912552620453 39.7013351368194,116.912864884842 39.7010196910999,116.914142884422 39.6996996913817,116.916447418209 39.6968332306518,116.918188078947 39.6935932347747,116.919305999784 39.6900892761431,116.919428000014 39.6895352757763,116.919436986177 39.6894942649111,116.920542985919 39.6844212647922,116.920959171416 39.6788528334389,116.920658171925 39.6742608339177,116.920034346844 39.6704482863379,116.91868916189 39.666826801253,116.916672808191 39.6635315023987,116.914060519314 39.6606853429508,116.910949764177 39.658394517934,116.907456610317 39.6567445019054,116.90415461003 39.6555595021175,116.902016751942 39.6549243917266,116.891367751891 39.6523973912059,116.887317787313 39.6518650609887,116.883244138068 39.6521666739344,116.879316732244 39.6532896485691,116.87918094546 39.6533608762647,116.877679059467 39.6528637376382,116.876466550332 39.6525601888654,116.87566656876 39.6522041276286,116.87133718735 39.6508409353409,116.869339187842 39.6504539354807,116.867529490815 39.6501885982342,116.866063295448 39.6500417244731,116.864616295235 39.6489957774708,116.86444987511 39.6489220654449,116.864256160439 39.6480131457983,116.862764132573 39.6444966517532,116.860630750388 39.641327967803,116.857933839013 39.6386226865679,116.85609583879 39.6371016865738,116.852375263453 39.6346647105807,116.851679339707 39.6343125518345,116.852567879017 39.6330780933036,116.854192047336 39.6295163555262,116.855091316817 39.6257064719159,116.855231236673 39.621794398149,116.854606446611 39.6179300047812,116.853240882179 39.6142613357438,116.851186857805 39.6109289368189,116.848523062643 39.6080604713656,116.84485106238 39.6048114708246,116.843158791197 39.6034698251738,116.841128791113 39.6020318254099,116.83808892508 39.6002579726289,116.834785180067 39.599044461988,116.827983180255 39.5972064617645,116.824805320107 39.5966182422504,116.821574209245 39.596549540627,116.821082057989 39.5965789198997,116.820992287463 39.5965253736329,116.820485131971 39.5962212726422,116.817291155506 39.5946733178812,116.813873849298 39.5937143247191,116.810340838413 39.5933744958055,116.806803391934 39.5936645337543,116.805006899052 39.5941414919628,116.803004682302 39.5937894899827,116.801338631488 39.5914069412071,116.800371479674 39.5880840693867,116.798564670279 39.5846084837356,116.796115064286 39.5815517393423,116.793116630147 39.5790310948765,116.789684389714 39.5771432438719,116.786351389796 39.5757152436698,116.784319137741 39.5749718970188,116.780097138195 39.5736818977847,116.776001476653 39.5728855761727,116.771829718919 39.57295635045,116.767763425853 39.5738911404189,116.76397956834 39.5756492626842,116.760642825264 39.5781542012526,116.757898416454 39.5812969376182,116.756077416115 39.5838839373085,116.754285081329 39.5869883044425,116.753075696309 39.5903627595336,116.752683848807 39.591869685187,116.749614405477 39.5946186345712,116.748778211602 39.5947451870566,116.748541615345 39.5947810062304,116.747282951416 39.5949695582348,116.745700902931 39.5951661440872,116.74337829311 39.5954080099967,116.742457917464 39.5953655091116,116.742700351002 39.5918903246284,116.742239431505 39.5881066829861,116.741069075013 39.5844791967294,116.739231790019 39.5811396197036,116.736794308509 39.5782092486095,116.733845162193 39.5757945173888,116.730491466947 39.5739831314389,116.72685503226 39.5728408820675,116.725629588364 39.5725810737318,116.712476592738 39.5695717663373,116.710414140031 39.5692122970421,116.708667140407 39.5690012972044,116.700161866499 39.567978939447,116.695960585587 39.5679184728401,116.691839385736 39.5687370870986,116.687980160404 39.5703986518279,116.684553240528 39.5728298321342,116.679403819025 39.5773669271596,116.657053995615 39.5825961412894,116.653534520793 39.5831868827056,116.653156969605 39.5825627370733,116.649805481753 39.57919046161,116.647171481694 39.5771064617333,116.643552970361 39.5748266236414,116.640076971044 39.5731256234404,116.638344468125 39.5723769557895,116.638230468264 39.5723339556052,116.634649854027 39.571351707839,116.630949378965 39.571048239065,116.627176378541 39.5710902392032,116.622853325607 39.5716124284714,116.618745754324 39.573057878731,116.615336754499 39.5746938786324,116.611911779604 39.5767839653143,116.608972920335 39.5795156354192,116.606896713592 39.5818759731945,116.60441279171 39.5838470604477,116.602260836877 39.5858671214311,116.596725587449 39.5895068888173,116.592251676104 39.5913791269383,116.590899320046 39.591834125434,116.587069541013 39.5935909371801,116.583692809725 39.5961111119295,116.582183087825 39.5978373862666,116.581641521866 39.5958876064549,116.579772899441 39.5922577669378,116.57720612193 39.5890830011373,116.574048143642 39.5864955974543,116.570430553466 39.584603369667,116.556156553521 39.5789073695966,116.552657649243 39.5778696538821,116.549028428411 39.5774850226346,116.540527739386 39.5773641184219,116.541216578128 39.5735284585711,116.541128018206 39.5695383865257,116.540248965052 39.5656453435849,116.5386144237 39.5620043557579,116.536289483704 39.5587604118931,116.533366727182 39.5560426900344,116.518910181204 39.5450727997072,116.517995024157 39.5438839944667,116.515687537725 39.5413648342425,116.513539537595 39.5393888339504,116.510153307632 39.536877502514,116.506315887798 39.5351317797526,116.502197787501 39.534229233972,116.49638091462 39.5335858305261,116.494740702415 39.5333762682158,116.494303934228 39.5333253376739,116.490127336294 39.5328847957869,116.488870487316 39.5295761115359,116.486852567665 39.5263202999569,116.484250962752 39.5235089016157,116.481161101453 39.5212450407734,116.477696322267 39.5196117575933,116.468914321978 39.5164597570289,116.452289611801 39.5104971152372,116.451734318308 39.5089407867175,116.449941750587 39.5058283937811,116.44762140785 39.5030868069405,116.445792818876 39.5015819118328,116.447877306156 39.5001247347583,116.450754502475 39.4970303290573,116.458152501893 39.4871603289569,116.459990133862 39.4842034695672,116.46128718354 39.4809727466083,116.462004350619 39.4775660502487,116.462785718194 39.4711045751174,116.463479188976 39.4705582817488,116.466057438748 39.4675225373397,116.467983022864 39.4640361005028,116.469179576913 39.4602372356182,116.469599648206 39.4562765972716,116.469226577643 39.4523112556269,116.468756577553 39.4499132556591,116.467951087735 39.4469948951196,116.466714329526 39.4442315509102,116.465993543323 39.4429013720029,116.465169917234 39.4403194243548,116.463758608562 39.4375133435701,116.462761608451 39.4358673432075,116.459084354406 39.431408324344,116.458349354685 39.4307423242082,116.455899998941 39.428846552339,116.454305999177 39.4277995526198,116.451458316101 39.4262669278216,116.451332806246 39.4261782734285,116.449817916226 39.4252073216226,116.445972916079 39.422980322346,116.442253259139 39.4213065810983,116.438271388235 39.4204222951577,116.435063739519 39.4203766394432,116.431912133812 39.4191741219897,116.428061970674 39.4185175428877,116.425562971444 39.4183395427731,116.422575742354 39.4183504233615,116.419623455132 39.4188061164476,116.418157454976 39.4191461168383,116.414805493535 39.4202427248795,116.411698269329 39.4219110631053,116.410667269449 39.4225880628491,116.409505170253 39.4235383688308,116.406901874841 39.4246226772297,116.403270862527 39.4272031281057,116.402613916065 39.4277903861501,116.402034854009 39.4279076066803,116.398913636073 39.4288299186553,116.395701434589 39.4286690373392,116.391719486599 39.4288674840669,116.389351487115 39.4292244843422,116.387847051504 39.4295681902328,116.386146202857 39.4290140264792,116.382674349887 39.4285267127133,116.381321349546 39.4284567130826,116.377700733445 39.4285980546703,116.374165036845 39.4293903222695,116.370830308719 39.4308075119971,116.370771862399 39.4308388791477,116.370206801613 39.4304347901352,116.36665825875 39.4284101439977,116.362772331215 39.4271488289582,116.358711172434 39.4267034776386,116.357553172093 39.4266954772697,116.353662232411 39.4270502363743,116.350821955377 39.427887274889,116.348182728299 39.4278414564558,116.342158412569 39.4271988280866,116.340158861462 39.4270863712797,116.338845862066 39.4270783709107,116.333994197768 39.4276453218137,116.329424667136 39.4293714358258,116.328769666608 39.429715436401,116.325423351532 39.4319272404573,116.322585704552 39.4347623174202,116.321911995805 39.4357795897632,116.319086913275 39.4371683631476,116.314842912715 39.4396963637144,116.312104829901 39.4416574080461,116.306661829807 39.446296407936,116.303804541803 39.4492952095674,116.302215410059 39.4518654045368,116.300922011221 39.4529262062166,116.298904181874 39.4551676766999,116.298379181945 39.455847676582,116.296303374927 39.4591619829651,116.295411581596 39.4615049195603,116.294728530062 39.4621127806651,116.294587529855 39.4622677806184,116.292628344235 39.4649033780135,116.291750919471 39.465537126976,116.290660427917 39.4662020519459,116.29052872456 39.4661862078682,116.287201006954 39.4663428506019,116.286187006958 39.46647585044,116.28215815334 39.4674368417845,116.27841364001 39.4692070919802,116.277163932278 39.46996532271,116.274564710593 39.4699866746395,116.270528970042 39.4704319785373,116.268973970278 39.4707659786512,116.264694307505 39.4722066122721,116.26084691696 39.4745706183375,116.260748900254 39.4746670479898,116.256582967837 39.475638597532,116.250900968413 39.4778605974774,116.247008073888 39.4799015754622,116.244831978775 39.4813680471184,116.240235001077 39.4834955170877,116.236828586792 39.4855026230487,116.233883571042 39.488140617524,116.231515046019 39.4913064068148,116.229252045981 39.4950534069388,116.227503651477 39.4987608484429,116.227481108508 39.4988548577443,116.226930931898 39.4993415911195,116.224203546165 39.5022936540349,116.222121463061 39.5057314205101,116.220768763911 39.5095160622899,116.220319557404 39.5126588169927,116.219344687804 39.5148767732047,116.21851335885 39.5183274009083,116.21830508117 39.521870642332,116.218726414438 39.5253949036242,116.218864192779 39.5258455835034,116.218443967914 39.5279197987996,116.218377393007 39.5315447102321,116.21896631717 39.535122081256,116.2192183171 39.5360930819671,116.219704972431 39.5373880264571,116.215251439757 39.5450868689181,116.214570639821 39.5461326835697,116.21404110127 39.546373232741,116.212325101277 39.5472512327607,116.208532295289 39.5497746321512,116.205408911356 39.5530908641317,116.204144911522 39.5547838639637,116.203233162355 39.5563836292546,116.202681470191 39.5563593459319,116.200483983737 39.5563834763353,116.193412983211 39.5568504762874,116.188708892862 39.5577351821849,116.184349736307 39.5597122822494,116.182330316356 39.5609386336281,116.169473501573 39.5606423203813,116.161746394896 39.5599255048223,116.159898272189 39.5597545828273,116.157667194952 39.5565165067839,116.155077561721 39.5534640780789,116.151931929847 39.5509884930208,116.148356309577 39.5491889205842,116.14449393598 39.5481374494871,116.14049953112 39.5478762004032,116.124302531528 39.5484422004241,116.124085467995 39.5484509662095,116.117828259931 39.548737700535,116.115248052515 39.5488544384732,116.114616333996 39.54889304392,116.113733333745 39.5489610443579,116.113707515777 39.5489630494312,116.11149833719 39.549136057412,116.098107666219 39.5498059819111,116.09437412551 39.5503490697785,116.090809007353 39.5515836560167,116.088181597751 39.553096023324,116.078327092212 39.5520605118351,116.07765962465 39.5519908929918,116.077636727729 39.5519885183032,116.072972727585 39.5515075177056,116.062659807694 39.5504444232797,116.061090311677 39.550344792502,116.048960311677 39.5500527925262,116.047239152086 39.5500854677469,116.044019151984 39.5502854679768,116.031916251097 39.5510387272278,116.031692879035 39.5510512234146,116.031557631426 39.5510592492481,116.029230632034 39.5512052487863,116.025788914065 39.551724663403,116.022489370748 39.5528329178971,116.021051370085 39.5534599180331,116.021025095945 39.5534713965606,116.016392096332 39.5554993965528,116.014137077183 39.5566621548259,116.012285077213 39.5577711547063,116.010881891054 39.5588444331749,116.010851034242 39.5588244932614,116.008806034365 39.5578214928733,116.004817382406 39.5563670960718,116.000611606745 39.5557873844243,115.996378226094 39.556108480628,115.994294855876 39.5564919883736,115.99403060637 39.5564244195766,115.990184185756 39.5558317257005,115.986295803036 39.5559951802643,115.985485909892 39.5561907251221,115.984811838524 39.5553681274339,115.98198002149 39.5529874275885,115.978758160727 39.5511690402012,115.975256499607 39.5499751855763,115.973013595498 39.5496514756242,115.97250046015 39.5492475167329,115.968846114902 39.5474260536235,115.964528114527 39.5458000532839,115.960812789025 39.5447966417188,115.956974063024 39.5445233999897,115.953694062852 39.54460640022,115.951628609696 39.5446596414273,115.949640250317 39.5448103376226,115.947266046876 39.545109914964,115.94343141318 39.5452808382683,115.93927147371 39.545909199322,115.935335019207 39.5473938743537,115.931796237133 39.5496691667382,115.928811718011 39.552634395163,115.926556717443 39.5554043951985,115.924510877689 39.5584526501678,115.924471886553 39.5584674640657,115.922170905279 39.5599416253715,115.921803753122 39.559375109711,115.919395752694 39.5564641103679,115.9151526739 39.552620344069,115.913595674044 39.5515723434043,115.910536050194 39.5498812339347,115.907228702115 39.5487487154912,115.905763074652 39.548386318489,115.905136923798 39.5467358019238,115.903081830449 39.5434374634759,115.900427667256 39.540598825962,115.898014730085 39.5388602984541,115.896662960154 39.5369168534589,115.893806497347 39.5341757586408,115.890466072582 39.5320510132548,115.886772571889 39.5306258700338,115.882870715681 39.5299561695826,115.876416715729 39.5294981691463,115.876364459542 39.5294945296194,115.876159429078 39.5294805194849,115.872719543449 39.5288404710666,115.865259914359 39.5274558666255,115.863289490431 39.5270918257009,115.859580489885 39.5267591425723,115.855874107148 39.5271198226576,115.853756161459 39.5277368174311,115.852628913633 39.5266532615129,115.849544638459 39.5246285633114,115.846140932313 39.5232048566595,115.84259557612 39.5220933775663,115.842407602758 39.5206252827255,115.840921154569 39.5089756095395,115.839957116413 39.5048941360539,115.838163149036 39.5011034268285,115.835618132266 39.4977701574089,115.83243396919 39.4950408899462,115.828750665828 39.4930356289346,115.824730175145 39.4918425446752,115.809751155964 39.4890636176174,115.808420861769 39.4888001295148,115.804272626231 39.4884207211147,115.793220625582 39.4885657215062,115.793180004441 39.4885662957124,115.790672004797 39.4886042956661,115.786102301449 39.4892046601298,115.784977602043 39.4894871896462,115.775229924994 39.4913712915153,115.774915284059 39.4910750133513,115.774106284524 39.4904500133076,115.770648277079 39.4883020236942,115.766826965614 39.4868987227461,115.762800663956 39.4862982480808,115.75873617851 39.4865254768757,115.754801897604 39.4875709952265,115.751160815299 39.4893914881577,115.747963778675 39.491911534127,115.747851368614 39.4920451627985,115.745167593261 39.4918046672137,115.741293817629 39.4922157083801,115.737573203553 39.4933698848759,115.73414690269 39.4952234098633,115.73114490112 39.4977059648097,115.728681087973 39.5007233672127,115.727153208714 39.5030191798419,115.722035331492 39.510702993545,115.722015523845 39.510732789832,115.720974523503 39.5123017902411,115.718644478099 39.5171003944865,115.715187903652 39.5275914590764,115.714068289318 39.5281375378184,115.710972012747 39.5304831334025,115.708669974565 39.532608783953,115.704048012507 39.5365874923277,115.702539608403 39.5380296261332,115.699522608668 39.5412316263053,115.699300284691 39.5414936110401,115.694659316861 39.5421774723511,115.690757773785 39.5424432407611,115.685578871111 39.543495865743,115.682875871467 39.5444308656933,115.680739644585 39.5453110244426,115.677415644183 39.5469110244826,115.67268921189 39.5500854890547,115.670820212036 39.5517724895095,115.667896568797 39.5550642200066,115.665763974001 39.5589158714571,115.664525768819 39.5631408007325,115.664048769305 39.565841800284,115.663791551077 39.5706983201295,115.664714118495 39.5754733400863,115.666459195316 39.5808701822712,115.665340261796 39.5829281685775,115.664898262098 39.5839101688966,115.66468077192 39.584667471025,115.66171479811 39.5829452725283,115.658305256615 39.581373040679,115.654661944456 39.5804657575327,115.640785944878 39.5783677570104,115.636657333337 39.5781754402696,115.635030941671 39.5784387010466,115.633023583138 39.5777146757367,115.628928470119 39.5771415875537,115.624803030133 39.5774228803349,115.621606061814 39.577977060881,115.617401102263 39.5786962911141,115.61399908609 39.5795920803621,115.611003225753 39.5809790964686,115.610522720948 39.5808649138391,115.600317899935 39.5794000895447,115.596365013519 39.5770381330789,115.59454175265 39.5747690423583,115.591732068227 39.5723607727361,115.588526793461 39.5705113666687,115.585035661648 39.5692841389849,115.581378192462 39.5687211040979,115.577679600177 39.5688415376325,115.565983600406 39.570313538065,115.563123008577 39.5708880634798,115.560210008243 39.5716980630616,115.556698387843 39.5730413204099,115.553500215493 39.5750180631219,115.550728513149 39.5775584340091,115.548481231511 39.5805726575194,115.54683778849 39.5839542123643,115.546476217137 39.5852911930431,115.543341764948 39.58902612495,115.540778964968 39.5898375805995,115.537415074953 39.5916684211095,115.536008842294 39.5928295417779,115.535606270534 39.5925799554523,115.532214622016 39.5912541117665,115.528638788793 39.5905652301328,115.524997319936 39.5905361489424,115.518568319338 39.5910721493785,115.516591212412 39.5913368052767,115.513588382588 39.5918924114491,115.511547968956 39.5920606030954,115.50775814301 39.5927450374213,115.504169756953 39.5941431492003,115.50091586124 39.5962030991356,115.49811710407 39.5988485082435,115.495877257978 39.6019812898398,115.494279372152 39.6054852864053,115.493382693125 39.6092305764834,115.493220468034 39.6130782919179,115.493798711871 39.6168857668173,115.495402222032 39.6232412160911,115.495115957637 39.627442632305,115.494868629594 39.6276517360959,115.491965668095 39.6307911773517,115.490938482717 39.630751730966,115.490868558107 39.6307491681837,115.48959733534 39.6307048036291,115.482875922111 39.6303227837233,115.474597722116 39.6293442731688,115.470812642331 39.6292577170808,115.467079071426 39.6298860237258,115.463530804912 39.6312066772309,115.460294997765 39.6331723509184,115.457487607731 39.6357126032943,115.455209239884 39.6387364023768,115.453541541364 39.6421353878994,115.452544275486 39.645787754489,115.452253180072 39.6495626166582,115.452348179956 39.6548896167991,115.452924053825 39.6593107443407,115.454465051604 39.6634944419036,115.45689459341 39.6672328006431,115.461735536382 39.6731222270366,115.461876761906 39.6771186642053,115.462721762202 39.6830076641826,115.463695019768 39.6869240529836,115.465434664233 39.6905653396609,115.467435866969 39.693209775166,115.466723235543 39.6950238592109,115.46603285053 39.6990358505149,115.465387632959 39.7084457330026,115.463677427036 39.7196537700949,115.46367603667 39.719661015498,115.46147080821 39.7195937893383,115.458105507808 39.7200668286101,115.454868877614 39.7211028005844,115.44262830766 39.7262036718239,115.434404844078 39.7289759896331,115.430941399754 39.7305232226568,115.427832387385 39.7326965654055,115.425189648846 39.7354178352609,115.423108252536 39.7385891387808,115.422733005016 39.739299775034,115.422032805564 39.7406184353652,115.420649994298 39.7432238479472,115.419566174804 39.7456398247519,115.411357174858 39.767753824713,115.41037128897 39.7714733684993,115.410115968644 39.7753128721773,115.410600665187 39.7791302071409,115.411807436371 39.7827840654129,115.412458320802 39.7842600015812,115.415007210986 39.7900519356451,115.417064286449 39.7936570171523,115.419821200992 39.7967598536505,115.423159213119 39.7992268046366,115.426934553213 39.8009516174699,115.431891553378 39.8026196170484,115.436071425101 39.8035427893973,115.440352011206 39.8035553355344,115.449594436277 39.8025879378465,115.456442719338 39.806311004448,115.464538032412 39.8129224171812,115.467634177268 39.8150020132739,115.471062610971 39.8164705755706,115.474704102601 39.8172770317594,115.478432011704 39.8173933356472,115.482116692479 39.8168154425241,115.490821692815 39.8146024420945,115.493953185802 39.8135233576712,115.496863642091 39.8119422454973,115.499473237533 39.8099024702285,115.502781783845 39.8068427347678,115.503002927651 39.8069034018902,115.518178445005 39.8097147103924,115.518180196529 39.809715268675,115.515344943942 39.8103643303361,115.511605776726 39.8116192890233,115.508185599982 39.8135836423052,115.504929600017 39.8159086425038,115.503445757217 39.8169699750002,115.502107382625 39.8178943073766,115.501707128743 39.8181780514808,115.499340128406 39.8199000517509,115.496049646305 39.8229084185237,115.489452646054 39.8304534184256,115.487272311663 39.8334742048354,115.485690047625 39.8368469558051,115.484760754581 39.8404546452356,115.484516676695 39.8441720952453,115.484966282866 39.8478703195327,115.486093972873 39.8514209988813,115.487860618669 39.854700933518,115.490204922023 39.8575963178403,115.492822232759 39.8598172034745,115.494767820668 39.862557231759,115.491760378379 39.864619079528,115.489169919105 39.8672032996107,115.487093621035 39.8702162149779,115.485400621203 39.8732302145743,115.483868328771 39.8766884730009,115.483014556411 39.8803733806254,115.482869842433 39.8841531331712,115.483439363065 39.8878925338843,115.484702747305 39.8914578293425,115.48661480557 39.8947214936383,115.487776062099 39.896332172104,115.48549093018 39.897437005942,115.484972496462 39.8976869125679,115.484952159443 39.8976967300105,115.482960893272 39.898659375532,115.479901627411 39.9001380205931,115.474741259901 39.9026326812828,115.471481012005 39.9046128002701,115.468655993153 39.9071758757725,115.466368964024 39.9102286753575,115.464362290803 39.9135204104296,115.459208234933 39.9182990614221,115.449018156568 39.9217888048806,115.446389549304 39.9229044918851,115.433520886306 39.9294874878586,115.432251314494 39.93012397897,115.431179480651 39.9299557856502,115.430717106995 39.9298887533264,115.422924106449 39.9288517540683,115.41894234119 39.9287221869571,115.415013889763 39.9293843890413,115.411294625678 39.9308120853968,115.407932122289 39.9329486277368,115.405059797359 39.935709242109,115.402791619301 39.938984392565,115.402242127671 39.9399761809287,115.399847777977 39.9442570486953,115.398231477314 39.9479969592517,115.397406614268 39.9519868163618,115.397407419333 39.9560610473513,115.398233859101 39.9600505781651,115.398632563727 39.9613113122079))";
	 var original = OpenLayers.Geometry.fromWKT(polygon);
	 LSGJLayer.addFeatures([new OpenLayers.Feature.Vector(original)]);
}
//标点
function test()
{   //var point="117.06874930912 40.242300670416,117.07558524674 40.15245691874,117.16005790457 40.151968637481,117.2074211867 40.171988169105,117.21621024937 40.224234263829,117.18398368626 40.285269421218,117.10683524733 40.296011608918";
    //DrawLSGJ(point);
    //alert(GetDzwl("北京市"));
	drawm();
  /*var popup = new OpenLayers.Popup("chicken", 
                     new OpenLayers.LonLat(116.41372, 39.90087),
                     new OpenLayers.Size(200,200),
                    "example popup",
                     true);      
  map.addPopup(popup);*/
  //clearHander();
  //map.events.on({"click":DrawMarkerHandler});
  //var nodes=polygonLayer.features[0].geometry.getVertices();
  //var flag=polygonLayer.features[0].geometry.containsPoint(new OpenLayers.Geometry.Point(116.41372, 39.90087));
}

//获取地图当前级别
function GetCurrentZoom()
{
	 return map.getZoom();
}
function getCenter()
{
	return map.getCenter();
}
//定位函数
function Location(x,y)
{
     var lonLat=new OpenLayers.LonLat(x,y);
     map.panTo(lonLat);
}
//设置中心点
function SetCenter(x,y,level)
{
	map.setCenter(new OpenLayers.LonLat(x, y), level);
}
//获取坐标位置回调函数
function GetPostionHandler(evt)
{
   var lonLat = map.getLonLatFromPixel(evt.xy);
   alert(lonLat);
}
//获取坐标位置回调函数
function GetPostionHandler1(evt)
{
   var lonLat = map.getLonLatFromPixel(evt.xy);
  return lonLat;
}

//标点回调函数
function DrawMarkerHandler(evt)
{
   var iconUrl="data/js/cmap/openlayer/img/marker.png";
   var lonLat = map.getLonLatFromPixel(evt.xy);
   Marker(lonLat,iconUrl);
}

//清除地图事件
function clearMap()
{  
   clearHander();
  // map.removeMarkers();
  // markers.clearMarkers();
}
//清除服刑人员标记
function ClearMarkers()
{
   markers.clearMarkers();	
}
//创建标记图层
function CreateMarkerLayers(layerName)
{
   var marker = new OpenLayers.Layer.Markers(layerName);	
   map.addLayers([marker]);
   return marker;
}
//清除标记图层
function ClearMarkersbylayer(marker)
{
   marker.clearMarkers();	
}
//初始化标绘电子围栏
function InitDrawDZWL()
{
	 drawDZWL = new OpenLayers.Control.DrawFeature( 
			 wfs, //wfs矢量图层服务 
			 OpenLayers.Handler.Polygon,//多边形绘图处理
		        {
		            title: "绘制电子监管围栏",
		            displayClass: "olControlDrawFeaturePolygon",
		            multi: true,
		            featureAdded:featureAddHander
		        }
		    );
		   map.addControl(drawDZWL);  
}
function GetStyle(color,width,opacity)
{
   var style= {
	        fillColor:color,
	        fillOpacity:(opacity!=null)?opacity:0.2,
	        hoverFillOpacity: 0.8,
	        strokeColor: color,
	        strokeOpacity: 0.8,
	        strokeWidth:(width)?width:2,
	        strokeLinecap: "round",
	        strokeDashstyle: "solid",
	        hoverStrokeColor: "red",
	        hoverStrokeOpacity: 1,
	        hoverStrokeWidth: 0.2,
	        pointRadius: 6,
	        hoverPointRadius: 1,
	        hoverPointUnit: "%",
	        pointerEvents: "visiblePainted",
	        cursor: "inherit",
	        fontColor: "#000000",
	        labelAlign: "cm",
	        labelOutlineColor: "white",
	        labelOutlineWidth: 5
   }
   return style;
}
//获取要素方法
function GetFeatureInfo(name,color,level)
{
      var protocol=new OpenLayers.Protocol.WFS({
		   		   version: "1.1.0",
                   srsName: "EPSG:4326",
                   url: baseUrl+"/geoserver/wfs",
                   featureType: "fxrywlsz",
           	       geometryName: "geom",
           		   filter: new OpenLayers.Filter.Logical({
                   type: OpenLayers.Filter.Logical.OR,
                    filters: [
                        new OpenLayers.Filter.Comparison({
                            type: OpenLayers.Filter.Comparison.EQUAL_TO,
                            property: "fxryid",
                            value: name
                        })
                    ]
                 })
                });
      protocol.read({
    	callback: function(resp) {
    		var features=resp.features;
    		unitlayer.removeAllFeatures();
    		if(features&&features[0]){
    			for(var i=0;i<features.length;i++){		
    				var id=parseInt(features[i].fid.split(".")[1]);
    				
    				if(id&&id<=18){
                    features[i].style=GetStyle(color);
                    var centerpoint=features[i].geometry.getCentroid();
                 if(level){
            	      SetCenter(centerpoint.x,centerpoint.y,level);
                  }
                else{
            	SetCenter(centerpoint.x,centerpoint.y,map.getZoom());
                }
                unitlayer.addFeatures([features[i]]);
    		}
    	    }
    	 }
     }
    	
    });
    
}
//地图放大
function ZoomIn()
{
	map.zoomIn();	
}
//地图缩小
function ZoomOut()
{
	map.zoomOut();	
}
//地图拉框放大
function ZoomBoxIn()
{
  zoomBoxInControl.activate();
}
//移动到某个坐标点
function PanTo(x,y)
{ 
  map.panTo(new OpenLayers.LonLat(x,y));	
}
//添加地图点击事件
function MapClick(handler)
{
   	map.events.on(
   	{
   	 "click":handler		
   	}		
   	);
}
//地图漫游
function MoveByPan()
{
  //zoomBoxInControl.deactivate();
  //panPanel.activate();
  
}
//获取位置信息
function GetPostion()
{
   clearHander();
   map.events.on({"click":GetPostionHandler});   
}
//标绘电子围栏
function DrawDZWL()
{      
	drawDZWL.activate();
}
function featureAddHander(feature)
{
	drawDZWL.deactivate();
    //var  layer=feature.layer;
    //var  p=layer.protocol; 
	 /*var protocol=new OpenLayers.Protocol.WFS({
         url: baseUrl+"/geoserver/wfs",
         featureType: "fxrywlsz",
         geometryName: "geom"
     });
     */
	var parms=OpenLayers.Util.getParameters(window.location.href);
   feature.attributes={
		   "fxryid":parms["fxryid"],
		   "geotype":parms["type"]
		   };
   var data=feature.layer.protocol.format.write([feature]);
   OpenLayers.Request.POST({
            url:baseUrl+"/geoserver/wfs",
            callback: createCallback,
            //headers: options.headers,
            data: data
        });
    
}
function ModifyDZWL()
{
	modifyDZWL.activate();
}
//添加回调函数
function createCallback(request)
{
 	var data = request.responseText;
        if(data.indexOf("SUCCESS")!=-1)
            {
        	 //alert("添加成功！");
        	 var parms=OpenLayers.Util.getParameters(window.location.href);
        	 GetFxryDZWL(parms["fxryid"],parms["type"],"blue");
            }

}
function MapClick(handler)
{
   map.events.on({
	 "click":handler  
   });	
}

//清除注册函数
function clearHander()
{;
   map.events.un({"click":DrawMarkerHandler});
   map.events.un({"click":GetPostionHandler});
   measureControls.polygon.deactivate();
   measureControls.line.deactivate();
   if(markers)
   markers.clearMarkers();
}
//标点
function Marker(lonLat,iconUrl)
{
	 var size = new OpenLayers.Size(50,50);
	 var icon = new CMapIcon(null, size,{x:-0,y:-0});
	 //icon.imageDiv.innerHTML="<div class='dwjk-box1 dwjk-dhs' ><div style='position:relative;bottom:12px;'><b><a href='/sqjz/data/dzjg/dwjk/dwjk.jsp?x=117.2596&y=40.2237&level=11&orgId=100028301'>黄松峪司法所</a></b></div><span>0</span><span>0</span><span>0</span></div>";
	 //markers.addMarker(new OpenLayers.Marker(lonLat,icon));
	 //var popupClass = OpenLayers.Popup.Anchored;
	 AutoSizeFramedCloudMinSize = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
         'autoSize': true
         //'minSize': new OpenLayers.Size(400,400)
     });
	  icon.imageDiv.innerHTML+="<div class='dwjk-box1 dwjk-dhs' onmouseover='testf(event);' ><div style='position:relative;bottom:12px;'><b><a href='/sqjz/data/dzjg/dwjk/dwjk.jsp?x=117.2596&y=40.2237&level=11&orgId=100028301'>黄松峪司法所</a></b></div><span>0</span><span>0</span><span>0</span></div>";
	 //alert(icon.imageDiv.innerHTML);
	 var popupClass = AutoSizeFramedCloudMinSize;
     var popupContentHTML = 'dddd';
	// addMarker(lonLat,icon,popupClass,popupContentHTML,true,true);
	var feature= addMarker(lonLat.lon,lonLat.lat,icon,popupContentHTML,true,true);
	//feature.marker.events.register("mousedown", feature, testf);
	 
}
function testf(evt)
{
   alert(this.data.popupContentHTML);	
}
//标点函数
//@x 经度
//@y 纬度
//@icon 标点图标
//@popupContentHTML 弹出内容
//@closeBox 是否有关闭按钮
//@overflow 内容超出是否有滚动
function addMarkerBylayer(x,y, icon,layer, popupContentHTML, closeBox, overflow) {
  var lonLat=new OpenLayers.LonLat(x,y);
  var marker=new OpenLayers.Marker(lonLat, icon, popupContentHTML);
  layer.addMarker(marker);
  return marker;
}
function  getPxFromLonLat(x,y){
	var lonLat=new OpenLayers.LonLat(x,y); 
	var px =map.getLayerPxFromLonLat(lonLat);
	return px;
}
//标点函数
//@x 经度
//@y 纬度
//@icon 标点图标
//@popupContentHTML 弹出内容
//@closeBox 是否有关闭按钮
//@overflow 内容超出是否有滚动
function addMarker(x,y, icon, popupContentHTML, closeBox, overflow) {
	AutoSizeFramedCloudMinSize = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
        'autoSize': true
        //'minSize': new OpenLayers.Size(400,400)
    });
	var lonLat=new OpenLayers.LonLat(x,y);
	var popupClass = AutoSizeFramedCloudMinSize;
    var feature = new OpenLayers.Feature(markers, lonLat); 
    feature.data.icon=icon;
    feature.closeBox = closeBox;          
    var marker = feature.createMarker();
    if(popupContentHTML){
    feature.popupClass = popupClass;
    feature.popupSize=new OpenLayers.Size(500,400);
    feature.data.popupContentHTML = popupContentHTML;
    feature.data.overflow = (overflow) ? "auto" : "hidden";
    var markerClick = function (evt) {
        if (this.popup == null) {
            this.popup = this.createPopup(this.closeBox);
            map.addPopup(this.popup);
            this.popup.show();
        } else {
            this.popup.toggle();
        }
        currentPopup = this.popup;
        OpenLayers.Event.stop(evt);
    };
    marker.events.register("mousedown", feature, markerClick);
}   
   markers.addMarker(marker);
   return marker;
}