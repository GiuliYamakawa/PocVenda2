package br.univel.enums;
/**
 * Estados (Enum Estados lista de estados)
 * Data: 02-11-2015 23:49
 * @author ggsgyamakawa
 */
public enum Estados {
	
	PR,
	SP,
	SC,
	RS;
	
	public static String[] names() {
	    Estados[] states = values();
	    String[] names = new String[states.length];

	    for (int i = 0; i < states.length; i++) {
	        names[i] = states[i].name();
	    }

	    return names;
	}
}
