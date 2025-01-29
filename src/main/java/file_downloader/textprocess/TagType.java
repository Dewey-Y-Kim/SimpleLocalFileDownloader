package main.java.file_downloader.textprocess;

public enum TagType {
    UL("ul"),
    OL("ol"),
    LI("li"),
    DIV("div"),
    STRONG("strong"),
    BUTTON("button");

    private final String tagName;

    TagType(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return this.tagName;
    }
}
