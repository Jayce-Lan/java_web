package com.learn.servlet;

import com.learn.entity.Student;
import com.learn.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private StudentRepository sr = new StudentRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断传入的请求类型
        String method = req.getParameter("method");
        //如果是删除数据，那么传入值为delete，如果为空，则为查询
        if (method == null)
            method = "findAll";

        switch (method) {
            case "findAll":
                //返回视图+数据
                List<Student> list = sr.findAll();
                req.setAttribute("list", list);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
            case "deleteById":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                sr.deleteById(id);
                resp.sendRedirect("/student");
                break;
            case "findById":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                //获取查询结果
                req.setAttribute("student", sr.findById(id));
                //将查询结果返回到页面
                req.getRequestDispatcher("update.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "add":
                //（添加用户）获取用户表单传入的内容并且处理
                String name = req.getParameter("name");
                //解决乱码问题(对客户端请求的重新编码)
//                req.setCharacterEncoding("UTF-8");
                String scoreStr = req.getParameter("score");
                Double score = Double.parseDouble(scoreStr);
                sr.add(name, score);
                break;
            case "update":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                name = req.getParameter("name");
                scoreStr = req.getParameter("score");
                score = Double.parseDouble(scoreStr);
                sr.update(id, name, score);
                break;
        }
        //让浏览器重新发起请求，返回/student页面
        resp.sendRedirect("/student");
    }
}
