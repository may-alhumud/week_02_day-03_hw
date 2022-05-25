package com.example.exercises1.Controller;

import com.example.exercises1.model.ApiResponse;
import com.example.exercises1.model.Park;
import com.sun.jdi.IntegerValue;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.function.Predicate;

@RestController
@RequestMapping("api/v1/park")
public class ParksManagement {
    ArrayList<Park> park = new ArrayList<Park>();

    @GetMapping
    public ResponseEntity getPark() {

        return ResponseEntity.status(200).body(park);
    }

    @PostMapping
    public ResponseEntity addPark(@RequestBody @Valid Park p, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            ApiResponse apiResponse = new ApiResponse(message, 400, error.getFieldError());
            return ResponseEntity.status(400).body(apiResponse);
        }
        System.out.println(p);
        park.add(p);
        return ResponseEntity.status(200).body(p);
    }


    @GetMapping("/{id}/{amount}")
    public ResponseEntity getTicket(@PathVariable @Valid String id, @PathVariable @Valid long amount, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            ApiResponse apiResponse = new ApiResponse(message, 400, error.getFieldError());
            return ResponseEntity.status(400).body(apiResponse);
        }
       var t = park.stream().filter(x->x.getRideID().equalsIgnoreCase(id)).findFirst();
       if(t.isEmpty() || t.get().getPrice() > amount || t.get().getTickets() <= 0){
            return ResponseEntity.status(400).body("bad Request");
       }
        t.get().setTickets(t.get().getTickets() - 1);
         return ResponseEntity.status(200).body(park);
      }

        @PutMapping("/{id}/refill")
        public ResponseEntity refill (@PathVariable @Valid String id, Errors error){
            if (error.hasErrors()) {
                String message = error.getFieldError().getDefaultMessage();
                ApiResponse apiResponse = new ApiResponse(message, 400, error.getFieldError());
                return ResponseEntity.status(400).body(apiResponse);
            }
            var t = park.stream().filter(x -> x.getRideID().equalsIgnoreCase(id)).findFirst();
            if (t.isEmpty() || t.get().getTickets() > 0) {
                return ResponseEntity.status(400).body("bad Request");
            }
            t.get().setTickets(5);
            return ResponseEntity.status(200).body(t.get());
        }


        @PutMapping("/{id}")
        public ResponseEntity updatePark (@PathVariable @Valid String id, @RequestBody @Valid Park p, Errors error){
            if (error.hasErrors()) {
                String message = error.getFieldError().getDefaultMessage();
                ApiResponse apiResponse = new ApiResponse(message, 400, error.getFieldError());
                return ResponseEntity.status(400).body(apiResponse);
            }
            var t = park.stream().filter(x -> x.getRideID().equalsIgnoreCase(id)).findFirst();
            if (t.isEmpty()) {
                return ResponseEntity.status(400).body("bad Request");
            }
            var pp = t.get();
            pp.setTickets(p.getTickets());
            return ResponseEntity.status(200).body(t.get());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity deletPark (@PathVariable @Valid String id, Errors error){
            if (error.hasErrors()) {
                String message = error.getFieldError().getDefaultMessage();
                ApiResponse apiResponse = new ApiResponse(message, 400, error.getFieldError());
                return ResponseEntity.status(400).body(apiResponse);
            }
            var t = park.stream().filter(x -> x.getRideID().equalsIgnoreCase(id)).findFirst();
            if (t.isEmpty()) {
                return ResponseEntity.status(400).body("bad Request");
            }
            var pp = t.get();
            park.remove(pp);
            return ResponseEntity.status(200).body(" park was deleted ");
        }

    }

