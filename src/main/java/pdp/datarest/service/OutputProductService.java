package pdp.datarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.datarest.entity.Output;
import pdp.datarest.entity.OutputProduct;
import pdp.datarest.entity.Product;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.OutputProductDto;
import pdp.datarest.repository.OutputProductRepository;
import pdp.datarest.repository.OutputRepository;
import pdp.datarest.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    //POST method to add new Output Product
    public ApiResponse addOutputProduct(OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if(optionalProduct.isEmpty()){
            return new  ApiResponse("Product not found", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()){
            return new  ApiResponse("Output not found", false);
        }
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setAmount(outputProduct.getAmount());
        outputProduct.setPrice(outputProduct.getPrice());
        outputProductRepository.save(outputProduct);
        return new  ApiResponse("Successfully added", true);
    }

    //GET method to get List of Output Products
    public  ApiResponse getOutputProductList(){
        return new  ApiResponse("Successfully retrieved", true, outputProductRepository.findAll());
    }

    //GET method to get Output Product by ID
    public  ApiResponse getOutputProductById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.map(outputProduct ->
                new  ApiResponse("Successfully retrieved", true, outputProduct)).orElseGet(() ->
                new  ApiResponse("OutputProduct not found", false));
    }

    //PUT method to upload output Product
    public  ApiResponse uploadOutputProduct(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if(optionalOutputProduct.isPresent()) {
            OutputProduct outputProduct = optionalOutputProduct.get();
            Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
            if(optionalProduct.isEmpty()){
                return new ApiResponse("Product not found", false);
            }
            Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
            if (optionalOutput.isEmpty()){
                return new  ApiResponse("Output not found", false);
            }
            outputProduct.setProduct(optionalProduct.get());
            outputProduct.setOutput(optionalOutput.get());
            outputProduct.setAmount(outputProduct.getAmount());
            outputProduct.setPrice(outputProduct.getPrice());
            outputProductRepository.save(outputProduct);
            return new  ApiResponse("Successfully added", true);
        }
        return new  ApiResponse("OutputProduct not found", false);
    }

    //DELETE method to delete Output Product by ID
    public  ApiResponse deleteOutputProduct(Integer id){
        if(outputProductRepository.existsById(id)){
            outputProductRepository.deleteById(id);
            return new  ApiResponse("Successfully deleted", true);
        }
        return new  ApiResponse("OutputProduct not found", false);
    }
}
