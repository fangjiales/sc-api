package com.fjl.blob.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjl.blob.entity.BlobRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author f_lesky
* @description 针对表【blob_role(角色)】的数据库操作Mapper
* @createDate 2024-03-31 16:11:43
* @Entity com.fjl.blob.entity.BlobRole
*/
@Repository
public interface BlobRoleMapper extends BaseMapper<BlobRole> {
    List<String> getRoleNameByUserId(Integer userId);
}




