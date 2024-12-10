package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Home() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    request.setCharacterEncoding("UTF8");

    String name = null;
    HttpSession session = request.getSession(false);
    // name属性が存在しない場合はセッション情報なしとして取り扱う
    if (session != null && (name = (String) session.getAttribute("name")) == null) {
      session = null;
    }

    // セッション情報がないもしくは期待した状態でなければログイン画面に遷移する
    if (session == null) {
      request.getRequestDispatcher("Login").forward(request, response);
      return;
    }

    // ホーム画面に名前を表示する
    request.setAttribute("name", name);

    int intervalTime = session.getMaxInactiveInterval();

    request.setAttribute("timeout", intervalTime);

    // ホーム画面を表示する
    request.getRequestDispatcher("View/Home/Home.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
