<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Basic Car Choice</title>
</head>
<body>
<hl><font size="26"><b>Select Car Model</b></font></hl>
<hr/>
<form action="configureCar" >
	<table border="1">
		<tr>
			<td>Make/Model</td>
			<td> 
				<select name="model">
					<option value="1"></option>
					<option value="2"></option>
				</select>
			</td>
		</tr>
	</table>  
	<input type="submit" value="Done" />
</form>

</body>
</html>