package br.com.tcc.financas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.tcc.financas.model.GastosCartao;

public class GastosCartaoDTO {
	
	
	public List<GastosCartao> criaListaDeParcelas (GastosCartao gastoscartao){
		LocalDate mes = gastoscartao.getMes();
		List<GastosCartao> novaParcela = new ArrayList<>();
		// Verifica se é alteração ou cadastro pelo ID
		if(gastoscartao.getIdgastoscartao() == null || gastoscartao.getIdgastoscartao() == 0 ) {
			// Faz a divisão das parcelas
			 BigDecimal parcelas = new BigDecimal(gastoscartao.getParcelas());
			 BigDecimal valorParcelado = gastoscartao.getValor().divide(parcelas);
		// Cria lista das parcelas alterando data para ser salva
		for (int i = 0; i < gastoscartao.getParcelas(); i++) {	
			GastosCartao parcela = new GastosCartao();
			parcela.setArea(gastoscartao.getArea());
			parcela.setCartao(gastoscartao.getCartao());
			parcela.setDatacompra(gastoscartao.getDatacompra());
			parcela.setDescricao(gastoscartao.getDescricao());
			parcela.setIdgastoscartao(null);
			parcela.setMes(mes);
			parcela.setParcelas(i+1);
			parcela.setPessoa(gastoscartao.getPessoa());
			parcela.setTipogasto(gastoscartao.getTipogasto());
			parcela.setValor(valorParcelado);	
			novaParcela.add(parcela);
			mes = mes.plusMonths(1);	
		}
		return novaParcela;
	}else { 
		GastosCartao parcela = new GastosCartao();
		parcela.setArea(gastoscartao.getArea());
		parcela.setCartao(gastoscartao.getCartao());
		parcela.setDatacompra(gastoscartao.getDatacompra());
		parcela.setDescricao(gastoscartao.getDescricao());
		parcela.setIdgastoscartao(gastoscartao.getIdgastoscartao());
		parcela.setMes(gastoscartao.getMes());
		parcela.setParcelas(gastoscartao.getParcelas());
		parcela.setPessoa(gastoscartao.getPessoa());
		parcela.setTipogasto(gastoscartao.getTipogasto());
		parcela.setValor(gastoscartao.getValor());	
		novaParcela.add(parcela);
		return novaParcela;	
	}	
		
	}
	
	

}
