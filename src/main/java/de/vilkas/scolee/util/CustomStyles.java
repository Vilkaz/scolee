package de.vilkas.scolee.util;

public enum CustomStyles {
    ICON_ONLY_BTN("icon_only_btn");


    private String styleName;

    CustomStyles(final String styleName) {
        this.styleName = styleName;
    }

    public String getStyleName() {
        return styleName;
    }
}

