<?php
include "../Conexao.php";

session_start();
$idPaciente=$_SESSION['idPaciente'];
$consultaData=$_POST['consultaData'];
$consultaDataMarcada=$_POST['consultaDataMarcada'];
$consultaTipo=$_POST['consultaTipo'];

$sql_insert="INSERT INTO consulta(consultaDataMarcada,consultaTipo,consultaData,pacienteConsulta)VALUES(:CONSULTADATAMARCADA,:CONSULTATIPO,:CONSULTADATA,:IDPACIENTE) ";

$stmt = $PDO->prepare($sql_insert);

$stmt -> bindParam(':CONSULTADATA',$consultaData);
$stmt -> bindParam(':CONSULTADATAMARCADA',$consultaDataMarcada);
$stmt -> bindParam(':CONSULTATIPO',$consultaTipo);
$stmt -> bindParam(':IDPACIENTE',$idPaciente);

if($stmt ->execute())
{
   $id = $PDO ->lastInsertId(); 
   $dados=array("CREATE"=>"OK","ID"=>$id);
    
} else {
     $dados=array("CREATE"=>"ERRO");
}



echo json_encode($dados);

?>

