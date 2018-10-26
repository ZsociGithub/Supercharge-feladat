
package banking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Unyatinszki Zsolt
 */
public class Banking {

    static LinkedList<User> userlist = new LinkedList<User>();
    static LinkedList<History> historylist = new LinkedList<History>();
    
    static User actuser, otheruser;
    
    
    static User userLogin (){
    
        Scanner sc = new Scanner(System.in);
        String input;
        User user;
                
        do{
                do{

                System.out.println("1 - Új User létrehozása");
                System.out.println("2 - Létező User login");
                System.out.println("3 - Kilépés a proramból");

                input = sc.nextLine();

                }while(!input.equals("1") && !input.equals("2") && !input.equals("3") );

                switch(input){

                    case "1":
                        
                        System.out.println("Írd be az új account nevet!");
                        input = sc.nextLine();
                        
                        if (isUser(input) != null) System.out.println("Van már ilyen User!\n");
                        else{
                            System.out.println("\n");
                            user = new User(input);
                            userlist.add(user);
                            return user;}
                        
                        
                        
                        break;

                    case"2":
                        
                        System.out.println("Írd be a User accountot!");
                        input = sc.nextLine();
                        
                        User found = isUser(input);
                        
                        if (found == null)System.out.println("Nincs ilyen User!\n");
                        else {System.out.println("\n");
                              return found;
                        }
                            
                        break;

                    case "3": System.exit(0);

                    default: break;

                }
                
                
        }while(true);        
    }    
        
    static User isUser (String str){
         
         
         
         if (userlist != null){
             
                    for(User user: userlist){

                       if (user.getBankaccount().equals(str)) return user; 
                }
            return null;        
             
         }else return null;
    }   
        
    static void menu(){
        
        Scanner sc = new Scanner(System.in);
        boolean stepout = false;
        String input;
        int intinput;
        History history;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        
        do{
            
            do{
                System.out.println("User: "+actuser.getBankaccount());
                System.out.println("Balance: "+actuser.getBalance()+"\n");
                System.out.println("1 - Deposit");
                System.out.println("2 - Withdrawal");
                System.out.println("3 - Transfer");
                System.out.println("4 - Transaction history all by date");
                System.out.println("5 - Transaction history deposit");
                System.out.println("6 - Transaction history withdrawal");
                System.out.println("7 - Kilépés a Userből");
             
                input = sc.nextLine();

             }while(!input.equals("1") && !input.equals("2") && !input.equals("3")
                    && !input.equals("4") && !input.equals("5") && !input.equals("6")
                    && !input.equals("7") && !input.equals("8"));
                
                    switch(input){
                        case "1":
                            
                            System.out.println("Írd be a deposit összegét!");
                            intinput = sc.nextInt();
                            actuser.setBalance(actuser.getBalance()+intinput);
                            history = new History(actuser, LocalDateTime.now(), intinput, actuser.getBalance(), "deposit");
                            historylist.add(history);
                            
                            break;
                            
                        case "2":
                            
                            System.out.println("Írd be a withdrawal összegét!");
                            intinput = sc.nextInt();
                            
                            if (actuser.getBalance() - intinput < 0){System.out.println("Nincs elég pénz a számlán!\n"); input = sc.nextLine();}
                            else{
                                actuser.setBalance(actuser.getBalance()-intinput);
                                history = new History(actuser, LocalDateTime.now(), intinput, actuser.getBalance(), "withdrawal");
                                historylist.add(history);
                            }
                            
                            break;
                            
                        case "3":
                            
                            System.out.println("Írd be a másik user account-ját!");
                            input = sc.nextLine();
                            otheruser = isUser(input);
                            
                            if (otheruser == null || otheruser.getBankaccount().equals(actuser.getBankaccount()))System.out.println("Vagy nincs ilyen account, vagy a sajátodat írtad be!\n");
                            else{
                                   System.out.println("Írd be a transzfer összegét!");
                                    intinput = sc.nextInt();

                                    if (actuser.getBalance() - intinput < 0){System.out.println("Nincs elég pénz a számlán!\n"); input = sc.nextLine();}
                                    else{
                                        actuser.setBalance(actuser.getBalance()-intinput);
                                        otheruser.setBalance(otheruser.getBalance()+intinput);
                                        
                                        history = new History(actuser, LocalDateTime.now(), intinput, actuser.getBalance(), "Outtransfer");
                                        historylist.add(history);
                                        history = new History(otheruser, LocalDateTime.now(), intinput, otheruser.getBalance(), "Intransfer");
                                        historylist.add(history);
                                    }
                            }
                            
                            break;
                            
                        case "4":                            
                           
                            for (History hist: historylist){
                                if(hist.getUser() == actuser)
                                    System.out.println("Dátum "+dtf.format(hist.getTime())+" Transaction: "+hist.getTransaction()+" Összeg: "+hist.getAmount()+" Egyenleg a tranzakció után: "+hist.getBalance());
                            }
                            
                            System.out.println("\n");
                            
                            break;
                            
                        case "5":
                            
                            for (History hist: historylist){
                                if(hist.getUser() == actuser && hist.getTransaction().equals("deposit"))
                                    System.out.println("Dátum "+dtf.format(hist.getTime())+" Transaction: "+hist.getTransaction()+" Összeg: "+hist.getAmount()+" Egyenleg a tranzakció után: "+hist.getBalance());
                            }
                            
                            System.out.println("\n");
                            
                            break;
                            
                        case "6":
                            
                            for (History hist: historylist){
                                if(hist.getUser() == actuser && hist.getTransaction().equals("withdrawal"))
                                    System.out.println("Dátum "+dtf.format(hist.getTime())+" Transaction: "+hist.getTransaction()+" Összeg: "+hist.getAmount()+" Egyenleg a tranzakció után: "+hist.getBalance());
                            }
                            
                            System.out.println("\n");
                            
                            break;
                        case "7":
                            System.out.println("\n");
                            stepout = true;
                            
                            break;
                        
                        default: break;    
                    }
        
                
        }while(!stepout);
    }   
    
    
    
    public static void main(String[] args) {
        
        do{
            
          actuser = userLogin();
          
          menu();
          
        //exit a userLogin-ben  
        }while(true);
        
    }
    
}
