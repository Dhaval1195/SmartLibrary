package com.example.hp_pc.smartlibrary.Model;

public class BookModel {


    String book_name,author,book_id,book_cat_id,emo_id,publication,path,edition;


    public String getEmo_id() {
        return emo_id;
    }

    public void setEmo_id(String emo_id) {
        this.emo_id = emo_id;
    }


    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_cat_id() {
        return book_cat_id;
    }

    public void setBook_cat_id(String book_cat_id) {
        this.book_cat_id = book_cat_id;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
