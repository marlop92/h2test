package pl.mlopatka;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stmt = conn.createStatement();
        String dropTable = "DROP TABLE abc;";
        String createTable = "CREATE TABLE abc ( \n" +
                "   id INT NOT NULL, \n" +
                "   title VARCHAR(50) NOT NULL, \n" +
                "   author VARCHAR(20) NOT NULL \n" +
                ");";
        String insertValues = "INSERT INTO abc \n" +
                "VALUES(1, 'Title', 'Author');";
        String insertValues2 = "INSERT INTO abc \n" +
                "VALUES(2, 'Jan', 'Walski');";
        String getValues = "SELECT * FROM abc;";
        stmt.execute(dropTable);
        stmt.execute(createTable);
        stmt.executeUpdate(insertValues);
        stmt.executeUpdate(insertValues2);
        ResultSet rs = stmt.executeQuery(getValues);
        int counter = 0;
        while(rs.next()){
            counter++;
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String author = rs.getString("author");

            System.out.println("ROW " + counter + ": " + id + ", " + title + ", " + author);
        }

        conn.close();
    }
}
