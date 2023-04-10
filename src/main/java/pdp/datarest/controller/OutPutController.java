package pdp.datarest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.OutputDto;
import pdp.datarest.service.OutputService;

@RestController
@RequestMapping("/outPut")
public class OutPutController {

    @Autowired
    OutputService outputService;

    @GetMapping
    public ResponseEntity<ApiResponse> getOutputList(){
        return ResponseEntity.ok(outputService.getOutList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOutputById(@PathVariable Integer id){
        ApiResponse apiResponse = outputService.getOutputById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addOutput(@RequestBody OutputDto outputDto){
        ApiResponse apiResponse = outputService.addOutput(outputDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editOutput(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        ApiResponse apiResponse = outputService.uploadOutput(id, outputDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOutput(@PathVariable Integer id){
        ApiResponse apiResponse= outputService.deleteOutput(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
