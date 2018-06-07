<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ include file="common/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HEAD>
<meta http-equiv="Content-Type" content="text/html"; charset="utf-8">
<TITLE>Test OA99+ OCX</TITLE>
<script src="js/jquery-1.6.2.js" type="text/javascript"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>指纹采集</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<SCRIPT language=javascript type=text/javascript>
        var zwcj_cap = "5";
        var zwcj_qam = "55";
        var zwcj_score = "0.97";
        var zwcj_verify = "0.68";
        var api;
        var fxryId;
        function ini()
        {
        	//$($(this).attr("iframe").contentDocument.getElementById
        	api = frameElement.api;
        	fxryId = api.data.fxryId;
            var fpo = document.all.fp_obj;
            fpo.AxBorderStyle = 0;
            fpo.P_compresstype = 0;
            fpo.P_capset = zwcj_cap;
            fpo.P_qamset = zwcj_qam;
            fpo.P_scoreset = zwcj_score;
            fpo.P_verifyset = zwcj_verify;
            //fpo.Cap_init();
        }
        function ok()
        {
            var fpo = document.all.fp_obj;
            fpo.AxBorderStyle = 0;
            fpo.P_compresstype = 0;
            fpo.P_capset = document.all.zwcj_cap.value;
            fpo.P_qamset =document.all.zwcj_qam.value;
            fpo.P_scoreset = document.all.zwcj_score.value;
            fpo.P_verifyset = document.all.zwcj_verify.value;
            document.all.zw1_tx.value="";
            document.all.zw1_tz.value="";
            document.all.zw1_zw.value="";
            document.all.zw1_zcjg.value="";
 
            document.all.zw2_tx.value="";
            document.all.zw2_tz.value="";
            document.all.zw2_zw.value="";
            document.all.zw2_zcjg.value="";
 
            document.all.zwcjqid.value="";
            document.all.ycdm1.value="";
            document.all.ycdm2.value="";
            document.all.ycdm3.value="";
        }
        function tq(){
        	var fpo = document.all.fp_obj;
        	if(fpo.s_leftmzcjg==1||fpo.s_leftszcjg==1||fpo.s_leftzzcjg==1||fpo.s_lefthzcjg==1||fpo.s_leftxzcjg==1
        			||fpo.s_rightmzcjg==1||fpo.s_rightszcjg==1||fpo.s_rightzzcjg==1||fpo.s_righthzcjg==1||fpo.s_rightxzcjg==1)
        	{
	        	$.ajax({
	        		type: "POST",
	        	    async:false,
	        	    url: "data/zwy/collectPrint2.action",
	        	    data: "fxryId="+fxryId+"&left1="+encodeURIComponent(fpo.P_leftcompresszwm)+"&left2="+encodeURIComponent(fpo.P_leftcompresszws)+"&left3="+encodeURIComponent(fpo.P_leftcompresszwz)+"&left4="+encodeURIComponent(fpo.P_leftcompresszwh)+"&left5="+encodeURIComponent(fpo.P_leftcompresszwx)
	        	    +"&right1="+encodeURIComponent(fpo.P_rightcompresszwm)+"&right2="+encodeURIComponent(fpo.P_rightcompresszws)+"&right3="+encodeURIComponent(fpo.P_rightcompresszwz)+"&right4="+encodeURIComponent(fpo.P_rightcompresszwh)+"&right5="+encodeURIComponent(fpo.P_rightcompresszwx)
	        	    +"&mleft1="+encodeURIComponent(fpo.P_leftzwm)+"&mleft2="+encodeURIComponent(fpo.P_leftzws)+"&mleft3="+encodeURIComponent(+fpo.P_leftzwz)+"&mleft4="+encodeURIComponent(fpo.P_leftzwh)+"&mleft5="+encodeURIComponent(fpo.P_leftzwx)
	        	    +"&mright1="+encodeURIComponent(fpo.P_rightzwm)+"&mright2="+encodeURIComponent(fpo.P_rightzws)+"&mright3="+encodeURIComponent(fpo.P_rightzwz)+"&mright4="+encodeURIComponent(fpo.P_rightzwh)+"&mright5="+encodeURIComponent(fpo.P_rightzwx),
	        	    
	        		success:function(data){
	        			api.opener.document.getElementById("fingerPrintNo").value=data;
	        		}
	            });
			}
        }
    </SCRIPT>
 
<META name=GENERATOR content="MSHTML 9.00.8112.16450"></HEAD>
<BODY onload="ini()" onbeforeunload="tq()">
      <OBJECT id=fp_obj border=0 
      classid=clsid:3BE765B4-14B4-40A6-B207-39DEEBEA6634 width=800 
      height=250></OBJECT>
  </BODY>
  </HTML>

