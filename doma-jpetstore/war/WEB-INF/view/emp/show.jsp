<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<html:errors/>

<table class="tablebg">
	<tr>
		<td> empNo </td>
		<td>
			${f:h(empNo)}
		</td>	
	</tr>
	<tr>
		<td> empName </td>
		<td>
			${f:h(empName)}
		</td>	
	</tr>
	<tr>
		<td> mgrId </td>
		<td>
			${f:h(mgrId)}
		</td>	
	</tr>
	<tr>
		<td> hiredate </td>
		<td>
			${f:h(hiredate)}
		</td>	
	</tr>
	<tr>
		<td> sal </td>
		<td>
			${f:h(sal)}
		</td>	
	</tr>
	<tr>
		<td> deptId </td>
		<td>
			${f:h(deptId)}
		</td>	
	</tr>

</table>

<s:link href="edit/${id}"> edit </s:link>


<br/><br/>
<s:link href="/emp/">list page</s:link>
</body>
</html>