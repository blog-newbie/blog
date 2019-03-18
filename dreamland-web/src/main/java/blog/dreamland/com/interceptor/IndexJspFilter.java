package blog.dreamland.com.interceptor;

import blog.dreamland.com.common.PageHelper;
import blog.dreamland.com.dao.UserContentMapper;
import blog.dreamland.com.entity.UserContent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * @auther SyntacticSugar
 * @data 2019/3/18 0018下午 11:56
 */
public class IndexJspFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 过滤分页
        ServletContext context = servletRequest.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        //手动获取mapper
        UserContentMapper userContentMapper = ctx.getBean(UserContentMapper.class);
        PageHelper.startPage(null, null);//开始分页
        List<UserContent> list =  userContentMapper.select( null );
        PageHelper.Page endPage = PageHelper.endPage();//分页结束
        servletRequest.setAttribute("page", endPage );
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
