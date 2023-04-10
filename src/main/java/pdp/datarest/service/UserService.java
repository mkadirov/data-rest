package pdp.datarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pdp.datarest.entity.User;
import pdp.datarest.entity.Warehouse;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.UniqueNumberGenerator;
import pdp.datarest.model.UserDto;
import pdp.datarest.repository.UserRepository;
import pdp.datarest.repository.WarehouseRepository;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    //POST method to add new User
    public ApiResponse addUser(@RequestBody UserDto userDto){
        if(userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            return new ApiResponse("Phone number exists ", false);
        }
        Set<Warehouse> warehouseSet = new HashSet<>();
        for (Integer id : userDto.getWarehousesIdList()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
            optionalWarehouse.ifPresent(warehouseSet::add);
            return new ApiResponse("Warehouse not found", false);
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
        user.setPassword(user.getPassword());
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new ApiResponse("Successfully added", true);
    }

    //GET method to get list of users
    public ApiResponse getUsersList(){
        return new ApiResponse("Successfully retrieved", true, userRepository.findAll());
    }

    //GET method to get user by ID
    public ApiResponse getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user ->
                new ApiResponse("Successfully retrieved", true, user)).orElseGet(() ->
                new ApiResponse("User not found", false));
    }

    //PUT method to upload User by ID
    public ApiResponse uploadUserById(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return new ApiResponse("User not found", false);
        }
        User user = optionalUser.get();
        if(!user.getPhoneNumber().equals(userDto.getPhoneNumber())) {
            if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber())) {
                return new ApiResponse("Phone number exists ", false);
            }
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        Set<Warehouse> warehouseSet = new HashSet<>();
        for (Integer warehouseId : userDto.getWarehousesIdList()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
            optionalWarehouse.ifPresent(warehouseSet::add);
            return new ApiResponse("Warehouse not found", false);
        }

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(user.getPassword());
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new ApiResponse("Successfully added", true);
    }

    //DELETE method to delete user by ID
    public ApiResponse deleteUserById(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        }
        return new ApiResponse("User not found", false);
    }
}