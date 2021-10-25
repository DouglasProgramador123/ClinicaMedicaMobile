<?php
include "../Conexao.php";

session_start();
$idPaciente=$_SESSION['idPaciente'];

$sql_read="SELECT * FROM paciente WHERE idPaciente=:IDPACIENTE ";

$dados = $PDO->prepare($sql_read);

$dados -> bindParam(':IDPACIENTE',$idPaciente);

$dados->execute();

$resultado = array();

while($c = $dados->fetch(PDO::FETCH_OBJ))
{
 $resultado[]=array("idPaciente"=>$c->idPaciente,"login"=>$c->login,"senha"=>$c->senha,"nome"=>$c->nome,"cpf"=>$c->cpf,"tel"=>$c->tel,"end"=>$c->end,"cep"=>$c->cep,"email"=>$c->email);
    

}

echo json_encode($resultado);

?>

