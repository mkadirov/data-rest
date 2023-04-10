package pdp.datarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.datarest.entity.Attachment;
import pdp.datarest.entity.Category;
import pdp.datarest.entity.Measurement;
import pdp.datarest.entity.Product;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.model.ProductDto;
import pdp.datarest.model.UniqueNumberGenerator;
import pdp.datarest.repository.AttachmentRepository;
import pdp.datarest.repository.CategoryRepository;
import pdp.datarest.repository.MeasurementRepository;
import pdp.datarest.repository.ProductRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    ProductRepository productRepository;



    //POST method to add new product
    public ApiResponse addProduct(ProductDto productDto){

        // check if name of product exists in this category
        if(productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId())){
            return new ApiResponse("Product exists", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new ApiResponse("Category not found", false);
        }
        // check if measurement exists
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()){
            return new ApiResponse("Measurement not found", false);
        }
        // Check if file exists
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty()){
            return new ApiResponse("File not found", false);
        }
        Product product = new Product();
        product.setName(product.getName());
        product.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
        product.setAttachment(optionalAttachment.get());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new ApiResponse("Successfully added", true);
    }

    //GET method to get List of Products

    public ApiResponse getProductList(){
        List<Product> allProducts = productRepository.findAll();
        return new ApiResponse("Successfully retrieved", true, allProducts);
    }

    //GET method to get product by ID

    public ApiResponse getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            return new ApiResponse("Successfully retrieved", true, product);
        }else {
            return new ApiResponse("product not found", false);
        }
    }

    // PUT method update Product by ID
    public ApiResponse updateProduct(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (!product.getName().equals(productDto.getName())) {
                // check if name of product exists in this category
                if (productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId())) {
                    return new ApiResponse("Product exists", false);
                }
                product.setName(product.getName());
            }
            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (optionalCategory.isEmpty()) {
                return new ApiResponse("Category not found", false);
            }
            // check if measurement exists
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
            if (optionalMeasurement.isEmpty()) {
                return new ApiResponse("Measurement not found", false);
            }
            // Check if file exists
            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
            if (optionalAttachment.isEmpty()) {
                return new ApiResponse("File not found", false);
            }

            product.setCode(String.valueOf(UniqueNumberGenerator.getNextUniqueNumber()));
            product.setAttachment(optionalAttachment.get());
            product.setCategory(optionalCategory.get());
            product.setMeasurement(optionalMeasurement.get());
            productRepository.save(product);
            return new ApiResponse("Successfully added", true);
        }
        return new ApiResponse("product not found", false);
    }

    //DELETE method to delete product by ID
    public ApiResponse deleteProduct(Integer id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return  new ApiResponse("Successfully deleted", true);
        }else {
            return new ApiResponse("Product not found", false);
        }
    }

}
