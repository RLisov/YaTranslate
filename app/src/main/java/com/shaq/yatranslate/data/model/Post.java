package com.shaq.yatranslate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("lang")
        @Expose
        private String lang;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "key='" + key + '\'' +
                    ", text='" + text + '\'' +
                    ", lang=" + lang +
                    '}';
        }

}
