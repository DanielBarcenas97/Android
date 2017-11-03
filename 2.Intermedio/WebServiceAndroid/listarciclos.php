<?php

$alu=$_REQUEST['alu'];

$cnx=new PDO("mysql:host=localhost;dbname=serviciosAndroidPHP","root","");
$res=$cnx->query("select r.codAlu, r.ciclo,c.descripcion from registro r "
        . "inner join ciclo c on c.ciclo=r.ciclo where codAlu='$alu' "
        . "group by ciclo");

$datos=array();

foreach ($res as $row){
    $datos[]=$row;
}

echo json_encode($datos);
