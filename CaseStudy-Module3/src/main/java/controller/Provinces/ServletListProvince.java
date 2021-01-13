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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletListProvince", urlPatterns = "/provinces")
public class ServletListProvince extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProvinceDAO provinceDAO;

    public void init() {
        provinceDAO = new ProvinceDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        List<Province> listProvince = null;
        try {
            listProvince = provinceDAO.selectAllProvince();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("listProvince", listProvince);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/dist/listProvince.jsp");
        dispatcher.forward(request, response);
    }
}
