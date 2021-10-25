<?php
include "../Conexao.php";

session_start();
$idPaciente=$_SESSION['idPaciente'];

$login=$_POST['login'];
$senha=$_POST['senha'];
$nome=$_POST['nome'];
$cpf=$_POST['cpf'];
$tel=$_POST['tel'];
$end=$_POST['end'];
$cep=$_POST['cep'];
$email=$_POST['email'];


$sql_insert = "UPDATE paciente SET login=:LOGIN,senha=:SENHA,nome=:NOME,cpf=:CPF,tel=:TEL,end=:END,cep=:CEP,email=:EMAIL WHERE idPaciente=:IDPACIENTE";

$stmt = $PDO->prepare($sql_insert);


$stmt -> bindParam(':LOGIN',$login);
$stmt -> bindParam(':SENHA',$senha);
$stmt -> bindParam(':NOME',$nome);
$stmt -> bindParam(':CPF',$cpf);
$stmt -> bindParam(':TEL',$tel);
$stmt -> bindParam(':END',$end);
$stmt -> bindParam(':CEP',$cep);
$stmt -> bindParam(':EMAIL',$email);
$stmt -> bindParam(':IDPACIENTE',$idPaciente);

if($stmt ->execute())
{
     $dados = array("UPDATE"=>"OK");
    
} else {
     $dados=array("UPDATE"=>"ERRO");
}

echo json_encode($dados);
?>
