package com.fjl.blob.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fjl.blob.entity.BlobUser;
import com.fjl.blob.result.Result;

/**
* @author f_lesky
* @description 针对表【user】的数据库操作Service
* @createDate 2024-03-28 08:27:21
*/
public interface BlobUserService extends IService<BlobUser> {
    Result login(BlobUser blobUser);

    Result logout();

    Result getUserInfo();

}
