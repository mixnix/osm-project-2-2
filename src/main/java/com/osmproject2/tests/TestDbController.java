package com.osmproject2.tests;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.DriverManager;

@Controller
public class TestDbController {
    @GetMapping("/test-db")
    public String index(Model m) {
        String user = "postgres";
        String pass = "admin";

        String jdbcUrl = "jdbc:postgresql://localhost/postgres";
        String driver = "org.postgresql.Driver";

        m.addAttribute("connectionWorks", "nothing works");

        try {


            Class.forName(driver);

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

            m.addAttribute("connectionWorks", "yes");

            myConn.close();

        }catch (Exception e){
            e.printStackTrace();
            m.addAttribute("connectionWorks", "no");

        }

        return "test-db";
    }
}
