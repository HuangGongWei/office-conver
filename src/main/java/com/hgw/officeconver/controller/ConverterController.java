package com.hgw.officeconver.controller;

import com.hgw.officeconver.dto.FileConverterDTO;
import com.hgw.officeconver.service.FileConvertService;
import com.hgw.officeconver.vo.ImagesVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: 转换器Controller
 *
 * @author LinHuiBa-YanAn
 * @date 2023/10/11 17:49
 */
@Slf4j
@RestController
@RequestMapping("/api/file")
public class ConverterController {

    @Resource
    private FileConvertService fileConvertService;

    /**
     * 转换
     * @param dto
     * @return
     */
    @PostMapping(value = "/convert")
    public ImagesVO fileConvert(@RequestBody @Validated FileConverterDTO dto) {
        return new ImagesVO(fileConvertService.fileConvert(dto));
    }
}
