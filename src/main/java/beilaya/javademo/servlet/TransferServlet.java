package beilaya.javademo.servlet;

import beilaya.javademo.service.AccountException;
import beilaya.javademo.service.AccountService;
import beilaya.javademo.service.impl.AccountServiceImpl;
import beilaya.javademo.util.SqlSessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TransferServlet extends HttpServlet {

    private static final AccountService ACCOUNT_SERVICE = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取请求参数
            String payer = request.getParameter("payer");
            String payee = request.getParameter("payee");
            int amount = Integer.parseInt(request.getParameter("money"));
            // 处理转账请求
            ACCOUNT_SERVICE.transfer(payer, payee, amount);
            response.sendRedirect("success.jsp");
        } catch (AccountException e) {
            response.sendRedirect("failure.jsp");
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        } finally {
            SqlSessionUtil.close();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if("POST".equals(method)) {
            doPost(request, response);
        } else {
            response.sendRedirect("404.html");
        }
    }
}
