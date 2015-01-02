
public class BadTransactionException extends Exception {
	public int accountNum;
	
	public BadTransactionException(int invalidAccountNum){
		super("Invalid transaction with account number: " + invalidAccountNum);

	    accountNum = invalidAccountNum;
	}
}
