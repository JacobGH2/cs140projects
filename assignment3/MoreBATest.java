package p3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class MoreBATest {
	
	public class MakeBankAccountsAndTransactions {


		public static void main(String[] args) {
			
			if(args.length != 1) {
				System.out.println("format: makeBankAccountsAndTransactions \"input file\"");
				System.exit(0);
			}

			try (BufferedReader input = new BufferedReader(
					new InputStreamReader(new FileInputStream(args[0])))) {
				String inn;
				ArrayList<String> inputLines = new ArrayList<>();
				while((inn = input.readLine()) != null) {
					if((inn.trim().length() > 0) && (!inn.trim().startsWith("#"))) {
						inputLines.add(inn);
					}
				}

				for(int i = 0; i < inputLines.size(); ) { // i is incremented in the loop
					// depending on the lines in the file
					inn = inputLines.get(i);
					System.out.println(inn);

					if(inn.startsWith("new BankAccount")) {
						StringTokenizer st = new StringTokenizer(inn, "\t");
						st.nextToken(); // consume "new BankAccount"
						String lastName = st.nextToken();
						String firstName = st.nextToken();
						double checkingInitialValue = Double.parseDouble(st.nextToken());
						double savingsInitialValue = Double.parseDouble(st.nextToken());
						BankAccount ba = new BankAccount(lastName, firstName, checkingInitialValue, savingsInitialValue);
						System.out.println(ba.toString());
						i++;
						while(true)	{ // make sure there are breaks 
							if(i >= inputLines.size()) {
								System.out.println(ba.toString());
								break;
							}
							inn = inputLines.get(i);
							if(inn.startsWith("new")) {
								System.out.println(ba.toString());
								break;
							}
							// must be a Transaction
							st = new java.util.StringTokenizer(inn, "\t");
							String type = st.nextToken();
							double amount = Double.parseDouble(st.nextToken());						
							if(type.equals("withdrawFromChecking")) {
								ba.withdrawFromChecking(amount);
								System.out.println("withdrawFromChecking " + amount);
							}						

							// add code below to process the other five transaction types
							// if the transaction type is withdrawFromSavings
							if(type.equals("withdrawFromSavings")) {
								ba.withdrawFromSavings(amount);
								System.out.println("withdrawFromChecking " + amount);
							}
							// if the transaction type is depositToChecking
							if (type.equals("depositToChecking")) {
								ba.depositToChecking(amount);
								System.out.println("depositToChecking " + amount);
							}
						
							// if the transaction type is depositToSavings
							if (type.equals("depositToSavings")) {
								ba.depositToSavings(amount);
								System.out.println("depositToSavings " + amount);
							}
							// if the transaction type is transferToSavings
							if (type.equals("transferToSavings")) {
								ba.transferFromCheckingToSavings(amount);
								System.out.println("transferToSavings " + amount);
							}
							// if the transaction type is transferToChecking
							if (type.equals("transferToChecking")) {
								ba.transferFromSavingsToChecking(amount);
								System.out.println("transferToChecking " + amount);
								
							}

							i++; // move to this index of the next line still inside while loop						
						} // end while true
					} // end if "new BankAccount"

					if(inn.startsWith("new EnhancedBankAccount")) {
						StringTokenizer st = new StringTokenizer(inn, "\t");
						st.nextToken(); // consume "new EnhancedBankAccount"
						String lastName = st.nextToken();
						String firstName = st.nextToken();
						double checkingInitialValue = Double.parseDouble(st.nextToken());
						double savingsInitialValue = Double.parseDouble(st.nextToken());
						EnhancedBankAccount eba = new EnhancedBankAccount(lastName, firstName, 
								checkingInitialValue, savingsInitialValue);
						System.out.println(eba.toString());
						i++;
						while(true) {
							if(i >= inputLines.size()) {
								System.out.println(eba.toString());
								break;
							}
							inn = inputLines.get(i);
							if(inn.startsWith("new")) {
								System.out.println(eba.toString());
								break;
							}
							st = new StringTokenizer(inn, "\t");
							String type = st.nextToken();
							double amount = Double.parseDouble(st.nextToken());						
							if(type.equals("withdrawFromChecking"))	{
								eba.withdrawFromChecking(amount);
								System.out.println("withdrawFromChecking " + amount);
							}
							// add code below to process the other five transaction types
							// if the transaction type is withdrawFromSavings
							// if the transaction type is depositToChecking
							// if the transaction type is depositToSavings
							// if the transaction type is transferToChecking						
							// if the transaction type is transferToSavings
							if(type.equals("withdrawFromSavings")) {
								eba.withdrawFromSavings(amount);
								System.out.println("withdrawFromChecking " + amount);
							}
							// if the transaction type is depositToChecking
							if (type.equals("depositToChecking")) {
								eba.depositToChecking(amount);
								System.out.println("depositToChecking " + amount);
							}
						
							// if the transaction type is depositToSavings
							if (type.equals("depositToSavings")) {
								eba.depositToSavings(amount);
								System.out.println("depositToSavings " + amount);
							}
							// if the transaction type is transferToSavings
							if (type.equals("transferToSavings")) {
								eba.transferFromCheckingToSavings(amount);
								System.out.println("transferToSavings " + amount);
							}
							// if the transaction type is transferToChecking
							if (type.equals("transferToChecking")) {
								eba.transferFromSavingsToChecking(amount);
								System.out.println("transferToChecking " + amount);
								
							}
							// write in arrayList getting types & arrayList with parameter
							if (type.equals(""))
							i++;
						}
					}
				}
			} catch(java.lang.IndexOutOfBoundsException e) {
				System.out.println(e.toString());
				System.exit(0);
			} catch(Exception e) {
				System.out.println(e.toString());
				System.exit(0);
			}
		}

	}
}
