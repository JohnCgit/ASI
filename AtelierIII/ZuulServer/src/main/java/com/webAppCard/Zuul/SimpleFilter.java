package com.webAppCard.Zuul;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class SimpleFilter extends ZuulFilter {

 // private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

  @Override
  public String filterType() {
    return "route";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
	  RequestContext ctx = RequestContext.getCurrentContext();
	  HttpServletRequest request = ctx.getRequest();
    return !request.getRequestURL().toString().contains("user/create");
  }
  
	  
  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    
    
    
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    RestTemplate restTemplate = new RestTemplate();
    Boolean res = restTemplate.getForObject("http://127.0.0.1:8060/"+username+"/"+password, Boolean.class);

    if(!res){
        ctx.setResponseStatusCode(400);
        ctx.setResponseBody("access denied");
        ctx.setSendZuulResponse(false);

    }   

    return null;


}}





