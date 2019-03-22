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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


    /** 通过category  查找 */
    @RequestMapping("findByCategory")
    @ResponseBody
    public Map<String,Object> getsdf(Model model,
                                     @RequestParam(value = "category", required = false) String category,
                                     @RequestParam(value = "pageNum", required = false) Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize){
        // var dataPage = data['pageCate'];


        User user = (User) getSession().getAttribute("user");
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        if (user==null) {
            stringObjectHashMap.put("pageCate", "fail");
            return stringObjectHashMap;
        }
        pageSize=4;
        PageHelper.Page<UserContent> pageCate = userContentService.findByCategory(category,user.getId(), pageNum, pageSize);
        stringObjectHashMap.put("pageCate", pageCate);
        return stringObjectHashMap;
    }


    @RequestMapping("/findPersonal")
    @ResponseBody
    public Map<String,Object> findPersonal(Model model,
                                           @RequestParam(value = "pageNum",required = false) Integer pageNum ,
                                           @RequestParam(value = "pageSize",required = false) Integer pageSize) {

        Map map = new HashMap<String,Object>();
        User user = (User)getSession().getAttribute("user");
        if(user==null) {
            map.put("page2","fail");
            return map;
        }
        pageSize = 4; //默认每页显示4条数据
        PageHelper.Page<UserContent> page = userContentService.findPersonal(user.getId(),pageNum,pageSize);
        map.put("page2",page);
        return map;
    }



}
