package com.accounts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BankAccountsDTO 
{

	private static final String getAllAccountsHistory = "select id,accountNumber,transferType,amount,closedBalance from accountsHistory";

	private static final String getAccountBalance = "select accountType,balance from accounts";
	
	public int addAccount(String acctName,String acctNo,String acctType, double balance)
	{
		try(Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				
				) {	
			String insert = "INSERT INTO accounts VALUES (" + acctName + acctNo  + acctType  + balance +  ")";
			int rs = stmt.executeUpdate(insert);
			String history = "INSERT INTO accountsHistory VALUES (" + acctNo + "credit" + balance +  ")";
			int count = stmt.executeUpdate(history);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public double getBalance(String acctNo)
	{
		try(Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(getAccountBalance + " where accountNumber=" + acctNo)) {	
			
				double balance = rs.getDouble("balance");
				
				System.out.println(" balance : " + balance);
				return balance;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean getAllAccountsHistory()
	{
		try(Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(getAllAccountsHistory)) {	
			boolean result = false;
			while(rs.next()){
				int id = rs.getInt("id");
				String accountNumber = rs.getString("accountNumber");
				String transferType = rs.getString("transferType");
				String amount = rs.getString("amount");
				String closedBalance = rs.getString("closedBalance");
				System.out.println(id + "," +accountNumber+ "," +transferType+ "," +amount+ "," +closedBalance);
			}
			result = true;
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkWithDrawLimit(String acctNo,String date)
	{
		try(Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select sum(amount) from accountsHistory where transationType=debit and accountNumber=" + acctNo + " and transactionDate ="+ date + "where sum(amount) < 100000")) {	
			boolean result = false;
			while(rs.next()){
				result = true;
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int depositAccount(String acctNo,double amount)
	{
		try(Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(getAccountBalance + " where accountNumber=" + acctNo)) {	
			
			while(rs.next()){
				double balance = rs.getDouble("balance");
				String type = rs.getString("accountType");
				
				double closedBalance = balance;
				balance = balance + amount;
				String sql = "UPDATE accounts " +
		                   "SET balance = "+ balance + " WHERE accountNumber=" + acctNo;
				int update = stmt.executeUpdate(sql);
				String history = "INSERT INTO accountsHistory VALUES (" + acctNo + "credit" + amount + balance + ")";
				int count = stmt.executeUpdate(history);
				return update;				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int withdrawAccount(String acctNo,double amount)
	{
		try(Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(getAccountBalance + " where accountNumber=" + acctNo)) {	
			
			while(rs.next()){
				double balance = rs.getDouble("balance");
				String type = rs.getString("accountType");
				if(type.equalsIgnoreCase("savings")) {
					if(checkWithDrawLimit(acctNo, "")) {
						System.out.println("per day limit reached couldnt do withdraw");
						break;
					}
				}
				double closedBalance = balance;
				if(balance >= amount) {
					balance = balance - amount;
					String sql = "UPDATE accounts " +
			                   "SET balance = "+ balance + " WHERE accountNumber=" + acctNo;
					int update = stmt.executeUpdate(sql);
					String history = "INSERT INTO accountsHistory VALUES (" + acctNo + "debit" + amount + balance + ")";
					int count = stmt.executeUpdate(history);
					return update;	
				}
							
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int transferToAccount(String sourceAcctNo,String destAcctNo,double amount)
	{
		int result = withdrawAccount(sourceAcctNo, amount);
		if(result !=0)
			result = depositAccount(destAcctNo, amount);
		else
			System.out.println("transfer to bank account failed");
		return result;

	}
	
	public void addInterest()
	{
		
		
	}
	
	
}
