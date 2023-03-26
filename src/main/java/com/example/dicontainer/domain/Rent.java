package com.example.dicontainer.domain;

public class Rent {
    private Long id;
    private Long bookId;
    private Long memberId;

    public Rent(Long id, Long bookId, Long memberId) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
