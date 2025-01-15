package com.example.hotel.controller;

import com.example.hotel.model.Client;
import com.example.hotel.model.Room;
import com.example.hotel.service.ClientService;
import com.example.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public Room create(@RequestBody Room room){
        return roomService.create(room);
    }

}
