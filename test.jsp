<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
  <%
    String hostname = "unknown";
    try {
        hostname = java.net.InetAddress.getLocalHost().getHostName();
    }catch (Exception e) {
        e.printStackTrace();
    }
    if (hostname.equals("ip-172-31-11-93.ap-northeast-1.compute.internal")) hostname += " (EC1)";
    if (hostname.equals("ip-172-31-37-60.ap-northeast-1.compute.internal")) hostname += " (EC2)";
  %>
  <h1>hostname: <%= hostname %></h1>
