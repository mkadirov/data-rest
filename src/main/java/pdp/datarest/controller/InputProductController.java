package pdp.datarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.InputProductDto;
import pdp.datarest.service.InputProductService;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @GetMapping
    public ResponseEntity<ApiResponse> getInputProductList(){
        return ResponseEntity.ok(inputProductService.getInputProductList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getInputProductById(@PathVariable Integer id){
        ApiResponse apiResponse = inputProductService.getInputProductById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addInputProduct(@RequestBody InputProductDto inputProductDto){
        ApiResponse apiResponse = inputProductService.addInputProduct(inputProductDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        ApiResponse apiResponse = inputProductService.uploadInputProduct(id, inputProductDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInputProduct(@PathVariable Integer id){
        ApiResponse apiResponse= inputProductService.deleteInputProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}

