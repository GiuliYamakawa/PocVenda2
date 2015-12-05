package br.univel.tela;

import java.util.List;

import br.univel.enums.Estados;

public class Testes {

	public static void main(String[] args) {
		String[] estados = Estados.names();
		for(int i=0; i<estados.length; i++){
			System.out.println(estados[i]);
		}
	}

}
