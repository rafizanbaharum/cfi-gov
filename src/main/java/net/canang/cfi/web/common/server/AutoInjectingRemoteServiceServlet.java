package net.canang.cfi.web.common.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * @author rafizan.baharum
 * @since 10/20/13
 */
public class AutoInjectingRemoteServiceServlet extends RemoteServiceServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext());
        AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }
}
