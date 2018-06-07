<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>移动执法终端信息管理</title>
		<%@ include file="/common/commonjsTemp.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#equtype").text($.dictionary.formatter('SBXH')($("#equtype").text()));
		$("#status").text($.dictionary.formatter('SBZT')($("#status").text()));
		$("#MOrgId").text($.organization.formatter()($("#MOrgId").text()));
	});
</script>

		<style>
		.comm-table{ width:100%;}
		.comm-table th,.comm-table td{ height:40px; border:1px solid #d9d9d9;}
		.comm-table th{ background:#fbfbfb;white-space: nowrap;
			min-width: 80px; text-align:right; padding-right:10px;}
		.comm-table td{ background:#fff;min-width: 110px; }
		.comm-table td input[type="text"]{ margin-top:8px; margin-left:3px; width:140px; height:20px; border:1px solid #c5c2c2; background:#fefce8; }
		.comm-table td select{ width:155px;margin-top:8px; margin-left:3px; }
		.comm-table td textarea{ margin:10px;width:90%; height:100px; border:1px solid #c5c2c2; background:#fefce8; }
		</style>
		</head>
<body>

		<table class="comm-table">
      	<tbody>
          	<!-- <tr>
              	<th>设备型号：</th>
                  <td id="equtype">${lawEn.equtype}</td>
                  <th>设备编号：</th>
                  <td>${lawEn.equnumber }</td>
              </tr> -->
              <tr>
              	<th>购进日期：</th>
                  <td>
                  		${lawEn.procureDate }
                  </td>
                  <th>设备状态：</th>
                  <td id="status">${lawEn.equstatus}</td>
              </tr>
              <tr>
              	<th>领用日期：</th>
                  <td>
                  		${lawEn.useTime }
                  </td>
                  <th>领用单位：</th>
                  <td>${lawEn.orgId }</td>
              </tr>
               <tr>
              	
                  <th>管理单位：</th>
                  <td colspan="3" id="MOrgId">${lawEn.MOrgId }</td>
              </tr>
        </tbody>
    </table>
</body>
</html>
