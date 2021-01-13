package controller.CustomerTypes;

import dao.CustomerTypeDAO;
import dao.ProvinceDAO;
import model.Customer;
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

@WebServlet(name = "ServletCustomerTypeDelete", urlPatterns = "/customerTypes/delete")
public class ServletCustomerTypeDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerTypeDAO customerTypeDAO;

    public void init() {
        customerTypeDAO = new CustomerTypeDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        CustomerType customer = new CustomerType();
        customer.setIdCustomerType(id);
        try {
            customerTypeDAO.delete(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        CustomerType existingUser = null;
        try {
            existingUser = customerTypeDAO.findById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/delete.jsp");
        request.setAttribute("customerType", existingUser);
        dispatcher.forward(request, response);
    }
}
