package com.teleBot.model;

import java.util.List;

/**
 * @author Serhii_Udaltsov on 6/7/2021
 */
public class MessageHolder {
    private List<Button> buttons;
    private String message;
    private KeyBoardType keyBoardType;
    private ButtonsType buttonsType;
    private boolean withCommonButtons;


    public MessageHolder(List<Button> buttons, String message, KeyBoardType keyBoardType,
                         ButtonsType buttonsType, boolean withCommonButtons) {
        this.buttons = buttons;
        this.message = message;
        this.keyBoardType = keyBoardType;
        this.buttonsType = buttonsType;
        this.withCommonButtons = withCommonButtons;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ButtonsType getButtonsType() {
        return buttonsType;
    }

    public void setButtonsType(ButtonsType buttonsType) {
        this.buttonsType = buttonsType;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public KeyBoardType getKeyBoardType() {
        return keyBoardType;
    }

    public void setKeyBoardType(KeyBoardType keyBoardType) {
        this.keyBoardType = keyBoardType;
    }

    public boolean isWithCommonButtons() {
        return withCommonButtons;
    }

    public void setWithCommonButtons(boolean withCommonButtons) {
        this.withCommonButtons = withCommonButtons;
    }

    public static class Builder {
        private List<Button> buttons;
        private String message;
        private KeyBoardType keyBoardType;
        private ButtonsType buttonsType;
        private boolean withCommonButtons;

        public Builder withButtons(List<Button> buttons) {
            this.buttons = buttons;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withKeyboardType(KeyBoardType keyboardType) {
            this.keyBoardType = keyboardType;
            return this;
        }

        public Builder withButtonsType(ButtonsType buttonsType) {
            this.buttonsType = buttonsType;
            return this;
        }

        public Builder withCommonButtons(boolean withCommonButtons) {
            this.withCommonButtons = withCommonButtons;
            return this;
        }

        public MessageHolder build() {
            return new MessageHolder(buttons, message, keyBoardType, buttonsType, withCommonButtons);
        }
    }
}
