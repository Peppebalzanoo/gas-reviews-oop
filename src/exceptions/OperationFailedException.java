package exceptions;

public class OperationFailedException extends Exception {

	public OperationFailedException() {
		
		 super("Si � verificato un errore, riprova pi� tardi.");
	}
	
	public OperationFailedException(String reason) {
		
		 super("Impossibile completare l'operazione: " + reason);
	}

}
