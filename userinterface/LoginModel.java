package userinterface;



import java.sql.*;

public class LoginModel {
private Connection conection;
  
  public LoginModel () {
   conection = SqliteConnection.Connector();
   if (conection == null) {
   System.out.println("Verbindung steht nicht!");
    System.exit(1);}
  }
  
  public boolean isDbConnected() {
   try {
	   return !conection.isClosed();
   		} catch (SQLException e) {
   			e.printStackTrace();
   			return false;
   		}
 }
  
   public boolean isLogin(String user,String pass) throws SQLException {
	   PreparedStatement preparedStatement = null;
	   ResultSet resultSet = null;
	   String query = "select id from user where username = ? and password = ? ";
	   try {
		   preparedStatement = conection.prepareStatement(query);
		   preparedStatement.setString(1, user);
		   preparedStatement.setString(2, pass);
		   resultSet = preparedStatement.executeQuery();
		   if(resultSet.next()) {
		   		Userinterface.playerName = user;
		   		Userinterface.playerID = resultSet.getInt(0);
			   return true;
		   }
		   else {
			   return false;
		   }
	   }catch (Exception e) {
		   return false;
		   
	   }finally {
		   preparedStatement.close();
		   resultSet.close();
	   }	   
   }  
}

