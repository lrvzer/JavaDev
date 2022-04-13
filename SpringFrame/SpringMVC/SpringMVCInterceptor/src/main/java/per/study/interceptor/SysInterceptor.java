package per.study.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysInterceptor implements HandlerInterceptor {

    /**
     * 预处理，controller方法执行前
     * @param request
     * @param response
     * @param handler
     * @return true 放行，执行下一个拦截器，如果没有，执行controller中的方法
     * @return false 不放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);
        return true;
    }

    /**
     * 后执行，controller方法执行后，success.jsp之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
//        request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
    }

    /**
     * success.jsp之后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
