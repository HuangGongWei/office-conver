package com.hgw.officeconver.service;

import com.hgw.officeconver.dto.FileDTO;

import java.util.List;

/**
 * Description: 文件转换
 *
 * @author LinHuiBa-YanAn
 * @date 2023/10/11 17:55
 */
public interface FileConvertService {

    /**
     * 文件转换
     * @param dto 文件
     * @return 图片集合
     */
    List<String> fileConvert(FileDTO dto);
}
