package com.customer_api.service;

import com.customer_api.entity.CustomerDTO;
import com.customer_api.entity.SavedCustomer;
import com.customer_api.exception.CustomerDoesNotExistException;
import com.customer_api.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private final Fixtures fixtures = new Fixtures();


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAllCustomerAfterAddingThem() {
        //given
        SavedCustomer savedCustomer = fixtures.savedCustomer;
        when(customerRepository.findById(2)).thenReturn(ofNullable(savedCustomer));

        // when
        SavedCustomer customerByID = customerService.findCustomerByID(2);

        // then
        assertEquals(savedCustomer, customerByID);

    }

    @Test
    void shouldModifyCustomer() {
        //given
        SavedCustomer savedCustomer = fixtures.savedCustomer;
        CustomerDTO customerToUpdate = fixtures.customerDTO;
        when(customerRepository.findById(fixtures.customerId)).thenReturn(Optional.of(savedCustomer));
        when(customerRepository.save(savedCustomer)).thenReturn(savedCustomer);

        // when
        SavedCustomer updatedCustomer = customerService.modifyCustomer(fixtures.customerId, customerToUpdate);

        // then
        assertThat(updatedCustomer.getCustomerName())
                .isEqualTo(customerToUpdate.getCustomerName());

        assertThat(updatedCustomer.getCustomerSurname())
                .isEqualTo(customerToUpdate.getCustomerSurname());

    }

    @Test
    void shouldNotModifyCustomerWhenCustomerNotFound() {
        //given
        CustomerDTO customerToUpdate = fixtures.customerDTO;
        when(customerRepository.findById(fixtures.customerId)).thenReturn(Optional.empty());
        when(customerRepository.save(Mockito.any(SavedCustomer.class))).thenReturn(fixtures.savedCustomer);

        // when
        assertThrows(CustomerDoesNotExistException.class, () -> customerService.modifyCustomer(fixtures.customerId, customerToUpdate));

        // then
        verify(customerRepository, never()).save(Mockito.any(SavedCustomer.class));

    }

    public static class Fixtures {
        SavedCustomer savedCustomer = createFakeCustomer();
        CustomerDTO customerDTO = createFakeCustomerToUpdate();
        Integer customerId = 2;

        private SavedCustomer createFakeCustomer() {
            SavedCustomer savedCustomer = new SavedCustomer();
            savedCustomer.setCustomerName("Mateusz");
            savedCustomer.setCustomerSurname("Bereda");
            savedCustomer.setId(customerId);
            savedCustomer.setCity("Gdańsk");
            savedCustomer.setAddress("Wschodnia 30/1");
            return savedCustomer;
        }

        private CustomerDTO createFakeCustomerToUpdate() {
            CustomerDTO customerToUpdate = new CustomerDTO();
            customerToUpdate.setCustomerName("Piotr");
            customerToUpdate.setCustomerSurname("Nowak");
            customerToUpdate.setAge(23);
            customerToUpdate.setCity("Warszawa");
            customerToUpdate.setAddress("Zachodnia 30/1");
            return customerToUpdate;
        }
    }
}