package controller.CustomerTypes;

import dao.CustomerDAO;
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

@WebServlet(name = "ServletCustomerTypeAdd", urlPatterns = "/customerTypes/add")
public class ServletCustomerTypeAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerTypeDAO customerTypeDAO;

    public void init() {
        customerTypeDAO = new CustomerTypeDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String customerTypeName = request.getParameter("customertypename");

        CustomerType newCustomerType = new CustomerType(customerTypeName);
        try {
            customerTypeDAO.insertTypeCustomerStore(newCustomerType);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CustomerTypeDAO.created = true;
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/createCustomerType.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/createCustomerType.jsp");
        dispatcher.forward(request, response);
    }
}
