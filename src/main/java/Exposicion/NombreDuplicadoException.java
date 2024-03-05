package Exposicion;

/**
 *
 * @author Andrés Lombana - Johan Serrano
 */
public class NombreDuplicadoException extends Exception {
	public NombreDuplicadoException() {
		super("Ya existe un perro con ese nombre");
	}

}