package com.example.dicontainer.service;

import com.example.dicontainer.domain.Book;
import com.example.dicontainer.domain.Member;
import com.example.dicontainer.domain.Rent;
import com.example.dicontainer.repository.RentRepository;

public class RentService {
    private final BookService bookService;
    private final MemberService memberService;
    private final RentRepository rentRepository;

    public RentService(BookService bookService, MemberService memberService, RentRepository rentRepository) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.rentRepository = rentRepository;
    }

    public void rent(Long bookId,Long memberId) {
        Book book = bookService.get(bookId);
        Member member = memberService.find(memberId);
        rentRepository.save(new Rent(1l,book.getId(),member.getId()));
    }
}
