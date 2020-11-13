
// função que faz requisição usando ajax
function request(url, method, parameters, headers, success, error) {
    /*
    * caso precise enviar um objeto via json, use esse header
    *
    * const headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append("Accept",  'application/json');
    *
    * */

    /*
    * Uma Promise representa um proxy para um valor que não é necessariamente conhecido quando a promessa é criada.
    * Isso permite a associação de métodos de tratamento para eventos da ação assíncrona num
    * caso eventual de sucesso ou de falha. Isto permite que métodos assíncronos retornem valores
    * como métodos síncronos: ao invés do valor final, o método assíncrono retorna uma promessa ao valor em algum momento no futuro.
    *
    * */
    return new Promise(resolve => {// O resolve é uma função anonima, no qual poderá ser usada para retornar a resposta da requisição
        $.ajax({
            url: url, // recebe o endereço que deseja realizar a requisição
            method: method, // recebe o tipo da requisição (get, delete, put, post e outros)
            data: parameters, // recebe o corpo da requisição. Podendo ser usado diversas formas.
            // exemplo:
            /*
            * {
            *   user: 'mauricio'
            * }
            * E lá no spring poderia pegar esse valor 'user' usando anotação @RequestParam.
            *
            * Outro exemplo
            *
            * JSON.stringify({user: 'mauricio'})
            * E no spring seria usando anotação @RequestBody
            *
            * */
            headers: headers

        })
            // função para receber a resposta com sucesso
            .done(function (data) {
                // data é o retorno da resposta do servidor
                resolve(success(data));
            })
            // função para receber a resposta com falhas
            .fail(function (data) {
                // data é o retorno da resposta do servidor
                resolve(error(data))
            });
    })
} 
