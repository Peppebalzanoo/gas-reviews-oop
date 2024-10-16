package exceptions;

public class OperationFailedException extends Exception {

	public OperationFailedException() {
		
		 super("Si è verificato un errore, riprova più tardi.");
	}
	
	public OperationFailedException(String reason) {
		
		 super("Impossibile completare l'operazione: " + reason);
	}

}
