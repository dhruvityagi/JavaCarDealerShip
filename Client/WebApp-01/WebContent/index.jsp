<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car Config Site</title>
</head>
<body>
<hl><font size="18"><b>Select whether you want to select a car or upload a car configuration</b></font></hl>
<hr/>
<form method="post" action="MyServelet" >
	<table border="1">
		<tr>
			<td>Select</td>
			<td> 
				<select name="CustomerChoice">
					<option>Select a car</option>
					<option>Upload a car configuration</option>
				</select>
			</td>
		</tr>
	</table>  
	<INPUT TYPE=hidden NAME="inital" VALUE="yes">
	<input type="submit" value="Done" />
</form>

</body>
</html>