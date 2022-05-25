package com.example.exercises2.controller;

import com.example.exercises2.model.ApiResponse;
import com.example.exercises2.model.Employees;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeesManagement {

    ArrayList<Employees> employee=new ArrayList<Employees>();

     @GetMapping
     public ResponseEntity getEmployee(){
         return ResponseEntity.status(200).body(employee);
     }

      @PostMapping
      public ResponseEntity addEmployee(@RequestBody @Valid Employees e, Errors error){
        if(error.hasErrors()){
           String message=error.getFieldError().getDefaultMessage();
           ApiResponse apiResponse=new ApiResponse(message,400,error.getFieldError());
           return ResponseEntity.status(400).body(apiResponse);
         }
         employee.add(e);
         return ResponseEntity.status(200).body(e);
       }


       @PutMapping("/{id}")
       public ResponseEntity updateEmployee(@PathVariable @Valid String id,@RequestBody @Valid Employees e, Errors error){
         if(error.hasErrors()){
             String message=error.getFieldError().getDefaultMessage();
             ApiResponse apiResponse=new ApiResponse(message,400,error.getFieldError());
             return ResponseEntity.status(400).body(apiResponse);
         }
         var E = employee.stream().filter(x->x.getID().equalsIgnoreCase(id)).findFirst();
         if(E.isEmpty()){
             return ResponseEntity.status(400).body("bad Request");
         }
         var ee = E.get();
         ee.setID(e.getID());

        return ResponseEntity.status(200).body(E.get());
        }

      @DeleteMapping("/{id}")
      public ResponseEntity deletEmployee(@PathVariable @Valid String id, Errors error){
       if(error.hasErrors()){
           String message=error.getFieldError().getDefaultMessage();
          ApiResponse apiResponse=new ApiResponse(message,400,error.getFieldError());
          return ResponseEntity.status(400).body(apiResponse);
       }
       var E = employee.stream().filter(x->x.getID().equalsIgnoreCase(id)).findFirst();
        if(E.isEmpty()){
            return ResponseEntity.status(400).body("bad Request");
        }
        var ee = E.get();
        employee.remove(ee);
        return ResponseEntity.status(200).body(" park was deleted ");
    }


    @PutMapping("{id}/AnnualLeave")
    public ResponseEntity checkAnnualLeave(@PathVariable  String id){
        Integer indx = getIndex(id);
        if(indx == null){
            return ResponseEntity.status(400).body("there is no employee");
        } else if (employee.get(indx).getAnnualLeave() == 0){
            return ResponseEntity.status(400).body("bad request,There is no enough days for annual leave");
        } else if (employee.get(indx).isOnLeave() == true){
            employee.get(indx).setAnnualLeave((employee.get(indx).getAnnualLeave() - 1));
            return ResponseEntity.status(200).body("bad request, employee on annual leave");
        } else {
            return ResponseEntity.status(400).body("bad request, Employee has applied already to annual leave");
        }
    }
    public Integer getIndex(String ID){
        for (int i = 0; i < this.employee.size(); i++) {
            if (employee.get(i).getID().equals(ID)){
                return i;
            }
        }
        return null;
    }


}

