package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.MatchDao;
import dao.MustEatDao;
import dao.PdsDao;
import dao.TpListDao;
import dto.MatchDto;
import dto.MustEatDto;
import dto.PdsDto;

@WebServlet("/fileupdate")
public class FileUpdateController extends HttpServlet {
	
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		mConfig = config;	
		
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FileUpdateController");
		String param = req.getParameter("param");
		
		if(param.equals("mustUpdate")) {
		String fupload = mConfig.getServletContext().getRealPath("/upload");
		
		// 지정 폴더 - client
		// String fupload = "d:\\tmp";

		System.out.println("업로드 폴더:" + fupload);

		String yourTempDir = fupload;


		//업로드제한용량
		//업로드 용량
		int yourMaxRequestSize = 100 * 1024 * 1024;	// 1 Mbyte
		int yourMaxMemorySize = 100 * 1024;			// 1 Kbyte

		// form field의 데이터를 저장할 변수
		String id = "";
		String title = "";
		String oldfile = "";
		String oldnewfile = "";
		String content = "";
		String sseq = "";
		String place = "";
		String name = "";
		
		// file명 저장
		String filename = "";
		String newfilename = "";
	
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(isMultipart == true){
			
			// FileItem 생성
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			factory.setSizeThreshold(yourMaxMemorySize);
			factory.setRepository(new File(yourTempDir));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(yourMaxRequestSize);
			
			List<FileItem> items  = null;
			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator<FileItem> it = items.iterator();
			
			while(it.hasNext()){
				
				FileItem item = it.next();
				
				if(item.isFormField()){	// id, title, content
					if(item.getFieldName().equals("id")){
						id = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("title")){
						title = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("oldfile")){
						oldfile = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("oldnewfile")){
						oldnewfile = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("content")){
						content = item.getString("utf-8");
					}
					else if(item.getFieldName().equals("seq")){
						sseq = item.getString("utf-8");
					}else if(item.getFieldName().equals("place")){
						place = item.getString("utf-8");
					}else if(item.getFieldName().equals("nameN")){
						name = item.getString("utf-8");
					}
				}
				else{	// file
					if(item.getFieldName().equals("fileload")){		
						
						if(item.getName() != null && !item.getName().equals("")){				
							// 확장자 명
							String fileName = item.getName();
							int lastInNum = fileName.lastIndexOf(".");
							String exName = fileName.substring(lastInNum);
							
							// 새로운 파일명
							newfilename = (new Date().getTime()) + "";
							newfilename = newfilename + exName;
							System.out.println(newfilename);
											
							filename = processUploadFile(item, newfilename, fupload);				
						}
					}
				}	
				
				// 변경된 파일이 없으므로 기존의 파일을 저장한다
				if(filename == null || filename.equals("")){
					filename = oldfile;
					newfilename = oldnewfile;
				}		
			}	
		}
		MustEatDao dao = MustEatDao.getInstance();
		int seq = Integer.parseInt(sseq);
		boolean isS = dao.updateMustEat(seq, new MustEatDto(place, id, name, title, content, filename, newfilename));
		String msg = "NO";
		if(isS) {
			msg = "YESUPDATE";
		}
		System.out.println("성공적으로 추가되었나요?" + msg);
		
		resp.sendRedirect("message.jsp?msg=" + msg);
		
		
		
		
		}else if(param.equals("matchupdate")) {
			String fupload = mConfig.getServletContext().getRealPath("/upload");
			
			// 지정 폴더 - client
			// String fupload = "d:\\tmp";

			System.out.println("업로드 폴더:" + fupload);

			String yourTempDir = fupload;


			//업로드제한용량
			//업로드 용량
			int yourMaxRequestSize = 100 * 1024 * 1024;	// 1 Mbyte
			int yourMaxMemorySize = 100 * 1024;			// 1 Kbyte

			// form field의 데이터를 저장할 변수
			String id = "";
			String title = "";
			String oldfile = "";
			String oldnewfile = "";
			String content = "";
			String sseq = "";
			String place = "";
			String name = "";
			String firdate = "";
			String lasdate= "";
			
			// file명 저장
			String filename = "";
			String newfilename = "";
		
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(isMultipart == true){
				
				// FileItem 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(yourMaxMemorySize);
				factory.setRepository(new File(yourTempDir));
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(yourMaxRequestSize);
				
				List<FileItem> items  = null;
				try {
					items = upload.parseRequest(req);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Iterator<FileItem> it = items.iterator();
				
				while(it.hasNext()){
					
					FileItem item = it.next();
					
					if(item.isFormField()){	// id, title, content
						if(item.getFieldName().equals("id")){
							id = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("title")){
							title = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("oldfile")){
							oldfile = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("oldnewfile")){
							oldnewfile = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("content")){
							content = item.getString("utf-8");
						}
						else if(item.getFieldName().equals("seq")){
							sseq = item.getString("utf-8");
						}else if(item.getFieldName().equals("place")){
							place = item.getString("utf-8");
						}else if(item.getFieldName().equals("nameN")){
							name = item.getString("utf-8");
						}else if(item.getFieldName().equals("firdate")){
							firdate = item.getString("utf-8");
						}else if(item.getFieldName().equals("lasdate")){
							lasdate = item.getString("utf-8");
						}
						
					}
					else{	// file
						if(item.getFieldName().equals("fileload")){		
							
							if(item.getName() != null && !item.getName().equals("")){				
								// 확장자 명
								String fileName = item.getName();
								int lastInNum = fileName.lastIndexOf(".");
								String exName = fileName.substring(lastInNum);
								
								// 새로운 파일명
								newfilename = (new Date().getTime()) + "";
								newfilename = newfilename + exName;
								System.out.println(newfilename);
												
								filename = processUploadFile(item, newfilename, fupload);				
							}
						}
					}	
					
					// 변경된 파일이 없으므로 기존의 파일을 저장한다
					if(filename == null || filename.equals("")){
						filename = oldfile;
						newfilename = oldnewfile;
					}		
				}	
			}
			MatchDao dao = MatchDao.getInstance();
			int seq = Integer.parseInt(sseq);
			boolean isS = dao.updateMatch(seq, new MatchDto(place, id, name, title, content, filename, newfilename, firdate, lasdate));			
			String msg = "NO";
			if(isS) {
				msg = "YESMATCHUPDATE";
			}
			System.out.println("성공적으로 추가되었나요?" + msg);
			
			resp.sendRedirect("message.jsp?msg=" + msg);
			
		} else if (param.equals("listupdate") ) {
			String fupload = mConfig.getServletContext().getRealPath("/upload");
			
			// 지정 폴더 - client
			// String fupload = "d:\\tmp";

			System.out.println("업로드 폴더:" + fupload);

			String yourTempDir = fupload;


			//업로드제한용량
			//업로드 용량
			int yourMaxRequestSize = 100 * 1024 * 1024;	// 1 Mbyte
			int yourMaxMemorySize = 100 * 1024;			// 1 Kbyte

			// form field의 데이터를 저장할 변수
			
			String id = "";
			
			String title = "";
			String oldfile = "";
			String oldnewfile = "";
			String content = "";
			String sseq = "";
			
			String name = "";
			
			String place = "";
			
			String concept = "";
			
			
			// file명 저장
			String filename = "";
			String newfilename = "";
		
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(isMultipart == true){
				
				// FileItem 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(yourMaxMemorySize);
				factory.setRepository(new File(yourTempDir));
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(yourMaxRequestSize);
				
				List<FileItem> items  = null;
				try {
					items = upload.parseRequest(req);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Iterator<FileItem> it = items.iterator();
				
				while(it.hasNext()){
					
					FileItem item = it.next();
					
					if(item.isFormField()){	// id, title, content
						if(item.getFieldName().equals("id")){
							id = item.getString("utf-8");
						} else if(item.getFieldName().equals("title")){
							title = item.getString("utf-8");
						} else if(item.getFieldName().equals("oldfile")){
							oldfile = item.getString("utf-8");
						} else if(item.getFieldName().equals("oldnewfile")){
							oldnewfile = item.getString("utf-8");
						} else if(item.getFieldName().equals("content")){
							content = item.getString("utf-8");
						} else if (item.getFieldName().equals("seq")){
							sseq = item.getString("utf-8");
						} else if(item.getFieldName().equals("place")){
							place = item.getString("utf-8");
						} else if(item.getFieldName().equals("nameN")){
							name = item.getString("utf-8");
						} else if(item.getFieldName().equals("concept")){
							concept = item.getString("utf-8");
					}
					}
					else{	// file
						if(item.getFieldName().equals("fileloader")){		
							
							if(item.getName() != null && !item.getName().equals("")){				
								// 확장자 명
								String fileName = item.getName();
								int lastInNum = fileName.lastIndexOf(".");
								String exName = fileName.substring(lastInNum);
								
								// 새로운 파일명
								newfilename = (new Date().getTime()) + "";
								newfilename = newfilename + exName;
								System.out.println(newfilename);
												
								filename = processUploadFile(item, newfilename, fupload);				
							}
						}
					}	
					
					
					
					// 변경된 파일이 없으므로 기존의 파일을 저장한다
					if(newfilename == null || newfilename.equals("")){
						newfilename = oldnewfile;
						filename = oldfile;
					}		
				}	
			}
		/*	MustEatDao dao = MustEatDao.getInstance();
			int seq = Integer.parseInt(sseq);
			boolean isS = dao.updateMustEat(seq, new MustEatDto(place, id, name, title, content, filename, newfilename));
			String msg = "NO";
			if(isS) {
				msg = "YESUPDATE";
			}
			System.out.println("성공적으로 추가되었나요?" + msg);
			
			resp.sendRedirect("message.jsp?msg=" + msg);
		*/	
			TpListDao dao = TpListDao.getInstance();
			int seq = Integer.parseInt(sseq);
			boolean isS = dao.updateTplist(seq, place, title, content, concept, filename, newfilename);
			String msg = "Nope";
			if(isS) {
				msg = "OK";
			}
			System.out.println("성공적으로 추가되었나  " + msg);
			resp.sendRedirect("TpList?param=Tplist&msg" + msg);
		}
		
		else if(param.equals("updatePds")) {
			

			String fupload = mConfig.getServletContext().getRealPath("/upload");
			
			System.out.println("업로드 폴더:" + fupload);

			String yourTempDir = fupload;

			int yourMaxRequestSize = 100 * 1024 * 1024;	// 1 Mbyte
			int yourMaxMemorySize = 100 * 1024;			// 1 Kbyte

			//폼필드의 데이터를 저장할 변수 
			  String sseq = "";
			  String id = "";
		      String name = "";
		      String title = "";
		      String content = "";
		      String oldfile = "";
		      String oldnewfile = "";
		      
		      
		      //file명 저장
		      String filename = "";
		      String newfilename = "";
		      
		      
		      boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		      if(isMultipart == true){
		    	  
		    	  DiskFileItemFactory factory = new DiskFileItemFactory();
					
					factory.setSizeThreshold(yourMaxMemorySize);
					factory.setRepository(new File(yourTempDir));
					
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setSizeMax(yourMaxRequestSize);
					
					List<FileItem> items  = null;
					try {
						items = upload.parseRequest(req);
					} catch (FileUploadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Iterator<FileItem> it = items.iterator();
					
					while(it.hasNext()){					
						FileItem item = it.next();
						if(item.isFormField()){						
							if(item.getFieldName().equals("title")){
								 title = item.getString("utf-8");
							}
			               else if(item.getFieldName().equals("oldfile")){
			                  oldfile = item.getString("utf-8");
			               }
			               else if(item.getFieldName().equals("content")){
			                  content = item.getString("utf-8");
			               }
			               else if(item.getFieldName().equals("id")){
			                  id = item.getString("utf-8");
			               }
			               else if(item.getFieldName().equals("oldnewfile")){
			                   oldnewfile = item.getString("utf-8");
			                }
			               else if(item.getFieldName().equals("name")){
			                   name = item.getString("utf-8");
			                }
			               else if(item.getFieldName().equals("seq")){
								sseq = item.getString("utf-8");
			               }						
						}else{	// file
							if(item.getFieldName().equals("newfileload")){		
								
								System.out.println("~~~~~~~~~~~~~item.getName():" + item.getName());
								
								if(item.getName() != null && !item.getName().equals("")){				
									// 확장자 명
									filename= item.getName();
									int lastInNum = filename.lastIndexOf(".");
									String exName = filename.substring(lastInNum);
									
									// 새로운 파일명
									newfilename = (new Date().getTime()) + "";
									newfilename = newfilename + exName;
									System.out.println("newfilename:" + newfilename);
													
									filename = processUploadFile(item, newfilename,fupload  );				
								}
							}
						}	
					}
					
					//변경된것이 없으면 기존의 파일로 저장
					if(filename == null || filename.equals("")){
						filename = oldfile;
						newfilename = oldnewfile;
						
					}
		      }
		
				System.out.println(id);
				System.out.println(name);
				System.out.println(title);
				System.out.println(content);
				System.out.println(filename);
				System.out.println(newfilename);
						
		
			PdsDao dao = PdsDao.getInstance();
			int seq = Integer.parseInt(sseq);
			boolean isS = dao.updatePdss(seq, new PdsDto(id , name, title, content, filename, newfilename));
			String msg = "NO";
			if(isS) {
				msg = "NICE";
			}
			System.out.println("성공적으로 수정되었나요?" + msg);
			
			
			resp.sendRedirect("message.jsp?msg=" + msg);
		}

			
			
		}
		
		
		
		
		
		
	
	
	public String processUploadFile(FileItem fileItem, String newfilename, String dir)throws IOException{

		String filename = fileItem.getName();	// 경로 + 파일명
		long sizeInBytes = fileItem.getSize();
		
		if(sizeInBytes > 0){	// 파일이 정상?		// d:\\tmp\\abc.txt d:/tmp/abc.txt 
			
			int idx = filename.lastIndexOf("\\"); 
			if(idx == -1){
				idx = filename.lastIndexOf("/");
			}
			
			filename = filename.substring(idx + 1);
		//	File uploadFile = new File(dir, filename);
			File uploadFile = new File(dir, newfilename); // 새로운 파일 명으로
		
			try{	
				fileItem.write(uploadFile);		// 실제 upload되는 부분
			}catch(Exception e){
				e.printStackTrace();
			}		
		}
		return filename;	// DB에 저장하기 위한 return;
	}    
	
	
}

