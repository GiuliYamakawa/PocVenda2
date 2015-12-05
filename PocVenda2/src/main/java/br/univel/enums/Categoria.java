package br.univel.enums;
/**
 * Categoria (Enum categoria lista de categorias)
 * Data: 02-11-2015 23:33
 * @author ggsgyamakawa
 */

public enum Categoria {
	
	LIMPEZA, 
	PEÇAS, 
	ALIMENTAÇÃO;

	public static String[] names() {
		Categoria[] states = values();
	    String[] names = new String[states.length];

	    for (int i = 0; i < states.length; i++) {
	        names[i] = states[i].name();
	    }

	    return names;
	}
}
