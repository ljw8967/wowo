package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import dao.MemberDao;
import dto.MemberDto;
import net.sf.json.JSONObject;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberController doProcess");
		req.setCharacterEncoding("utf-8");		
		
		String param = req.getParameter("param");
		
		if(param.equals("login")) {
			resp.sendRedirect("login_resist_form.jsp");
		}
		else if(param.equals("loginAf")) {
			System.out.println("MemberController loginAf");
			String userid = req.getParameter("userID");
			String userpwd = req.getParameter("userPassword");
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto dto = dao.login(new MemberDto(userid, userpwd, null, null, 0, 0));
			
			 if(!userpwd.equals(dao.pwdCheck(userpwd))) {
	            String msg = "notEqual";
	          
	            resp.sendRedirect("message.jsp?msg=" + msg);               
	         }
	         else {
	         
		         if(dto != null ) {
		            req.getSession().setAttribute("login", dto);
		            resp.sendRedirect("main.jsp");
		         }else {
		            System.out.println("login 정보 확인");
		      
		            resp.sendRedirect("login_resist_form.jsp");
		         }         
	         }

			
		}
		else if(param.equals("regi")) {
			resp.sendRedirect("regi.jsp");
		}
		else if(param.equals("regiAf")) {
			System.out.println("MemberController regiAf");
			String fupload = mConfig.getServletContext().getRealPath("/upload");
			
			 // 지정 폴더 - client
		    // String fupload = "d:\\tmp";

		    System.out.println("업로드 폴더:" + fupload);
		    
		    String yourTempDir = fupload;
		    
		    //업로드제한용량
		    //업로드 용량
		    int yourMaxRequestSize = 100 * 1024 * 1024;   // 1 Mbyte
		    int yourMaxMemorySize = 100 * 1024;         // 1 Kbyte	   
		    
		    // form field의 데이터를 저장할 변수
		    String userID = "";
		    String userPWD = "";
		    String userName = "";
		    String userEmail = "";
		    String userPic = "";
		    String newuserPic = "";
		    
		    // file명 저장
		    String filename = "";
		    
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
		        
		        if(item.isFormField()){   // id, title, content
		             if(item.getFieldName().equals("userId")){
		                userID = item.getString("utf-8");
		             }
		             else if(item.getFieldName().equals("userPwd")){
		                userPWD = item.getString("utf-8");
		             }
		             else if(item.getFieldName().equals("userEmail")){
		                userName = item.getString("utf-8");
		             }else if(item.getFieldName().equals("userName")){
		                userEmail = item.getString("utf-8");
		             }else if(item.getFieldName().equals("img")){
		                userPic = item.getString("utf-8");
		             }else if(item.getFieldName().equals("newuserPic"))
		            	 newuserPic = item.getString("utf-8");
		                        System.out.println(userID + userPWD + userEmail + userName 
		            		 						+ userPic + newuserPic + "get Item input test");
		          }else {
		        	  
		        	  if(item.getFieldName().equals("photo-user")){
		        		  if(item.getName() != null && !item.getName().equals("")) {
			        		//확장자명
			                  String fileName = item.getName();
			                  int lastInNum = fileName.lastIndexOf(".");
			                  
			                //본래파일에서 확장자명만 가져온다 ex) .png
			                  String exName = fileName.substring(lastInNum);
			                  
			                  //새로운 파일명
			                  newuserPic = (new Date().getTime()) + "" + exName;
			                  filename = processUploadFile(item, newuserPic, fupload);
			                  
			                  System.out.println("newuserPic = "+ newuserPic);
			                  //filename이 원본이름
			                  //newfilename 수정한 이름
		        		  }
		               }
		            }      
		         }//while문 종료
		    
		    
		    // 데이터 저장 확인
		    System.out.println(userID + " " + userPWD + " " + userName + " " + userEmail + " " + userPic + " " + newuserPic);
			
		    MemberDao dao =  MemberDao.getInstance();
		       boolean isS = dao.insert(new MemberDto(userID, userPWD, userName, userEmail, newuserPic));
		       
		       String msg = "OK";
		       if(isS == false) {
		          msg = "NO";
		       }
		       resp.sendRedirect("message.jsp?msg=" + msg);
		    
		    }
		}  
		    
	 	else if (param.equals("login")) {
	 		resp.sendRedirect("login_resist_form.jsp");
	 	}
		    
	 	else if (param.equals("mapageAf")) {
	 		System.out.println("MemberController mapageAf");
	 		
	 		String userId = req.getParameter("userID");
	 		
	 		System.out.println(userId);
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto dto = dao.getUserData(userId);
	 		
			if(dto != null ) {
				req.setAttribute("mapage", dto);
				System.out.println(dto.toString());
				req.getRequestDispatcher("mypage.jsp").forward(req, resp);
			}else {
				System.out.println("login 정보 확인");
				resp.sendRedirect("member?param=login");
			}	
	 		
		
		}else if(param.equals("main")) {
			resp.sendRedirect("main.jsp");
		}
		
		else if(param.equals("deleteMember")) {
			System.out.println("MemberController deleteMember");
			 MemberDto mem = (MemberDto)req.getSession().getAttribute("login"); 
		    
			 String id = mem.getId();
			 String pw = mem.getPwd();
		     
		     System.out.println("id : " +id+ " pwd : " + pw);
			
		     req.getSession().invalidate(); 
			 MemberDao dao = MemberDao.getInstance();
			 boolean check = dao.deleteConfirm(id, pw);
	
			 String msg = "quitNO";
			 if(check) {
				 msg="quitYES";
				 resp.sendRedirect("quitMessage.jsp?msg=" + msg);
				/*
				 * out.println("<script>"); out.println("alert('성공적으로 탈퇴 되었습니다.');");
				 * out.println("location.href='member?param=main';"); out.println("</script>");
				 */
			 } else {
				/*
				 * out.println("<script>"); out.println("alert('비밀번호가 맞지 않습니다.');");
				 * out.println("location.href='member?param=main';"); out.println("</script>");
				 */
				 
				 resp.sendRedirect("quitMessage.jsp?msg=" + msg);
		
				 
			 }
			
		}
		
		else if(param.equals("memUpdateAf")) {
			String id = req.getParameter("userId");
			String pwd = req.getParameter("userPwd");
			String name = req.getParameter("userName");
			String email = req.getParameter("userEmail");
			
			System.out.println(id+pwd);
			
			MemberDao dao = MemberDao.getInstance();
			
			String msg = "pwdNull";
			
			
			if(pwd.equals(dao.updateMemPwd(id))) {
			
				Boolean isS = dao.memUpdate(id, pwd, name, email);
			
				
				if(isS) {
					System.out.println("memUpdateAf isS");
					 msg = "updateInfo";
					
				
				}
				
				
				resp.sendRedirect("message.jsp?msg="+ msg);
				
			}
			else {
				resp.sendRedirect("message.jsp?msg="+ msg);
			}
			
		}
		
		
		else if(param.equals("Pwdcheck")) {
			System.out.println();
			String pwd = req.getParameter("pwd");
			System.out.println(pwd);
			MemberDao dao = MemberDao.getInstance();
			
			String msg = "YESPwd";
			
			if(!pwd.equals(dao.pwdCheck(pwd)) ) {
				 msg = "noPwd";
			}
				JSONObject jobj = new JSONObject();
				jobj.put("msg", msg);
				
				resp.setContentType("application/x-json; charset=UTF-8");
		         resp.getWriter().print(jobj);
				
			
		}
			
		else if(param.equals("idcheck")) {
			String id = req.getParameter("id");
			System.out.println("id:" + id);
			
			if(id != null && !id.equals("")) {
			
			MemberDao dao = MemberDao.getInstance();
			boolean b = dao.idCheck(id);
		
			
			String idcheck = "NO";
			if(b == false) {
				idcheck = "YES";
			}
			
			JSONObject jobj = new JSONObject();
			jobj.put("msg", idcheck);
			
			resp.setContentType("application/x-json; charset=UTF-8");
			resp.getWriter().print(jobj);
			}
		}

		else if(param.equals("memlist")) {
			System.out.println("memlist");
			String spage = req.getParameter("pageNumber");
		
			int page = 0;
			if(spage != null && !spage.equals("")) {
				page = Integer.parseInt(spage);
			}
			
			System.out.println("다오확인");
			MemberDao dao = MemberDao.getInstance();
			List<MemberDto> list = dao.getAllMemPaging(page);
			
		
			req.setAttribute("list", list);
			System.out.println("memlist2");
			
			int len = dao.getAllMem();
			int memPage = len / 10;		// 23 -> 2
			if((len % 10) > 0){
				memPage = memPage + 1;
			}
			req.setAttribute("memPage", memPage + "");
			req.setAttribute("pageNumber", page + "");
							
			forward("mypagememlist.jsp", req, resp);
			
		}
		else if (param.equals("deleteMem")) {
	         System.out.println("deleteMem");
	         
	         String id = req.getParameter("id");
	         
	         MemberDao dao = MemberDao.getInstance();         
	         boolean b = dao.deleteMem(id);
	         
	         String msg = "DELETE";
	         if(b == false) {
	            msg = "";
	         }         
	         resp.sendRedirect("message.jsp?msg=" + msg);
	         
	         
	      }	
	}

	
	public String processUploadFile(FileItem fileItem, String newfilename, String dir)throws IOException{

		 String filename = fileItem.getName();   // 경로 + 파일명
		 //중복방지를 위해 파일명을 변경해준다.
		 
		 
		 
		 
		 long sizeInBytes = fileItem.getSize();
		 
		 if(sizeInBytes > 0){   // 파일이 정상?      // d:\\tmp\\abc.txt d:/tmp/abc.txt 
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
		       fileItem.write(uploadFile);      // 실제 upload되는 부분
		    }catch(Exception e){
		       e.printStackTrace();
		    }      
		 }
		 return filename;   // DB에 저장하기 위한 return;
		}
	
private void forward(String arg, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	RequestDispatcher dispatch = req.getRequestDispatcher(arg);
	dispatch.forward(req, resp);
	
}
	
}

	
	

