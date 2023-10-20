package com.hgw.officeconver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Description: 文件转换事件类型
 *
 * @author LinHuiBa-YanAn
 * @date 2023/9/20 17:34
 */
@Getter
@AllArgsConstructor
public enum FileConvertTypeEnum {
    /**
     * ppt转换png图片
     */
    PPT_TO_PNG(1, "PPT_TO_PNG"),
    /**
     * pptx转换png图片
     */
    PPTX_TO_PNG(2, "PPTX_TO_PNG"),
    /**
     * pdf转换png图片
     */
    PDF_TO_PNG(3, "PDF_TO_PNG"),
    /**
     * html转换png图片
     */
    HTML_TO_PNG(4, "HTML_TO_PNG");

    private final Integer code;

    private final String name;

    public static FileConvertTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        return Arrays.stream(values()).filter(item -> item.getCode().equals(code)).findFirst().orElse(null);
    }

}
