package net.board.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.reviewBean;
import net.board.db.reviewDAO;

public class FileBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("M : FileBoardWriteAction_execute 실행!");
		
		//파일업로드
		//파일을 업로드할 폴더 생성 /upload
		
		//실제 파일이 저장될 서버의 경로 확인
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath("/upload");
		System.out.println("M : realPath -> "+ realPath);
		
		
		//파일크기 지정 10MB
		int maxSize = 10*1024*1024;
		
		//파일업로드
		MultipartRequest multi = 
				new MultipartRequest(
						request, 
						realPath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
						);
		
		System.out.println("M: MultipartRequest에서 객체생성 완료! 파일업로드 성공");
		
		
		//전달된 정보 저장(BoardBean)
		//name, pass, subject, content, filename
		//System.out.println(multi.getParameter("name"));
		
		
		reviewBean rb = new reviewBean();
		
		rb.setName(multi.getParameter("name"));
		rb.setPass(multi.getParameter("pass"));
		rb.setSubject(multi.getParameter("subject"));
		rb.setContent(multi.getParameter("content"));
		rb.setFile(multi.getFilesystemName("file"));//업로드한 파일이름
		//multi.getOriginalFileName("filename") //업로드한 파일의 원본이름
		rb.setRegionDivied(multi.getParameter("regionDivied"));
		rb.setIp(request.getRemoteAddr());
		rb.setRating(multi.getParameter("rating"));
		
		
		
		
		String imgName = multi.getFilesystemName("file");
		
		System.out.println("M : rb -> "+rb);
		System.out.println("이미지 이름 : "+ imgName);
		
		
		
		//DB저장
		reviewDAO rdao = new reviewDAO();
		rdao.inserBoard(rb);
		
		//페이지이동
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}

}
