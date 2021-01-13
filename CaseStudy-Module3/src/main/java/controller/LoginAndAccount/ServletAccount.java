package controller.LoginAndAccount;

import dao.AccountDAO;
import dao.CustomerDAO;
import dao.LoginDAO;
import dao.ProvinceDAO;
import model.Account;
import model.Customer;
import model.Province;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletAccount", urlPatterns = "/accounts")
public class ServletAccount extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO;

    public void init() {
        accountDAO = new AccountDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Account accountChangePassword = (Account) session.getAttribute("account");
        accountChangePassword.setPassword(request.getParameter("newpassword"));
        AccountDAO accountDAO = new AccountDAO();
        try {
            accountDAO.update(accountChangePassword);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        AccountDAO.created = true;
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/changePass.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        Account accountChangePassword = (Account) session.getAttribute("account");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/changePass.jsp");
        request.setAttribute("account", accountChangePassword);
        dispatcher.forward(request, response);
    }
}
