package com.accounts;


public class BankUtils 
{
	
	private static BankUtils bankUtils = null;
	
	private BankUtils() {
		
	}
	
	public static BankUtils getInstance()
	{
		if(bankUtils == null)
				bankUtils = new BankUtils();
		return bankUtils;
	}
	
	public boolean validateString(String str)
	{
		return str != null && !str.isEmpty(); 
	}
}
