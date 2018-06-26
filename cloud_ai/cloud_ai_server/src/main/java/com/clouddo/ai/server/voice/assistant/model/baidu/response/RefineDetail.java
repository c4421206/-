package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度UNIT实体类
 * 『泛澄清』时，即clarify/guide/faqguide时有效
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午3:17
 */
public class RefineDetail implements Serializable {


    private static final long serialVersionUID = 7785854848279555584L;

    /**
     * 具体有以下几种：select/ask/selectandask
     */
    @SerializedName("interact")
    private String interact;


    /**
     * 『泛澄清』选项列表。这里的『选项』是广义的选项，在意图、词槽不置信or缺失澄清，以及faqguide时，也会有一个长度为1的option_list，存放相应细节信息。
     */
    @SerializedName("option_list")
    private List<Option> optionList;

    /**
     * clarify时有值，表明起因
     */
    @SerializedName("clarify_reason")
    private String clarifyReason;


    public String getInteract() {
        return interact;
    }

    public void setInteract(String interact) {
        this.interact = interact;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public String getClarifyReason() {
        return clarifyReason;
    }

    public void setClarifyReason(String clarifyReason) {
        this.clarifyReason = clarifyReason;
    }

    class Option implements Serializable {

        private static final long serialVersionUID = -5558728857186260793L;
        /**
         * 选项文字
         */
        @SerializedName("option")
        private String option;

        @SerializedName("info")
        Map<String, Object> info = new HashMap<String, Object>();


        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public Map<String, Object> getInfo() {
            return info;
        }

        public void setInfo(Map<String, Object> info) {
            this.info = info;
        }
    }
}
