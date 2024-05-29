const alertaMesAtual = document.getElementById("alertaMesAtual").value;
const alertaListaId = document.getElementById("alertaListaId").value;
const alertaListaIdFormatado = alertaListaId.split(",");


if(alertaMesAtual >=1){	
	for (let i = 0; i < alertaListaIdFormatado.length; i++) {	
				alert("Existem contas a vencer, por favor verificar!!! \n"+
						"Consulte o ID:- " + alertaListaIdFormatado[i].replace(/\[|\]/g, ""));
		}

}     

