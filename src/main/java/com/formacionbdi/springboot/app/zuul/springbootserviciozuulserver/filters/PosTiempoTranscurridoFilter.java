package com.formacionbdi.springboot.app.zuul.springbootserviciozuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PosTiempoTranscurridoFilter extends ZuulFilter {

    private static Logger log= LoggerFactory.getLogger(PosTiempoTranscurridoFilter.class);
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx= RequestContext.getCurrentContext();
        HttpServletRequest request= ctx.getRequest();

       log.info("Entrado a post filter");

        Long tiempoInicio= (Long) request.getAttribute("tiempoInicio");
        Long tiempoFinal= System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal-tiempoInicio;

        log.info(String.format("Tiempo transcurrido en segundos %s", tiempoTranscurrido.doubleValue()/1000));
        log.info(String.format("Tiempo transcurrido en miliseg %s", tiempoTranscurrido));

        return null;
    }


}
