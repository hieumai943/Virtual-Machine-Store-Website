package kltn.virtualmachinesales.website.controller.nginx_auth;

import kltn.virtualmachinesales.website.dto.request.VirtualMachineDTO;
import kltn.virtualmachinesales.website.request.AuthRequest;
import kltn.virtualmachinesales.website.service.NginxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Properties;

@RestController
@CrossOrigin("http://localhost:3000/")
public class NginxAuthController {

    @Value("${nginx.htpasswd.path}")
    private String htpasswdPath;

    @Value("${passwordUbuntu}")
    private String passwordUbuntu;

    @Autowired
    private NginxService nginxService;

    @PostMapping("/change-auth")
    public String changeAuth(@RequestBody AuthRequest request) {
        StringBuilder output = new StringBuilder();
        List<String> commands = List.of(
//                "cd /home/hieunm369/Documents/kltn/XPRA_/app/ubuntu/nginx",
                "cd /home/hieunm369/Documents/'Virtual machine'/website/src/main/resources/nginx",
                "echo '"+ passwordUbuntu +"' | sudo -S htpasswd -b .htpasswd " + request.getUsername() + " " + request.getPassword()
        );

        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                processBuilder.command("cmd.exe", "/c", String.join(" && ", commands));
            } else {
                processBuilder.command("bash", "-c", String.join(" && ", commands));
            }
            Process process = processBuilder.start();
            // Đọc output tiêu chuẩn
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append("stdout: ").append(line).append("\n");
            }

            // Đọc lỗi tiêu chuẩn
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                output.append("stderr: ").append(line).append("\n");
            }

            int exitCode = process.waitFor();
            output.append("Exited with error code : ").append(exitCode);
        } catch (Exception e) {
            output.append("Error executing command: ").append(e.getMessage());
        }

    return output.toString();
    }
    @PostMapping("/send-email")
    public void sendEmail(@RequestBody AuthRequest request) {
        nginxService.sendEmail(request.getEmail(), request.getUsername());
    }
    @PostMapping("create/virtual-machine")
    public void createVirtualMachine(@RequestBody VirtualMachineDTO virtualMachineDTO) {
        nginxService.createVirtualMachine(virtualMachineDTO);
    }
}
