package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.MustEatDao;
import dto.CommentDto;
import dto.MustEatDto;


@WebServlet("/MustEat")
public class MustEatController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MustEatController");
		req.setCharacterEncoding("utf-8");
		
		String param = req.getParameter("param");
		
		if(param.equals("MustEatlist")) {
			System.out.println("MustEatlist");
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
			
			
			
	         MustEatDao dao = MustEatDao.getInstance();
	         List<MustEatDto> list = dao.getMustPagingList(choice, search, page);
	         
	         //추천 상위 3개 랭킹
	         List<MustEatDto> listrank = dao.getranking();
	        
	         
	         
	         
	         req.setAttribute("list", list);
	         req.setAttribute("listrank", listrank);
	     	
				int len = dao.getAllmust(choice, search);
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
				
	      
	         req.getRequestDispatcher("MustlistGa.jsp").forward(req, resp);
	     
		
		
		
		
		
		 }else if(param.equals("MEWrite")) {
	    	  System.out.println("MEWrite success");
	    	  resp.sendRedirect("MustWrite.jsp");
	    	  
	    	  
	      }else if(param.equals("Mustdetail")) {
	    	  System.out.println("1/4 Mustdetail Controller");
	    	
			//detail
	    	  String  getseq = req.getParameter("seq");
	    	  System.out.println("Mustdetail");
	    	  int seq = Integer.parseInt(getseq);
	    	  
	    	  MustEatDao dao = MustEatDao.getInstance();
	    	  //dao.downcount(seq);
	    	  
	    	  //조회수
	    	  dao.readcount(seq);
	    	  
	    	  
	    	  System.out.println("2/4 Mustdetail Controller");
	    	  //디테일에서 시퀀스에대한 SELECT
	    	  MustEatDto dto = dao.getMustEat(seq);
	    	  
	    	  CommentDao Ddao = CommentDao.getInstance();
	    	  List<CommentDto> list = Ddao.getCommentData(seq); 
	    	 
	    	 
	    	  //commentController에서 받아온 getAttribute Dto list
	    	  /*
	    	  List<CommentDto> list = (List<CommentDto>) req.getAttribute("Slist");
	    	  req.setAttribute("Clist", list);
	    	  CommentDto Cdto = (CommentDto) req.getAttribute("Sdto");
	    	  req.setAttribute("Cdto", Cdto);
	    	  */
	    	  
	    	  req.setAttribute("Clist",list);
	    	  req.getAttribute("Cdto");
	    	  
	    	  req.setAttribute("dto", dto);
	    	  
	    	  req.getRequestDispatcher("Mustdetail.jsp").forward(req, resp);
	    	  
	      }else if(param.equals("MustUpdate")) {
	    	  
	    	  String  getseq = req.getParameter("seq");
	    	  int seq = Integer.parseInt(getseq);
	    	  
	    	  MustEatDao dao = MustEatDao.getInstance();
	    	  MustEatDto dto = dao.getMustEat(seq);
	    	  
	    	  req.setAttribute("dto", dto);
	    	  
	    	  
	    	  req.getRequestDispatcher("MustUpdate.jsp").forward(req, resp);
	      }
		
		
		
		
		
		
	}
	
}
