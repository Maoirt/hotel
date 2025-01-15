package com.example.hotel.service;

import com.example.hotel.model.Client;
import com.example.hotel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client create(Client client){
        return clientRepository.save(client);
    }
}
