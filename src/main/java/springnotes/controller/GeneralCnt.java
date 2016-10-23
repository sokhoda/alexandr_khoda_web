package springnotes.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springnotes.service.NotebookService;

import javax.servlet.ServletException;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/16/15
 */
@Controller
@SessionAttributes("id")
public class GeneralCnt {
    public static final Logger log = Logger.getLogger(GeneralCnt.class);
    @Autowired
    private NotebookService service;

    @RequestMapping(value = "/back2Menu.html", method = RequestMethod.GET)
    public String back2Menu(Model model) throws ServletException{
        log.info("general /back2Menu.html controller");
            return "menu";
    }

    @RequestMapping(value = "/menu.html", method = RequestMethod.GET)
    public String Menu() {
        log.info("All ia good!");
        return "menu";

    }
}
