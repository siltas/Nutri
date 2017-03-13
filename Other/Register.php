<?php
	$con = mysqli_connect("localhost", "id974431_nutri", "123456", "id974431_example");
	$First = $_POST["First"];
	$Last = $_POST["Last"];
	$Birthdate = $_POST["Birthdate"];
	$email = $_POST["email"];
	$username = $_POST["username"];
	$password = $_POST["password"];
	
	$statement = mysqli_prepare($con, "INSERT INTO User (First, Last, Birthdate, email, username, password) VALUES(?, ?, ?, ?, ?, ?)");
	mysqli_stmt_bind_param($statement, "siss", $First, $Last, $Birthdate, $email, $username, $password);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"] = true;
	
	echo json_encode($response);
	?>
	
