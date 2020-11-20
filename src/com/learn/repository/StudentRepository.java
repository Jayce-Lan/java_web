package com.learn.repository;

//用于查询数据库的类

import com.learn.entity.Student;
import com.learn.util.JDBCTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StudentRepository {
    /**
     * 将表数据遍历，并添加到List集合中，便于查询
     * @return 返回一个遍历了表数据的集合
     */
    public List<Student> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();

        try {
            conn = JDBCTools.getConnection();
            String sql = "select id, name, score, birthday from student";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Student student = null;
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getNString("name");
                Double score = rs.getDouble("score");
                Date birthday = rs.getDate("birthday");
                student = new Student(id, name, score, birthday);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn, ps, rs);
        }
        return list;
    }

    /**
     * 添加数据库中学生的数据
     * @param name 学生姓名
     * @param score 学生成绩
     */
    public void add(String name, Double score) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCTools.getConnection();
            String sql = "insert into student (name, score, birthday) values (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, score);
            ps.setDate(3, new java.sql.Date(1));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn, ps, null);
        }
    }

    /**
     * 通过id删除表中信息
     * @param id 传入一个学生的id值
     */
    public void deleteById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCTools.getConnection();
            String sql = "delete from student where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn, ps, null);
        }
    }

    /**
     * 通过id查找到某个学生，返回一个Student对象
     * 该目的主要是为修改做准备，一般情况下，修改信息的流程是先查找，后修改
     * @param id 学生id
     * @return 通过id查找到的对应的学生对象
     */
    public Student findById(Integer id) {
        Student student = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCTools.getConnection();
            String sql = "select * from student where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Integer StudentId = rs.getInt("id");
                String name = rs.getNString("name");
                Double score = rs.getDouble("score");
                Date birthday = rs.getDate("birthday");
                student = new Student(StudentId, name, score, birthday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn, ps, rs);
        }

        return student;
    }

    /**
     * 修改学生表的信息
     * @param id
     * @param name
     * @param score
     */
    public void update(Integer id, String name, Double score) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCTools.getConnection();
            String sql = "update student set name = ?, score = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, score);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn, ps, null);
        }
    }
}
