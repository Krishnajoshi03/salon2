package com.example.cruddemo.service;

import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Owner;

import io.swagger.v3.oas.annotations.servers.Server;

@Service
public interface OwnerService {

	Owner getOwner(String id);

	String createOwner(String userId);

}
