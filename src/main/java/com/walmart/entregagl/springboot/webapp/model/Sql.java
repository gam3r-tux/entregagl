package com.walmart.entregagl.springboot.webapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

/**
 * autor jose ramos
 *
 */
public class App 
{
  //The SQL Server JDBC Driver is in 
  //C:\Program Files\Microsoft JDBC Driver 6.0 for SQL Server\sqljdbc_6.0\enu\auth\x64
  private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
  
  //The JDBC connection URL which allows for Windows authentication is defined below.
  private static final String jdbcURL = "jdbc:sqlserver://44.237.122.125:1433;databasename=app_mf2c_db_dev;encrypt=true;trustServerCertificate=true";
  //To make Windows authenticaion work we have to set the path to sqljdbc_auth.dll at the command line
  
    public static void main( String[] args )
    {
        System.out.println("Program started");
        try
        {
        Class.forName(jdbcDriver).newInstance();
        System.out.println("JDBC driver loaded");
        }
        catch (Exception err)
        {
        System.err.println("Error loading JDBC driver");
        err.printStackTrace(System.err);
        System.exit(0);
        }
        
        Connection databaseConnection= null;
        try
        {
            String usr = "maurol";
            String pwd = "Pswlorma000";
        //Connect to the database
        databaseConnection = DriverManager.getConnection(jdbcURL, usr, pwd);
        System.out.println("Connected to the database");
        }
        catch (SQLException err)
        {
        System.err.println("Error connecting to the database");
        err.printStackTrace(System.err);
        System.exit(0);
        }
        
        try
        {
        //declare the statement object
        Statement sqlStatement = databaseConnection.createStatement();
    
        //Build the command string
        String commandString="INSERT INTO app_mf2c_db_dev.dbo.EDGL_ARCH_MASTER_ENTRADA "
                             + " (VENDOR, DIVISION, STORE, INV_COST, SIGNO, ACCOUNT, TRAN_ID) "
                             + " VALUES(10, 1, 2, 300.00, '', 1000, 10);";
        
        //print the command string to the screen
        System.out.println("\nCommand string:");
        System.out.println(commandString);
        
        //execute the command using the execute method
        sqlStatement.execute(commandString);
        
        System.out.println("Closing database connection");

        //close the database connection
        databaseConnection.close();
        }
        catch (SQLException err)
        {
        System.err.println("SQL Error");
        err.printStackTrace(System.err);
        System.exit(0);
        }
        System.out.println("Program finished");
    }
}
