package com.example.hotel.controller;

import com.example.hotel.model.Client;
import com.example.hotel.model.Room;
import com.example.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/available-rooms")
    public ArrayList<Room> availableRooms(){
       return hotelService.availableRooms();
    }

    @PostMapping("/check-in/{roomId}")
    public Room checkIntoHotel(@RequestBody UUID clientId, @RequestParam int roomId){
        return hotelService.checkIntoHotel(clientId, roomId);
    }

    @PostMapping("/check-out/{roomId}")
    public Room checkOutHotel(@RequestBody UUID clientId, @RequestParam int roomId){
        return hotelService.checkOutHotel(clientId, roomId);
    }

    @GetMapping("/settled-clients")
    public ArrayList<Client> getSettledClients(){
        return hotelService.getSettledClients();
    }

    @PostMapping("/prolong/{days}")
    public Client prolongCheckIn(@RequestBody UUID clientId, @RequestParam int days){

        return hotelService.prolongCheckIn(clientId, days);
    }

}
