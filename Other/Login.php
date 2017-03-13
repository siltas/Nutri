<?php
	$con = mysqli_connect("localhost", "id974431_nutri", "123456", "id974431_example");
	
	$username = $_POST["username"];
	$email = $_POST["email"];
	$password = $_POST["password"];
	
	$statement = mysqli_prepare($con, "SELECT * FROM User WHERE ((username = ? AND password = ?) OR (email = ? AND password = ?)) ");
	mysqli_stmt_bind_param($statement, "ss", $username, $password);
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_store_result($statement);
	mysqli_bind_result($statement, $userID, $First, $Last, $Birthdate, $email, $Username, $password);
	
	$response = array();
	$response["success"] = false;
	
	while(mysqli_stmt_fetch($statement))
	{
			$response["success"] = true;
			$response["First"] = $First;
			$response["Last"] = $Last;
			$response["Birthdate"] = $Birthdate;
			$response["email"] = $email;
			$response["Username"] = $username;
			$response["password"] = $password;
	}
	
	echo json_encode($response);
?>
