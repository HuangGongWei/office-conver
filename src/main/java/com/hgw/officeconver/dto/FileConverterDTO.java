package com.hgw.officeconver.dto;

import lombok.Data;

/**
 * Description: 转换请求
 *
 * @author LinHuiBa-YanAn
 * @date 2023/10/19 15:58
 */
@Data
public class FileConverterDTO {

    /**
     * 输入源
     */
    private String inputSource;

    /**
     * 转换类型
     * @see com.hgw.officeconver.enums.FileConvertTypeEnum
     */
    private Integer convertTypeCode;

}
