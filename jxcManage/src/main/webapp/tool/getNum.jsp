<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
Object o = session.getAttribute("userLoginVcode");
	int num=9999999;
 if(o!=null){
  num= (Integer)o; 
 }
 out.print(num);
%>

