
package banking;

/**
 *
 * @author Zsolt
 */
public class User {
    
    private String bankaccount;
    private int balance;

    public User(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
    
    
}
