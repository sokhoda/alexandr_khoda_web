package springnotes.controller;

import springnotes.domain.CPU;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ses17.domain.Employee;
import springnotes.domain.Notebook;
import springnotes.exception.PortionException;
import springnotes.service.NotebookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static hw7.notes.view.Servlet.String2Integer;
//import web.service.EmployeeService;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/16/15
 */
@Controller
@SessionAttributes("id")
@RequestMapping("cpu")
public class CPUCnt {
    public static final Logger log = Logger.getLogger(CPUCnt.class);
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

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public @ResponseBody Employee getString(@RequestParam String name, @RequestParam String pass) {
//        return new Employee(name + " from Server");
        return new Employee();
    }

    @RequestMapping(value = "/update.html", method = RequestMethod.GET)
    public String update(@RequestParam("updCPUPortion") String sPortionPar,
                         Model model) throws ServletException{
        log.info("/cpu/update.html controller");
        try{
            Integer sPortion = String2Integer(sPortionPar);
            List cpuPortion = service.listCPUByPortion(sPortion, 1);
            Integer totPages = service.getCPUTotPages(sPortion);
            model.addAttribute("cnt", 1);
            model.addAttribute("totPages", totPages);
            model.addAttribute("cpuPortion", cpuPortion);
            model.addAttribute("sPortion", sPortion);
            return "updateCPU";
        }
        catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    @RequestMapping(value = "/back.html", method = RequestMethod.GET)
    public String back(@RequestParam("sPortion") String sPortionPar,
                            @RequestParam("cntMark") String cntMarkPar,
                            Model model) throws ServletException{
        log.info("/cpu/back.html controller");
        try {
            Integer sPortion = Integer.parseInt(sPortionPar);
            int cnt = Integer.parseInt(cntMarkPar.split(" of ")[0]);
            int totPages = Integer.parseInt(cntMarkPar.split(" of ")[1]);

            if (cnt > 1) {
                cnt--;
            }

            List cpuPortion = (List<CPU>)service.getCPUByPortion(sPortion, cnt);
            model.addAttribute("cnt", cnt);
            model.addAttribute("totPages", totPages);
            model.addAttribute("cpuPortion", cpuPortion);
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
