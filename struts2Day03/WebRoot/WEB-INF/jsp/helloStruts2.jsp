<%@page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
</head>
<body>
<s:debug></s:debug>
<h1>OGNL显示基本属性</h1>
<s:property value="name"/>
从Map中取<s:property value="#action.name"/>
<h1>OGNL显示实体对象属性</h1>
<s:property value="user.Name"/>
<s:property value="user.password"/>
从Map中取<s:property value="#action.user.name"/>
<h1>OGNL创建集合</h1>
<s:property value="{name,user.name}" />
<s:property value="{name,user.name}.size()" />
<h1>OGNL创建Map</h1>
<s:property value="#{name:'hhhh'}.get(name)" />
<h1>迭代集合</h1>
<s:iterator value="users">
<s:property value="name"/>
</s:iterator>
</body>
</html>