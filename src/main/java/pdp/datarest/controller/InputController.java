package pdp.datarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.InputDto;
import pdp.datarest.model.ProductDto;
import pdp.datarest.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @GetMapping
    public ResponseEntity<ApiResponse> getInputList(){
        return ResponseEntity.ok(inputService.getInputList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getInputById(@PathVariable Integer id){
        ApiResponse apiResponse = inputService.getInputById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addInput(@RequestBody InputDto inputDto){
        ApiResponse apiResponse = inputService.addInput(inputDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editInput(@PathVariable Integer id, @RequestBody InputDto inputDto){
        ApiResponse apiResponse = inputService.uploadInput(id, inputDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInput(@PathVariable Integer id){
        ApiResponse apiResponse= inputService.deleteInput(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}

