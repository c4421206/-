package com.bootdo.common.media.audio;

import com.bootdo.common.media.util.FileWriteUtil;
import com.bootdo.common.media.util.ProcessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongming
 * @since 3.0
 * 2018/6/8下午2:07
 */
public class AudioWrapper {

    private static Logger log = LoggerFactory.getLogger(AudioWrapper.class);

    public static Builder<String> of(String str) {
        Builder<String> builder = new Builder<>();
        return builder.setSource(str);
    }


    public static Builder<URI> of(URI uri) {
        Builder<URI> builder = new Builder<>();
        return builder.setSource(uri);
    }


    public static Builder<InputStream> of(InputStream inputStream) {
        Builder<InputStream> builder = new Builder<>();
        return builder.setSource(inputStream);
    }


    private static void checkNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new IllegalStateException(msg);
        }
    }

    private static boolean run(String cmd) {
        try {
            return ProcessUtil.instance().process(cmd);
        } catch (Exception e) {
            log.error("operate audio error! cmd: {}, e: {}", cmd, e);
            return false;
        }
    }


    public static class Builder<T> {
        /**
         * 输入源
         */
        private T source;


        /**
         * 源音频格式
         */
        private String inputType;


        /**
         * 输出音频格式
         */
        private String outputType;


        /**
         * 命令行参数
         */
        private Map<String, Object> options = new HashMap<>();


        /**
         * 临时文件信息
         */
        private FileWriteUtil.FileInfo tempFileInfo;


        private String tempOutputFile;


        public Builder<T> setSource(T source) {
            this.source = source;
            return this;
        }

        public Builder<T> setInputType(String inputType) {
            this.inputType = inputType;
            return this;
        }

        public Builder<T> setOutputType(String outputType) {
            this.outputType = outputType;
            return this;
        }

        public Builder<T> addOption(String conf, Object val) {
            this.options.put(conf, val);
            return this;
        }


        private String builder() throws Exception {

            checkNotNull(source, "src file should not be null!");

            checkNotNull(outputType, "output Audio type should not be null!");


            tempFileInfo = FileWriteUtil.saveFile(source, inputType);

            tempOutputFile = tempFileInfo.getPath() + "/" + tempFileInfo.getFilename() + "_out." + outputType;

            return new AudioOptions().addOptions(this.options).setSrc(tempFileInfo.getAbsFile())
//            return new AudioOptions().setSrc(tempFileInfo.getAbsFile())
                    .setDest(tempOutputFile)
                    .addOption("y", "") // 覆盖写
                    .addOption("write_xing", 0) // 解决mac/ios 显示音频时间不对的问题
                    .addOption("loglevel", "quiet") // 不输出日志
                    .build();
        }


        public InputStream asStream() throws Exception {
            String output = asFile();

            if (output == null) {
                return null;
            }


            return new FileInputStream(new File(output));
        }


        public String asFile() throws Exception {
            String cmd = builder();
            return !run(cmd) ? null : tempOutputFile;
        }
    }

    public static void main(String[] args) {
        String[] arys = new String[]{
                "/Users/ming/Documents/测试/1/1528263906629.wav"
        };

        for (String src : arys) {
            try {
                String output = AudioWrapper.of(src)
                        .setInputType("wav")
                        .setOutputType("pcm")
                        .addOption("acodec", "pcm_s16le")
                        .addOption("f", "s16le")
                        .addOption("ac", "1")
                        .addOption("ar", "16000")
                        .asFile();
                System.out.println(output);
                log.info("the parse audio abs path: {}", output);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("parse audio error! e: {}", e);
            }
        }
    }
}
