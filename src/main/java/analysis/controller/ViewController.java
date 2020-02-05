package analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * View Controller
 */
@Controller
@CrossOrigin
public class ViewController {

    @GetMapping("/view/{view}")
    public String view(@PathVariable String view){
        return view;
    }
}
