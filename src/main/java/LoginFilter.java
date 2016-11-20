import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public class LoginFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        Database database = new Database();
        List<User>users= database.allUsers();
        int sameUser = -1;
        for (int i = 0; i < users.size() ; i++) {
            if(login.equals(users.get(i).getLogin()) && password.equals(users.get(i).getPassword())){
                sameUser = 1;
                break;
            }
            if(login.equals(users.get(i).getLogin())){
                sameUser = 0;
                break;
            }
        }
        switch (sameUser){
            case 1:
                filterChain.doFilter(servletRequest, servletResponse);
                break;
            case 0:
                System.out.println("incorrect password");
                throw new ServletException("incorrect password");
            case -1:
                System.out.println("User isn`t registered");
                throw new ServletException("User isn`t registered");

        }

    }




    @Override
    public void destroy() {

    }
}
