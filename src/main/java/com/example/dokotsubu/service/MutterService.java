package com.example.dokotsubu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dokotsubu.model.Mutter;
import com.example.dokotsubu.repository.MutterRepository;
import org.springframework.data.domain.Sort;

@Service
public class MutterService {

    @Autowired
    private MutterRepository repository;

    public List<Mutter> findAll() {
        // IDの降順で取得 (ORDER BY ID DESC)
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void create(Mutter mutter) {
        repository.save(mutter);
    }
}
