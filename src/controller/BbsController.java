package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.PdsDao;

import dto.PdsDto;

@WebServlet("/bbs")
public class BbsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doprocess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doprocess(req, resp);
	}

	public void doprocess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PdsDao dao = PdsDao.getInstance();
		System.out.println("PdsController doProcess");
		
		
		req.setCharacterEncoding("utf-8");	

		String param = req.getParameter("param");
		
		if(param.equals("bbslist")) {
			
			System.out.println("파란으로오니 목록아1");
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
			
			System.out.println("파란으로오니 목록아2");
			
			List<PdsDto> list = dao.getPdspagingList(choice, search, page);
			req.setAttribute("list", list);
			
			int len = dao.getAllPbs(choice, search);
			
			int bbsPage = len / 10;		// 23 -> 2
			if((len % 10) > 0){
				bbsPage = bbsPage + 1;
			}
			req.setAttribute("bbspage", bbsPage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len + "");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
			
			forward("bbslist.jsp", req, resp);
			}
			
		else if(param.equals("filewrite")) {
			
			resp.sendRedirect("filewrite.jsp");
		}
	
		else if(param.equals("filewriteAf")) {
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String filename = req.getParameter("filename");
			System.out.println(id+" "+title+" "+content+" "+filename+" ");
			
			
			
			
			
			boolean isS = dao.writePds(new PdsDto(id, name, title, content, filename));
			if(isS){
				resp.sendRedirect("filewrite.jsp");	
			}
			
			 resp.sendRedirect("bbs?param=bbslist"); 
		}
		
		else if(param.equals("filedetail")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
		
			System.out.println("seq2");
			System.out.println(seq);
			dao.pdsReadCount(seq);
			
			PdsDto dto = dao.getPds(seq);
			System.out.println(dto);
			
			req.setAttribute("pds", dto);
			
			System.out.println("seq3");
			
			forward("filedetail.jsp", req, resp);
		//	forward("bbs?param=filedetail", req, resp);
			
			
		}else if(param.equals("fileUpdate")) {
		  	  
		  	  String  getseq = req.getParameter("seq");
		  	  int seq = Integer.parseInt(getseq);
		  	  
		  	  PdsDao dao1 = PdsDao.getInstance();
		  	  PdsDto dto = dao1.getPds(seq);
		  	  
		  	  req.setAttribute("dto", dto);
		  	  
		  	  
		  	  req.getRequestDispatcher("pdsupdate.jsp").forward(req, resp);
		    }

		else if(param.equals("answer")) {

			
			 int seq = Integer.parseInt( req.getParameter("seq") );
			 PdsDto dto = PdsDao.getInstance().getPds(seq);
			  
			 	System.out.println("answer 1");
			  req.setAttribute("bbs", dto);
			
			  System.out.println("answer 2");
			  forward("answerbbs.jsp", req, resp);
			 
		}
	else if(param.equals("answerAf")) {
		int seq = Integer.parseInt( req.getParameter("seq") );
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String wdate = req.getParameter("wdate");
		
		System.out.println("answer 3");
		boolean isS = dao.answer(seq, new PdsDto(id, name ,title, content, wdate));
		if(!isS) {
			resp.sendRedirect("bbs?param=answer&seq=" + seq);
		}else{
		resp.sendRedirect("bbs?param=bbslist");
	}
	
}
/*	 else if(param.equals("delete")) {
         int seq = Integer.parseInt( req.getParameter("seq") );
         System.out.println("seq:" + seq);

         PdsDao pds = PdsDao.getInstance();
         dao.deletePds(seq);
         
         resp.sendRedirect("bbs?param=bbslist");
      }	*/
		
		
		
		
	}
	
	
	
	
	
	
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);			
	}
}

