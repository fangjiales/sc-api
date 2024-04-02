package com.fjl.blob.controller;

import com.fjl.blob.properties.MinioProperties;
import com.fjl.blob.result.Result;
import com.fjl.blob.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/file"})
public class FileController {
    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private MinioProperties minioProperties;

    @PostMapping(value = {"/upload"})
    public Result upload(@RequestParam(value = "file") MultipartFile multipartFile) {
        Boolean exists = minioUtil.bucketExists();
        if (!exists) {
            minioUtil.makeBucket();
        }
        String fileName = minioUtil.uploadFile(multipartFile);
        return Result.ok(minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + fileName);
    }
}
