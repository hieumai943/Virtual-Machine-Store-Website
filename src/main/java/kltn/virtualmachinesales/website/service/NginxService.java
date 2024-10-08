package kltn.virtualmachinesales.website.service;

import kltn.virtualmachinesales.website.dto.request.VirtualMachineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NginxService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

   public void sendEmail(String gmail, String account){
       kafkaTemplate.send("sendEmail", gmail+ " "+ account);
   }
   public void createVirtualMachine(VirtualMachineDTO virtualMachineDTO){

   }
}
