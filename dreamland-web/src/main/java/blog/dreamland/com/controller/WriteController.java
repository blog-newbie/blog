package blog.dreamland.com.controller;

import blog.dreamland.com.entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther SyntacticSugar
 * @data 2019/3/22 0022下午 11:29
 */
@Controller
public class WriteController extends BaseController {

    private final static Logger log = Logger.getLogger(WriteController.class);

    @RequestMapping("writeBlog")
    public String write(Model model,
                        @RequestParam(value = "writeBlog",required = false) String writeBlog
                         ){
        User user = (User) getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "writeBlog";

    }





}
