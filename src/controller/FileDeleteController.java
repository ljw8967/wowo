package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MatchDao;
import dao.MustEatDao;
import dao.PdsDao;

@WebServlet("/filedelete")
public class FileDeleteController extends HttpServlet {
	
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	public void init(ServletConfig config) throws ServletException {
		mConfig = config;	
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			System.out.println("FileDeleteController");
			
			String param = req.getParameter("param");
			
			if(param.equals("Mustdelete")) {
				System.out.println("Mustdelete");
			
			//int  idx = Integer.parseInt(request.getParameter("idx"));
         	int seq = Integer.parseInt(req.getParameter("seq"));
	        String newfilename = req.getParameter("newfilename");
	        
	        MustEatDao dao = MustEatDao.getInstance();
	        
	        
	        //String filename = article.getFilename();
	        //String uploadFileName = request.getRealPath("/upload") +"/"+ filename;
	        
	        String uploadFileName = mConfig.getServletContext().getRealPath("/upload")+"/" + newfilename; 
	        System.out.println("파일 경로" + uploadFileName);
	        
	        File uploadfile = new File(uploadFileName);
	        if ( uploadfile.exists()&& uploadfile.isFile() )
	        {
	             uploadfile.delete();       // 파일 삭제
	        }
	         
	        dao.deleteMustEat(seq); 
	 
	        resp.sendRedirect("MustEat?param=MustEatlist");
			
			
			}else if(param.equals("Matchdelete")) {
				
				//int  idx = Integer.parseInt(request.getParameter("idx"));
	         	int seq = Integer.parseInt(req.getParameter("seq"));
		        String newfilename = req.getParameter("newfilename");
		        
		        MatchDao dao = MatchDao.getInstance();
		        
		        
		        //String filename = article.getFilename();
		        //String uploadFileName = request.getRealPath("/upload") +"/"+ filename;
		        
		        String uploadFileName = mConfig.getServletContext().getRealPath("/upload")+"/" + newfilename; 
		        System.out.println("파일 경로" + uploadFileName);
		        
		        File uploadfile = new File(uploadFileName);
		        if ( uploadfile.exists()&& uploadfile.isFile() )
		        {
		             uploadfile.delete();       // 파일 삭제
		        }
		         
		        boolean iS = dao.deleteMatch(seq); 
		        
		        String msg = "DELETEMATCH";
				if(iS == false) {
					msg = "NO";
				}
		        System.out.println("msg확인:" + msg);
		        
		        resp.sendRedirect("message.jsp?msg="+msg);
				
			
			} else if(param.equals("deletefile")) {
				
				
				int seq = Integer.parseInt(req.getParameter("seq"));
				
		        String newfilename = req.getParameter("newfilename");
				
		        System.out.println("삭제 1");
		        
		        PdsDao dao = PdsDao.getInstance();
		        
		        String uploadFileName = mConfig.getServletContext().getRealPath("/upload")+"/" + newfilename; 
		        System.out.println("파일 경로" + uploadFileName);

		        File uploadfile = new File(uploadFileName);
		        if ( uploadfile.exists() && uploadfile.isFile() )
		        {
		             uploadfile.delete();       // 파일 삭제
		        }
		        System.out.println("삭제 2");
		        
		         
		        dao.deletePds(seq); 
		 
		        resp.sendRedirect("bbs?param=bbslist");
		
				
			}
			
			
			
		
	}

	
}
