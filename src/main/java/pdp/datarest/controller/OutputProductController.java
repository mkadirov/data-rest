package pdp.datarest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.OutputProductDto;
import pdp.datarest.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {


    @Autowired
    OutputProductService outputProductService;

    @GetMapping
    public ResponseEntity<ApiResponse> getOutputProductList(){
        return ResponseEntity.ok(outputProductService.getOutputProductList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOutputProductById(@PathVariable Integer id){
        ApiResponse apiResponse = outputProductService.getOutputProductById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        ApiResponse apiResponse = outputProductService.addOutputProduct(outputProductDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        ApiResponse apiResponse = outputProductService.uploadOutputProduct(id, outputProductDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOutputProduct(@PathVariable Integer id){
        ApiResponse apiResponse= outputProductService.deleteOutputProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
