<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<html:errors/>


<table border="1">
<tr style="background-color:pink">

	<th>empNo</th>
	<th>empName</th>
	<th>mgrId</th>
	<th>hiredate</th>
	<th>sal</th>
	<th>deptId</th>
<th></th><th></th><th></th>
</tr>

<c:forEach var="e" varStatus="s" items="${empItems}">
	<tr style="background-color:${s.index %2 == 0 ? 'white' : 'aqua'}">
		<td>
			${f:h(e.empNo)}
		</td>
		<td>
			${f:h(e.empName)}
		</td>
		<td>
			${f:h(e.mgrId)}
		</td>
		<td>
			${f:h(e.hiredate)}
		</td>
		<td>
			${f:h(e.sal)}
		</td>
		<td>
			${f:h(e.deptId)}
		</td>
		<td><s:link href="show/${e.id}"> show </s:link></td>
		<td><s:link href="edit/${e.id}"> edit </s:link></td>
		<td><s:link onclick="return confirm('delete OK?');" href="delete/${e.id}/${e.versionNo}">delete</s:link></td>
	</tr>
</c:forEach>

</table>

<s:link href="create"> create new Object </s:link>
</body>
</html>