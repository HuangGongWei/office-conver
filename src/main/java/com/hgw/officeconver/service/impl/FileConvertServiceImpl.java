package com.hgw.officeconver.service.impl;

import com.hgw.officeconver.converter.PDFToPNGConverter;
import com.hgw.officeconver.converter.PPTToPNGConverter;
import com.hgw.officeconver.converter.PPTXToPNGConverter;
import com.hgw.officeconver.dto.FileDTO;
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
    public List<String> fileConvert(FileDTO dto) {
        List<String> result = new ArrayList<>();
        if (Objects.isNull(dto) || Objects.isNull(dto.getType()) || Objects.isNull(dto.getUrl())) {
            return result;
        }
        switch (dto.getType().toUpperCase()) {
            case "PDF":
                PDFToPNGConverter pdfConverter = new PDFToPNGConverter(dto.getUrl());
                result = pdfConverter.convertToPNG();
                break;
            case "PPT":
                PPTToPNGConverter pptConverter = new PPTToPNGConverter(dto.getUrl());
                result = pptConverter.convertToPNG();
                break;
            case "PPTX":
                PPTXToPNGConverter pptxConverter = new PPTXToPNGConverter(dto.getUrl());
                result = pptxConverter.convertToPNG();
                break;
        }
        return result;
    }

}
