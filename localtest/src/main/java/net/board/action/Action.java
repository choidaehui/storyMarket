package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//추상메서드 구현 -> 인터페이스 구현시 반드시 사용해야 한다. (오버라이딩)
	// => 매서드를 강제로 사용하게 한다. (모든 동작을 동일하게 처리)
	
	//=> 매서드를 실행하고나서 페이지 이동정보를 리턴하는 메서드! DB가는 것
	//=> 액션포워드 리턴하고  페이지 요청정보(request), 페이지 응답정보(response)를 가지고 있는 메서드 
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
	
}
