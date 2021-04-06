package com.example.demo.controller;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VirtualMachine;
import com.example.demo.repository.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class VirtualMachineController {

    @Autowired
    private VirtualMachineRepository virtualMachineRepository;

    //get all virtual machines
    @GetMapping("/virtualMachines")
    public List<VirtualMachine> getAllVirtualMachines() {
        return virtualMachineRepository.findAll();
    }



    @GetMapping("/availableVirtualMachines/{datestring}")
    public List<VirtualMachine> getAvailableVirtualMachines(@PathVariable String datestring) throws ParseException {

        System.out.println("running method");

        List<VirtualMachine> virtualMachines = virtualMachineRepository.findAll();

        List<VirtualMachine> availableVms =  new ArrayList<VirtualMachine>();

        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date date=formatter2.parse(datestring);


        System.out.println("---------------------------------------date is    " + date);

        for(int i=0 ; i<virtualMachines.size() ; i++){

            int availability = 1; // if one day is busy it will show 0


            for( int j =0 ; j< virtualMachines.get(i).getTrainingSessions().size() ; j++){

                Date date1 = formatter2.parse(virtualMachines.get(i).getTrainingSessions().get(j).getStartDate().toString());
                System.out.println(date1);

//////////////////////////////////////////////////////

                System.out.println(" Relevant training Session date is  " + date1);

                for(int k=0 ; k<virtualMachines.get(i).getTrainingSessions().get(j).getDuration() ; k++){

                    //increment date


                    String dt = date1.toString();  // Start date
                    Calendar c = Calendar.getInstance();
                    c.setTime(date1);
                    c.add(Calendar.DATE, k);  // number of days to add

                    System.out.println("Duration Function date is " + c.getTime().toString() + " After adding " + k);


                    if(c.getTime().toString().equals(date.toString())){

                        System.out.println("---------------busy because of -------------" + c.getTime().toString() + " si equal to " +date.toString() );
                        availability = 0;
                    }

                }


///////////////////////////////////////

            }

            if(availability == 1 ){

                availableVms.add(virtualMachines.get(i));
            }

        }

        return availableVms;

    }




    //add virtual machines
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/virtualMachines")
    public VirtualMachine addVirtualMachine(@RequestBody VirtualMachine virtualMachine) {
        return virtualMachineRepository.save(virtualMachine);
    }

    //get virtual machine by id
    @GetMapping("/virtualMachines/{id}")
    public ResponseEntity<VirtualMachine> getVirtualMachineById(@PathVariable Long id) {
        VirtualMachine virtualMachine = virtualMachineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Virtual Machine Not Found"));
        return ResponseEntity.ok(virtualMachine);
    }

    //delete virtual machine
    @DeleteMapping("/virtualMachines/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteVirtualMachine(@PathVariable Long id){
        VirtualMachine virtualMachine = virtualMachineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Virtual Machine Not Found"));


        virtualMachineRepository.delete(virtualMachine);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
