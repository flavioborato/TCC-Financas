
//Verificação para gerar alerta do Mes Atual
const alertaMesAtual = document.getElementById("alertaMesAtual").value;
const alertaListaIdAtual = document.getElementById("alertaListaIdAtual").value;
const alertaListaDataAtual = document.getElementById("alertaListaDataAtual").value;
const alertaListaIdFormatadoAtual = alertaListaIdAtual.split(",");
const alertaListaDataFormatadoAtual = alertaListaDataAtual.split(",");
let mensagem = "" ;
	for (let i = 0; i < alertaListaIdFormatadoAtual.length; i++) {		
		mensagem += "Verifique o dia "+ alertaListaDataFormatadoAtual[i].replace(/\[|\]/g, "") +
		" correspondente do ID:- " + alertaListaIdFormatadoAtual[i].replace(/\[|\]/g, "")+"\n";
	}
	if(alertaMesAtual >=1){	
		alert("Existem contas a vencer do Mês Atual, por favor verificar!!! \n" + mensagem);
					
	}     

//Verificação para gerar alerta do Proximo Mes
const alertaMesProximo = document.getElementById("alertaMesProximo").value;
const alertaListaIdProximo = document.getElementById("alertaListaIdProximo").value;
const alertaListaDataProximo = document.getElementById("alertaListaDataProximo").value;
const alertaListaIdFormatadoProximo = alertaListaIdProximo.split(",");
const alertaListaDataFormatadoProximo = alertaListaDataProximo.split(",");
	mensagem = "" ;
	for (let i = 0; i < alertaListaIdFormatadoProximo.length; i++) {		
		mensagem += "Verifique o dia "+ alertaListaDataFormatadoProximo[i].replace(/\[|\]/g, "") +
		" correspondente do ID:- " + alertaListaIdFormatadoProximo[i].replace(/\[|\]/g, "")+"\n";
	}
	if(alertaMesProximo >=1){	
		alert("Existem contas a vencer do proximo Mês, por favor verificar!!! \n" + mensagem);				
	} 