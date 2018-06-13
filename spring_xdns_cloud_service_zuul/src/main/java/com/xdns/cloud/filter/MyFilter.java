package com.xdns.cloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class MyFilter extends ZuulFilter{
	
	private final static Logger log = LoggerFactory.getLogger(MyFilter.class);
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("{}>>>>{}",request.getMethod(),request.getRequestURI().toString());
		Object  accessToken = request.getParameter("token");
		if(accessToken == null){
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
			return null;
		}
		log.info("ok");
		return null;
	}

	@Override
//	shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
	public boolean shouldFilter() {
		return true;
	}

	@Override
//	filterOrder：过滤的顺序
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
//		pre：路由之前
//		routing：路由之时
//		post： 路由之后
//		error：发送错误调用
//		run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
		return "pre";
	}
	
	public static void main(String[] args) {
		log.info("dsadasd:{}da:{}", "321","321");
	}
}
