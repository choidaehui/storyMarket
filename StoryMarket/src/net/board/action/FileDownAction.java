package net.board.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("M: FileDownAction-execute 실행!");
		
		//전달된 정보 확인
		String fileName = request.getParameter("file_name");
		System.out.println(fileName);
		
		//프로젝트의 정보를 가져오기
		ServletContext context= request.getSession().getServletContext();
		String savePath = "upload";
	    String sDownloadPath = context.getRealPath(savePath);
	    
	    //다운로드할 파일의 위치(업로드한 위치)
	    System.out.println("sDownloadPath : "+sDownloadPath);
	    
		//다운로드 할 파일의 전체경로 생성
		String sFilePath = sDownloadPath + "\\" +  fileName;
	    System.out.println("sFilePath : "+sFilePath);
	  
	  	//파일을 스트림을 사용해서 정보를 가져오기
		//파일을 한번에 읽고 쓰고 처리하기위한 배열]
	  	byte[] b = new byte[4096];  
	  
	  	//파일 입력스트림 객체 생성
	  	FileInputStream in = new FileInputStream(sFilePath);
	  	
	  	//다운로드할 파일의 MIME타입을 체크
	  	//MIME타입 : 클라이언트에게 전달될 문서의 다양성을 표현하지 위한 메커니즘
	  	//https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types
	  	//=>웹에서는 파일의 확장자는 의미없음. 각 파일의 정보를 가지고 있는 문서와 
	  	//MIME타입을 전송해야 서버가 정확하게 파일을 처리할 수 있음.
	  	//브라우저가 리소스를 받아서 사용할 때 어떤 형태로 처리할 지 결정할 수 있음.
	  	
	  	String sMimeType = request.getSession().getServletContext().getMimeType(sFilePath);
	  	
	  	System.out.println("sMimeType >> " +sMimeType);
	  	
	  	//업로드한 파일의 MIME타입이 지정된 게 없을 경우 => 기본값을 지정
		//application/octect-stream : 이진파일을 처리하기위한 기본ㄱ밧
	  	if(sMimeType == null){
	  		sMimeType = "application/octect-stream";
	  	}
	  	
	  	// 페이지 응답 데이터를 마임타입으로 지정 -> 파일 다운로드시 사용
	  	response.setContentType(sMimeType);
	  	
	  	// 다운로드시 브라우저마다 처리
	  	
	  	//사용자의 브라우저 정보, 운영체제 정보 확인(JSP교과서 p25 참조)
	    String agent = request.getHeader("User-Agent");
	  	
	  	System.out.println("agent : " + agent );
	  	
	  	//IE(인터넷익스플로러) - "MSIE" / "Trident" 정보를 포함하고 있음
	  	//IE인지 구분
	  	boolean ieBrowser =
	  	agent.indexOf("MSIE") > -1 || agent.indexOf("Trident") > -1 ;//인덱스 값(배열) 리턴 //정보가 있다없다 판단
	  	//         A연산                       ||(둘중하나면 참)     B연산
	  	
	  	if(ieBrowser){
	  		fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	  		//=> IE경우 다운로드시 한글파일깨짐 
			//=> 한글로 변경시 IE공백문자가 "+" 변경됨 => 공백문자("%20")로 변경	
	  	}else{
	  		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
	  		// -> IE가 아닌 경우 한글 깨짐 변경(인코딩방식 변경)
	  	}
	  	
	  	//브라우저에서 해석되는 확장자의 파일도 다운로드로 실행되도록 처리
	  	//(img, png, jpg, txt... 브라우저에서 바로 실행 => 실행없이 다운로드로 변경)
	  	response.setHeader("Content-Disposition", "attachment; filename= "+fileName);
	  	
	  	
	  	// jsp->Servlet(java) 변환시 out 내장객체가 자동생성
	  	//내장객체 + 추가 out객체를 생성시 에러메세지 출력(실행가능, 에러로그만 출력)
	  	
	  	//out.close();
		/*
		 * out.clear();// 기존 내장객체를 비워낸다. out=pageContext.pushBody();
		 */
	  	
	  	//response 객체를 사용해서 데이터를 출력할 통로 생성
	  	ServletOutputStream out2 = response.getOutputStream();//출력하는 스트림통로를 통해 정보를 돌려준다.
	  	
	  	int numRead;
	  	
	  	//-1 : EOF(End Of File) 파일의 끝
	  	//FileInputStream객체를 사용해서 파일을 읽어서
	  	while( (numRead = in.read(b,0,b.length)) != -1 ){
	  		//response(보여줄 화면).getOutputStream();을 사용한 출력
	  		out2.write(b, 0, numRead);
	  	}
	  	
	  	// flush(); 출력 스크림 안에 공백을 채워서 전달
	  	//-> 배열(버퍼)의 빈공ㄱ나을 채워서 데이터를 모두 처리할 수 있도록 사용
	  	out2.flush();
	  	
	  	//자원해제(IO작업이 가장 리소스가 많이 필요하기 때문임)
	  	//사용한 객체의 역순으로 해제한다.
	  	out2.close();
	  	in.close();
		
	  	
	  	ActionForward forward = new ActionForward();
		forward.setPath("./BoardContent.bo");
		forward.setRedirect(true);
		return forward;

	}

}
