<?php

$alu=$_REQUEST['alu'];

$cnx=new PDO("mysql:host=localhost;dbname=serviciosAndroidPHP","root","");

$res=$cnx->query("select * from alumnos where codalu='$alu'");

$datos=array();

foreach ($res as $row){
    $datos[]=$row;
}

echo json_encode($datos);
