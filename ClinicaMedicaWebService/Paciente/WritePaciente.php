<?php
include "../Conexao.php";

$login=$_POST['login'];
$senha=$_POST['senha'];
$nome=$_POST['nome'];
$cpf=$_POST['cpf'];
$tel=$_POST['tel'];
$end=$_POST['end'];
$cep=$_POST['cep'];
$email=$_POST['email'];


$sql_insert = "INSERT INTO paciente(login,senha,nome,cpf,tel,end,cep,email)VALUES(:LOGIN,:SENHA,:NOME,:CPF,:TEL,:END,:CEP,:EMAIL)";

$stmt = $PDO->prepare($sql_insert);

$stmt -> bindParam(':LOGIN',$login);
$stmt -> bindParam(':SENHA',$senha);
$stmt -> bindParam(':NOME',$nome);
$stmt -> bindParam(':CPF',$cpf);
$stmt -> bindParam(':TEL',$tel);
$stmt -> bindParam(':END',$end);
$stmt -> bindParam(':CEP',$cep);
$stmt -> bindParam(':EMAIL',$email);

if($stmt ->execute())
{
   $id = $PDO ->lastInsertId(); 
   $dados=array("CREATE"=>"OK","ID"=>$id);
    
} else {
     $dados=array("CREATE"=>"ERRO");
}

echo json_encode($dados);
?>
