package com.example.work.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.work.model.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

}
