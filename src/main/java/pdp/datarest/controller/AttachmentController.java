package pdp.datarest.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.datarest.model.ApiResponse;
import pdp.datarest.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public ApiResponse upload(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.uploadFile(request);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFileById(id, response);
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateFile(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {
        return attachmentService.updateFile(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteFileById(@PathVariable Integer id){
        return attachmentService.deleteFileById(id);
    }
}
