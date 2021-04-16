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
import dto.CommentDto;

@WebServlet("/comment")
public class CommentController extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doProcess(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doProcess(req, resp);
   }
   protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("CommentController");
      req.setCharacterEncoding("utf-8");
      String param = req.getParameter("param");
      
      //댓글작성
      if(param.equals("writeMEAf")) {
      
         
         int commListSeq = Integer.parseInt(req.getParameter("commListSeq"));
         System.out.println("부모 게시물 번호:" + commListSeq);
         
         String commid = req.getParameter("commid");
         System.out.println("댓글 작성자 아이디:" + commid);
         
         String commContent = req.getParameter("commContent");
         System.out.println("댓글내용:" + commContent);
         
         CommentDao dao = CommentDao.getInstance();
         CommentDto Cdto =  new CommentDto(commListSeq, commid, commContent);
         boolean check = dao.writeComment(Cdto);
         
         
         System.out.println("성공적 데이터");
         
         
         //가져올 데이터 아이디, 댓글내용,      
         List<CommentDto> list = dao.getCommentData(commListSeq);
         
         
         for (CommentDto c : list) {
            System.out.println(c.toString());
         }
            
         req.setAttribute("Clist", list);
         req.setAttribute("Cdto", Cdto);
      
         resp.sendRedirect("MustEat?param=Mustdetail&seq="+commListSeq);
            
      
         //req.getRequestDispatcher("MustEat?param=Mustdetail&seq=" + commListSeq).forward(req, resp);;
      
      
      }else if(param.equals("deleteAf")) {
         System.out.println("코멘트컨트롤러 딜리트 af");
         int commNum = Integer.parseInt(req.getParameter("commNum"));
         
         int commListSeq = Integer.parseInt(req.getParameter("commListSeq"));
         System.out.println("부모 게시물 번호:" + commListSeq);
         
         CommentDao Cdao = CommentDao.getInstance();
         boolean deleteRe = Cdao.deletecomment(commNum);
         
         resp.sendRedirect("TpList?param=tp_list_detail&seq="+commListSeq);
      
      
      
      }else if(param.equals("contactMate")) {
         System.out.println("contactMate hello");
         
         int commatchListSeq = Integer.parseInt(req.getParameter("commatchListSeq"));
         System.out.println("부모 게시물 번호:" + commatchListSeq);
         
         String commid = req.getParameter("commid");
         System.out.println("댓글 작성자 아이디:" + commid);
         
         String commContent = req.getParameter("commContent");
         System.out.println("댓글내용:" + commContent);
         
         CommentDao dao = CommentDao.getInstance();
         CommentDto Mdto =  new CommentDto(0 ,commatchListSeq, commid, commContent);
         boolean check = dao.writeMatchComment(Mdto);
         
         
         System.out.println("성공적 데이터");
         
         
         //가져올 데이터 아이디, 댓글내용,      
         List<CommentDto> list = dao.getMatchCommentData(commatchListSeq);
         
         
         for (CommentDto c : list) {
            System.out.println(c.toString());
         }
            
         req.setAttribute("Mlist", list);
         req.setAttribute("Mdto", Mdto);
      
         resp.sendRedirect("match?param=matchdetail&seq="+commatchListSeq);
            
      
         //req.getRequestDispatcher("MustEat?param=Mustdetail&seq=" + commListSeq).forward(req, resp);;
      
      
      } else if(param.equals("writeTPAf")) {
         
         System.out.println("writeTPAf받음");
         int commListSeq = Integer.parseInt(req.getParameter("comListSeq"));
         System.out.println("부모 게시물 번호:" + commListSeq);
         
         String commid = req.getParameter("commid");
         System.out.println("댓글 작성자 아이디:" + commid);
         
         String commContent = req.getParameter("commContent");
         System.out.println("댓글내용:" + commContent);
         
         CommentDao dao = CommentDao.getInstance();
         CommentDto Cdto =  new CommentDto(commListSeq, commid, null, commContent);
         boolean check = dao.writetpComment(Cdto);
         
         
         System.out.println("성공적 데이터");
         
         
         //가져올 데이터 아이디, 댓글내용,      
         List<CommentDto> list = dao.getCommentData(commListSeq);
         
         
         for (CommentDto c : list) {
            System.out.println(c.toString());
         }
            
         req.setAttribute("Ctplist", list);
         req.setAttribute("Ctpdto", Cdto);
      
         resp.sendRedirect("TpList?param=tp_list_detail&seq="+commListSeq);
       //   req.getRequestDispatcher("TpList?param=tp_list_detail&seq="+commListSeq);
         
         //req.getRequestDispatcher("MustEat?param=Mustdetail&seq=" + commListSeq).forward(req, resp);;
      
         
         
      } else if(param.equals("deletetpAf")) {
      //   System.out.println("코멘트컨트롤러 딜리트 af");
         
         int commNum = Integer.parseInt(req.getParameter("commNum"));
         
         
         int commTPSeq = Integer.parseInt(req.getParameter("commTPSeq"));
         System.out.println("부모 게시물 번호:" + commTPSeq);
         
         CommentDao Cdao = CommentDao.getInstance();
         boolean deleteRe = Cdao.deletecomment(commNum);
         
         resp.sendRedirect("TpList?param=tp_list_detail&seq="+commTPSeq);
      
   }
}
}