package pdp.datarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.datarest.entity.Currency;
import pdp.datarest.entity.Input;
import pdp.datarest.entity.Supplier;
import pdp.datarest.entity.Warehouse;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.InputDto;
import pdp.datarest.model.UniqueNumberGenerator;
import pdp.datarest.repository.CurrencyRepository;
import pdp.datarest.repository.InputRepository;
import pdp.datarest.repository.SupplierRepository;
import pdp.datarest.repository.WarehouseRepository;


import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;




    //POST method to add new Input
    public ApiResponse addInput(InputDto inputDto){
        if(inputRepository.existsByFactureNumber(inputDto.getFactureNumber())){
            return new ApiResponse("FactureNumber exists", false);
        }
        Optional<Warehouse> opWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if(opWarehouse.isEmpty()){
            return new ApiResponse("Warehouse not found", false);
        }
        Optional<Supplier> opSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (opSupplier.isEmpty()){
            return new ApiResponse("Supplier not found", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new ApiResponse("Currency not found", false);
        }

        Input input = new Input();
        input.setTimestamp(inputDto.getTimestamp());
        input.setWarehouse(opWarehouse.get());
        input.setSupplier(opSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
        inputRepository.save(input);
        return new ApiResponse("Successfully added", true);
    }

    //GET method to get InputList
    public ApiResponse getInputList(){
        return new ApiResponse("Successfully retrieved", true, inputRepository.findAll());
    }

    //GET method to get input by ID
    public ApiResponse getInputById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.map(input ->
                new ApiResponse("Successfully retrieved", true, input)).orElseGet(() ->
                new ApiResponse("Input not found", false));
    }

    //PUT method to update Input by ID
    public ApiResponse uploadInput(Integer id, InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()){
            return new ApiResponse("Input not found", false);
        }
        Input input = optionalInput.get();
        if(!input.getFactureNumber().equals(inputDto.getFactureNumber())) {
            if (inputRepository.existsByFactureNumber(inputDto.getFactureNumber())) {
                return new ApiResponse("FactureNumber exists", false);
            }
            input.setFactureNumber(inputDto.getFactureNumber());
        }
        Optional<Warehouse> opWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if(opWarehouse.isEmpty()){
            return new ApiResponse("Warehouse not found", false);
        }
        Optional<Supplier> opSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (opSupplier.isEmpty()){
            return new ApiResponse("Supplier not found", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new ApiResponse("Currency not found", false);
        }

        input.setTimestamp(inputDto.getTimestamp());
        input.setWarehouse(opWarehouse.get());
        input.setSupplier(opSupplier.get());
        input.setCurrency(optionalCurrency.get());
        inputRepository.save(input);
        return new ApiResponse("Successfully added", true);
    }

    //DELETE method to delete input by ID
    public ApiResponse deleteInput(Integer id){
        if (inputRepository.existsById(id)){
            inputRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        }
        return new ApiResponse("Input not found", false);
    }
}
