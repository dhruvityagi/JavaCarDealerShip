<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Basic Car Choice</title>
</head>
<body>
<hl><font size="26"><b>Basic Car Selection</b></font></hl>
<hr/>
<form action="MyServelet" >
	<table border="1">
		<tr>
			<td>Make/Model</td>
			<td> 
				<select name="makeModel">
					<option></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Color</td>
			<td> 
				<select name="Color">
					<option></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Transmission</td>
			<td> 
				<select name="Transmission">
					<option></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Brakes/Traction Control</td>
			<td> 
				<select name="brakes">
					<option></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Side Impact Air Bags</td>
			<td> 
				<select name="AirBags">
					<option></option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Power Moonroof</td>
			<td> 
				<select name="moonroof">
					<option></option>
				</select>
			</td>
		</tr>
	</table>  
	<input type="submit" value="Done" />
</form>

</body>
</html>