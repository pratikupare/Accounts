package com.accounts;

public class AccountsService 
{

	private static AccountsService accountsService = null;
	
	private static BankAccountsDTO bankAccountsDTO = null;
	
	private AccountsService() 
	{
		bankAccountsDTO = new BankAccountsDTO();
	}
	
	public static AccountsService getInstance()
	{
		if(accountsService == null) {
			accountsService = new AccountsService();
		}
		return accountsService;
			
	}
	
	
	public boolean addAccount(String acctName,String acctType,String acctNo,double balance)
	{
		boolean result = false;
		int count = bankAccountsDTO.addAccount(acctName, acctNo, acctType, balance);
		if(count != 0)
			result = true;
		return result;
	}
	
	public boolean getAllAccountsHistory()
	{
		boolean result = false;
		result = bankAccountsDTO.getAllAccountsHistory();
		return result;
		
	}
	
	public double getBalance(String acctNo)
	{
		double amount = bankAccountsDTO.getBalance(acctNo);
		
		return amount;
		
	}
	
	
	public boolean depositAccount(String acctNo,double amount)
	{
		int count = bankAccountsDTO.depositAccount(acctNo, amount);
		boolean result = false;
		if(count !=0)
			result = true;
		return result;
	}
	
	public boolean withdrawAccount(String acctNo,double amount)
	{
		int count = bankAccountsDTO.withdrawAccount(acctNo, amount);
		boolean result = false;
		if(count !=0)
			result = true;
		return result;
	}
	
	public boolean transferToAccount(String sourceAcctNo,String destAcctNo,double amount)
	{
		int count = bankAccountsDTO.transferToAccount(sourceAcctNo, destAcctNo, amount);
		boolean result = false;
		if(count !=0)
			result = true;
		return result;
	}
	
	public boolean addInterest()
	{
		boolean result = false;
		
		return result;
		
	}
}
