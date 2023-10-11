package com.hgw.officeconver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Description: office办公文件 文件类型
 *
 * @author YanAn
 * @date 2023/9/20 17:34
 */
@Getter
@AllArgsConstructor
public enum OfficeFileTypeEnum {
    PPT(1, "PPT"),
    PPTX(2, "PPTX"),
    PDF(3, "PDF");

    private final Integer code;

    private final String vale;

    public OfficeFileTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        return Arrays.stream(values()).filter(item -> item.getCode().equals(code)).findFirst().orElse(null);
    }

}
