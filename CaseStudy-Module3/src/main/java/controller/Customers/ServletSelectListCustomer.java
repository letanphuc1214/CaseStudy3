package controller.Customers;

import dao.CustomerDAO;
import dao.ProvinceDAO;
import model.Customer;
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

@WebServlet(name = "ServletSelectList", urlPatterns = "/customers")
public class ServletSelectListCustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    private ProvinceDAO provinceDAO;

    public void init() {
        customerDAO = new CustomerDAO();
        provinceDAO = new ProvinceDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        List<Customer> listCustomer = null;
        try {
            listCustomer = customerDAO.selectAllCustomer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listCustomer", listCustomer);
        List<Province> listProvince = null;
        try {
            listProvince = provinceDAO.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listProvince", listProvince);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/list.jsp");
        dispatcher.forward(request, response);
    }
}
