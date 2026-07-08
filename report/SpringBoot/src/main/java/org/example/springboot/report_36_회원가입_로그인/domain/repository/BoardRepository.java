package org.example.springboot.report_36_회원가입_로그인.domain.repository;

import org.example.springboot.report_36_회원가입_로그인.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
