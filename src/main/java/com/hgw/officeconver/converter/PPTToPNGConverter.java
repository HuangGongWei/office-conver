package com.hgw.officeconver.converter;

import com.hgw.officeconver.thread.BizThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Description: ppt转换为图片转换器
 *
 * @author YanAn
 * @date 2023/9/21 13:35
 */
@Slf4j
public class PPTToPNGConverter extends AbstractPPTToPNGConverter{

    public PPTToPNGConverter(String inputPath) {
        super(inputPath);
    }

    @Override
    public List<String> convertToPNG() {
        InputStream is = null;
        HSLFSlideShow ppt = null;
        try {
            is = getInputStream(inputSource);
            ppt = new HSLFSlideShow(is);
            Dimension pgSize = ppt.getPageSize();
            List<CompletableFuture<String>> completableFutures = new ArrayList<>();
            for (HSLFSlide slide : ppt.getSlides()) {
                completableFutures.add(BizThreadPool.supplyAsync(() -> toPNG(pgSize.width, pgSize.height, slide)));
            }
            CompletableFuture<Void> completableFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
            completableFuture.get();
            for (CompletableFuture<String> future : completableFutures) {
                outPathUrlList.add(future.get());
            }
        } catch (Exception e) {
            log.error("ppt转换图片失败,{}", e);
            throw new RuntimeException("ppt转换图片失败" + e.getMessage());
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
