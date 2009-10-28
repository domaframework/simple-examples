<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<html:errors/>

<s:form>

<table class="tablebg">
	<tr>
		<td> empNo </td>
		<td>
			<html:text property="empNo" />
		</td>	
	</tr>
	<tr>
		<td> empName </td>
		<td>
			<html:text property="empName" />
		</td>	
	</tr>
	<tr>
		<td> mgrId </td>
		<td>
			<html:text property="mgrId" />
		</td>	
	</tr>
	<tr>
		<td> hiredate </td>
		<td>
			<html:text property="hiredate" />
		</td>	
	</tr>
	<tr>
		<td> sal </td>
		<td>
			<html:text property="sal" />
		</td>	
	</tr>
	<tr>
		<td> deptId </td>
		<td>
			<html:text property="deptId" />
		</td>	
	</tr>

</table>

<input type="submit" name="insert" value="CREATE" />
</s:form>
<br/><br/>

<s:link href="/emp/">list page</s:link>

</body>
</html>