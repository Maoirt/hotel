package com.example.hotel.service;

import com.example.hotel.exception.AmountIsSmallException;
import com.example.hotel.exception.RoomNotFoundException;
import com.example.hotel.exception.TimeFormatException;
import com.example.hotel.exception.UserNotFoundException;
import com.example.hotel.model.Client;
import com.example.hotel.model.Room;
import com.example.hotel.model.RoomStatus;
import com.example.hotel.repository.ClientRepository;
import com.example.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final RoomRepository roomRepository;
    private final ClientRepository clientRepository;

    public ArrayList<Room> availableRooms(){

        ArrayList<Room> rooms = (ArrayList<Room>) roomRepository.findAll();
        ArrayList<Room> availableRooms = (ArrayList<Room>) rooms.stream().filter(room->room.getStatus()== RoomStatus.AVAILABLE).toList();

        return availableRooms;
    }

    public Room checkIntoHotel(UUID clientId, int roomId){

        Room room = roomRepository.findById(roomId).orElseThrow(()->new RoomNotFoundException("room not found"));
        Client client = clientRepository.findById(clientId).orElseThrow(()->new UserNotFoundException("user not found"));

        if(room.getAmount()>room.getClients().size()+1) throw new AmountIsSmallException("Amount small");

        List<Client> clientList = room.getClients();
        clientList.add(client);
        client.setRoom(room);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        client.setCheckIn(Timestamp.valueOf(timeStamp));

        room.setClients(clientList);
        room.setAmount(room.getAmount()+1);

        clientRepository.save(client);

        return roomRepository.save(room);
    }

    public Room checkOutHotel(UUID clientId, int roomId){

        Room room = roomRepository.findById(roomId).orElseThrow(()->new RoomNotFoundException("room not found"));
        Client client = clientRepository.findById(clientId).orElseThrow(()->new UserNotFoundException("user not found"));

        if(room.getAmount()<0) throw new AmountIsSmallException("Amount error");

        List<Client> clientList = room.getClients();
        clientList.remove(client);
        room.setClients(clientList);
        room.setAmount(room.getAmount()-1);

        client.setRoom(null);
        clientRepository.save(client);

        return roomRepository.save(room);
    }

    public ArrayList<Client> getSettledClients(){

        ArrayList<Client> clients = (ArrayList<Client>) clientRepository.findAll();

        return (ArrayList<Client>) clients.stream().filter(client -> client.getRoom()!=null).toList();

    }

    public Client prolongCheckIn(UUID clientId, int days){

        Client client = clientRepository.findById(clientId).orElseThrow(()->new UserNotFoundException("user not found"));

        if(days<0) {
            throw new TimeFormatException("days must be positive number");
        }

        LocalDateTime localDateTime = client.getEviction().toLocalDateTime();
        localDateTime = localDateTime.plusDays(days);
        client.setEviction(Timestamp.valueOf(localDateTime));

        return clientRepository.save(client);
    }

}
