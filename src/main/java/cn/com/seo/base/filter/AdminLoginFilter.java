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

public class AdminLoginFilter implements Filter{

	/**
	 * 
	 */

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("----admin��¼����������----");
	}

	public Logger log = Logger.getLogger(LoginFilter.class);

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("----admin��¼��������ʼ��----");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String currentURL = request.getServletPath();
		// ȡ�ø�Ŀ¼����Ӧ�ľ���·��:
		HttpSession session = request.getSession();
		if (!"/admin/login.jsp".equals(currentURL)) {// �жϵ�ǰҳ�Ƿ����ض����Ժ�ĵ�¼ҳ��ҳ�棬����ǾͲ���session���жϣ���ֹ������ѭ��
			
			if (session.getAttribute("admin") == null) {
				// *�û���¼�Ժ����ֶ����session
				response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
				// ���sessionΪ�ձ�ʾ�û�û�е�¼���ض���login.jspҳ��
				return;
				
			}
		}
			// ����filter����������ִ��
			filterChain.doFilter(request, response);
		
	
	}

}
