# Sistema Pagamento
Projeto Integrador


### Características

Pagamento API REST formas de Pagamento cartão ou boleto, Spring Boot REST API. Um  pequeno sistema de formas de pagamento que pode ser mantido por um banco de dados central e acessado por outros subsistemas utilizando uma api disponibilizada pelo módulo pagamento. 

#### link para acesso a documentação da api:  https://api-projetopagamento.herokuapp.com/swagger-ui.html

#### link para acesso ao sistema web: https://api-projetopagamento.herokuapp.com/


Acesso a documentação da Api disponibilizada  para os demais módulos que irão consumir do módulo pagamento.


**usuario-json: Usuario Json**,   para cadastrar um novo usuário, usuário acessar o sistema através de login, e detalhes do usuário pelo email.

**pagamento-json : Pagamento Json**,  para retornar todos os pagamentos feitos pelo usuário, retornar o pagamento pelo id do pagamento e salvar compra escolhendo uma das formas de pagamento Boleto ou Cartão.

![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/APII.PNG)


Em **usuario-json : Usuario Json** 

O usuário poderá criar um novo usuário se cadastrar no sistema, passando as credenciais como nome, email, senha.

POST       /api/usuarios/save             Criando um novo usuário

![save](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/CRIANDO.PNG)

O usuário poderá logar no sistema passando seu email e senha cadastrados.
 POST      /api/usuarios/login  Usuário faz login
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/DADOSLOGIN.PNG)

Ao fazer login  se as credenciais forem enviadas corretamente o usuário receberá como resposta um token para a autenticação do usuário para ter acesso aos demais serviços do sistema. 

![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/TOKEN.PNG)

Se as credenciais forem passadas corretamente,retornará a resposta 200 com o token gerado.
Se as credenciais forem passadas incorretas retornará a resposta de erro 404.

Com o usuário autenticado  o token é gerado e o usuário poderá fazer um pagamento em **pagamento-json : Pagamento Json**, para isso será necessario que o usuário
obrigatoriamente informe a origin onde está vindo a requisição, e a autenticação que foi gerada ao fazer login em seguida informará os dados para fazer o pagamento.

**Exemplo para passar dados de um pagamento pelo Boleto**

POST    /api/compras/saveCompra     Salva uma compra no cartão ou boleto 
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/PAGBOLETO.PNG)

**Exemplo para passar dados de um pagamento pelo Cartao**

POST    /api/compras/saveCompra     Salva uma compra no cartão ou boleto 
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/PAGCARTAO.PNG)

* Importante lembrar que na autenticação sempre será passada a palavra:  **Bearer** + o token gerado, para ter acesso ao salvar compra ou outras funcionalidades onde a Authorization seja exigida na api.


O usuário poderá ver os detalhes de todos os seus pagamentos realizados, passando o email e o token gerado. 

GET /api/compras/detalhes/{email} Retorna todos os Pagamentos do Usuário
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/detalhes%20por%20email.PNG)

Se os dados passados incorretos retorna o erro 404.

O usuário poderá verificar seu pagamento relizado  pelo id, caso deseje. para isso pegará o id que é gerado após fazer seu pagamento.

GET /api/compras/detalhesCompra/{id} Retorna os Pagamentos pelo id 
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/BUSCAPAGID.PNG)





























