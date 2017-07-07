package com.my.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {
    String errorPage;

   public String getErrorPage() {
        return errorPage;
    }

   public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("----------------------");
        HttpSession session = (HttpSession) request.getSession();
        System.out.println("--"+session.getAttribute("role"));
       
        System.out.println("-----"+request.getParameter("registeruser"));
       if(request.getParameter("registeruser")!=null){
          System.out.println("1");
    	   if(request.getParameter("registeruser").equals("Register User")){
                System.out.println("set user!!");
            	session.setAttribute("role", "user");
            }  
        }
        if(request.getParameter("registerhotel")!=null){
            if(request.getParameter("registerhotel").equals("Register Hotel")){
                session.setAttribute("role", "hotel");
            }
        }
       
       if(session.getAttribute("role") == null){
    	   if(request.getRequestURI().equals("/lab8/user/register")){
    		   System.out.println("set user!!");
           	   session.setAttribute("role", "user");
           	   return true;
    	   }
    	   System.out.println("----"+request.getRequestURI());
            if((request.getRequestURI().contains("hotel/"))||
                    (request.getRequestURI().contains("user/"))||(request.getRequestURI().contains("admin/")))
            {
                System.out.println("in interceptor1");
                System.out.println("1 -false");
                response.sendRedirect(request.getContextPath());
                return false;
            }
            System.out.println("in interceptor2");
            return true;
        }

       if(session.getAttribute("role") != null){
            System.out.println("in interceptor3");
            if((request.getRequestURI().contains("admin") && session.getAttribute("role").equals("admin")) ||
                    (request.getRequestURI().contains("hotel") && session.getAttribute("role").equals("hotel"))||
                    (request.getRequestURI().contains("user") && session.getAttribute("role").equals("user")))
            {
                System.out.println("in interceptor4");
                return true;
            }else if((!request.getRequestURI().contains("admin"))&&
                    (!request.getRequestURI().contains("hotel"))&&
                    (!request.getRequestURI().contains("user")))
            {
                System.out.println("in interceptor5");
                return true;
            }
        }

       System.out.println("NOT VALID!!");
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath());
        System.out.println("1 -false");
        return false;
    }
}