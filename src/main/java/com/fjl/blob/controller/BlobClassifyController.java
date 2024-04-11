package com.fjl.blob.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjl.blob.entity.BlobClassify;
import com.fjl.blob.result.Result;
import com.fjl.blob.service.BlobClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/classify"})
public class BlobClassifyController {
    @Autowired
    private BlobClassifyService blobClassifyService;

    @GetMapping(value = "/getById/{id}")
    public Result getById(@PathVariable(value = "id") Integer id) {
        BlobClassify blobClassify = blobClassifyService.getById(id);
        return Result.ok(blobClassify);
    }

    @GetMapping(value = "/list")
    public Result getById(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        IPage<BlobClassify> classifyIPage = new Page<>(page, size);
        IPage<BlobClassify> result = blobClassifyService.page(classifyIPage);
        return Result.ok(result);
    }

    @PostMapping(value = "/save")
    public Result saveClassify(@RequestBody BlobClassify blobClassify) {
        blobClassifyService.save(blobClassify);
        return Result.ok(null);
    }

    @PutMapping(value = "/update")
    public Result updateClassify(@RequestBody BlobClassify blobClassify) {
        blobClassifyService.updateById(blobClassify);
        return Result.ok(null);
    }

    @DeleteMapping(value = "/remove/{id}")
    public Result removeClassify(@PathVariable(value = "id") Integer id) {
        blobClassifyService.removeById(id);
        return Result.ok(null);
    }
}
