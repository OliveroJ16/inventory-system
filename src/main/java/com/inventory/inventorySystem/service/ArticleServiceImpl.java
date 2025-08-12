package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.repository.ArticleRepository;
import com.inventory.inventorySystem.service.interfaces.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRespository;

}
