/**
 * HelloWorldServlet.java
 * <p>
 * Copyright (c) 2014 Teamnet. All Rights Reserved.
 * <p>
 * This source file may not be copied, modified or redistributed,
 * in whole or in part, in any form or for any reason, without the express
 * written consent of Teamnet.
 **/

package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello World Servlet expose get method to say hello to input user
 */
public class HelloWorldServlet extends HttpServlet {

    /**
     * This method is used to map a GET request from the client side
     * @param request the HttpServletRequest instance
     * @param response the HttpServletResponse instance
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = "";

        // Set the response type
        response.setContentType("text/html");

        // Obtain the user from the request instance
        user = request.getParameter("user");

        //Write the response content
        response.getWriter().write("Hello <b>" + user + "</b>");
    }

}
