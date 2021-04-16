package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MustEatDao;
import dao.TpListDao;
import dto.MustEatDto;
import dto.TpListDto;


//"location.href='pdsupdate.jsp?seq




@WebServlet("/filedown")
public class FileDownController extends HttpServlet{
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		mConfig = config;	// 업로드한 경로를 취득하기 위해서
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
		
		System.out.println("FileDownLoader doGet");
		
		String param = req.getParameter("param");
		
		
		if(param.equals("MEdownload")){
		
		String filename = req.getParameter("filename");
		int seq = Integer.parseInt( req.getParameter("seq") );
		
		MustEatDao dao = MustEatDao.getInstance();
		MustEatDto dto = dao.getMustEat(seq);
		dao.downcount(seq);
		// dao -> down load 카운터 증가
		
		BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
		
		// path(경로)
		// tomcat - server
		String filepath = mConfig.getServletContext().getRealPath("/upload");
		
		// 폴더 - client
		// String filepath = "d:\\tmp";
		
		filepath = filepath + "\\" + filename;
		System.out.println("filepath:" + filepath);
		
		File f = new File(filepath);		
		
		if(f.exists() && f.canRead()) {
			
			// download window
		//	resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
			// 셋헤더로 파일이름 정할때 원래이름을 넣어주면 원본명으로 다운된다.
			resp.setHeader("Content-Disposition","attachment; filename=\"" + dto.getFilename() + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary;");
	        resp.setHeader("Content-Length", "" + f.length());
	        resp.setHeader("Pragma", "no-cache;"); 
	        resp.setHeader("Expires", "-1;");
	        
	        // 파일 생성, 기입
	        BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
	        System.out.println("file down 1 ");
	        byte buffer[] = new byte[BUFFER_SIZE];	        
	        int read = 0; 
	        System.out.println("file down 2 ");
	        while((read = fileInput.read(buffer)) != -1) {
	        	out.write(buffer, 0, read);		// 실제 다운로드        	
	        }
	        	        
	        fileInput.close();
	        out.flush();
	        
	        
	        //resp.sendRedirect("MustEat?param=Mustdetail&seq" + seq);
		}
		
		/*
			1. 파일명 : 원본명 -> 변경 upload
			2. original filename, new filename
				- downcount++
			3. pdsdetail -> down load 기능
				- readcount++
			4. update
			5. delete
			
			6. (paging, search)
		
		*/
		
		} else if(param.equals("listdownload")) {	
			
			String filename = req.getParameter("filename");
			System.out.println("파일다운로더리스트다운로드 : " + req.getParameter("seq"));
			int seq = Integer.parseInt( req.getParameter("seq") );
			
			TpListDao dao = TpListDao.getInstance();
			TpListDto dto = dao.getTplistdetail(seq);
			dao.readCount(seq);
			// dao -> down load 카운터 증가
			
			BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
			
			// path(경로)
			// tomcat - server
			String filepath = mConfig.getServletContext().getRealPath("/upload");
			
			// 폴더 - client
			// String filepath = "d:\\tmp";
			
			filepath = filepath + "\\" + filename;
			System.out.println("filepath:" + filepath);
			
			File f = new File(filepath);		
			
			if(f.exists() && f.canRead()) {
				
				// download window
			//	resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
				// 셋헤더로 파일이름 정할때 원래이름을 넣어주면 원본명으로 다운된다.
				resp.setHeader("Content-Disposition","attachment; filename=\"" + dto.getFilename() + "\";");
				resp.setHeader("Content-Transfer-Encoding", "binary;");
		        resp.setHeader("Content-Length", "" + f.length());
		        resp.setHeader("Pragma", "no-cache;"); 
		        resp.setHeader("Expires", "-1;");
		        
		        // 파일 생성, 기입
		        BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
		        
		        byte buffer[] = new byte[BUFFER_SIZE];	        
		        int read = 0; 
		        
		        while((read = fileInput.read(buffer)) != -1) {
		        	out.write(buffer, 0, read);		// 실제 다운로드        	
		        }
		        	        
		        fileInput.close();
		        out.flush();	        
			}
			/*
			1. 파일명 : 원본명 -> 변경 upload
			2. original filename, new filename
				- downcount++
			3. pdsdetail -> down load 기능
				- readcount++
			4. update
			5. delete
			
			6. (paging, search)
		
		*/
			
		}
	
	
	}
	
}
