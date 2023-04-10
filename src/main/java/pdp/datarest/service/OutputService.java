package pdp.datarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.datarest.entity.Client;
import pdp.datarest.entity.Currency;
import pdp.datarest.entity.Output;
import pdp.datarest.entity.Warehouse;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.OutputDto;
import pdp.datarest.model.UniqueNumberGenerator;
import pdp.datarest.repository.ClientRepository;
import pdp.datarest.repository.CurrencyRepository;
import pdp.datarest.repository.OutputRepository;
import pdp.datarest.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    //POST method to add new Output
    public ApiResponse addOutput(OutputDto outputDto){
        if(outputRepository.existsByFactureNumber(outputDto.getFactureNumber())){
            return new ApiResponse("FactureNumber exists", false);
        }
        Optional<Warehouse> opWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(opWarehouse.isEmpty()){
            return new ApiResponse("Warehouse not found", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()){
            return new ApiResponse("Client not found", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new ApiResponse("Currency not found", false);
        }

        Output output = new Output();
        output.setTimestamp(output.getTimestamp());
        output.setWarehouse(opWarehouse.get());
        output.setClientId(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(output.getFactureNumber());
        output.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
        outputRepository.save(output);
        return new ApiResponse("Successfully added", true);
    }

    //GET method to get OutputList
    public ApiResponse getOutList(){
        return new ApiResponse("Successfully retrieved", true, outputRepository.findAll());
    }

    //GET method to get output by ID
    public ApiResponse getOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.map(output ->
                new ApiResponse("Successfully retrieved", true, output)).orElseGet(() ->
                new ApiResponse("Output not found", false));
    }

    //PUT method to update Output by ID
    public ApiResponse uploadOutput(Integer id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()){
            return new ApiResponse("Input not found", false);
        }
        Output output = optionalOutput.get();
        if(!output.getFactureNumber().equals(outputDto.getFactureNumber())) {
            if (outputRepository.existsByFactureNumber(outputDto.getFactureNumber())) {
                return new ApiResponse("FactureNumber exists", false);
            }
            output.setFactureNumber(outputDto.getFactureNumber());
        }
        Optional<Warehouse> opWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if(opWarehouse.isEmpty()){
            return new ApiResponse("Warehouse not found", false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()){
            return new ApiResponse("Client not found", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()){
            return new ApiResponse("Currency not found", false);
        }

        output.setTimestamp(output.getTimestamp());
        output.setWarehouse(opWarehouse.get());
        output.setClientId(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        outputRepository.save(output);
        return new ApiResponse("Successfully added", true);
    }

    //DELETE method to delete output by ID
    public ApiResponse deleteOutput(Integer id){
        if (outputRepository.existsById(id)){
            outputRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        }
        return new ApiResponse("Output not found", false);
    }
}