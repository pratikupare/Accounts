package com.accounts;

import java.util.Scanner;


public class BankAccountApplication 
{
	
	public static void main(String[] args)
	{
		
        try (Scanner sc = new Scanner(System.in))
        {
        	int option;
            AccountsService accService = AccountsService.getInstance();
            do {
                System.out.println("Main Menu\n 1.Add Account\n 2. Display Accounts History\n 3. Transfer to Other Account\n 4. Deposit\n 5. Withdrawal\n 6.Add Interest\n 7.Display Balance\n 8.Exit ");
                    System.out.println("Select Option :");
                    String acctNo = "";
                    double balance = 0;
                    option = sc.nextInt();
                    switch (option) {
                        case 1:
                        	System.out.print("Enter Account Name you Want to Add: ");
                            String acctName = sc.next();
                            System.out.print("Enter Account Type you Want to Add:\n 1.Current Account\n 2.Savings Account ");
                            int acctType = sc.nextInt();
                            String type = "Current";
                            if(acctType != 1 && acctType != 2) {
                            	System.out.print("Enter valid account type: ");
                            	break;
                            }
                            else if(acctType == 2) {
                            	type = "Savings";
                            }
                            System.out.print("Enter Account Number you Want to Add: ");
                            acctNo = sc.next();
                            System.out.print("Enter Balance you Want to Add at the start of account: ");
                            balance = sc.nextDouble();
                            
                            boolean added = accService.addAccount(acctName, type,acctNo, balance);;
                            if (!added) {
                                System.out.println("Failed..to Add Account..");
                            }
                            break;
                        case 2:
                            accService.getAllAccountsHistory();
                            break;
                        case 3:
                        	System.out.print("Enter Account Number from where you Want to transfer Amount: ");
                            acctNo = sc.next();
                            System.out.print("Enter Account Number to where you Want to transfer : ");
                            String destNo = sc.next();
                            System.out.print("Enter Amount you Want to transfer : ");
                            balance = sc.nextDouble();
                            boolean transfered = accService.transferToAccount(acctNo,destNo,balance);
                            if (!transfered) {
                                System.out.println("Failed..to withdraw amount..");
                            }
                            break;
                        case 4:
                        	System.out.print("Enter Account Number you Want to deposit Amount: ");
                            acctNo = sc.next();
                            System.out.print("Enter Amount you Want to deposit : ");
                            balance = sc.nextDouble();
                            boolean deposited = accService.depositAccount(acctNo, balance);
                            if (!deposited) {
                                System.out.println("Failed..to deposit amount..");
                            }
                            break;

                        case 5:
                        	System.out.print("Enter Account Number you Want to withdraw Amount: ");
                            acctNo = sc.next();
                            System.out.print("Enter Amount you Want to withdraw : ");
                            balance = sc.nextDouble();
                            boolean withdraw = accService.withdrawAccount(acctNo, balance);
                            if (!withdraw) {
                                System.out.println("Failed..to withdraw amount..");
                            }
                            break;
                        case 6:
                        	
                            break;
                        case 7:
                        	System.out.print("Enter Account Number you Want to check Balance: ");
                            acctNo = sc.next();
                            accService.getBalance(acctNo);
                            break;
                        case 8:
                            System.out.println("Good Bye..");
                            System.exit(0);
                            break;
                    }
                }
                while (option != 8);
        }
        catch (Exception c)
        {
        	
        }
	}
}
