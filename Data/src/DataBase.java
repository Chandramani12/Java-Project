import java.sql.*;

public class DataBase {
    Connection con;

    public Connection connection() {
        try {
        	String url="jdbc:sqlserver://localhost:1433;databaseName=library;encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2;trustStore=C:/Program Files/Java/jdk1.8.0_251/jre/lib/security/cacerts;trustStorePassword=changeit;trustStoreType=jks";

            //String url = "jdbc:sqlserver://localhost:1433;databaseName=library";
            String user = "sa";
            String password = "sql321";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public boolean loginPage(String username, String password) {
        Connection c = connection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean success = false;
        try {
            String query = "SELECT * FROM admin WHERE cust_id=?";
            pstmt = c.prepareStatement(query);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                if (dbPassword.equals(password)) {
                    success = true;
                    System.out.print("Login successfully");
                } else {
                    System.out.println("Incorrect password.");
                }
            } else {
                System.out.println("Username does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
    
}
