package com.teleBot.model;

/**
 * @author Serhii_Udaltsov on 6/7/2021
 */
public class Button {
    private String value;
    private String callback;
    private boolean checkable;

    public Button(String value, String callback, boolean checkable) {
        this.value = value;
        this.callback = callback;
        this.checkable = checkable;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public boolean isCheckable() {
        return checkable;
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }

    public static class Builder {
        private String value;
        private String callback;
        private boolean checkable;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withCallback(String callback) {
            this.callback = callback;
            return this;
        }

        public Builder withCheckable(boolean checkable) {
            this.checkable = checkable;
            return this;
        }

        public Button build() {
            return new Button(value, callback, checkable);
        }
    }
}
