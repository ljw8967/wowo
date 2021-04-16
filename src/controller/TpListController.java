package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import dao.TpListDao;
import dto.CommentDto;
import dto.TpListDto;

@WebServlet("/TpList")
public class TpListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TpListController doProcess");
		
		req.setCharacterEncoding("UTF-8");
		
		String param = req.getParameter("param");
		
		System.out.println(param);		
		
		if(param.equals("Tplist")) {
			
			String choice = req.getParameter("choice");
			//String choice = (String)req.getAttribute("choice");
		//	System.out.println("write확인");
			String search = req.getParameter("search");
			//String search = (String)req.getAttribute("search");
		//	System.out.println("write search 확인");
			String spage = req.getParameter("pageNumber");
			//String spage = (String)req.getAttribute("pageNumber");
		//	System.out.println("write3확인");
			
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
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpPagingList(choice, search, page);
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			
			int len = dao.getAllTplist(choice, search);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}

			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_list.jsp", req, resp);
			
		//	req.getRequestDispatcher("tp_list.jsp").forward(req, resp);

		} else if (param.equals("Tplist1")) {
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			
			int page = 0;
			if(spage != null && !spage.equals("")) {
				page = Integer.parseInt(spage);
			}
			if(choice == null) {
				choice = "title";
			}
			if(search == null) {
				search = "서울";
			}
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpPagingList(choice, search, page);
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			
			int len = dao.getAllTplist(choice, search);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}

			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_list.jsp", req, resp);
		
		
		} else if (param.equals("Tplist2")) {
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			
			int page = 0;
			if(spage != null && !spage.equals("")) {
				page = Integer.parseInt(spage);
			}
			if(choice == null) {
				choice = "title";
			}
			if(search == null) {
				search = "제주";
			}
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpPagingList(choice, search, page);
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			
			int len = dao.getAllTplist(choice, search);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}

			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_list.jsp", req, resp);
		
		} else if (param.equals("Tplist3")) {
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			
			int page = 0;
			if(spage != null && !spage.equals("")) {
				page = Integer.parseInt(spage);
			}
			if(choice == null) {
				choice = "title";
			}
			if(search == null) {
				search = "인천";
			}
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpPagingList(choice, search, page);
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			
			int len = dao.getAllTplist(choice, search);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}

			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_list.jsp", req, resp);
		
		} else if (param.equals("Tplist4")) {
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			
			int page = 0;
			if(spage != null && !spage.equals("")) {
				page = Integer.parseInt(spage);
			}
			if(choice == null) {
				choice = "title";
			}
			if(search == null) {
				search = "부산";
			}
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpPagingList(choice, search, page);
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			
			int len = dao.getAllTplist(choice, search);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}

			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_list.jsp", req, resp);
			
		} else if (param.equals("Tplist5")) {
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			
			int page = 0;
			if(spage != null && !spage.equals("")) {
				page = Integer.parseInt(spage);
			}
			if(choice == null) {
				choice = "title";
			}
			if(search == null) {
				search = "강원도";
			}
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpPagingList(choice, search, page);
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			
			int len = dao.getAllTplist(choice, search);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}

			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_list.jsp", req, resp);
			
		} else if (param.equals("Tplist6")) {
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			String concept = req.getParameter("concept");
			
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
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpCPagingList(choice, search, page, concept);
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			
			int len = dao.getAllTplist(choice, search);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}

			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
			
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_listsakura.jsp", req, resp);
			
		} else if (param.equals("Tplist7")) {
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			String concept = req.getParameter("concept");
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
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpCPagingList(choice, search, page, concept);
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			
			int len = dao.getAllTplist(choice, search);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}

			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_listcamping.jsp", req, resp);
			
		} else if (param.equals("Tplistr")) {
			
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
			System.out.println("page = " + page);
			TpListDao dao = TpListDao.getInstance();
			List<TpListDto> list = dao.getTpPagingList(choice, search, page);
			
			/*
			 * for (int i = 0; i < list.size(); i++) { System.out.println("리스트컨트롤러 랭킹.겟" +
			 * list.get(i)); }
			 */
			
			
			List<TpListDto> listrank = dao.tprank();
		/*	for (int i = 0; i < listrank.size(); i++) {
				System.out.println("리스트컨트롤러 랭킹.겟" + listrank.get(i));
			}
		*/	
			
		//	System.out.println("list: " + list);
			req.setAttribute("list", list);
			req.setAttribute("listrank", listrank);
			
			int len = dao.getAllTplist(choice, search);
			System.out.println("총게시물 수:" + len);
			int tplistpage = len / 9;
			if((len % 9) > 0) {
				tplistpage = tplistpage + 1;
			}
			
			req.setAttribute("tplistpage", tplistpage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("len", len+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
		//	System.out.println(tplistpage);
		//	System.out.println(page);
			
			forward("tp_listr.jsp", req, resp);
			
		//	req.getRequestDispatcher("tp_listr.jsp").forward(req, resp);
		//	resp.sendRedirect("tp_listr.jsp");
		
		} else if (param.equals("Tpwrite")) {
			
			//TpList?param=Tpwrite
		//	System.out.println("Tpwrite 확인");
			resp.sendRedirect("tp_list_write.jsp");
			
		//	System.out.println("tp_list_writecheck");
			
		} else if (param.equals("TpwriteAf")) {
			String place = req.getParameter("place");
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String concept = req.getParameter("concept");
	//		System.out.println("place : " + place);
	//		System.out.println("id : " + id);
	//		System.out.println("name: " + name);
	//		System.out.println("title : " + title);
	//		System.out.println("content: " + content);
	//		System.out.println("concept: " + concept);
			TpListDao dao = TpListDao.getInstance();
			
			boolean isS = dao.writeTplist(new TpListDto(place, id, name, title, content, concept));
			if(isS) {
				forward("TpList?param=Tplist", req, resp);
			} else {
				forward("tp_list_write.jsp", req, resp);
			}
			
		} else if (param.equals("tp_list_detail")) {
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			System.out.println("tp컨트롤러 디테일 seq 값 : " + seq);
			TpListDao dao = TpListDao.getInstance();
			TpListDto dto = dao.getTplistdetail(seq);
			
			System.out.println("리스트컨트롤러 dto값:" + dto);
			
			CommentDao cdao = CommentDao.getInstance();
			
			
			
			List<CommentDto> Clist = cdao.getCommenttpData(seq);
			System.out.println("tp컨트롤러 코멘트 리스트 값" + Clist);
			
			
			
			req.setAttribute("dto", dto);
			req.getAttribute("Cdto");
			req.setAttribute("Clist", Clist);
			
			
			
			forward("tp_list_detail.jsp", req, resp);
			
		} else if (param.equals("Tplistupdate")) {
			
			String sseq = (String)req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
//			String place = req.getParameter("place");
//			String title = req.getParameter("title");
//			String content = req.getParameter("content");
//			String concept = req.getParameter("concept");
//			System.out.println("tplistupdate부분");
//			System.out.println("seq=" + seq);
//			System.out.println("place=" + place);
//			System.out.println("title=" + title);
//			System.out.println("content= " + content);
//			System.out.println("concept= " + concept);
			
			TpListDao dao = TpListDao.getInstance();
			TpListDto dto = dao.getTplistdetail(seq);
			
			req.setAttribute("dto", dto);
			
			req.getRequestDispatcher("tp_list_update.jsp").forward(req, resp);
		
	//		forward("tp_list_update.jsp", req, resp);	
	//		resp.sendRedirect("tp_list_update.jsp");
			
/*		} else if (param.equals("TplistupdateAf")) {
		
	//		System.out.println("컨트롤러 업데이트확인1");
	//		System.out.println("seq" + req.getParameter("seq"));
			int seq = Integer.parseInt(req.getParameter("seq"));
	//		System.out.println("seq" + seq);
	//		System.out.println("컨트롤러 업데이트확인2");
	//		int seq = (int)req.getAttribute("seq");
	//		String place = (String)req.getAttribute("place");
			String place = req.getParameter("place");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String concept = req.getParameter("concept");
			
			System.out.println("tplistupdateaf부분");
			System.out.println("seq=" + seq);
			System.out.println("place=" + place);
			System.out.println("title=" + title);
			System.out.println("content= " + content);
			System.out.println("concept= " + concept);
			
			TpListDao dao = TpListDao.getInstance();
		/*	boolean isS = dao.updateTplist(seq, place, title, content, concept);
			if(isS) {
				forward("TpList?param=Tplist", req, resp);
			} else {
				forward("tp_list_update.jsp", req, resp);
			}
		*/	
			
		} else if (param.equals("Tplistdelete")) {
			String sseq = req.getParameter("seq").trim();
			int seq = Integer.parseInt(sseq);
			System.out.println("seq:" + seq);

			TpListDao dao = TpListDao.getInstance();
			boolean isS = dao.deleteTplist(seq);
			if(isS) {
				forward("TpList?param=Tplist", req, resp);
			} else {
				System.out.println("삭제실패");
			}
		}
			
		
		
	}
	
	public void forward(String string, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(string);
		dispatch.forward(req, resp);
	}
	
}
