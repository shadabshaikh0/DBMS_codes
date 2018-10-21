import java.sql.*;
import java.util.*;

public class AssignmentNo2 {
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/";
	   static final String USER = "root";
	   static final String PASS = "Shadab@786";

	   public static void main(String[] args) {
		   
		   Scanner input = new Scanner(System.in);
		   int option;
		   Connection conn = null;
		   Statement stmt = null;
		   try{
			      Class.forName("com.mysql.jdbc.Driver");
			      conn = DriverManager.getConnection(DB_URL, USER, PASS);
			      stmt = conn.createStatement();
			      
			      String sqlQuery="";
			      
			      String databaseName="",tableName="",columnName="",dataType="",referenceTableName="",referenceColumnName="",viewName="",condition="",indexName="";
			      
			      String[] columnNames = new String[10];
			      String[] dataTypes = new String[10];
			      
			      int columnNo=0;
			      char choice;
			      
			      while(true){
				      System.out.println("1.Create Database");
				      System.out.println("2.Drop Database");
				      System.out.println("3.Create Table");
				      System.out.println("4.Drop Table");
				      System.out.println("5.Alter Table");
				      System.out.println("6.show Table");
				      System.out.println("7.Operations on View");
				      System.out.println("8.Operations on index");
				      System.out.println("9.SHOW ALL VIEWS");
				      System.out.println("0.Exit");
				      System.out.println("Enter Choice");
				      option = input.nextInt();
				      switch (option) {
						case 1:
							  System.out.println("Enter Database Name");
							  databaseName = "mysqlassignments";
//							  databaseName = input.next();
						      sqlQuery = "CREATE DATABASE IF NOT EXISTS "+ databaseName;
						      stmt.executeUpdate(sqlQuery);
						      System.out.println("Database created successfully...");
						      stmt.executeQuery("use "+databaseName);
						      
							break;
						case 2:
							  System.out.println("Enter Database Name");
							  databaseName = input.next();
						      sqlQuery = "DROP DATABASE "+ databaseName;
						      stmt.executeUpdate(sqlQuery);
						      System.out.println("Database dropped successfully...");
							break;
						case 3:

							  System.out.println("Enter Table Name");
							  tableName = input.next();
							  System.out.println("Enter No of Columns");
							  columnNo = input.nextInt();
							  for(int i=0 ;i<columnNo;i++){
								System.out.println("Enter "+ (i+1) +" column Name");
								columnNames[i] = input.next();
								System.out.println("Enter dataType of "+ (i+1) +" column Name");
								dataTypes[i]   = input.next();
 							  }

							  sqlQuery = "create Table "+tableName+" ( ";
							  
							  for (int i = 0; i < columnNo; i++) {
								 if(i == columnNo -1){
									 sqlQuery += columnNames[i]+" "+dataTypes[i];									 
								 }
								 else{
									 sqlQuery += columnNames[i]+" "+dataTypes[i]+",";									 
								 }
							  }

							  System.out.println("Do you want to add PRIMARY KEY(Y/N)");
						      choice = input.next().charAt(0);
						      if(choice == 'y' || choice == 'Y'){
						    	  System.out.println("Enter Column Name");
						    	  columnName = input.next();
						    	  sqlQuery += ",";
						    	  sqlQuery += "PRIMARY KEY ("+columnName+")";						    	  
						      }

							  System.out.println("Do you want to add FOREIGN KEY(Y/N)");
						      choice = input.next().charAt(0);
						      if(choice == 'y' || choice == 'Y'){
						    	  System.out.println("Enter Column Name");
						    	  columnName = input.next();

						    	  System.out.println("Enter REFERENCE TABLE Name");
						    	  referenceTableName = input.next();

						    	  System.out.println("Enter REFERENCE COLUMN Name");
						    	  referenceColumnName = input.next();

						    	  sqlQuery += ",";	
						    	  sqlQuery += " CONSTRAINT FK_"+tableName+" FOREIGN KEY ("+columnName+") "+ "REFERENCES " + referenceTableName + "("+referenceColumnName+")"+ " ON DELETE CASCADE ON UPDATE CASCADE";						    	  
						      }

							  sqlQuery += " )";							  
						      
						      stmt.executeUpdate(sqlQuery);
						      
						      System.out.println("Table Created successfully...");
							break;
						case 4:
							  System.out.println("Enter Table Name");
							  tableName = input.next();
							  
							  System.out.println("Is TABLE has PRIMARY KEY?(Y/N)");
							  choice = input.next().charAt(0);
							  if(choice == 'y' || choice == 'Y'){
								  sqlQuery = "ALTER TABLE "+tableName+" DROP PRIMARY KEY";
							      stmt.executeUpdate(sqlQuery);								  
							  }

							  System.out.println("Is TABLE has FOREIGN KEY?(Y/N)");
							  choice = input.next().charAt(0);
							  if(choice == 'y' || choice == 'Y'){
								  sqlQuery = "ALTER TABLE "+ tableName +" DROP FOREIGN KEY FK_"+tableName;
								  stmt.executeUpdate(sqlQuery);								  
							  }
							  
						      sqlQuery = "DROP TABLE "+ tableName;
						      stmt.executeUpdate(sqlQuery);
						      System.out.println("Table dropped successfully...");
							break;
							
						case 5:
							int suboption1;
							do{
								System.out.println("1.ADD COLUMN");
								System.out.println("2.DROP COLUMN");
								System.out.println("3.MODIFY COLUMN");
								System.out.println("Enter choice");
								suboption1 = input.nextInt();
								switch (suboption1)
								{
									case 1:		
										  System.out.println("Enter Table Name");
										  tableName = input.next();
										  System.out.println("Enter Column Name");
										  columnName = input.next();
										  System.out.println("Enter dataType");
										  dataType = input.next();
										  sqlQuery = "ALTER TABLE "+tableName+" ADD "+columnName+ " "+dataType;
									      stmt.executeUpdate(sqlQuery);
									      System.out.println("Column Added successfully...");
										break;
									case 2:
										  System.out.println("Enter Table Name");
										  tableName = input.next();
										  System.out.println("Enter Column Name");
										  columnName = input.next();
										  sqlQuery = "ALTER TABLE "+tableName+" DROP COLUMN "+columnName;
									      stmt.executeUpdate(sqlQuery);
									      System.out.println("Column Dropped successfully...");
										break;
									case 3:
										  System.out.println("Enter Table Name");
										  tableName = input.next();
										  System.out.println("Enter Column Name");
										  columnName = input.next();
										  System.out.println("Enter dataType");
										  dataType = input.next();
										  sqlQuery = "ALTER TABLE "+tableName+" MODIFY COLUMN "+columnName+ " "+dataType;
									      stmt.executeUpdate(sqlQuery);
									      System.out.println("Column Modified successfully...");										
										break;
									case 0:
										break;
	
									default:
										break;
								}
								
							}while(suboption1 != 0);
							break;
							
						case 6:
							  System.out.println("Enter Table Name");
							  tableName = input.next();
							  sqlQuery = "select * from  "+tableName;
							  ResultSet rs = stmt.executeQuery(sqlQuery);
							  ResultSetMetaData rsmd = rs.getMetaData();
							  int columnsNumber = rsmd.getColumnCount();
							  
							  for(int i = 1 ; i <= columnsNumber; i++){
							        System.out.print(rsmd.getColumnName(i) + " ");
							  }
							  System.out.println();

							  while (rs.next()) {
								  for(int i = 1 ; i <= columnsNumber; i++){
							        System.out.print(rs.getString(i) + " ");
							      }
							    System.out.println();
							  }
							  break;
						
						case 7:
							int suboption2;
							do{
								System.out.println("1.CREATE VIEW");
								System.out.println("2.REPLACE / UPDATE VIEW");
								System.out.println("3.DROP VIEW");
								System.out.println("Enter choice");
								suboption2 = input.nextInt();
								switch (suboption2)
								{
									case 1:		
										  System.out.println("Enter VIEW Name");
										  viewName = input.next();
										  System.out.println("Enter Table Name");
										  tableName = input.next();
										  System.out.println("Enter No of Columns");
										  columnNo = input.nextInt();
										  for(int i=0 ;i<columnNo;i++){
											System.out.println("Enter "+ (i+1) +" column Name");
											columnNames[i] = input.next();
			 							  }
										  System.out.println("Enter condition");
										  condition = input.next();
										  
										  sqlQuery ="";
										  sqlQuery += "CREATE VIEW "+ viewName +" AS SELECT ";

										  for (int i = 0; i < columnNo; i++) {
												 if(i == columnNo -1){
													 sqlQuery += columnNames[i];									 
												 }
												 else{
													 sqlQuery += columnNames[i]+",";									 
												 }
										  }
										  sqlQuery += " FROM "+ tableName + " WHERE "+ condition;										  
										  stmt.executeUpdate(sqlQuery);
									      System.out.println("VIEW created successfully...");
									      input.nextLine();
										break;
									case 2:
										  System.out.println("Enter VIEW Name");
										  viewName = input.next();
										  System.out.println("Enter Table Name");
										  tableName = input.next();
										  System.out.println("Enter No of Columns");
										  columnNo = input.nextInt();
										  for(int i=0 ;i<columnNo;i++){
											System.out.println("Enter "+ (i+1) +" column Name");
											columnNames[i] = input.next();
			 							  }
										  System.out.println("Enter condition");
										  condition = input.next();
										  
										  sqlQuery ="";
										  sqlQuery += "CREATE OR REPLACE VIEW "+ viewName +" AS SELECT ";

										  for (int i = 0; i < columnNo; i++) {
												 if(i == columnNo -1){
													 sqlQuery += columnNames[i];									 
												 }
												 else{
													 sqlQuery += columnNames[i]+",";									 
												 }
										  }
										  sqlQuery += "FROM "+ tableName + " WHERE "+ condition;										  
										  stmt.executeUpdate(sqlQuery);
									      System.out.println("VIEW updated successfully...");
										break;
									case 3:
										  System.out.println("Enter VIEW Name");
										  viewName = input.next();
										  sqlQuery ="DROP VIEW "+ viewName;
										  stmt.executeUpdate(sqlQuery);
									      System.out.println("VIEW dropped successfully...");										 
										break;
									case 0:
										break;
	
									default:
										break;
								}
								
							}while(suboption2 != 0);
							break;
						case 8:
							int suboption3;
							do{
								System.out.println("1.CREATE INDEX");
								System.out.println("2.DROP INDEX");
								System.out.println("Enter choice");
								suboption3 = input.nextInt();
								switch (suboption3)
								{
									case 1:		
										  System.out.println("Enter INDEX Name");
										  indexName = input.next();
										  System.out.println("Enter Table Name");
										  tableName = input.next();
										  System.out.println("Enter No of Columns");
										  columnNo = input.nextInt();
										  for(int i=0 ;i<columnNo;i++){
											System.out.println("Enter "+ (i+1) +" column Name");
											columnNames[i] = input.next();
			 							  }
										  
										  sqlQuery ="";
										  sqlQuery += "CREATE INDEX "+ indexName +" ON "+ tableName+" (";

										  for (int i = 0; i < columnNo; i++) {
												 if(i == columnNo -1){
													 sqlQuery += columnNames[i];									 
												 }
												 else{
													 sqlQuery += columnNames[i]+",";									 
												 }
										  }
										  sqlQuery += ") ";										  
										  stmt.executeUpdate(sqlQuery);
									      System.out.println("INDEX created successfully...");
										break;
									case 2:
										  System.out.println("Enter INDEX Name");
										  indexName = input.next();
										  System.out.println("Enter Table Name");
										  tableName = input.next();
										  sqlQuery = "ALTER TABLE "+tableName+" DROP INDEX "+indexName;
										  stmt.executeUpdate(sqlQuery);
									      System.out.println("INDEX dropped successfully...");										 
										break;
									case 0:
										break;
	
									default:
										break;
								}
								
							}while(suboption3 != 0);
							break;
							
						case 0:
							System.exit(0);
						default:
							break;
					}
				      
			      }      
		   }
		   catch(Exception e){
			   	input.close();
				System.out.println(e);  
		   }

	}

}
