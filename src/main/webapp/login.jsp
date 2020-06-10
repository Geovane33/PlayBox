<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>NOTESTORE - Login</title>
        <meta charset="UTF-8">
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Open+Sans:600'><link rel="stylesheet" href="./login.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script  src="jqueryForm.js"></script> 
        <script  src="login.js"></script>
    </head>
    <body>
        <div class="login-wrap">
            <div class="login-html">
                <label class="tab">NOTESTORE</label>
                <div class="login-form">
                    <form action="login" method="POST">
                        <div>
                            <div class="group">
                                <label for="user"  class="label">Usuário</label>
                                <input id="user" name="usuario" type="text" required class="input">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Senha</label>
                                <input id="pass" onfocus="msgPassword()" name="senha" type="password" required class="input" data-type="password">
                            </div>
                            <div class="group">
                                <input class="button" value="Logar" type="submit" name="Logar">
                            </div>
                            <div class="message">
                            </div>
                        </div>
                    </form>
                    <label class="label">Geovane Araujo</label><br>
                    <label class="label">Jessica Gomes</label><br>
                    <label class="label">Jonathan Miquelino</label><br>
                    <label class="label">Welligton Evangelista</label>
                </div>
            </div>
        </div>
    </body>
</html>

