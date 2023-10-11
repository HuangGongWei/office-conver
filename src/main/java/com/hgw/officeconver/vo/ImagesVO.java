package com.hgw.officeconver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description: 图片集合
 *
 * @author LinHuiBa-YanAn
 * @date 2023/10/11 17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagesVO {
    /**
     * 图片链接集合
     */
    private List<String> images;
}
