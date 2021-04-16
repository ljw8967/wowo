package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QnaDao;
import dto.QnaDto;

@WebServlet("/qna")
public class QnaController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("QnaController doProcess");
		
		req.setCharacterEncoding("utf-8");	
		
		String param = req.getParameter("param");
		if(param.equals("qnalist")) {
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			int page = 0;
			if(spage != null && !spage.equals("")) {
				page = Integer.parseInt(spage);
			}
			if(choice == null) {
				choice = "";
			}
			if(search == null) {
				search = "";
			}
			
			QnaDao dao = QnaDao.getInstance();
			List<QnaDto> list = dao.getQnaPagingList(choice, search, page);
			req.setAttribute("list", list);
			
			int len = dao.getAllQna(choice, search);
			int qnaPage = len / 10;		// 23 -> 2
			if((len % 10) > 0){
				qnaPage = qnaPage + 1;
			}
			req.setAttribute("qnaPage", qnaPage + "");
			req.setAttribute("pageNumber", page + "");
			
			forward("qnalist.jsp", req, resp);			
		}
		else if(param.equals("qnawrite")) {
			resp.sendRedirect("qnawrite.jsp");
		}
		else if(param.equals("qnawriteAf")) {
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			QnaDao dao = QnaDao.getInstance();
			boolean isS = dao.writeQna(new QnaDto(id, title, content));
			if(!isS) {
				resp.sendRedirect("qnawrite.jsp");
			}
			
			resp.sendRedirect("qna?param=qnalist");
		}
		else if(param.equals("qnadetail")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			
			QnaDao dao = QnaDao.getInstance();
			
			dao.readcount(seq);		// 조회수 늘리기
			
			QnaDto dto = dao.getQna(seq);			
			
			req.setAttribute("qna", dto);
			
			forward("qnadetail.jsp", req, resp);
		}
		else if(param.equals("qnaupdate")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			
			QnaDao dao = QnaDao.getInstance();
			QnaDto dto = dao.getQna(seq);			
			
			req.setAttribute("qna", dto);
			
			forward("qnaupdate.jsp", req, resp);
		}
		else if(param.equals("qnaupdateAf")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq.trim());

			String title = req.getParameter("title");
			String content = req.getParameter("content");

			QnaDao dao = QnaDao.getInstance();
			boolean isS = dao.updateQna(seq, title, content);			
			if(!isS) {
				resp.sendRedirect("qna?param=qnaupdate&seq=" + sseq);
			}
			resp.sendRedirect("qna?param=qnalist");
		}
		else if(param.equals("qnadelete")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			System.out.println("seq:" + seq);

			QnaDao dao = QnaDao.getInstance();
			dao.deleteQna(seq);
			
			resp.sendRedirect("qna?param=qnalist");
		}
		else if(param.equals("answer")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			QnaDto dto = QnaDao.getInstance().getQna(seq);
			
			req.setAttribute("qna", dto);
			
			forward("qnaanswer.jsp", req, resp);
		}
		else if(param.equals("answerAf")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			QnaDao dao = QnaDao.getInstance();

			boolean isS = dao.answer(seq, new QnaDto(id, title, content));
			if(!isS){
				resp.sendRedirect("qna?param=answer&seq=" + seq);
			}
			resp.sendRedirect("qna?param=qnalist");
		}
	}
	

	private void forward(String arg, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);
		
	}
	
	

}
