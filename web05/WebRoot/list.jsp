<%@ page import="dao.*,java.util.* ,entity.*" pageEncoding="utf-8"%>
<html>
<style>
	#table{
	border-collapse:collapse;
	border:2px solid red;
	}
	.row1{
	background-color:yellow
	}
	.row2{
	background-color:blue
	}
</style>
<head>
<title>
</title>
</head>
<body>
<table id="table" border="1px" width="40%">
<caption>员工列表</caption>
<tr><th>姓名</th><th>薪水</th><th>年龄</th></tr>
<%
	EmpDao empdao=new EmpDao();
	List<Emp> emplist =empdao.findall();
	for(int i=0;i<emplist.size();i++){
		Emp e=emplist.get(i);
		%>
		<tr class="row<%=i%2+1 %>"><td><%=e.getName() %></td><td><%=e.getSalary() %></td><td><%=e.getAge() %></td></tr>
		<%
	}
 %>
</table>
</body>
</html>