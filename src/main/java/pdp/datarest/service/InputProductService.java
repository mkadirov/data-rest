package pdp.datarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.datarest.entity.Input;
import pdp.datarest.entity.InputProduct;
import pdp.datarest.entity.Product;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.InputProductDto;
import pdp.datarest.repository.InputProductRepository;
import pdp.datarest.repository.InputRepository;
import pdp.datarest.repository.ProductRepository;

import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    //POST method to add new Input Product
    public ApiResponse addInputProduct(InputProductDto inputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if(optionalProduct.isEmpty()){
            return new ApiResponse("Product not found", false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()){
            return new ApiResponse("Input not found", false);
        }
        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new ApiResponse("Successfully added", true);
    }

    //GET method to get List of Input Products
    public ApiResponse getInputProductList(){
        return new ApiResponse("Successfully retrieved", true, inputProductRepository.findAll());
    }

    //GET method to get Input Product by ID
    public ApiResponse getInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.map(inputProduct ->
                new ApiResponse("Successfully retrieved", true, inputProduct)).orElseGet(() ->
                new ApiResponse("InputProduct not found", false));
    }

    //PUT method to upload Input Product
    public ApiResponse uploadInputProduct(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if(optionalInputProduct.isPresent()) {
            InputProduct inputProduct = optionalInputProduct.get();
            Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
            if (optionalProduct.isEmpty()) {
                return new ApiResponse("Product not found", false);
            }
            Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
            if (optionalInput.isEmpty()) {
                return new ApiResponse("Input not found", false);
            }

            inputProduct.setProduct(optionalProduct.get());
            inputProduct.setInput(optionalInput.get());
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(inputProductDto.getExpireDate());
            inputProductRepository.save(inputProduct);
            return new ApiResponse("Successfully uploaded", true);
        }
        return new ApiResponse("InputProduct not found", false);
    }

    //DELETE method to delete Input Product by ID
    public ApiResponse deleteInputProduct(Integer id){
        if(inputProductRepository.existsById(id)){
            inputProductRepository.deleteById(id);
            return new ApiResponse("Successfully deleted", true);
        }
        return new ApiResponse("InputProduct not found", false);
    }
}