package controller.Provinces;

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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletAddProvince", urlPatterns = "/provinces/add")
public class ServletAddProvince extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProvinceDAO provinceDAO;

    public void init() {
        provinceDAO = new ProvinceDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String provinceName = request.getParameter("provincename");

        Province newProvince = new Province(provinceName);
        try {
            provinceDAO.insertProvinceStore(newProvince);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CustomerDAO.created = true;
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/createProvince.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/createProvince.jsp");
        dispatcher.forward(request, response);
    }
}
