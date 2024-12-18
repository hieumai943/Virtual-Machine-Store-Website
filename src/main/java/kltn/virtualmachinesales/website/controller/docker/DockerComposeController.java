package kltn.virtualmachinesales.website.controller.docker;

import kltn.virtualmachinesales.website.dto.request.ChangeContainerRequest;
import kltn.virtualmachinesales.website.request.CreateContainerRequest;
import kltn.virtualmachinesales.website.request.RestartDockerRequest;
import kltn.virtualmachinesales.website.service.DockerComposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/shop/docker-compose")
@CrossOrigin(origins = "http://localhost:3000")
public class DockerComposeController {
    @Autowired
    private DockerComposeService dockerComposeService;

    @PutMapping("/update")
    public ResponseEntity<String> updateConfig(@RequestBody CreateContainerRequest request) {
        try {
            String result = dockerComposeService.createUpdatedServiceResources( request.getCpuLimit(), request.getMemoryLimit(), request.getPort(), request.getMachineId());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating configuration: " + e.getMessage());
        }
    }

    @PostMapping("/off")
    public ResponseEntity<String> offContainer(@RequestBody ChangeContainerRequest request) {
        try {
            String result = dockerComposeService.changeStatusContainer( request.getIsStart(), request.getContainerName());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating configuration: " + e.getMessage());
        }
    }

    @PostMapping("/restart")
    public ResponseEntity<String> restartContainer(@RequestBody RestartDockerRequest request) {
        try {
            String result = dockerComposeService.restartContainer(request.getContainerName());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating configuration: " + e.getMessage());
        }
    }
}
