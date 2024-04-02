package com.fjl.blob.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjl.blob.entity.BlobMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author f_lesky
* @description 针对表【blob_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-03-30 20:11:52
* @Entity com.fjl.blob.entity.BlobMenu
*/
@Repository
public interface BlobMenuMapper extends BaseMapper<BlobMenu> {
    List<String> getPermsByUserId(Integer userId);
}




