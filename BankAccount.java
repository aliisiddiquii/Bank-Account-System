//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

class BankAccount {
    int amount;
    String CustomerName = "";
    int id = 0;
    int balance;
    int previousTransaction;
    Vector transactions = new Vector();

    BankAccount() {
    }

    void deposit(int var1) {
        if (var1 > 0) {
            this.balance += var1;
            System.out.println("\nTransaction saved successfully");
            this.transactions.addElement(new Date());
            this.transactions.addElement("D");
            this.transactions.addElement(var1);
            this.transactions.addElement(this.balance);
        }

    }

    void withdraw() {
        boolean var1 = true;

        while(var1) {
            try {
                BufferedReader var2 = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter amount to withdraw : ");
                this.amount = Integer.parseInt(var2.readLine());
                if (this.amount > this.balance) {
                    System.out.println("Insufficient balance in account!");
                } else {
                    if (this.amount <= 0) {
                        throw new Exception();
                    }

                    var1 = false;
                    this.balance -= this.amount;
                    System.out.println("\nTransaction saved successfully");
                    this.transactions.addElement(new Date());
                    this.transactions.addElement("W");
                    this.transactions.addElement(this.amount);
                    this.transactions.addElement(this.balance);
                }
            } catch (Exception var3) {
                System.out.println("Please enter Valid amount!");
            }
        }

    }

    void getPreviousTransaction() {
        try {
            System.out.println("DATE\t\t\t\t\t\tWITHDRAW\tDEPOSIT\t\tBALANCE\n");
            Enumeration var1 = this.transactions.elements();

            String var2;
            for(var2 = ""; var1.hasMoreElements(); var2 = var2 + "\n") {
                Date var3 = (Date)var1.nextElement();
                String var4 = (String)var1.nextElement();
                int var5 = (Integer)var1.nextElement();
                int var6 = (Integer)var1.nextElement();
                if (var4 == "D") {
                    var2 = var2 + var3 + "\t\t\t\t\t" + var5 + "\t\t" + var6;
                } else if (var4 == "W") {
                    var2 = var2 + var3 + "\t\t\t" + var5 + "\t\t\t\t" + var6;
                }
            }

            System.out.println(var2);
            System.out.println("----------------------------------------------------------------");
            System.out.println("BALANCE: " + this.balance);
            System.out.println("----------------------------------------------------------------");
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    void getData() {
        BufferedReader var1 = new BufferedReader(new InputStreamReader(System.in));
        boolean var2 = true;
        String var3 = "";
        String var4 = "";
        boolean var5 = false;

        while(var2) {
            try {
                System.out.print("Enter your Name : ");
                this.CustomerName = var1.readLine();
                if (this.CustomerName == null || this.CustomerName.isEmpty()) {
                    throw new Exception();
                }

                var2 = false;
                this.id = (int)(Math.random() * 100.0);
                FileInputStream var6 = new FileInputStream("AccountDetails.txt");
                boolean var7 = false;
                int var16;
                if ((var16 = var6.read()) == -1) {
                    var5 = true;
                } else {
                    for(var4 = var4 + (char)var16; (var16 = var6.read()) != -1; var4 = var4 + (char)var16) {
                    }
                }

                var6.close();
                FileOutputStream var8 = new FileOutputStream("AccountDetails.txt");
                String var9;
                byte[] var10;
                if (var5) {
                    var9 = "ID\tNAME\t\t\tDATE AND TIME";
                    var10 = var9.getBytes();
                    var8.write(var10);
                } else {
                    byte[] var17 = var4.getBytes();
                    var8.write(var17);
                }

                var9 = Integer.toString(this.id) + "\t";
                var10 = var9.getBytes();
                var3 = this.CustomerName + "\t\t";
                byte[] var11 = var3.getBytes();
                Date var12 = new Date();
                String var13 = var12.toString();
                byte[] var14 = var13.getBytes();
                var8.write(10);
                var8.write(var10);
                var8.write(var11);
                var8.write(var14);
            } catch (Exception var15) {
                System.out.println("Name is empty!");
            }
        }

    }

    void showMenu() {
        try {
            BufferedReader var2 = new BufferedReader(new InputStreamReader(System.in));
            this.getData();

            int var1;
            label36:
            do {
                System.out.println("......................................................");
                System.out.println("Your Customer ID : " + this.id);
                System.out.println("1.Display Account Information");
                System.out.println("2.Deposit");
                System.out.println("3.Withdraw");
                System.out.println("4.Previous Transactions");
                System.out.println("5.Exit");
                System.out.println("......................................................");
                System.out.print("Enter your choice : ");
                var1 = Integer.parseInt(var2.readLine());
                System.out.println("\n");
                switch (var1) {
                    case 1:
                        System.out.println("Your Name:" + this.CustomerName);
                        System.out.println("Your Customer ID: " + this.id);
                        System.out.println("Your Current Balanace: " + this.balance);
                        break;
                    case 2:
                        while(true) {
                            try {
                                System.out.print("Enter amount to deposit : ");
                                int var6 = Integer.parseInt(var2.readLine());
                                if (var6 <= 0) {
                                    throw new Exception();
                                }

                                this.deposit(var6);
                                continue label36;
                            } catch (Exception var4) {
                                System.out.println("Please enter avalid amount!");
                            }
                        }
                    case 3:
                        if (this.balance == 0) {
                            System.out.println("There is no balance");
                        } else {
                            this.withdraw();
                        }
                        break;
                    case 4:
                        this.getPreviousTransaction();
                        break;
                    case 5:
                        FileOutputStream var3 = new FileOutputStream("AccountDetails.txt", true);
                        var3.write(10);
                        var3.write(10);
                        break;
                    default:
                        System.out.println("Invaild choice , Enter again");
                }
            } while(var1 != 5);
        } catch (Exception var5) {
        }

        System.out.println("Thank you, " + this.CustomerName + "!");
        System.out.println("\t TOPIC : BANKING APPLICATION \n\n");

    }

    public static void main(String[] var0) throws Exception {
        BankAccount var1 = new BankAccount();
        var1.showMenu();
    }
}
