package blog.dreamland.com.controller;

import blog.dreamland.com.common.PageHelper;
import blog.dreamland.com.entity.User;
import blog.dreamland.com.entity.UserContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther SyntacticSugar
 * @data 2019/3/19 0019下午 10:31
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("index_list")
    public String page(Model model, @RequestParam(value = "id", required = false) String id,
                       @RequestParam(value = "pageNum", required = false) Integer pageNum,
                       @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        User user = (User) getSession().getAttribute("user");
        if (user!=null) {
            model.addAttribute("user",user);
        }
        PageHelper.Page<UserContent> page = findAll(null, pageNum, pageSize);
//        PageHelper.startPage(pageNum, pageSize);
        model.addAttribute("page",page);
        return "../index";
    }





}
