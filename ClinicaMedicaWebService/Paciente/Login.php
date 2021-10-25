<?php
include "../Conexao.php";
 
$login=$_POST['login'];
$senha=$_POST['senha'];

$sql_login="SELECT idPaciente FROM paciente WHERE login=:LOGIN AND senha=:SENHA ";



$dados= $PDO->prepare($sql_login);
$dados->bindParam(':LOGIN',$login);
$dados->bindParam(':SENHA',$senha);

session_start();

$dados->execute();



if($dados->rowCount()>0)
{
    $id=$dados->fetch();
    $_SESSION['idPaciente']=$id['idPaciente'];
    
    $retornoApp = array("LOGIN"=>"SUCESSO");
}else {
      $retornoApp = array("LOGIN"=>"ERRO");
}



echo json_encode($retornoApp);

?>

