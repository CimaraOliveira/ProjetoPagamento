var cardValid = false;

function validateCardNumber(input, event) { // função recebe o input e o evento que é disparado
	/*var regex = new RegExp("^[0-9]{16}$");
	if (!regex.test(numero.value))
		return false;*/ 
	const lenght = input.value.length; // recebe o tamanho do valor do input
	if (lenght == 16) { // se for igual a 16 ele bloquea o evento, assim não será dispado o mesmo
		event.preventDefault(); // função usado para barrar o evento
	}
	input.value = input.value.replace(/[^0-9]/g, ""); // passa para o input receber somente números
	if (input.value != "") { // se o valor do input for diferente de vazio, segue o fluxo abaixo
		if (lenght == 16) { // se for igual a 16 ele verifica se o cartão é valido ou não
			if (!luhnCheck(input.value)) { // se não for valido, mostra a mensagem de erro
				alertErrorMessage("Cartão Inválido!", "invalid-card");
				cardValid = false;
			} else { // senão mostra mensagem de sucesso
				cardValid = true;
				alertSuccessMessage("Cartão Válido!", "invalid-card");
			}
		}
	}
}

function alertSuccessMessage(value, divPai) { // função para mostrar a mensagem de sucesso
	const div = document.getElementById(divPai); // retorna o elemento do html
	const divChild = document.createElement("div"); // cria e remove um novo elemento "div" no html
	creatAndRemoveChilders(div, divChild); // vincula o elemento pai ao filho no html

	divChild.setAttribute("class", "alert alert-success") // seta a class 
	divChild.textContent = value; // passa o value para o elemento
}

function creatAndRemoveChilders(div, divChild) { // função para criar e remover os elementos usado para mostrar as mensagens de sucesso e erro
	if (div.children.length > 0) { // se o elemento div tiver filhos, deverá remover o primeiro filho que já foi criado
		div.children.item(0).remove(); // removendo o filho
	}
	// após a remoção caso tenha tido filho
	div.appendChild(divChild); // adicionando o elemento como filho
}

function alertErrorMessage(value, divPai) {// função para mostrar a mensagem de erro
	const div = document.getElementById(divPai); // retorna o elemento do html
	const divChild = document.createElement("div");// cria um novo elemento "div" no html
	creatAndRemoveChilders(div, divChild);// cria e remove um novo elemento "div" no html
	divChild.setAttribute("class", "alert alert-danger") // seta a class 
	divChild.textContent = value; // passa o value para o elemento
}

function luhnCheck(val) {
	var sum = 0;
	for (var i = 0; i < val.length; i++) {
		var intVal = parseInt(val.substr(i, 1));
		if (i % 2 == 0) {
			intVal *= 2;
			if (intVal > 9) {
				intVal = 1 + (intVal % 10);
			}
		}
		sum += intVal;
	}
	return (sum % 10) == 0;
}


function saveCard(button, event){// função para verificar se o cartão é valido ou não
	if(cardValid){ // se for válido o formulario é submetido
		button.submit();
	}else{// senão, o evento é barrado para que não envie a requisição, e assim possa ser ajustado a validade do cartão
		event.preventDefault();
		alertErrorMessage("Cartão Inválido!", "invalid-card");
	}
}