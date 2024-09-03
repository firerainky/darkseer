package datatypes.quiz;

import java.math.BigDecimal;

public class Account {
    
    // your code goes here
    private BigDecimal account;
    
    public Account() {
        account = BigDecimal.ZERO;
    }
    
    public void deposit(BigDecimal amount) {
        account = account.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        account = account.subtract(amount);
    }

    public String getBalanceString() {
        return account.toString();
    }
}
