<?php

	$lectura = $_POST['lectura'];
	$medidor = $_POST['medidor'];

	$resta =$medidor -$lectura;

       $total = $resta * 500;
		



    $servername = "localhost";
    $database = "demo";
    $username = "root";
    $password = "";
    $enlace = mysqli_connect($servername, $username, $password, $database);

	$query = mysqli_query($enlace, "INSERT INTO lecturas (lectura, medidor, total) VALUES ($lectura, $medidor, $total)");

    mysqli_close($enlace);

	echo "DATOS: ". $lectura . "--". $medidor . "SOY EL PHP";

?>
