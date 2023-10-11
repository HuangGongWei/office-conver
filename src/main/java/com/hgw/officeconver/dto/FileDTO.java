package com.hgw.officeconver.dto;

import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * Description: 文件DTO
 *
 * @author LinHuiBa-YanAn
 * @date 2023/10/11 17:51
 */
@Data
public class FileDTO {
    /**
     * 文件类型
     * @see com.hgw.officeconver.enums.OfficeFileTypeEnum
     */
    @NotNull
    private String type;

    /**
     * 文件url
     */
    @NotNull
    private String url;
}
