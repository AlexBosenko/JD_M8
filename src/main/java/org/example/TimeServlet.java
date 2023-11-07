package org.example;

import org.example.utils.TimeServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        String formattedUtc = TimeServletUtils.getUtcDateTime(req.getParameter("timezone"));

        writer.print("<p>${dateTime}</p>"
                .replace("${dateTime}", formattedUtc));
        writer.close();
    }
}
