package springnotes.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springnotes.domain.Notebook;
import springnotes.service.NotebookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//import web.service.EmployeeService;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/16/15
 */
@Controller
@SessionAttributes("id")
@RequestMapping("notebook")
public class NotebookCnt {
    public static final Logger log = Logger.getLogger(NotebookCnt.class);
    @Autowired
    private NotebookService service;


    @Autowired
    private String str;

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody String/* List<Employee>*/ ajax(@RequestParam("name") String name) {
        return "Hello from Ajax to " + name;//Arrays.asList(new Employee("Pasha"), new Employee("Masha"));
    }

    @RequestMapping(value = "/ajaxa", method = RequestMethod.POST)
    public @ResponseBody
    List<Notebook> ajaxa(@RequestParam("name") String name) {
//        return Arrays.asList(new Employee("Pasha"), new Employee("Masha"));
//        return service.findAll();
        return new ArrayList<Notebook>();

    }

//    @RequestMapping(value = "/get", method = RequestMethod.POST)
//    public @ResponseBody Employee getString(@RequestParam String name, @RequestParam String pass) {
////        return new Employee(name + " from Server");
//        return new Employee();
//    }

    @RequestMapping(value = "/getAllNtbTypesByPortion.html", method =
            RequestMethod.GET)
    public String getAllNtbTypesByPortion(@RequestParam("listNtbByPortionPortion")
                        String sPortionPar, Model model) throws ServletException{
        log.info("/getAllNtbTypesByPortion.html controller");
        try{
            Integer sPortion = String2Integer(sPortionPar);
            Integer totPages = service.getNotebookInStoreTotPages(sPortion);
            List notebookPortion = (List<Notebook>)service.getNotebookTypesByPortion(sPortion, 1);
            model.addAttribute("notebookPortion", notebookPortion);
            model.addAttribute("cnt", 1);
            model.addAttribute("totPages", totPages);
            return "updateNotebook";
        }
        catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }


    @RequestMapping(value = "/back.html", method = RequestMethod.GET)
    public String helloBody(@RequestParam("sPortion") String sPortionPar,
                            @RequestParam("cntMark") String cntMarkPar,
                            Model model) throws ServletException{
        log.info("/back.html controller");
        try {
            Integer sPortion = Integer.parseInt(sPortionPar);
            int cnt = Integer.parseInt(cntMarkPar.split(" of ")[0]);
            int totPages = Integer.parseInt(cntMarkPar.split(" of ")[1]);

            if (cnt > 1) {
                cnt--;
            }

            List notebookPortion = service.getNotebookTypesByPortion(sPortion, cnt);
            model.addAttribute("cnt", cnt);
            model.addAttribute("totPages", totPages);
            model.addAttribute("notebookPortion", notebookPortion);
            model.addAttribute("sPortion", sPortion);
            return "updateNotebook";
        }
        catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    @RequestMapping(value = "/helloBodyKHO.html", method = RequestMethod.GET)
    public @ResponseBody String helloBodyKHO(@RequestParam("login") String name) {
        log.info("/helloBodyKHO.html controller");
        Random rand = new Random();
        return Integer.toString(rand.nextInt(9) + 1);
    }

    @RequestMapping(value = "/great.html", method = RequestMethod.GET)
    public String great(@RequestParam("login") String name, Model model, HttpSession session) {
        log.info("/great.html controller");
        Long sessId = (Long) session.getAttribute("id");
        if (sessId == null) {
            return "index";
        } else {
            // Goto DB and retrieve User by id
        }

        return "index";
    }

    @RequestMapping(value = "/form.html", method = RequestMethod.GET)
    public @ResponseBody String form(@RequestParam String login) {
//        employeeService.findAll();
        return login + "[" + "pass" + "]";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(Model model) {
        log.info("/index MyController");
        model.addAttribute("name", "al1");
        System.out.println(str);
        return "index";
    }

    @RequestMapping(value = "/menu.html", method = RequestMethod.GET)
    public String Menu() {
        return "menu";
    }


    @RequestMapping(value = "/helloAngular", method = RequestMethod.GET)
    public String angular() {
        return "helloAngular";
    }

    @RequestMapping(value = "/angularModel", method = RequestMethod.GET)
    public String angularModel() {
        return "angularModel";
    }

    @RequestMapping(value = "/angularController", method = RequestMethod.GET)
    public String angularController() {
        return "angularController";
    }

    public static Integer String2Integer(String str) throws NumberFormatException{
        if (str == null) {
            return null;
        }
        if (str.length() == 0){
            return 0;
        }
        return Integer.parseInt(str);
    }
}
