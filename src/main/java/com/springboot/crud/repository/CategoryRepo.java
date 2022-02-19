package com.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.crud.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {


}
