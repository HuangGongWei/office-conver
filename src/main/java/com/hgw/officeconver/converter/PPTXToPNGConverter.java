package com.hgw.officeconver.converter;

import com.hgw.officeconver.thread.BizThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Description: pptx转换为图片转换器
 *
 * @author YanAn
 * @date 2023/9/21 13:35
 */
@Slf4j
public class PPTXToPNGConverter extends AbstractPPTToPNGConverter {

    public PPTXToPNGConverter(String inputPath) {
        super(inputPath);
    }

    @Override
    public List<String> convertToPNG() {
        InputStream is = null;
        XMLSlideShow ppt = null;
        try {
            is = getInputStream(inputSource);
            ppt = new XMLSlideShow(is);
            Dimension pgSize = ppt.getPageSize();
            List<CompletableFuture<String>> completableFutures = new ArrayList<>();
            for (XSLFSlide slide : ppt.getSlides()) {
                completableFutures.add(BizThreadPool.supplyAsync(() -> toPNG(pgSize.width, pgSize.height, slide)));
            }
            CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
            completableFuture.get();
            for (CompletableFuture<String> future : completableFutures) {
                outPathUrlList.add(future.get());
            }
        } catch (Exception e) {
            log.error("pptx转换图片失败,{}", e);
            throw new RuntimeException("pptx转换图片失败" + e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ppt != null) {
                    ppt.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outPathUrlList;
    }
}
