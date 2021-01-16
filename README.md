# ProjetoPagamento
projeto integrador

https://projeto-pag-api.herokuapp.com/swagger-ui.html

**Características**

API REST Spring Boot REST API. O serviço de pagamento possui um serviço que os demais módulos devem consumir. Nesse serviço recebe um objeto no formato JSON que contem as informações, como, a forma de pagamento(Cartão ou Boleto), o email do usuário que deseja realizar o pagamento, valor, quantidade, tipo pagamento e enviar o token no header da requisição.

**Descrição**

**1 .** O cliente que deseja enviar uma requisição para o serviço, deve informar um objeto no formato JSON, se esse objeto não estiver de acordo com as especificações da regra de negócio, o Serviço retornará ao status 400. Ex: Não passar o header na requisição.

**2 .** O cliente deverá enviar um email do usuário. Caso o email não seja encontrado na base de dados, o Serviço retornará a um status 404.

**3 .** Caso o cliente envie uma requisição com o token inválido, o serviço deverá retornar um status 403.

**4 .** Caso a forma de pagamento seja cartão o cliente(os módulos) deverá envir um objeto que contenha as informações tais como nome, número, cvv, quantidade de parcelas, mês e ano. Após salvar, o serviço deve retornar a um status 202 e o objeto salvo.



**link para acesso a documentação da api:**

 https://api-projetopagamento.herokuapp.com/swagger-ui.html

**link para acesso ao sistema web:** 

https://api-projetopagamento.herokuapp.com/


**Como Funciona utilizando o Postman**

**Salvando um usuário utilizando o Postman**

Para salvar um usuário utilizando o postman, selecionamos o método POST e utilizamos a opção Body raw, passamos os dados tais como nome, email senha.  Estrutura abaixo.

![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/LoGINPOSTMMANNN.png) 

Se os dados forem informados corretamente o status é igual a 200 retornando um token como resposta.

Se os dados forem passados incorretos status 404, com isso tem-se que deu um erro ao fazer login.


**Login utilizando Postman**

Para fazer login utilizamos o método POST, e a opção body raw e informamos os dados necessários para login, email e senha. 

![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/LOGINPOST.png)


Se os dados forem informados corretamente o status é igual a 200 retornando um token como resposta.

Se os dados forem passados incorretos status 404, com isso tem-se que deu um erro ao fazer login.

**Formas de pagamento utilizando o Postman**

Para fazer um pagamento no postman, utilizamos o método POST, precisamos passar algumas informações para ser possível realizar o pagamento, tanto em pagamento boleto como em cartão no Headers precisamos passar a authentication que é a palavra Bearer mais token gerado, e a origem de onde vem a requisição. Exemplo da imagem abaixo. 

![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/COMPRACARTAO(1).png)


Logo em seguida, no mesmo método escolhido POST, para realizar a compra vai na opção Body ray e informa os dados para o pagamento. Se o usuário escolher cartão deve passar as seguintes informações do modelo do exemplo abaixo, mudando as informações para qual ele deseja. 

![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/COMPRACARTAOPOST.png)

Se o usuário escolher boleto deve ter a mesma configuração no header e ficar do seguinte modelo. 

![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/COMPRABOLETO.png)

**Todos os pagamentos realizados pelo Usuário**

O usuário poderá ver as transações de todos os seus pagamentos realizados e qual forma de pagamento escolhido em todos os seus pagamentos realizados, e o status, através do método GET, passando apenas o e-mail e o token gerado no login. segue o modelo: 

![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/DETALHESDETODASCOMPRAS.png)




**Como Funciona na documentação Swagger**

Api disponibilizada  para os demais módulos que irão consumir do módulo pagamento.

![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/APII.PNG)

**usuario-json: Usuario Json**,   para cadastrar um novo usuário, usuário acessar o sistema com email e token, e detalhes do usuário pelo email.

**pagamento-json :  Pagamento Json** ,  para retornar todos os pagamentos feitos pelo usuário, retornar o pagamento pelo id do pagamento e salvar compra escolhendo uma das formas de pagamento Boleto ou Cartão.

Em **usuario-json : Usuario Json** 

**POST  /api/usuarios/save**              Criando um novo usuário

**POST  /api/usuarios/login**             Usuario faz login

O usuário poderá ter acesso a seus dados pessoais passando seu email

**GET /api/usuarios/findByEmail/{email}**        Detalhes do usuário pelo email

Funcionamento

O usuário poderá criar um novo usuário , se cadastrar no banco.  Passando as credenciais de nome, email, senha.
![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/CRIANDO.PNG)


O usuário poderá logar no sistema passando seu email e senha passados para fazer seu cadastro.
![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/DADOSLOGIN.PNG)

Ao fazer login  se as credenciais forem enviadas corretamente ele gerará um token para a autenticação do usuário para ter acesso aos demais serviços do sistema. 
![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/DADOSLOGIN.PNG)

Se as credenciais forem passadas corretamente,retornará  a resposta 200 com o token gerado.

Se as credenciais forem passadas incorretas retornará a resposta de erro 404.

Com o usuário autenticado  o token é gerado e o usuário poderá fazer uma compra em **pagamento-json : Pagamento Json**, para salvar uma pagamento o usuário precisa obrigatoriamente passar a origin o destino de onde está vindo a requisição e a autenticação que foi gerada ao fazer login, e em seguida informar os dados para fazer o pagamento.

**Exemplo para passar dados de um pagamento pelo Boleto**

POST /api/compras/saveCompra Salva uma compra no cartão ou boleto 
![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/PAGBOLETO.PNG)

**Exemplo para passar dados de um pagamento pelo Cartao**

POST /api/compras/saveCompra Salva uma compra no cartão ou boleto 
![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/PAGCARTAO.PNG)


*Importante lembrar que na autenticação sempre será passada a palavra Bearer + o token gerado para ter acesso ao salvar compra ou outras funcionalidades do sistema.


O usuário poderá ver os detalhes de todos os seus pagamentos realizados, passando o email e o token gerado.

GET /api/compras/detalhes/{email} Retorna todos os Pagamentos do Usuário documentacao
![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/detalhes%20por%20email.PNG)

Se os dados passados incorretos retorna o erro 404.

O usuário poderá verificar seu pagamento relizado pelo id, caso deseje. para isso pegará o id que é gerado após fazer seu pagamento.

GET /api/compras/detalhesCompra/{id} Retorna os Pagamentos pelo id 
![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/BUSCAPAGID.PNG)


**Exemplo escrito de como passar as credenciais na Api com os dados passados pelo usuário**

**Criar novo usuário**

{
     "nome" : " ",

     "email" : " ",

     "senha" : " "

}




**Login**

 {

  "email":" ",

  "senha":" "

 }



**Escolher Forma de Pagamento: Boleto**



**origin**     http://localhost:8080/

**Pagamento** 

                              {
                                  
                                 "cartao":null,  
                                 "boleto":{
                                 "numeroBoleto":" ",
 	                         "dataCompra":" ",
 	                         "dataVencimento":" "
                                  },**
	                         "valor":" ",
	                         "quantidade":" ",
 	                         "tipoPagamento":"BOLETO",
	                         "usuario":{
    	                         "email":" "
	                          }
                             }


**Authorization** Bearer  + token gerado.



**Escolher Forma de Pagamento: Cartão**


**origin**  http://localhost:8080/

**Pagamento** 

                {
                  "boleto":null,
                  "cartao":{
    	          "nome":" ",
               	  "numero": " ",
            	  "cvv": " ",
     	          "mes":" ",
     	          "ano":" ",
     	          "qtd_parcelas":" "
 	          },  

 	          "valor":" ",
 	          "quantidade":,
 	          "tipoPagamento":"CARTAO",
 	         
                 "usuario":{
    	          "email":" "
 	          }
              }

**Authorization**  Bearer  + token gerado.


O usuário poderá ver os detalhes de todas suas compras passando o email e o token gerado. Mostra todas as compras feitas por aquele usuário.

**GET /api/compras/detalhes/{email}** Retorna todos os Pagamentos do Usuário passando o email.

![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/detalhes%20por%20email.PNG)

se os dados passados incorretos retorna o erro 500.

O usuário poderá verificar a sua compra feita pelo id, caso queira.

**GET /api/compras/detalhesCompra/{id}**  Retorna os Pagamentos pelo id do usuário.
![](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/BUSCAPAGID.PNG)





