package blog.dreamland.com.controller;

import blog.dreamland.com.common.PageHelper;
import blog.dreamland.com.entity.User;
import blog.dreamland.com.entity.UserContent;
import blog.dreamland.com.service.UserContentService;
import blog.dreamland.com.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @auther SyntacticSugar
 * @data 2019/3/20 0020下午 9:33
 */
@Controller
public class PersonalController extends BaseController {

    private final static Logger log = Logger.getLogger(PersonalController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserContentService userContentService;

    @RequestMapping("list")
    public String getList(Model model,
                          @RequestParam(value = "uid", required = false) Long uid,
                          @RequestParam(value = "pageNum", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        User user = (User) getSession().getAttribute("user");
        UserContent userContent = new UserContent();
        UserContent userContentPersonal = new UserContent();
        if (user!=null) {
            userContent.setuId(user.getId());
            model.addAttribute("user", user);
            userContentPersonal.setuId(user.getId());
        }else {
            return "../login";
        }

        //查询文章分类，依据category 查询content
        List<UserContent> categorys = userContentService.findCategoryByUserId(user.getId());
        userContent.setPersonal("0");
        model.addAttribute("categorys", categorys);
        pageSize=4;
        PageHelper.Page<UserContent> page = findAll(userContent, pageNum, pageSize);
        model.addAttribute("page", page);
        log.info("个人中心的content");
        //personal blog

        userContentPersonal.setPersonal("1");
        PageHelper.Page<UserContent> page2 = findAll(userContentPersonal, pageNum, pageSize);
        model.addAttribute("page2", page2);
        // hot blog content
        UserContent userContentHot = new UserContent();
        userContentHot.setPersonal("0");
        PageHelper.Page<UserContent> pageHot = findAll(userContentHot, pageNum, pageSize);
        model.addAttribute("pageHot", pageHot);

         return "personal/personal";
    }
}
