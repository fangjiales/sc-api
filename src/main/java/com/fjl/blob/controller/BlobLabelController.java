package com.fjl.blob.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjl.blob.entity.BlobLabel;
import com.fjl.blob.result.Result;
import com.fjl.blob.service.BlobLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/label"})
public class BlobLabelController {
    @Autowired
    private BlobLabelService blobLabelService;

    @GetMapping(value = {"/list"})
    public Result labelList(@RequestParam(value = "page") Integer page,
                            @RequestParam(value = "size") Integer size) {
        IPage<BlobLabel> blobLabelIPage = new Page<>(page, size);
        IPage<BlobLabel> result = blobLabelService.page(blobLabelIPage);
        return Result.ok(result);
    }

    @GetMapping(value = {"/getById/{id}"})
    public Result getById(@PathVariable(value = "id") Integer id) {
        BlobLabel blobLabel = blobLabelService.getById(id);
        return Result.ok(blobLabel);
    }

    @PostMapping(value = {"/save"})
    public Result saveLabel(@RequestBody BlobLabel blobLabel) {
        blobLabelService.save(blobLabel);
        return Result.ok(null);
    }

    @PutMapping(value = {"/update"})
    public Result updateLabel(@RequestBody BlobLabel blobLabel) {
        blobLabelService.updateById(blobLabel);
        return Result.ok(null);
    }

    @DeleteMapping(value = {"/remove/{id}"})
    public Result removeLabel(@PathVariable(value = "id") Integer id) {
        blobLabelService.removeById(id);
        return Result.ok(null);
    }
}
