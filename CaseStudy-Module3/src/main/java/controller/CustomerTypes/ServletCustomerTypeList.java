package controller.CustomerTypes;

import dao.CustomerTypeDAO;
import dao.ProvinceDAO;
import model.CustomerType;
import model.Province;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletCustomerTypeList", urlPatterns = "/customerTypes")
public class ServletCustomerTypeList extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerTypeDAO customerTypeDAO;

    public void init() {
        customerTypeDAO = new CustomerTypeDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        List<CustomerType> listCustomerType = null;
        try {
            listCustomerType = customerTypeDAO.selectAllCustomerType();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listCustomerType", listCustomerType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/listCustomerType.jsp");
        dispatcher.forward(request, response);
    }
}
