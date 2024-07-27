package com.ophelos.incomeexpenditure.services;

import com.ophelos.incomeexpenditure.api.Expenditure;
import com.ophelos.incomeexpenditure.api.Income;
import com.ophelos.incomeexpenditure.web.data.ExpenditureImpl;
import com.ophelos.incomeexpenditure.web.data.IncomeImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IncomeExpenditureServiceTest {

    private IncomeExpenditureService service = new IncomeExpenditureService();

    @Test
    public void testCalculateDisposableIncome() throws Exception {

        // Setup some test income and expenditure data

        ArrayList<Income> incomes = new ArrayList<>();
        incomes.add(new IncomeImpl("Salary", 2800));
        incomes.add(new IncomeImpl("Other", 300));

        ArrayList<Expenditure> expenditures = new ArrayList<>();
        expenditures.add(new ExpenditureImpl("Mortgage", 600));
        expenditures.add(new ExpenditureImpl("Utilities", 100));
        expenditures.add(new ExpenditureImpl("Travel", 150));
        expenditures.add(new ExpenditureImpl("Food", 500));
        expenditures.add(new ExpenditureImpl("Loan Repayment", 1000));

        assertEquals(750, service.calculateDisposableIncome(incomes, expenditures));
    }

    @Test
    public void testGetRating() throws Exception {

        // Setup some test income and expenditure data

        ArrayList<Income> incomes = new ArrayList<>();
        ArrayList<Expenditure> expenditures = new ArrayList<>();

        assertThrows(RuntimeException.class, () -> service.getRating(incomes, expenditures));

        incomes.add(new IncomeImpl("Salary", 700));
        incomes.add(new IncomeImpl("Other", 300));

        assertEquals("A", service.getRating(incomes, expenditures));

        expenditures.add(new ExpenditureImpl("Mortgage", 90));
        assertEquals("A", service.getRating(incomes, expenditures));

        expenditures.add(new ExpenditureImpl("Utilities", 10));
        assertEquals("B", service.getRating(incomes, expenditures));

        expenditures.add(new ExpenditureImpl("Travel", 190));
        assertEquals("B", service.getRating(incomes, expenditures));

        expenditures.add(new ExpenditureImpl("Food", 10));
        assertEquals("C", service.getRating(incomes, expenditures));

        expenditures.add(new ExpenditureImpl("Food", 190));
        assertEquals("C", service.getRating(incomes, expenditures));

        expenditures.add(new ExpenditureImpl("Misc", 10));
        assertEquals("D", service.getRating(incomes, expenditures));

        expenditures.add(new ExpenditureImpl("Utilities", 250));
        assertEquals("D", service.getRating(incomes, expenditures));

        expenditures.add(new ExpenditureImpl("Loan Repayment", 600));
        assertEquals("D", service.getRating(incomes, expenditures));

    }
}
