package com.aqiang.xysht.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.aqiang.xysht.entities.ShopKeeper;

public class ShopKeeperLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		ShopKeeper shopKeeper = (ShopKeeper) session.getAttribute("shopKeeper");
		if (shopKeeper != null) {
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/nonLogin/shopKeeper_toLoginPage");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
