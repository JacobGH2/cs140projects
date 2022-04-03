package p3;

public class BankAccount {
	private final String ownerLastName;
	private final String ownerFirstName;
	private final String accountNumber;
	private double checkingBalance;
	private double savingsBalance;
	private static int bankAccountCount = 0;
	
	BankAccount(String ownerLastNameIn, String ownerFirstNameIn, 
			double checkingBalanceIn, double savingsBalanceIn) {
		ownerLastName = ownerLastNameIn;
		ownerFirstName = ownerFirstNameIn;
		accountNumber = makeAccountNumber();
		checkingBalance = checkingBalanceIn;
		savingsBalance = savingsBalanceIn;		
		bankAccountCount = bankAccountCount + 1;
	}
	
	BankAccount(String ownerLastName, String ownerFirstName) {
		this(ownerLastName, ownerFirstName, 0.0, 0.0);
	}
	
	public boolean withdrawFromChecking(double amount) {
		if (checkingBalance < amount) {
			return false;
		}
		checkingBalance -= amount;
		return true;
	}
	
	public boolean withdrawFromSavings(double amount) {
		if (savingsBalance < amount) {
			return false;
		}
		savingsBalance -= amount;
		return true;
	}
	
	public boolean transferFromSavingsToChecking(double amount) {
		if (savingsBalance < amount) {
			return false;
		}
		savingsBalance -= amount;
		checkingBalance += amount;
		return true;
	}
	
	public boolean transferFromCheckingToSavings(double amount) {
		if (checkingBalance < amount) {
			return false;
		}
		checkingBalance -= amount;
		savingsBalance += amount;
		return true;
	}
	
	public void depositToSavings(double amount) {
		savingsBalance += amount;
	}
	
	public void depositToChecking(double amount) {
		checkingBalance += amount;
	}
	
	public String toString() {
		String returnString = ownerLastName + ", " + ownerFirstName + ", " + 
				accountNumber + "\ncheckingBalance -- " + 
				String.format("$%.2f\n", checkingBalance) + "savingsBalance -- " 
				+ String.format("$%.2f\n", savingsBalance);
		return returnString;
	}
	
	public String getOwnerLastName() {
		return ownerLastName;
	}
	
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public double getCheckingBalance() {
		return checkingBalance;
	}
	
	public double getSavingsBalance() {
		return savingsBalance;
	}
	
	private String makeAccountNumber() {
		String aNumber = "" + bankAccountCount;
		while(aNumber.length() < 6) {
			aNumber = "0" + aNumber;
		}
		return aNumber;
	}
	
	public static int getBankAccountCount() {
		return bankAccountCount;
	}
}
