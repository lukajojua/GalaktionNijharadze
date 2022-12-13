import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws SQLException {
        Products products=new Products("Lasha Xelauri",2.0,"Unknown","2000");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/gaga", "root", "root")) {
                CreateDb(con);
                addIntoTable(con,products);
                quantity(con);
                forth(con);
                Update(con);
                Delete(con);

            }


        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }

    }
    private static void CreateDb(Connection con) throws Exception{
        Statement statement= con.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS Products(Name VARCHAR(50) NOT NULL,Price DOUBLE NOT NULL,Manufacturer VARCHAR(60) NOT NULL,release_year INT NOT NULL,ID INTEGER PRIMARY KEY AUTO_INCREMENT);");
    }

    private static void addIntoTable(Connection con,Products products)throws Exception{
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO products(Name,Price,Manufacturer,release_year) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, products.getName());
        pstmt.setDouble(2,products.getPrice());
        pstmt.setString(3,products.getManufacturer());
        pstmt.setString(4,products.getReleaseDate());
        pstmt.executeUpdate();
    }
    private static Map<String,String>  quantity(Connection con)throws Exception{
        Map<String,String>result=new HashMap<>();
        Statement statement=con.createStatement();
       ResultSet  resultSet= statement.executeQuery("SELECT  Name, count(Name) as Total FROM gaga.products GROUP BY Name ORDER BY Total DESC");
        while (resultSet.next()){
            String Total=resultSet.getString("Total");
            String Name=resultSet.getString("Name");
           result.put(Total,Name);
        }
        return result;
    }
    private static Map<String,Long>forth(Connection con) throws Exception{
        ArrayList<String> result=new ArrayList<>();
        Statement statement=con.createStatement();
        ResultSet  resultSet= statement.executeQuery("SELECT  Name FROM products");
        while (resultSet.next()) {
            String Name = resultSet.getString("Name");
            result.add(Name);
        }
       return result.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));



    }
    private static void Update(Connection con)throws Exception{
        PreparedStatement statement=con.prepareStatement( "update products set Name = ? where ID = ?");
        statement.setString(1,"Gaga ");
        statement.setInt(2,1);
        statement.executeUpdate();
    }
    private static void Delete(Connection con)throws Exception{
        PreparedStatement statement=con.prepareStatement("delete from products WHERE ID=?");
        statement.setInt(1,1);
        statement.executeUpdate();
    }

}
