package bank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BankTests {
    private Bank bank;

    @Before
    public void setup() {
        bank = new Bank("CentralBank", 2);
        bank.addClient(new Client("Pesho"));
    }

    @Test
    public void testBankIsCreatedSuccessfully() {
        bank = new Bank("CentralBank", 2);
        assertEquals("CentralBank", bank.getName());
        assertEquals(2, bank.getCapacity());

    }

    @Test(expected = NullPointerException.class)
    public void testBankIsNotCreatedWhenNameIsEmpty() {
        new Bank("", 1000);
    }

    @Test(expected = NullPointerException.class)
    public void testBankIsNotCreatedWhenNameIsNull() {
        new Bank(null, 1000);
    }

    @Test(expected = NullPointerException.class)
    public void testBankIsNotCreatedWhenNameIsWhiteSpace() {
        new Bank(" ", 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBankIsNotCreatedWhenCapacityIsNegativeNumber() {
        new Bank("CentralBank", -1);
    }

    @Test
    public void testGetClientCount() {
        assertEquals(1, bank.getCount());
    }

    @Test
    public void testClientIsAddedSuccessfully() {
        int clientsCount = bank.getCount();
        bank.addClient(new Client("Ivan"));
        assertEquals(clientsCount + 1, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientIsNotAddedBecauseOfBankIsFull() {
        Bank bank = new Bank("NoCapacityBank", 0);
        bank.addClient(new Client("Ivan"));

    }

    @Test
    public void testClientIsRemovedSuccessfully() {
        int clientsCount = bank.getCount();
        bank.removeClient("Pesho");
        assertEquals(clientsCount - 1, bank.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientIsNotRemovedBecauseOfMissingClient() {
        bank.removeClient("Ivan");
    }

    @Test
    public void testClientIsApprovedForLoan() {
        Client client = new Client("Ivan");
        bank.addClient(client);
        assertEquals(client, bank.loanWithdrawal("Ivan"));
        assertFalse(client.isApprovedForLoan());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientIsNotApprovedForLoanBecauseOfMissingClient() {
        bank.loanWithdrawal("Ivan");
    }

    @Test
    public void testBankStatisticsOneClient() {
        assertEquals("The client Pesho is at the CentralBank bank!", bank.statistics());
    }

    @Test
    public void testBankStatisticsTwoClient() {
        assertEquals("The client Pesho is at the CentralBank bank!", bank.statistics());
    }

    @Test
    public void testBankStatisticsEmptyClient() {
        bank.addClient(new Client("Ivan"));
        assertEquals("The client Pesho, Ivan is at the CentralBank bank!", bank.statistics());
    }
}
