package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeDao;
import dto.NoticeDto;

@WebServlet("/notice")
public class NoticeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("NoticeController");
		req.setCharacterEncoding("utf-8");
		String param = req.getParameter("param");
		
		if(param.equals("write")) {
			 System.out.println("NoticeWrite success");
	    	  resp.sendRedirect("NoticeWrite.jsp");
		
		
		
		
		}else if(param.equals("writeAf")) {
			
			String id = req.getParameter("id");
			System.out.println("id 체크" +id);
			String title = req.getParameter("title");
			System.out.println("제목체크="+title);
			String content = req.getParameter("content");
			System.out.println("내용체크="+content);
			NoticeDao dao = NoticeDao.getInstance();
			dao.writeNotice(new NoticeDto(id, title, content));
			
			resp.sendRedirect("notice?param=list");
			
			
		}else if(param.equals("list")) {
			
			NoticeDao dao = NoticeDao.getInstance();
			
			
			List<NoticeDto> list = dao.getnoticeData();
			
			
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			NoticeDto dto = dao.getNotice();
			
			
			
			req.setAttribute("dto", dto);
			req.setAttribute("Nlist", list);
			
			req.getRequestDispatcher("NoticePage.jsp").forward(req, resp);
			
		}else if(param.equals("detail")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			NoticeDao dao = NoticeDao.getInstance();
			NoticeDto dto = dao.getNoticedetail(seq);
			
			dao.readcount(seq);
			req.setAttribute("dto", dto);
			System.out.println("디테일확인");
			 req.getRequestDispatcher("NoticeDetail.jsp").forward(req, resp);
			
		}
		else if(param.equals("update")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			NoticeDao dao = NoticeDao.getInstance();
			NoticeDto dto = dao.getNoticedetail(seq);
			req.setAttribute("dto", dto);
			req.getRequestDispatcher("NoticeUpdate.jsp").forward(req, resp);
			
		}
		else if(param.equals("updateAf")) {
			System.out.println("공지사항 수정이후 컨트롤러");
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			String noId = req.getParameter("id");
			String noTitle = req.getParameter("title");
			String noContent = req.getParameter("content");
			
			System.out.println(noId +":"+ noTitle +":"+ noContent);
			
			NoticeDao dao = NoticeDao.getInstance();
			dao.updateNotice(seq, new NoticeDto(noId, noTitle, noContent));
			System.out.println("공지사항 수정 컨트롤러");
			resp.sendRedirect("notice?param=list");
		}
		else if(param.equals("delete")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			NoticeDao dao = NoticeDao.getInstance();
			dao.deleteNotice(seq);
			resp.sendRedirect("notice?param=list");
		}
		
		
	}
}
