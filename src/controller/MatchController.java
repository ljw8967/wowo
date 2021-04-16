package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.MatchDao;
import dao.MustEatDao;
import dto.CommentDto;
import dto.MatchDto;
import dto.MustEatDto;


@WebServlet("/match")
public class MatchController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MatchController");
		req.setCharacterEncoding("utf-8");
		
		String param = req.getParameter("param");
		
		if(param.equals("Matchlist")) {
			System.out.println("Matchlist");
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
			
			
			
	         MatchDao dao = MatchDao.getInstance();
	         List<MatchDto> list = dao.getMatchPagingList(choice, search, page);
	         
	        
	         req.setAttribute("list", list);
	         
	         
				int len = dao.getAllMatch(choice, search);
				System.out.println("총 게시글" + len);
				int bbsPage = len/9;		// 23 -> 2
				if((len % 9) > 0){
					bbsPage = bbsPage + 1;
				}
				req.setAttribute("bbsPage", bbsPage + "");
				req.setAttribute("pageNumber", page + "");
				
				// 추가
				req.setAttribute("choice", choice);
				req.setAttribute("search", search);
				
	      
	         req.getRequestDispatcher("Matchlist.jsp").forward(req, resp);
	     
		
		
		
		
		
		 }else if(param.equals("matchWrite")) {
			 System.out.println("matchWrite sucess");
			 resp.sendRedirect("MatchWrite.jsp");
		 
		 
		 
		 }else if(param.equals("matchdetail")) {
			 
		  System.out.println("1/4 Mustdetail Controller");
	    	
			//detail
	    	  String  getseq = req.getParameter("seq");
	    	  System.out.println("matchdetial");
	    	  int seq = Integer.parseInt(getseq);
	    	  
	    	  MatchDao dao = MatchDao.getInstance();
	    	  //dao.downcount(seq);
	    	  
	    	  //조회수
	    	  dao.readcount(seq);
	    	  
	    	  
	    	  System.out.println("2/4 Mustdetail Controller");
	    	  //디테일에서 시퀀스에대한 SELECT
	    	  MatchDto dto = dao.getMatch(seq);
	    	  
	    	  CommentDao Ddao = CommentDao.getInstance();
	    	  List<CommentDto> list = Ddao.getMatchCommentData(seq);
	    	 
	    	 
	    	  //commentController에서 받아온 getAttribute Dto list
	    	  /*
	    	  List<CommentDto> list = (List<CommentDto>) req.getAttribute("Slist");
	    	  req.setAttribute("Clist", list);
	    	  CommentDto Cdto = (CommentDto) req.getAttribute("Sdto");
	    	  req.setAttribute("Cdto", Cdto);
	    	  */
	    	  
	    	  req.setAttribute("Mlist",list);
	    	  req.getAttribute("Mdto");
	    	  
	    	  req.setAttribute("dto", dto);
	    	  
	    	  req.getRequestDispatcher("Matchdetail.jsp").forward(req, resp);
		
		
	}else if(param.equals("matchUpdate")) {
  	  
  	  String  getseq = req.getParameter("seq");
  	  int seq = Integer.parseInt(getseq);
  	  
  	  MatchDao dao = MatchDao.getInstance();
  	  MatchDto dto = dao.getMatch(seq);
  	  
  	  req.setAttribute("dto", dto);
  	  
  	  
  	  req.getRequestDispatcher("MatchUpdate.jsp").forward(req, resp);
    }
	
}
	
}
