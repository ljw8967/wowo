package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.ServletConfig;


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
import dto.TpListDto;




@WebServlet("/fileuse")
public class FileUploadController extends HttpServlet {
	

	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	@Override
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
		System.out.println("FileUploadController");
		String param = req.getParameter("param");
		
		
		if(param.equals("fileupload")) {

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
			String content = "";
			String place = "";
			String nameN = "";
			

			// file명 저장
			String filename = "";
			String newfilename = "";
			//이걸 새로운 파일이라 생각

			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(isMultipart == true){
				
				// FileItem 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(yourMaxMemorySize);
				factory.setRepository(new File(yourTempDir));
				
				//파일 세팅 및 업로드
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(yourMaxRequestSize);
				
				List<FileItem> items = null;
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
						else if(item.getFieldName().equals("content")){
							content = item.getString("utf-8");
						}else if(item.getFieldName().equals("place")){
							place = item.getString("utf-8");
						}else if(item.getFieldName().equals("nameN")){
							nameN = item.getString("utf-8");
						}			
					}
					else{	// file
						if(item.getFieldName().equals("fileload")){
							
							//확장자명
							String fileName = item.getName();
							int lastInNum = fileName.lastIndexOf(".");
							
							//본래파일에서 확장자명만 가져온다 ex) .png
							String exName = fileName.substring(lastInNum);
							
							//새로운 파일명
							newfilename = (new Date().getTime()) + "" + exName;
							filename = processUploadFile(item, newfilename, fupload);
							
							System.out.println("newfilename = "+ newfilename);
							//filename이 원본이름
							//newfilename 수정한 이름
						}
					}		
				}//while문 종료
				MustEatDao dao =  MustEatDao.getInstance();
				Boolean isS = dao.writeMustEat(new MustEatDto(place, id, nameN, title, content, filename, newfilename));
				
				String msg = "OK";
				if(isS == false) {
					msg = "NO";
				}
				resp.sendRedirect("MustEat?param=MustEatlist&msg=" + msg);
			
			}
		}else if(param.equals("matchfileupload")) {
			System.out.println("matchfileupload/matchfileupload");
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
			String content = "";
			String place = "";
			String nameN = "";
			String firdate = "";
			String lasdate= "";

			// file명 저장
			String filename = "";
			String newfilename = "";
			
			
			//이걸 새로운 파일이라 생각

			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(isMultipart == true){
				
				// FileItem 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(yourMaxMemorySize);
				factory.setRepository(new File(yourTempDir));
				
				//파일 세팅 및 업로드
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(yourMaxRequestSize);
				
				List<FileItem> items = null;
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
						else if(item.getFieldName().equals("content")){
							content = item.getString("utf-8");
						}else if(item.getFieldName().equals("place")){
							place = item.getString("utf-8");
						}else if(item.getFieldName().equals("nameN")){
							nameN = item.getString("utf-8");
						}else if(item.getFieldName().equals("firdate")){
							firdate = item.getString("utf-8");
						}else if(item.getFieldName().equals("lasdate")){
							lasdate = item.getString("utf-8");
						}			
					}
					else{	// file
						if(item.getFieldName().equals("fileload")){
							
							//확장자명
							String fileName = item.getName();
							int lastInNum = fileName.lastIndexOf(".");
							
							//본래파일에서 확장자명만 가져온다 ex) .png
							String exName = fileName.substring(lastInNum);
							
							//새로운 파일명
							newfilename = (new Date().getTime()) + "" + exName;
							filename = processUploadFile(item, newfilename, fupload);
							
							System.out.println("newfilename = "+ newfilename);
							//filename이 원본이름
							//newfilename 수정한 이름
						}
					}		
				}//while문 종료
				MatchDao dao =  MatchDao.getInstance();
				Boolean isS = dao.writeMatch(new MatchDto(place, id, nameN, title, content, filename, newfilename, firdate, lasdate));
				
				String msg = "OK";
				if(isS == false) {
					msg = "NO";
				}
				resp.sendRedirect("match?param=Matchlist&msg=" + msg);
			
			}
		} else if (param.equals("fileuploadlist")) {
			
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
			String place = "";
			String id = "";
			String namey = "";
			String title = "";
			String content = "";
			String concept = "";			

			// file명 저장
			String filename = "";
			String newfilename = "";
			//이걸 새로운 파일이라 생각

			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(isMultipart == true){
				
				// FileItem 생성
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(yourMaxMemorySize);
				factory.setRepository(new File(yourTempDir));
				
				//파일 세팅 및 업로드
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(yourMaxRequestSize);
				
//				System.out.println("컨트롤러 확인1");
				
				List<FileItem> items = null;
				try {
//					System.out.println("컨트롤러 확인2");
					items = upload.parseRequest(req);
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Iterator<FileItem> it = items.iterator();
//				System.out.println("파일컨트롤러 it = " + it);
//				System.out.println("컨트롤러 확인3");
				while(it.hasNext()){
					
					FileItem item = it.next();
//					System.out.println("파일컨트롤러 item = " + item);
					if(item.isFormField()){	// id, title, content
						if(item.getFieldName().equals("place")){
							place = item.getString("utf-8");
						} else if(item.getFieldName().equals("id")){
							id = item.getString("utf-8");
						} else if(item.getFieldName().equals("namey")){
							namey = item.getString("utf-8");
						} else if(item.getFieldName().equals("title")){
							title = item.getString("utf-8");
				//			System.out.println("컨트롤러 name: " + namey);
						} else if(item.getFieldName().equals("content")){
							content = item.getString("utf-8");
						} else if(item.getFieldName().equals("concept")){
							concept = item.getString("utf-8");
							
					}
					}else {	// file
						if(item.getFieldName().equals("fileloader")){
							
							//확장자명
							String fileName = item.getName();
							int lastInNum = fileName.lastIndexOf(".");
//							System.out.println("else 뒤에 파일네임" + fileName);
							//본래파일에서 확장자명만 가져온다 ex) .png
							String exName = fileName.substring(lastInNum);
							
							//새로운 파일명
							newfilename = (new Date().getTime()) + "" + exName;
							filename = processUploadFile(item, newfilename, fupload);
							
							System.out.println("newfilename = "+ newfilename);
							//filename이 원본이름
							//newfilename 수정한 이름
						}
					}	
				}
					
				//	MustEatDao dao =  MustEatDao.getInstance();
				//	Boolean isS = dao.writeMustEat(new MustEatDto(place, id, nameN, title, content, filename, newfilename));
					
				//	String msg = "OK";
				//	if(isS == false) {
				//		msg = "NO";
				//	}
				//	resp.sendRedirect("MustEat?param=MustEatlist&msg=" + msg);
				}

				TpListDao dao = TpListDao.getInstance();
				boolean isS = dao.writeTplist(new TpListDto(place, id, namey, title, content, concept, filename, newfilename));
				
				String msg = "okay";
				if(isS == false) {
					msg = "omg it's fail";
				}
				resp.sendRedirect("TpList?param=Tplist&msg=" + msg);
				
				
			} else if(param.equals("filewrite")) {
				String fupload = mConfig.getServletContext().getRealPath("/upload"); //톰캣 서버에 파일 저장하기
			      System.out.println("fupload:" + fupload);
			      int yourMaxRequestSize = 100 * 1024 * 1024;   // 파일 최대 사이즈 (1 Mb)
			      int yourMaxMemorySize = 100 * 1024;         // 메모리 사이즈 (1 Kb)

			      
			      // 변수 셋팅
			      String id = "";
			      String name = "";
			      String title = "";
			      String content = "";
			      
			      String filename = "";
			      String newfileName = "";

			      //////////////////////DB 저장을 위한 셋팅 (이름 받아오기) ///////////////////////////

			      
			      //넘어온게  multipart인지 검사
			      boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			      if(isMultipart == true){

			         // FileItem 객채 생성
			         DiskFileItemFactory factory = new DiskFileItemFactory();
			         System.out.println("FileuploadController 1");

			         // File 셋팅 및 업로드
			         factory.setSizeThreshold(yourMaxMemorySize);  // 파일 사이즈 
			         factory.setRepository(new File(fupload)); // 저장소 
			         ServletFileUpload upload = new ServletFileUpload(factory); //펙토리에서의 값을 저장
			         upload.setSizeMax(yourMaxRequestSize); //메모리사이즈 셋팅
			         System.out.println("FileuploadController 2");


			         //list에 정보 저장후 꺼내기
			         List<FileItem> items = null;
			         try {
			            items = upload.parseRequest(req);
			         } catch (FileUploadException e) {
			            
			            e.printStackTrace();
			         } 
			         System.out.println("FileuploadController 3");

			         Iterator<FileItem> it = items.iterator();
			         System.out.println("FileuploadController 4");

			         
			         //데이터 타입 조사
			         while(it.hasNext()){
			            FileItem item = it.next();

			            //폼 필드라면
			            if(item.isFormField()){   
			               
			               if(item.getFieldName().equals("title")){
			                  title = item.getString("utf-8");
			               }
			           /*    else if(item.getFieldName().equals("filename")){
			                  fileName = item.getString("utf-8");
			               }*/
			               else if(item.getFieldName().equals("content")){
			                  content = item.getString("utf-8");
			               }
			               else if(item.getFieldName().equals("id")){
			                  id = item.getString("utf-8");
			               }
			               else if(item.getFieldName().equals("newfileName")){
			                   newfileName = item.getString("utf-8");
			                }
			               else if(item.getFieldName().equals("name")){
			                   name = item.getString("utf-8");
			                }

			            }
			         
			            //폼 필드가 아니라면 
			            else{   
			               if(item.getFieldName().equals("fileload")){

			                  //파일위치+파일명
			            	   filename = item.getName();
			            	   System.out.println("~~~~~~~~~~~~~filename : "+filename);

			                  //파일을 다시 업로드 하는 경우
			                  if(filename != null && !filename.equals("")){
			                	  
			                	  System.out.println("filename != null && !newfileName.equals(\"\")");
			                	  
			                     int lastInNum = filename.lastIndexOf(".");
			                     String exName = filename.substring(lastInNum);
			                     
			                     //새로운 파일명 + 확장자
			                     newfileName = (new Date().getTime()) + ""; 
			                     newfileName = newfileName + exName;
			                     
			                     //파일 업로드
			                     processUploadFile(item, newfileName, fupload); 
			                     
			                 }
			              }    
			           } //else
			            
			          
			              
			        } // while
			         
			       //확인하기 
			         System.out.println("id :"+id);
			         System.out.println("title :"+title);
			         System.out.println("content :"+content);
			         System.out.println("filename : "+filename);
			         System.out.println("newfileName :"+newfileName);
			         
			         //DB에 저장
			         PdsDao dao = PdsDao.getInstance();         
			         PdsDto dto = new PdsDto(id, name, title, content, filename, newfileName);
			         boolean isS = dao.writePds(dto);

			         if(isS)
			            System.out.println("FileController Success!!");
			           resp.sendRedirect("bbs?param=bbslist");
			      }
			}
	}
			
			
			
	
		
		
	
	
	public String processUploadFile(FileItem fileItem, String newfilename, String dir)throws IOException{

		String filename = fileItem.getName();	// 경로 + 파일명
		//중복방지를 위해 파일명을 변경해준다.
		long sizeInBytes = fileItem.getSize();
		
		if(sizeInBytes > 0){	// 파일이 정상?		// d:\\tmp\\abc.txt d:/tmp/abc.txt 
			//파일사이즈가 존재할떄
			//1
			// \\혹은 /를 빼고 마지막번찌부터 파일명 + 확장자명이 있기 때문
			int idx = filename.lastIndexOf("\\"); 
			if(idx == -1){
				idx = filename.lastIndexOf("/");
			}
			
			
			//2
			//파일 이름은 \\,/가 마지막으로 나온 인덱스에서 +1를 추가하였다
			
			filename = filename.substring(idx + 1);
			//File uploadFile = new File(dir, filename);
			//새로운파일명
			File uploadFile = new File(dir, newfilename);
			//명칭을 바꾸어 업로드
			//기존의 파일이 아닌 날짜형식으로 파일을 변경하여 업로드
			try{	
				fileItem.write(uploadFile);		// 실제 upload되는 부분
			}catch(Exception e){
				e.printStackTrace();
			}		
		}
		return filename;	// DB에 저장하기 위한 return;
	}
	

	
}
