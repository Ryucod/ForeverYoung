package forever.young.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	
	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession httpSession = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVo = modelMap.get("user");

		if (userVo != null) {
			logger.info("login success");
			httpSession.setAttribute(LOGIN, userVo);
			response.sendRedirect("main.do");
		}
	}

	// ���� �α��� ���� ����
	/*
	 * @Override public boolean preHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object handler) throws Exception { HttpSession
	 * httpSession = request.getSession(); if (httpSession.getAttribute(LOGIN) !=
	 * null) { logger.info("�α��� ���� ����"); httpSession.removeAttribute(LOGIN); }
	 * return true; }
	 */

}// �ش� interceptor mapping�� user-servlet.xml��
