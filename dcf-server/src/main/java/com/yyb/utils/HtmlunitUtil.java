package com.yyb.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
public class HtmlunitUtil {
    public static InputStream downloadSheet(String url) {
        try {
            WebClient webclient = new WebClient(BrowserVersion.CHROME); // 设置浏览器版本
            webclient.getOptions().setTimeout(10 * 1000);
            webclient.getOptions().setRedirectEnabled(true);
            webclient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            // webclient.getOptions().setUseInsecureSSL(true);
            webclient.getOptions().setJavaScriptEnabled(true); // 启用javascript
            webclient.getOptions().setThrowExceptionOnScriptError(false); // 关闭js的异常抛出
            webclient.getOptions().setCssEnabled(false); // 不加载CSS文件
            webclient.getCookieManager().setCookiesEnabled(true);
            // webclient.setJavaScriptTimeout(600 * 1000);
            // webclient.waitForBackgroundJavaScript(60 * 1000);
            Page page = webclient.getPage(url);
            InputStream is = page.getWebResponse().getContentAsStream();
            return is;
        }catch (Exception e){
            log.info("下载三大报表失败",e);
        }
        return null;
    }
}
