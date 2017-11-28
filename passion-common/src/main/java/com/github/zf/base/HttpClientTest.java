package com.github.zf.base;

import com.github.zf.base.conf.HttpConfig;
import com.github.zf.base.exception.HttpProcessException;
import com.github.zf.base.httpclient.HttpAsyncClientUtil;
import com.github.zf.base.utils.ConvertMap2Json;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author zengfen
 * @date 2017/11/28
 */
public class HttpClientTest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

    public static void main(String[] args) {
        try {
            String url = "http://localhost:9091/porta/cps/v1/fanLiAuthUser";
            Map<String, Object> paraMap = Maps.newHashMap();
            paraMap.put("transNo", "HZ20150409000004");

            HttpAsyncClientUtil.post(HttpConfig.custom().url(url).json(ConvertMap2Json.buildJsonBody(paraMap, 0, false)).handler(new AsyncHandler("123")));
        } catch (HttpProcessException e) {
            logger.error("系统异常",e);
        }
    }

    static class AsyncHandler implements HttpAsyncClientUtil.IHandler {

        private CountDownLatch countDownLatch;

        private String id;

        public AsyncHandler(String id) {
            this.id = id;
        }

        public AsyncHandler(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public AsyncHandler setCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
            return this;
        }

        public void countDown() {
            if (this.countDownLatch == null) {
                return;
            }
            countDownLatch.countDown();
        }

        @Override
        public Object failed(Exception e) {
            logger.error("当前线程池：{},状态:{}", Thread.currentThread().getName(), "--失败了--" + e.getClass().getName() + "--" + e.getMessage());
            countDown();
            return null;
        }

        @Override
        public Object completed(String respBody) {
            logger.info("当前线程池：{},内容:{},获取内容长度:{}", Thread.currentThread().getName(), respBody, respBody.length());
            countDown();
            return null;
        }

        @Override
        public Object cancelled() {
            logger.info("当前线程池：{},状态:{}", Thread.currentThread().getName(), "取消了");
            countDown();
            return null;
        }

        @Override
        public Object down(OutputStream out) {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                logger.error("系统异常",e);
            }
            logger.info(Thread.currentThread().getName() + "--下载完成");
            return null;
        }
    }
}
