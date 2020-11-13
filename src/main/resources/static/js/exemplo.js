
// função usada para exibir o valor
function showValue(value) { // recebe por parametro o valor
	const div = document.getElementById("show"); // recebe o elemento do html para manipular
	const divChild = document.createElement("div"); // cria um elemento auxiliar, que será usado para exibir a resposta
	creatAndRemoveChilders(div, divChild); // usando a função do arquivo validate-card.js
	divChild.setAttribute("class", "card"); // seta a class no elemento
	divChild.setAttribute("style", "padding: 20px;"); // seta o estilo css no elemento
	const divValue = document.createElement("div"); // cria outro elemento auxiliar
	creatAndRemoveChilders(divChild, divValue); // usando a função do arquivo validate-card.js
	divValue.setAttribute("class", "col-12");  // seta a class no elemento
	divValue.setAttribute("style", "font-size: 20px;"); // seta o estilo css no elemento
	if (value > 0) { // se o valor for maior que zero, mostra a resposta com o total
		divValue.textContent = "Total " + value; // seta valor no elemento
	} else { // caso contrário, mostra uma mensagem de erro
		divValue.setAttribute("class", "alert alert-danger") // seta a class no elemento
		divValue.textContent = "Valor Inválido!"; // seta a mensagem no elemento
	}
}
// função usada para pegar o valor e tratar o input, quando o evento onkeyup for disparado
function keyNumber(input) { // recebe por parametro o elemento
	const div = document.getElementById("show"); // retorna o elemento do html
	input.value = input.value.replace(/[^0-9]/g, ""); // tratando o input para aceitar apenas numeros
	if (div.children.length > 0) {
		// div.children retorna um array de filhos. No JavaScript o array tem o atributo length para informar a quantidade de filhos.
		// se o elemento retornado na linha 22 tive filhos, é pq já foi disparado a função que faz o calculo.
		// Desta forma o input será usado para alterar o total, caso precise.
		const select = document.getElementById("valor"); // retorna o elemento select do html
		showValue(parseInt(input.value) * parseInt(select.value)); // chama a função para exibir o valor
	}
}
// função usada para pegar o valor do select do html, somente quando o evento onclick for disparado
function calValue(select) { // recebe por parametro o elemento
	const inputQtd = document.getElementById("quantidade"); // retorna o elemento input do html
	const value = parseInt(inputQtd.value.replace(/[^0-9]/g, "")); // tratando o value para pegar apenas numeros
	showValue(value * parseInt(select.value));// chama a função para exibir o valor
}


// função assíncrono usada para renderizar o usuario no html em forma de tabela
async function render(){
	const url = "/api/user/findByNome/Cleo" // url para realizar a requisição do recurso que tenha com o nome "Cleo"
	await request(url, "get", null, null, getUser, error); // função usada do arquivo request.js
	dataPassagem();
}
// cria a tabela no html com os dados do usuario
function getUser(data){
	creatTable("table-cliente", ["Nome", "E-mail"], [[data.nome, data.email]]);// chamando a função que cria a tabela
}

function error(){ // função usada para mostrar o erro, caso não encontre o usuario
	creatTable("table-cliente", ['Erro no servidor'], [["Usuário não encontrado!"]]);
}

// função usada para desmostrar uma sugestão para exibir informações da passagem
function dataPassagem(){
	creatTable("table-passagem", ["Origem", "Destino", "Dia e Horário", "Total de Horas de Viagem"], [["São Paulo", "Pau dos Ferros-RN", "Terça 10 de Nov ás 12:36", "5h"]]); // // chamando a função que cria a tabela
}

// função usada para criar a tabela no html
function creatTable(idName, head, body){// recebe o nome do id do html, um array de string para ser usado o head da tabela e outro array para ser o corpo da mesma.
	const divTable = document.getElementById(idName); // retorna o elemento do html 
	const table = creatElement("table", divTable); // chama a função que cria o elemento (table) e seta como filho do elemento (div) retornado
	table.setAttribute("class", "table"); // seta a class ao elemento table
	const tHead = creatElement("thead", table); // chama a função que cria o elemento (thead) e seta como filho do elemento (table) retornado
	tHead.setAttribute("class", "thead-light"); // seta a class ao elemento thead
	const tbody = creatElement("tbody", table); // chama a função que cria o elemento (tbody) e seta como filho do elemento (table) retornado
	
	const trHead = creatElement("tr", tHead); // chama a função que cria o elemento (tr) e seta como filho do elemento (thead) retornado
	for(let i = 0; i < head.length; i++){ // como o head é um array de string, são o head da tabela
		const th = creatElement("th", trHead); // chama a função que cria o elemento (th) e seta como filho do elemento (tr) retornado
		th.setAttribute("scope", "col"); // seta o scope ao elemento th
		th.textContent = head[i]; // seta o valor ao elemento th
	}
	
	for(let i = 0; i < body.length; i++){
		const tr = creatElement("tr", tbody); // chama a função que cria o elemento (tr) e seta como filho do elemento (tbody) retornado
		for(let j = 0; j < body[i].length; j++){ // como o body é um array de string, será o body da tabela
			const td = creatElement("td", tr);// chama a função que cria o elemento (td) e seta como filho do elemento (tr) retornado
			td.textContent = body[i][j]; // seta o valor ao elemento td
		}
	}
}

// função usada para criar e setar o elemento novo ao pai
function creatElement(tag, father){
	const element = document.createElement(tag); // cria o novo elemento html
	father.appendChild(element); // seta o filho ao pai
	return element; // retorna o elemento novo
}


