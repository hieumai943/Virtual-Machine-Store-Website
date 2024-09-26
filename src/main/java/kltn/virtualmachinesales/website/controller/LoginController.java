package kltn.virtualmachinesales.website.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private DockerMonitorService dockerMonitorService;

    @GetMapping("/showMyLoginPage")
        public String showMyLoginPage() {
            return "plain-login";
        }

    @GetMapping("/test-docker")
    public void testDocker() {
        dockerMonitorService.monitorContainer();;
    }
}
