<?php

$cod=$_REQUEST['cod'];
$dis=$_REQUEST['dis'];
$est=$_REQUEST['est'];
$pas=$_REQUEST['pas'];

$cnx=new PDO("mysql:host=localhost;dbname=serviciosAndroidPHP","root","");
$res=$cnx->query("update alumnos set distrito='$dis', estadocivil='$est', "
        . "pasusu='$pas' where codalu='$cod'");


