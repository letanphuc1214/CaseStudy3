package controller.Customers;

import dao.CustomerDAO;
import dao.ProvinceDAO;
import model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletDelete", urlPatterns = "/customers/delete")
public class ServletDeleteCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    private ProvinceDAO provinceDAO;

    public void init() {
        customerDAO = new CustomerDAO();
        provinceDAO = new ProvinceDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = new Customer();
        customer.setId(id);
        try {
            customerDAO.delete(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<Customer> listCustomer = null;
        try {
            listCustomer = customerDAO.selectAllCustomer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/list.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingUser = null;
        try {
            existingUser = customerDAO.getCustomerById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/delete.jsp");
        request.setAttribute("customer", existingUser);
        dispatcher.forward(request, response);
    }
}
