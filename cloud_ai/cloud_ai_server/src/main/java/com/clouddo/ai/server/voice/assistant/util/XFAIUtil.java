package com.clouddo.ai.server.voice.assistant.util;

import com.cloudd.commons.auth.util.Base64Util;
import com.clouddo.ai.server.config.AIConfig;
import com.clouddo.ai.server.voice.assistant.model.ResolveResult;
import com.clouddo.commons.common.util.JSONUtils;
import com.clouddo.commons.common.util.http.HttpUtil;
import com.clouddo.commons.common.util.security.MD5Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongming
 * @since 3.0
 * 2018/6/4下午1:26
 */
public class XFAIUtil {


    private static Logger logger = LoggerFactory.getLogger(XFAIUtil.class);


    /**
     * 语义解析结果进一步解析为对象方便处理
     * @param result
     * @return
     * @throws IOException
     */
    public static List<ResolveResult> resolveResult(String result) throws IOException {

        //解析后的结果
        List<ResolveResult> resolveResults = new ArrayList<ResolveResult>();
        if(StringUtils.isEmpty(result)) {
            return null;
        }
        //将json转为map
        Map<String, Object> resultMap = JSONUtils.jsonToMap(result);
        //获取结果码
        String code = (String) resultMap.get("code");
        if(!"0".equals(code)) {
            logger.warn("语义解析失败！！，错误码：{}，请在讯飞开发平台查看错误码原因：{},网址：{}", code, "http://aiui.xfyun.cn/docs/access_docs?target=webapi%2Fsummary.html");
            return null;
        }
        //获取数据
        List<Map<String, Object>> datas = (List<Map<String, Object>>) resultMap.get("data");
        Map<String, Object> data = datas.get(0);
        if(data != null) {
            //获取语义结果
            Map<String, Object> intent = (Map<String, Object>) data.get("intent");
            //获取应答码
            int rc = (int) intent.get("rc");
            if(rc != 0) {
                logger.warn("语义解析失败，应答码：{}", rc);
                return null;
            }
            //获取文字
            String text = (String) intent.get("text");
            //获取服务编码
            String serviceId = (String) intent.get("service");
            // 1、解析具体操作semantic（首选结果）
            List<Map<String, Object>> semantic = (List<Map<String, Object>>) intent.get("semantic");
            if(semantic != null) {
                //创建首选结果集
                List<ResolveResult> firstResults = resolveSemantic(semantic, serviceId, text);
                if(firstResults != null) {
                    resolveResults.addAll(firstResults);
                }

            }

            //2、解析更多结果moreResults
            List<Map<String, Object>> moreResults = (List<Map<String, Object>>) intent.get("moreResults");
            if(moreResults != null) {
                //循环遍历所有结果
                for(Map<String, Object> map : moreResults) {
                    //获取应答码，应答码为0时表明解析成功
                    int rcMore = (int) map.get("rc");
                    if(rcMore == 0) {
                        ResolveResult resolveResult = new ResolveResult();
                        //获取服务ID
                        String category = (String) map.get("category");
                        resolveResult.setServiceId(category);
                        //获取操作具体信息
                        List<ResolveResult>  resolveResultMores = resolveSemantic((List<Map<String, Object>>) map.get("semantic"), category, text);
                        if(resolveResultMores != null) {
                            resolveResults.addAll(resolveResultMores);
                        }
                    }
                }
            }

            return resolveResults;

        }

        return null;
    }


    /**
     * 解析具体操作
     * @param semantics
     * @param serviceId
     * @return
     */
    private static List<ResolveResult> resolveSemantic(List<Map<String, Object>> semantics, String serviceId, String text) {
        List<ResolveResult> resolveResults = new ArrayList<ResolveResult>();
        if(semantics != null) {
            for(Map<String, Object> semantic : semantics) {
                //创建首选结果
                ResolveResult resolveResult = new ResolveResult();
                resolveResult.setServiceId(serviceId);
                resolveResult.setText(text);
                resolveResult.setOperationId((String) semantic.get("intent"));
                //解析参数
                List<Map<String, Object>> slots = (List<Map<String, Object>>) semantic.get("slots");
                if(slots != null) {
                    for(Map<String, Object> map : slots) {
                        resolveResult.getParameters().put((String) map.get("name"), map.get("normValue"));
                    }
                }
                resolveResults.add(resolveResult);
            }
            return resolveResults;
        }

        return null;
    }


    /**
     * 解析语义
     * @param value 要解析的语义
     * @param xfSemantiConfig 讯飞语义解析配置
     * @return 解析后的数据
     * @throws IOException
     */
    public static String recognition(String value, AIConfig xfSemantiConfig) throws Exception {
        return baseRecognition(value.getBytes(), xfSemantiConfig, "text");
    }

    /**
     * 解析语义
     * @param audio 音频文件
     * @param xfSemantiConfig
     * @return
     * @throws Exception
     */
    public static String recognition(File audio, AIConfig xfSemantiConfig) throws Exception {
        InputStream in = new FileInputStream(audio);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        byte[] data = out.toByteArray();
        in.close();
        return baseRecognition(data, xfSemantiConfig, "audio");
    }

    /**
     * 解析语义
     * @param voice 声音信息
     * @param xfSemantiConfig
     * @return
     * @throws Exception
     */
    public static String recognition(MultipartFile voice, AIConfig xfSemantiConfig) throws Exception {
        String path = "/Users/ming/Documents/测试/1/" + System.currentTimeMillis() + ".wav";
        File file = new File(path);
        voice.transferTo(file);
        //TODO 测试代码
//        path = "/Users/ming/Documents/测试/1/bj_weather.wav";
        InputStream inputStream = new FileInputStream(path);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        byte[] data = out.toByteArray();

        return baseRecognition(data, xfSemantiConfig, "audio");
    }

    /**
     * 语义解析
     * @param inputStream 音频流
     * @param xfSemantiConfig
     * @return
     * @throws Exception
     */
    public static String recognition(InputStream inputStream, AIConfig xfSemantiConfig) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        byte[] data = out.toByteArray();
        inputStream.close();
        return baseRecognition(data, xfSemantiConfig, "audio");
    }

    /**
     * 语音合成
     * @param value 要合成的文字
     * @param aiConfig 语音合成服务信息
     *                 TODO 如果发生错误转换错误实体
     * @throws IOException
     */
    public static Object phoneticContract(String value, AIConfig aiConfig, OutputStream outputStream) throws IOException {
        //生成请求信息
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("auf", "audio/L16;rate=16000");
        parameters.put("aue", "raw");
        parameters.put("voice_name", "xiaoyan");

        //生成请求头信息
        Map<String, String> headers = createHeaders(aiConfig, parameters);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        //设置请求的数据
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("text", value);
        HttpEntity httpEntity = HttpUtil.httpPost(aiConfig.getServiceURL(), headers, data);
        if(httpEntity != null) {
            //获取返回类型
            String contentType = httpEntity.getContentType().getValue();
            if("text/plain".equals(contentType)) {
                //TODO
                logger.warn("语音解析失败，失败信息：{}", httpEntity);
                return null;

            } else if("audio/mpeg".equals(contentType)) {
                httpEntity.writeTo(outputStream);
                return null;
            }
        }
        return null;
    }

    /**
     * 语音听写
     * @param voice 语音信息
     * @param aiConfig 语音听写配置信息
     * @return
     * @throws IOException
     */
    public static String voiceDictation(MultipartFile voice, AIConfig aiConfig) throws IOException {
        //生成请求信息
        Map<String, Object> parameters = new HashMap<String, Object>();
        //引擎类型，可选值：sms16k（16k采样率普通话音频）、sms8k（8k采样率普通话音频）等，其他参见引擎类型说明
        parameters.put("engine_type", "sms16k");
        //音频编码，可选值：raw（未压缩的pcm或wav格式）、speex（speex格式）、speex-wb（宽频speex格式）
        parameters.put("aue", "raw");

        //生成请求头信息
        Map<String, String> headers = createHeaders(aiConfig, parameters);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

        //处理数据对数据进行base64转码
        String voiceBase64 = Base64Util.encoder(voice.getBytes());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("audio", voiceBase64);
        HttpEntity httpEntity = HttpUtil.httpPost(aiConfig.getServiceURL(), headers, data);

        return null;
    }

    //生成讯飞请求的请求头信息
    private static Map<String, String> createHeaders(AIConfig aiConfig, Map<String, Object> parameters) throws JsonProcessingException, UnsupportedEncodingException {
        Map<String, String> headers = new HashMap<String, String>();
        //时间戳
        String currentTime = System.currentTimeMillis() + "";
        currentTime = currentTime.substring(0, currentTime.length() - 3);
        headers.put("X-CurTime", currentTime);

        //设置appid
        headers.put("X-Appid", aiConfig.getAppId());

        //设置参数
        String parameterStr = JSONUtils.mapToJson(parameters);
        //进行base64转码
        String parameter = Base64Util.encoder(parameterStr);
        headers.put("X-Param", parameter);

        //设置令牌
        //生成令牌
        String checkSumStr = aiConfig.getApiKey() + currentTime + parameter;
        //进行MD5加密
        String checkSum = MD5Utils.md5(checkSumStr);
        headers.put("X-CheckSum", checkSum);
        return headers;
    }

    /**
     * 基础的语义解析函数
     * @param data
     * @param aiConfig
     * @param dataType
     * @return
     * @throws Exception
     */
    private static String baseRecognition(byte[] data, AIConfig aiConfig, String dataType) throws Exception {
        URL url = new URL(aiConfig.getServiceURL());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        String currentTime = System.currentTimeMillis() + "";
        currentTime = currentTime.substring(0, currentTime.length() - 3);
        connection.setRequestProperty("X-CurTime", currentTime);
        connection.setRequestProperty("X-Appid", aiConfig.getAppId());
        //设置参数
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("scene", "main");    //情景模式 2049a1b2fdedae553bd03ce6f4820ac4
        parameters.put("data_type", dataType);
        parameters.put("auth_id", "f33f4de1ef8e4ef6599293db5cbf6ff3");
        String parameterStr = JSONUtils.mapToJson(parameters);
        //进行base64转码
        String parameter = Base64Util.encoder(parameterStr);
        connection.setRequestProperty("X-Param", parameter);
        //生成令牌
        String checkSumStr = aiConfig.getApiKey() + currentTime + parameter;
        //进行MD5加密
        String checkSum = MD5Utils.md5(checkSumStr);
        connection.setRequestProperty("X-CheckSum", checkSum);

        connection.setDoOutput(true);
        connection.setDoInput(true);

        BufferedReader in = null;
        OutputStream out = null;

        StringBuffer result = new StringBuffer();

        out = connection.getOutputStream();
        out.write(data);
        out.flush();
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        in.close();
        return result.toString();
    }
}
