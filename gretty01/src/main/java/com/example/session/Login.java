package com.example.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Login() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    request.getRequestDispatcher("View/Session/Login.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    // ログイン名をリクエストから取得する
    HttpSession session = request.getSession(false);
    // 既にセッションが存在する場合は一度破棄する
    if (session != null) {
      log("セッション破棄 セッションID=[" + session.getId() + "]");
      session.invalidate();
    }

    try {
      // セッションを新規で作成する
      session = request.getSession(true);
      log("セッション作成 セッションID=[" + session.getId() + "]");

      request.setCharacterEncoding("UTF-8");
      // 各フォームデータを取得する
      String name = request.getParameter("name");

      // ログイン名をセッションで保持する
      session.setAttribute("name", name);

      // ログイン後ホーム画面へ自動遷移する
      //request.getRequestDispatcher("Home").forward(request, response);
      response.sendRedirect(request.getContextPath() + "/Home");
    } catch (Exception e) {
      log("セッション作成 失敗");
      // 例外によりセッションの作成に失敗
      e.printStackTrace();
    }
  }

}
