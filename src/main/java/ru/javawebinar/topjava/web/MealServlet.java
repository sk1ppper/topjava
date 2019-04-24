package ru.javawebinar.topjava.web;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out =response.getWriter();

        request.setAttribute("lists",MealsUtil.getFilteredWithExceeded(MealsUtil.meals, LocalTime.MIN,LocalTime.MAX,2000));
        request.getRequestDispatcher("/meals.jsp").forward(request,response);
    }
}
