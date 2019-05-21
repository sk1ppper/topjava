package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private UserService service;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new UserServiceImpl(new InMemoryUserRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        //log.debug(repository.get(1).toString()+"like");
        //log.debug(repository.getAll().toString());
        request.setAttribute("users",service.getAll());
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
