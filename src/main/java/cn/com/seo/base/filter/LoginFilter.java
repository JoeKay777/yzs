package cn.com.seo.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LoginFilter implements Filter {

	/**
	 * 
	 */

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("----user登录过滤器销毁----");
	}

	public Logger log = Logger.getLogger(LoginFilter.class);

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("----user登录过滤器初始化----");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getServletPath();
		// 取得根目录所对应的绝对路径
		HttpSession session = request.getSession();
		if (!"/client/login.jsp".equals(currentURL)&&!"/client/register.jsp".equals(currentURL)) {// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
			
			if (session.getAttribute("user") == null) {
				// *用户登录以后需手动添加session
				response.sendRedirect(request.getContextPath()  + "/client/login.jsp");
				// 如果session为空表示用户没有登录就重定向到login.jsp页面
				return;
				
			}
		}
			// 加入filter链继续向下执行
			filterChain.doFilter(request, response);
		
	
	}

}
