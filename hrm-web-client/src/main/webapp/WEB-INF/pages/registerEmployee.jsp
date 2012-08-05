<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: Web service client</title>

<style>
.error {
	font-size:12px;
	color: #ff0000;
}
</style>

</head>
<body>

	<h4>Register Employee</h4>

	<form:form method="POST" commandName="employee">
		<table style="border: none;">
			<tr>
				<td>First Name</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td align="right"><input type="submit" value="Register" id="btn_register"></td>
				<td><input type="reset" value="Reset" id="btn_reset"></td>
				<td></td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>