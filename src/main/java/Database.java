

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    private String sqlReq;
    List <Book> books;
    List<User> users;

public List showAllBooks(){
    try {
        Properties properties = loadProperties();

        Connection connection = DriverManager
                .getConnection(properties.getProperty("url"),
                        properties.getProperty("username"),
                        properties.getProperty("password"));
        System.out.println("Connected");
        books = getCollectionOfAllBooksFromDatabase(connection);
        return books;
    } catch (SQLException | IOException e) {
        System.out.println("connection refused");
    }
        return null;
}

public List allUsers(){
    try {
        Properties properties = loadProperties();

        Connection connection = DriverManager
                .getConnection(properties.getProperty("url"),
                        properties.getProperty("username"),
                        properties.getProperty("password"));
        System.out.println("Connected");
        users = getCollectionOfAllUsersFromDatabase(connection);
        return users;
    } catch (SQLException | IOException e) {
        System.out.println("connection refused");
    }
    return null;

}

private List getCollectionOfAllUsersFromDatabase(Connection connection) throws SQLException {
    sqlReq = "select name, login, password from users";

    Statement statement = connection.createStatement();
    statement.executeQuery(sqlReq);
    ResultSet resultSet = statement.getResultSet();
    List<User> users = new ArrayList<>();
    while (resultSet.next()){
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        users.add(new User(name,login,password));
    }
    return users;
}

private List getCollectionOfAllBooksFromDatabase(Connection connection) throws SQLException {
    sqlReq = "SELECT books.id, books.book, authors.author, gengre.gengre, cover.cover, books.stock, books.price FROM books, authors, gengre, cover WHERE (authors.id=books.id_author AND gengre.id=books.id_gengre AND cover.id=books.id_cover)";

    Statement statement = connection.createStatement();
    statement.executeQuery(sqlReq);

    ResultSet resultSet = statement.getResultSet();
    List<Book> books = new ArrayList();
    while (resultSet.next()) {
    int id = resultSet.getInt("id");
    String book = resultSet.getString("book");
    String author = resultSet.getString("author");
    String gengre = resultSet.getString("gengre");
    String cover = resultSet.getString("cover");
    int stock = resultSet.getInt("stock");
    int price = resultSet.getInt("price");
    books.add(new Book(id,book,author,gengre,cover,stock,price));
    }
    return books;
}


private Properties loadProperties() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();
        properties.load(stream);
        return properties;
    }

}


