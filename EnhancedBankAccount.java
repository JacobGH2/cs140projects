package p3;

import java.time.LocalDate;
import java.util.ArrayList;
public class EnhancedBankAccount extends BankAccount {
	private ArrayList<Transaction> successfulTransactions;
	private ArrayList<Transaction> failedTransactions;
	
	EnhancedBankAccount(String ownerLastName, String ownerFirstName, 
			double checkingBalance, double savingsBalance) {
		super(ownerLastName, ownerFirstName, checkingBalance, savingsBalance);
		
		successfulTransactions = new ArrayList<>();
		failedTransactions = new ArrayList<>();
		
		LocalDate date = LocalDate.now();
		successfulTransactions.add(new Transaction("new EnhancedBankAccount", date, 0.0));
		if(checkingBalance > 0.0) {
			successfulTransactions.add(new Transaction("depositToChecking", date, checkingBalance));
		}
		
		if(savingsBalance > 0.0) {
			successfulTransactions.add(new Transaction("depositToSavings", date, savingsBalance));
		}
	}
	
	EnhancedBankAccount(String ownerLastName, String ownerFirstName) {
		this(ownerLastName, ownerFirstName, 0.0, 0.0);
	}
	
	public boolean withdrawFromChecking(double amount) {
		boolean returnValue = false;
		LocalDate date = LocalDate.now();
		if(super.withdrawFromChecking(amount)) {
			successfulTransactions.add(new Transaction("withdrawFromChecking", date, amount));
			returnValue = true;
		} else {
			failedTransactions.add(new Transaction("withdrawFromChecking", date, amount));
		}
		return returnValue;
	}
	
	public boolean withdrawFromSavings(double amount) {
		boolean returnValue = false;
		LocalDate date = LocalDate.now();
		if(super.withdrawFromSavings(amount)) {
			successfulTransactions.add(new Transaction("withdrawFromSavings", date, amount));
			returnValue = true;
		} else {
			failedTransactions.add(new Transaction("withdrawFromSavings", date, amount));
		}
		return returnValue;
	}
	
	public boolean transferFromSavingsToChecking(double amount) {
		boolean returnValue = false;
		LocalDate date = LocalDate.now();
		if(super.transferFromSavingsToChecking(amount)) {
			successfulTransactions.add(new Transaction("transferFromSavingsToChecking", date, amount));
			returnValue = true;
		} else {
			failedTransactions.add(new Transaction("transferFromSavingsToChecking", date, amount));
		}
		return returnValue;
	}
	
	public boolean transferFromCheckingToSavings(double amount) {
		boolean returnValue = false;
		LocalDate date = LocalDate.now();
		if(super.transferFromCheckingToSavings(amount)) {
			successfulTransactions.add(new Transaction("transferFromCheckingToSavings", date, amount));
			returnValue = true;
		} else {
			failedTransactions.add(new Transaction("transferFromCheckingToSavings", date, amount));
		}
		return returnValue;
	}
	
	public void depositToSavings(double amount) {
		LocalDate date = LocalDate.now();
		super.depositToSavings(amount);
		successfulTransactions.add(new Transaction("depositToSavings", date, amount));
	}
	
	public void depositToChecking(double amount) {
		LocalDate date = LocalDate.now();
		super.depositToChecking(amount);
		successfulTransactions.add(new Transaction("depositToChecking", date, amount));
	}
	
	
	public ArrayList<Transaction> getAllSuccessfulTransactions() {
		return successfulTransactions;
	}
	
	public ArrayList<Transaction> getAllFailedTransactions() {
		return failedTransactions;
	}
	
	public ArrayList<Transaction> getSavingsDepositTransactions() {
		ArrayList<Transaction> results = new ArrayList<>();
		for(Transaction t : successfulTransactions) {
			if(t.type().equals("depositToSavings")) {
				results.add(t);
			}
		}
		return results;
	}
	
	public ArrayList<Transaction> getCheckingDepositTransactions() {
		ArrayList<Transaction> results = new ArrayList<>();
		for(Transaction t : successfulTransactions) {
			if(t.type().equals("depositToChecking")) {
				results.add(t);
			}
		}
		return results;
	}
	
	public ArrayList<Transaction> getSavingsWithdrawalTransactions() {
		ArrayList<Transaction> results = new ArrayList<>();
		for(Transaction t : successfulTransactions) {
			if(t.type().equals("withdrawFromSavings")) {
				results.add(t);
			}
		}
		return results;
	}
	
	public ArrayList<Transaction> getCheckingWithdrawalTransactions() {
		ArrayList<Transaction> results = new ArrayList<>();
		for(Transaction t : successfulTransactions) {
			if(t.type().equals("withdrawFromChecking")) {
				results.add(t);
			}
		}
		return results;
	}
	
	public ArrayList<Transaction> getSavingsToCheckingTransferTransactions() {
		ArrayList<Transaction> results = new ArrayList<>();
		for(Transaction t : successfulTransactions) {
			if(t.type().equals("transferFromSavingsToChecking")) {
				results.add(t);
			}
		}
		return results;
	}
	
	public ArrayList<Transaction> getCheckingToSavingsTransferTransactions() {
		ArrayList<Transaction> results = new ArrayList<>();
		for(Transaction t : successfulTransactions) {
			if(t.type().equals("transferFromCheckingToSavings")) {
				results.add(t);
			}
		}
		return results;
	}
	
	public ArrayList<Transaction> getFailedTransactions(LocalDate startDate, LocalDate endDate) {
		ArrayList<Transaction> results = new ArrayList<Transaction>();
		for (Transaction t : failedTransactions) {
			if ((t.date().compareTo(startDate) > 0) && (t.date().compareTo(endDate) < 0)) {
				results.add(t);
			} 
		}
		return results;
	}
	
	public ArrayList<Transaction> getSuccessfulTransactions(LocalDate startDate, LocalDate endDate) 	{
		ArrayList<Transaction> results = new ArrayList<Transaction>();
		for (Transaction t : successfulTransactions) {
			if ((t.date().compareTo(startDate) > 0) && (t.date().compareTo(endDate) < 0)) {
				results.add(t);
			} 
		}
		return results;
	}
	
	public void updateTransactions() {
        ArrayList<Transaction> successful = new ArrayList<>();
        LocalDate date = LocalDate.of(2022, 1, 1);
        for(Transaction trans : successfulTransactions) {
            var newTrans = new Transaction(trans.type(), date, trans.amount());
            successful.add(newTrans);
            date = date.plusDays(3);
        }
        successfulTransactions = successful;
        ArrayList<Transaction> failed = new ArrayList<>();
        date = LocalDate.of(2022, 1, 1);
        for(Transaction trans : failedTransactions) {
            var newTrans = new Transaction(trans.type(), date, trans.amount());
            failed.add(newTrans);
            date = date.plusDays(3);
        }
        failedTransactions = failed;
    }
}
