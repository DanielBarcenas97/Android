<?php

$alu=$_REQUEST['alu'];
$ciclo=$_REQUEST['ciclo'];

$cnx=new PDO("mysql:host=localhost;dbname=serviciosAndroidPHP","root","");

$res=$cnx->query("select r.codcurso,c.Descripcion,r.promedio,cl.descripcion "
        . "from(registro r inner join cursos c on r.codcurso=c.codcurso) "
        . "inner join ciclo cl on r.ciclo=cl.ciclo where r.codalu='$alu' "
        . "and cl.descripcion='$ciclo'");

$datos=array();

foreach ($res as $row){
    $datos[]=$row;
}

echo json_encode($datos);