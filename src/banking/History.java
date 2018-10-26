
package banking;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Zsolt
 */
public class History {
    
    User user;
    LocalDateTime time;
    int amount, balance;
    String transaction;


    public History(User user, LocalDateTime time, int amount, int balance, String transaction) {
        this.user = user;
        this.time = time;
        this.amount = amount;
        this.balance = balance;
        this.transaction = transaction;
    }
    
    

    public User getUser() {
        return user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    public String getTransaction() {
        return transaction;
    }
    
    
    
}
