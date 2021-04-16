package filedown;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MustEatDao;
import dto.MustEatDto;


public class FileDownLoader extends HttpServlet {
	
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		mConfig = config;	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FileDownLoader doGet");
		
		String filename = req.getParameter("filename");
		int seq = Integer.parseInt( req.getParameter("seq") );
		
		MustEatDao dao = MustEatDao.getInstance();
		MustEatDto dto = dao.getMustEat(seq);
		// dao -> down load
		
		BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
		
		// path
		// tomcat - server
		String filepath = mConfig.getServletContext().getRealPath("/upload");

		
		filepath = filepath + "\\" + filename;
		System.out.println("filepath:" + filepath);
		
		File f = new File(filepath);		
		
		if(f.exists() && f.canRead()) {
	
			resp.setHeader("Content-Disposition","attachment; filename=\"" + dto.getFilename() + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary;");
	        resp.setHeader("Content-Length", "" + f.length());
	        resp.setHeader("Pragma", "no-cache;"); 
	        resp.setHeader("Expires", "-1;");
	        
	        // �뙆�씪 �깮�꽦, 湲곗엯
	        BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
	        
	        byte buffer[] = new byte[BUFFER_SIZE];	        
	        int read = 0; 
	        
	        while((read = fileInput.read(buffer)) != -1) {
	        	out.write(buffer, 0, read);		        	
	        }
	        	        
	        fileInput.close();
	        out.flush();	        
		}
		
		
	}

	
}






