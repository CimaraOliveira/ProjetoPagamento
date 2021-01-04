# Sistema Pagamento
Projeto Integrador


### Características

API REST Spring Boot REST API. O serviço de pagamento possui um serviço que os demais módulos devem consumir. Nesse serviço recebe um objeto no formato JSON que contem as informações, como, a forma de pagamento(Cartão ou Boleto), o email do usuário que deseja realizar o pagamento, valor, quantidade, tipo pagamento e enviar o token no header da requisição.


### Descrição 

**1 .** O cliente que deseja enviar uma requisição para o serviço, deve informar um objeto no formato JSON, se esse objeto não estiver de acordo com as especificações da regra de negócio, o Serviço retornará ao status 400. Ex: Não passar o header na requisição.


**2 .** O cliente deverá enviar um email do usuário. Caso o email não seja encontrado na base de dados, o Serviço retornará a um status 404.

**3 .** Caso o cliente envie uma requisição com o token inválido, o serviço deverá retornar um status 403.

**4 .** Caso a forma de pagamento seja cartão o cliente(os módulos) deverá envir um objeto que contenha as informações tais como nome, número, cvv, quantidade de parcelas, mês e ano. Após salvar, o serviço deve retornar a um status 202 e o objeto salvo.

#### link para acesso a documentação da api:  https://api-projetopagamento.herokuapp.com/swagger-ui.html

#### link para acesso ao sistema web: https://api-projetopagamento.herokuapp.com/


### Como Funciona utilizando o Postman

#### Salvando um usuário utilizando o Postman.

Para salvar um usuário utilizando o postman, selecionamos o método POST e utilizamos a opção Body raw, passamos os dados de acordo com o modelo abaixo.
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/LoGINPOSTMMANNN.png)

Se os dados forem passados corretamente retorna ao status 201.

Se os dados forem passados incorretos retorna ao status 403.

#### Login utilizando Postman

Para fazer login utilizamos o método POST, e a opção body  raw e informamos os dados necessários  para login, email e senha.
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/LOGINPOST.png)


Se os dados forem informados corretamente o status é igual a 200 retornando um token como resposta.

Se os dados forem passados incorretos status 404, com isso tem-se que deu um erro ao fazer login.

#### Formas de pagamento utilizando o Postman

Para fazer um pagamento no postman, utilizamos o método POST, precisamos passar algumas informações para ser possível realizar o pagamento, tanto em pagamento boleto como em cartão no Headers precisamos passar a authentication que é a palavra Bearer mais token gerado, e a origem de onde vem a requisição.  Exemplo  da imagem abaixo.
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/COMPRACARTAO(1).png)



Logo em seguida, no mesmo método escolhido POST, para realizar a compra vai na opção Body ray e informa os dados para o pagamento. Se o usuário escolher **cartão** deve passar as  seguintes informações do modelo do exemplo abaixo, mudando as informações para qual ele deseja.
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/COMPRACARTAOPOST.png)


Se o usuário escolher **boleto** deve ter a mesma configuração no header e ficar do seguinte modelo.
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/COMPRABOLETO.png)


#### Todos os pagamentos realizados pelo Usuário
O usuário poderá ver as transações de todos os seus pagamentos realizados e qual forma de pagamento escolhido em todos os seus pagamentos realizados, e o status,  através do método GET, passando apenas o e-mail e o token gerado no login. segue o modelo:
![documentacao](https://github.com/CimaraOliveira/ProjetoPagamento/blob/master/DETALHESDETODASCOMPRAS.png)





### Como Funciona na documentação Swagger
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
































