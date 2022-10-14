package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.dto.UserDTO;
import com.example.bookstoreapplication.exception.UserException;
import com.example.bookstoreapplication.model.User;
import com.example.bookstoreapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


    @RestController
    @RequestMapping("/user")
    public class UserController {
        @Autowired
        IUserService service;


        // for getting the Welcome message
        @GetMapping("/welcome")
        public ResponseEntity<String> getWelcome() {
            String welcome = service.getWelcome();
            return new ResponseEntity<String>(welcome, HttpStatus.OK);
        }

        // for crearing new data in database
        @PostMapping("/create")
        public ResponseEntity<String> addDataToRepo(@Valid @RequestBody UserDTO userDTO) {
            User newUser = service.postDataToRepo(userDTO);
            ResponseDTO responseDTO = new ResponseDTO("Record Added Succesfully", newUser);
            return new ResponseEntity(responseDTO, HttpStatus.CREATED);
        }

        //for getting all data from database
        @GetMapping("/get")
        public ResponseEntity<String> getAllDataFromRepo() {
            List<User> listOfUser = service.getAllData();
            ResponseDTO responseDTO = new ResponseDTO("Record Retrieved Successfully", listOfUser);
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }

        // for getting data by Id from data base
        @GetMapping("/get/{userId}")
        public ResponseEntity<String> getDataFromRepoById(@PathVariable Integer userId) throws UserException {
            User existingUser = service.getDataById(userId);
            ResponseDTO responseDTO = new ResponseDTO("Record for given ID Retrieved Successfully", existingUser);
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }

        // for updating data by id in database
        @PutMapping("/update/{userId}")
        public ResponseEntity<String> updateDataInRepo(@PathVariable Integer userId, @Valid @RequestBody UserDTO userDTO)
                throws UserException {
            User updatedUser = service.updateDataById(userId, userDTO);
            ResponseDTO responseDTO = new ResponseDTO("Record for particular ID Updated Successfully", updatedUser);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }
        //for deleting the data by Id in database
        @DeleteMapping("/delete/{userId}")
        public ResponseEntity<String> deleteDataInRepo(@PathVariable Integer userId) throws UserException {
            ResponseDTO responseDTO = new ResponseDTO
                    ("Record for particular ID Deleted Successfully", service.deleteDataById(userId));
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }


        @GetMapping("/getbyfirstname/{firstname}")
        public ResponseEntity<ResponseDTO> getRecordFromRepoByFirstName(@PathVariable String firstname) throws UserException {
            List<User> newUser = service.getDataByFirstName(firstname);
            ResponseDTO dto = new ResponseDTO("Record for given Department Retrieved Successfully", newUser);
            return new ResponseEntity(dto, HttpStatus.OK);
        }



    }
