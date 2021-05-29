package com.webAppCard.Zuul;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class SimpleFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }
  
 // https://stackoverflow.com/questions/31829773/how-to-do-url-rewrite-in-zuul-proxy
	  
	  
	  
  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    
    Object originalRequestPath = ctx.get(REQUEST_URI_KEY);
    String modifiedRequestPath = "/api/microservicePath" + originalRequestPath;
    ctx.put(REQUEST_URI_KEY, modifiedRequestPath);
    
    
    String username = request.getRequestURL().toString();
    String password = request.getRequestURL().toString();	
    RestTemplate restTemplate = new RestTemplate();
    Boolean res = restTemplate.getForObject("http://127.0.0.1:8060/"+username+"/"+password, Boolean.class);

    if(!res){
        ctx.setResponseStatusCode(400);
        ctx.setResponseBody("access denied");
        ctx.setSendZuulResponse(false);

    }
    else {
    	forward la ressource demandé
    }
    return null;
  }

}





