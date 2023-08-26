package com.banquito.core.productsaccounts.service;

import com.banquito.core.productsaccounts.exception.CRUDException;
import com.banquito.core.productsaccounts.model.InterestRate;
import com.banquito.core.productsaccounts.repository.InterestRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InterestRateServiceTest {

    @Mock
    private InterestRateRepository interestRateRepository;

    @InjectMocks
    private InterestRateService interestRateService;

    private InterestRate rate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void listAllActives_ReturnListOfInterestRates() {

        List<InterestRate> rates = new ArrayList<>();
        when(interestRateRepository.findByState("ACT")).thenReturn(rates);

        List<InterestRate> result = interestRateService.listAllActives();
        assertNotNull(result);
        assertEquals(rates, result);
    }

    @Test
    void obtainById_InterestRateExists() {
        Integer id = 1;
        InterestRate rate = new InterestRate();
        when(interestRateRepository.findById(id)).thenReturn(Optional.of(rate));
        InterestRate result = interestRateService.obtainById(id);
        assertNotNull(result);
        assertEquals(rate, result);
    }

    @Test
    void create_ValidInterestRate() throws CRUDException {
        InterestRate interestRate = new InterestRate();
        interestRateService.create(interestRate);
        verify(interestRateRepository).save(interestRate);
    }





    @Test
    void update_ExistingInterestRate() throws CRUDException {
        Integer id = 1;
        InterestRate existingRate = new InterestRate();
        when(interestRateRepository.findById(id)).thenReturn(Optional.of(existingRate));
        when(interestRateRepository.save(existingRate)).thenReturn(existingRate);
        assertDoesNotThrow(() -> interestRateService.update(id, existingRate));
    }


    @Test
    void inactivate_ExistingInterestRate() {

        Integer id = 1;
        InterestRate interestRate = new InterestRate();
        when(interestRateRepository.findById(id)).thenReturn(Optional.of(interestRate));
        when((interestRateRepository).save(interestRate)).thenReturn(interestRate);
        assertDoesNotThrow(() -> interestRateService.inactivate(id));
    }
}