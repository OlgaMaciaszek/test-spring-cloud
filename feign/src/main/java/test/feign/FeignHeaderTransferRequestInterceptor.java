package test.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class FeignHeaderTransferRequestInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(FeignHeaderTransferRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        Assert.notNull(attributes, "ServletRequestAttributes is null");
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
            logger.info("feign interceptor header:{}", requestTemplate);
        }
       /* Enumeration<String> bodyNames = request.getParameterNames();
        StringBuffer body =new StringBuffer();
        if (bodyNames != null) {
            while (bodyNames.hasMoreElements()) {
                String name = bodyNames.nextElement();
                String values = request.getParameter(name);
                body.append(name).append("=").append(values).append("&");
            }
        }
        if(body.length()!=0) {
            body.deleteCharAt(body.length()-1);
            template.body(body.toString());
            //logger.info("feign interceptor body:{}",body.toString());
        }*/
    }
}
