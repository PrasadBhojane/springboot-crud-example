package com.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.crud.entity.Category;

@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<Category, Integer> {


}
