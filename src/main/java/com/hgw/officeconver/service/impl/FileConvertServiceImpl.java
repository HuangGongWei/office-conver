package com.hgw.officeconver.service.impl;

import com.hgw.officeconver.converter.*;
import com.hgw.officeconver.dto.FileConverterDTO;
import com.hgw.officeconver.enums.FileConvertTypeEnum;
import com.hgw.officeconver.service.FileConvertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description: 文件转换
 *
 * @author LinHuiBa-YanAn
 * @date 2023/10/11 17:58
 */
@Slf4j
@Service
public class FileConvertServiceImpl implements FileConvertService {

    @Override
    public List<String> fileConvert(FileConverterDTO dto) {
        FileConvertTypeEnum convertTypeEnum = FileConvertTypeEnum.getByCode(dto.getConvertTypeCode());
        BaseConverter converter = null;
        if (FileConvertTypeEnum.PPT_TO_PNG.equals(convertTypeEnum)) {
            converter = new PPTToPNGConverter(dto.getInputSource());
        } else if (FileConvertTypeEnum.PPTX_TO_PNG.equals(convertTypeEnum)) {
            converter = new PPTXToPNGConverter(dto.getInputSource());
        } else if (FileConvertTypeEnum.PDF_TO_PNG.equals(convertTypeEnum)) {
            converter = new PDFToPNGConverter(dto.getInputSource());
        } else if (FileConvertTypeEnum.HTML_TO_PNG.equals(convertTypeEnum)) {
            converter = new HtmlToPNGConverter(dto.getInputSource());
        }
        List<String> urls = new ArrayList<>();
        if (Objects.nonNull(converter)) {
            urls = converter.convertToPNG();
        }
        return urls;
    }

}
