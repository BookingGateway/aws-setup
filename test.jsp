<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
  <%
    String hostname = "unknown";
    try {
        hostname = java.net.InetAddress.getLocalHost().getHostName();
    }catch (Exception e) {
        e.printStackTrace();
    }
  %>
  <p>hostname: <%= hostname %></p>

