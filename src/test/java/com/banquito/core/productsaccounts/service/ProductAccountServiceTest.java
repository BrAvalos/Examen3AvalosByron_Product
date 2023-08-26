package com.banquito.core.productsaccounts.service;

import com.banquito.core.productsaccounts.model.ProductAccount;
import com.banquito.core.productsaccounts.repository.ProductAccountRepository;
import com.banquito.core.productsaccounts.exception.CRUDException;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ProductAccountServiceTest {
    @Mock
    private ProductAccountRepository productAccountRepository;

    @InjectMocks
    private ProductAccountService productAccountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listAllActives_ReturnsListOfProductAccounts() {

        List<ProductAccount> productAccounts = new ArrayList<>();
        productAccounts.add(new ProductAccount());
        when(productAccountRepository.findByState("ACT")).thenReturn(productAccounts);
        List<ProductAccount> result = productAccountService.listAllActives();
        assertEquals(productAccounts, result);
    }

    @Test
    void obtainById_ExistingProductAccount_ReturnsProductAccount() {
        String id = "1";
        ProductAccount productAccount = new ProductAccount();
        when(productAccountRepository.findById(id)).thenReturn(Optional.of(productAccount));
        ProductAccount result = productAccountService.obtainById(id);
        assertEquals(productAccount, result);
    }



    @Test
    void create_ValidProductAccount_CreatesSuccessfully() throws CRUDException {
        ProductAccount productAccount = new ProductAccount();
        when(productAccountRepository.save(productAccount)).thenReturn(productAccount);
        assertDoesNotThrow(() -> productAccountService.create(productAccount));
    }
}