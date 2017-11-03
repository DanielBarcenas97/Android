<?php

//recibiendo usuario y pas
$usu=$_REQUEST['usu'];
$pas=$_REQUEST['pas'];

$cnx=new PDO("mysql:host=localhost;dbname=serviciosAndroidPHP","root","");
$res=$cnx->query("select * from alumnos where codAlu='$usu' and pasUsu='$pas'");

$datos=array();

foreach ($res as $row){
    $datos[]=$row;
}

echo json_encode($datos);