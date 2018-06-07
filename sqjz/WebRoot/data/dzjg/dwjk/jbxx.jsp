<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>基本信息</title>
		<%@ include file="/common/commonjsTemp.jsp"%>
		<script type="text/javascript">
			$(function(){
				$.ajax({
					url:'${ctx}/data/fxry/getFxryCrimeInfo.action',
					data:'id=<%=request.getParameter("id")%>',
					success:function(data){
						//alert(eval("("+data+")"));
						$("#name").text(data.name);
						$("#sex").text($.dictionary.formatter('XB')(data.sex));
						$("#idcard").text(data.idcard==null?"":data.idcard);
						$("#phoneNumber").text(data.phoneNumber==null?"":data.phoneNumber);
						var address="";
						if(data.houseAddress!=null && data.houseAddress.fulltext!=null){address=data.houseAddress.fulltext};
						$("#houseAddress").text(address);
						$("#adjustType").text($.dictionary.formatter('JZLB')(data.adjustType));
						$("#adjustPeriod").text(data.adjustPeriod==null?"":data.adjustPeriod);
						$("#crimeType").text($.dictionary.formatter('FZLX')(data.crimeType));
						$("#dateSAdjust").text(data.dateSAdjust==null?"":data.dateSAdjust.substring(0,data.dateSAdjust.indexOf("T")));
						$("#dateEAdjust").text(data.dateEAdjust==null?"":data.dateEAdjust.substring(0,data.dateEAdjust.indexOf("T")));
						$.fields.photo("#picture",{readonly:true, defValue:data.picture});
					}
				});
			});
		</script>
		<style type="text/css">
			.comm-table{ width:100%;}
			.comm-table th,.comm-table td{ height:40px; border:1px solid #d9d9d9;}
			.comm-table th{ background:#fbfbfb;white-space: nowrap;
				min-width: 80px; text-align:right; padding-right:10px;}
			.comm-table td{ background:#fff;min-width: 110px; }
			.comm-table td input[type="text"]{ margin-top:8px; margin-left:3px; width:140px; height:20px; border:1px solid #c5c2c2; background:#fefce8; }
			.comm-table td select{ width:155px;margin-top:8px; margin-left:3px; }
			.comm-table td textarea{ margin:10px;width:90%; height:100px; border:1px solid #c5c2c2; background:#fefce8; }
			.img-box{ background:url(../images/icon5.png) no-repeat; width:124px; height:156px; margin:0 auto; cursor:pointer;}
			.footer-fixed{position:fixed; bottom:0px;}
			.add-icon{ background:url(../images/add-row.gif) no-repeat; width:16px; height:16px; display:block; margin:0 auto;}
			.delete-icon{ background:url(../images/delete-row.gif) no-repeat; width:16px; height:16px; display:block; margin:0 auto;}
			.comm-table td input[type="checkbox"]{ float:left; margin-right:2px; margin-bottom:3px; margin-left:10px;}
			.comm-table td label{ float:left; display:inline-block}
			.comm-table td input[class="organiz"]{ margin-top:5px; margin-left:3px; margin-bottom:5px; height:20px; border:1px solid #c5c2c2; background:#fefce8; }
		</style>
	</head>

	<body>
		<table class="comm-table">
      	<tbody>
          	<tr>
            	  <th  >姓名：</th>
                <td>
                		<span id="name"></span>
                </td>
                <th  >性别：</th>
                <td>
                		<span id="sex"></span>
                </td>
                <td rowspan="5" id="picture">
                </td>
            </tr>
            <tr>
            	  <th  >身份证号：</th>
                <td>
                		<span id="idcard"></span>
                </td>
                <th>联系电话：</th>
                <td>
                		<span id="phoneNumber"></span>
                </td>
            </tr>
            <tr>
            	  <th >居住地详细地址：</th>
                <td colspan="3">
                		<span id="houseAddress"></span>
                </td>
            </tr>
            <tr>
            	  <th >矫正类别：</th>
                <td>
                		<span id="adjustType"></span>
                </td>
                <th >矫正期限：</th>
                <td>
                		<span id="adjustPeriod"></span>
                </td>
            </tr>
            <tr>
            	  <th >矫正起止日：</th>
                <td>
                		<span id="dateSAdjust"></span>至<span id="dateEAdjust"></span>
                </td>
                <th >犯罪类型：</th>
                <td>
                		<span id="crimeType"></span>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
