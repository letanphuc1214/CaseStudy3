package controller.CustomerTypes;

import dao.CustomerDAO;
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

@WebServlet(name = "ServletCustomerTypeView", urlPatterns = "/customerTypes/view")
public class ServletCustomerTypeView extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    private ProvinceDAO provinceDAO;
    private CustomerTypeDAO customerTypeDAO;

    public void init() {
        customerDAO = new CustomerDAO();
        provinceDAO = new ProvinceDAO();
        customerTypeDAO = new CustomerTypeDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Customer> listCustomer = null;
        try {
            listCustomer = customerTypeDAO.selectViewAllCustomerType(id);
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
        List<CustomerType> listCustomerType = null;
        try {
            listCustomerType = customerTypeDAO.findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listCustomerType", listCustomerType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/list.jsp");
        dispatcher.forward(request, response);
    }
}
