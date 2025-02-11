package main.java.file_downloader.textprocess;

public enum ImageType {
    JPG("jpg"),
    PNG("png"),
    JPEG("jpeg"),
    GIF("gif"),

    ;

    private final String imgType;

    ImageType(String tagName) {
        this.imgType = tagName;
    }

    public String getImgType() {
        return this.imgType;
    }
}
