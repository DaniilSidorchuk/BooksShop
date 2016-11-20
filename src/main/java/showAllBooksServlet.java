import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet ("/showallbooks")
public class showAllBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Database database = new Database();

        List<Book> books = database.showAllBooks();
        out.println("Book Author Gengre Cover Availability Price");
        for (int i = 0; i < books.size(); i++) {
            out.print(books.get(i).getBook());
            out.print(books.get(i).getAuthor());
            out.print(books.get(i).getGengre());
            out.print(books.get(i).getCover());
            out.print(books.get(i).getStock());
            out.print(books.get(i).getPrice());
            out.println();
        }
    }
}
