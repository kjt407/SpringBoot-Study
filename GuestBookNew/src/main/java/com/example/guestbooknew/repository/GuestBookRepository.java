package com.example.guestbooknew.repository;

import com.example.guestbooknew.entity.Guestbook;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<Guestbook,Long>{
}
