package main.java.file_downloader.domain;

public class Img {
    private String chaptor;
    private String address;
    public Img(String chaptor, String address){
        this.chaptor = chaptor;
        this.address = address;
    }
    public String getChaptor() {
        return chaptor;
    }

    public void setChaptor(String chaptor) {
        this.chaptor = chaptor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
