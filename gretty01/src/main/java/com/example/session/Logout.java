package com.example.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Logout() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // ログアウト時はセッションを破棄する
    HttpSession session = request.getSession(false);
    // 既にセッションが存在する場合は一度破棄する
    if (session != null) {
      log("セッション破棄 セッションID=[" + session.getId() + "]");
      session.invalidate();
    }

    // セッション破棄後にログイン画面に遷移する
    //request.getRequestDispatcher("View/Session/Login.jsp").forward(request, response);
    response.sendRedirect(request.getContextPath() + "/Home");
  }
}
