<?php
include "../Conexao.php";

$idConsulta=$_POST['id'];


$sql_delete = "DELETE FROM Consulta WHERE idConsulta=:IDCONSULTA";

$stmt = $PDO-> prepare($sql_delete);

$stmt->bindParam(':IDCONSULTA',$idConsulta);

if($stmt -> execute())
{
    $dados = array("DELETE"=>"OK");
}
 else {
    $dados = array("DELETE"=>"ERRO");
 }
 
 echo json_encode($dados);

?>

