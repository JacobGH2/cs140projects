package p3;

import java.time.LocalDate;
public record Transaction(String type, LocalDate date, double amount) {	
	public String toString() {
		return type + " " + date + " " + String.format("$%.2f", amount);
	}
}
