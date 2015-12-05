package br.univel.enums;
/**
 * Genero (Enum Generos lista de generos)
 * Data: 02-11-2015 00:02
 * @author ggsgyamakawa
 */
public enum Genero {
	
	FEMININO,
	MASCULINO;
	
	public static String[] names() {
		Genero[] states = values();
	    String[] names = new String[states.length];

	    for (int i = 0; i < states.length; i++) { //LISTA DE STRING
	        names[i] = states[i].name();
	    }

	    return names;
	}

}
