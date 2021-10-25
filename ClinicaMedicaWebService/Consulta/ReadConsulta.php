<?php
include "../Conexao.php";

session_start();
$idPaciente=$_SESSION['idPaciente'];

$sql_read="SELECT * FROM consulta WHERE pacienteConsulta=:IDPACIENTE ";

$dados = $PDO->prepare($sql_read);

$dados -> bindParam(':IDPACIENTE',$idPaciente);

$dados->execute();

$resultado = array();

while($c = $dados->fetch(PDO::FETCH_OBJ))
{
 $resultado[]=array("idConsulta"=>$c->idConsulta,"consultaData"=>$c->consultaData,"consultaDataMarcada"=>$c->consultaDataMarcada,"consultaTipo"=>$c->consultaTipo);
    

}

echo json_encode($resultado);

?>

