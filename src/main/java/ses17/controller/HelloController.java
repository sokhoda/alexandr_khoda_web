package ses17.controller;

import ses17.domain.Employee;
import ses17.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
public class HelloController {
    public static final Logger log = Logger.getLogger(HelloController.class);
    @Autowired
    private EmployeeService service;

    @Autowired
    private String str;

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody String/* List<Employee>*/ ajax(@RequestParam("name") String name) {
        return "Hello from Ajax to " + name;//Arrays.asList(new Employee("Pasha"), new Employee("Masha"));
    }

    @RequestMapping(value = "/ajaxa", method = RequestMethod.POST)
    public @ResponseBody
    List<Employee> ajaxa(@RequestParam("name") String name) {
//        return Arrays.asList(new Employee("Pasha"), new Employee("Masha"));
        return service.findAll();
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public @ResponseBody Employee getString(@RequestParam String name, @RequestParam String pass) {
        return new Employee(name + " from Server");
    }

    @RequestMapping(value = "/hello.html", method = RequestMethod.GET)
    public String hello(@RequestParam("login") String name, Model model) {
        log.info("/hello.html controller");
        Employee employee = service.getEmployee(name);
        model.addAttribute("employee", employee);
        return "index";
    }

    @RequestMapping(value = "/helloBody.html", method = RequestMethod.GET)
    public @ResponseBody String helloBody(@RequestParam("login") String name) {
        log.info("/hello.html controller");

        return "hello " +  service.getEmployee(name).toString();
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
        log.info("/index controller");
        model.addAttribute("name", "al1");
        System.out.println(str);
        return "index";
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
}
