package XMLReader;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Controllers {
    @GetMapping("/forecast")
    public String index(Model model) {
        XMLReader xmlReader = new XMLReader();
        model.addAttribute("forecasts", xmlReader.parseForecasts());
        return "forecast";
    }
}