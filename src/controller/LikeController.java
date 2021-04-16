package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MustEatDao;


import net.sf.json.JSONObject;

@WebServlet("/likeController")
public class LikeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int seq = Integer.parseInt(req.getParameter("seq"));
		
		MustEatDao dao = MustEatDao.getInstance();
		dao.updatelike(seq);
		
		int like = dao.selectlike(seq);
		System.out.println("좋아요 개수 = " + like);
		
		JSONObject obj = new JSONObject();
		obj.put("like", like);
		
		resp.setContentType("application/x-json; charset=UTF-8");
		resp.getWriter().print(obj);
		
	}
}
