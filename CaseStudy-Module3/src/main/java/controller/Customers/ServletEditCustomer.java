package controller.Customers;

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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletEdit", urlPatterns = "/customers/edit")
public class ServletEditCustomer extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullname");
        Date dateOfBirth = Date.valueOf(request.getParameter("dateofbirth"));
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phonenumber");
        int cmt = Integer.parseInt(request.getParameter("cmt"));
        int idProvince = Integer.parseInt(request.getParameter("province"));
        int idCustomerType = Integer.parseInt(request.getParameter("customertype"));
        Customer customer = new Customer(id, fullName, dateOfBirth, address, gender, email, phoneNumber, cmt, idProvince, idCustomerType);
        try {
            customerDAO.update(customer);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        CustomerDAO.created = true;
        request.setAttribute("customer",customer);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/edit.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = null;
        try {
            customer = customerDAO.getCustomerById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/edit.jsp");
        request.setAttribute("customer", customer);
        dispatcher.forward(request, response);
    }
}
