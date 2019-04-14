package seedu.address.model.event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid name
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("^")); // only non-alphanumeric characters
        assertFalse(Date.isValidDate("abc")); // contains alphabetical characters
        assertFalse(Date.isValidDate("2019/02/13")); // YYYY/MM/DD format
        assertFalse(Date.isValidDate("2019/31/01")); // YYYY/DD/MM format
        assertFalse(Date.isValidDate("01/31/2019")); // MM/DD/YYYY format
        assertFalse(Date.isValidDate("2019-02-13")); // YYYY-MM-DD format
        assertFalse(Date.isValidDate("13-02-2019")); // DD-MM-YYYY format
        assertFalse(Date.isValidDate("01-31-2019")); // MM-DD-YYYY format
        assertFalse(Date.isValidDate("00/01/2019")); // Date is 0
        assertFalse(Date.isValidDate("18/00/2019")); // Month is 0
        assertFalse(Date.isValidDate("32/01/2019")); // Date exceeds 31
        assertFalse(Date.isValidDate("31/04/2019")); // Date exceeds 30 for 30-day month
        assertFalse(Date.isValidDate("30/02/2019")); // Date exceeds 29 for any February
        assertFalse(Date.isValidDate("29/02/2019")); // Date exceeds 28 for February in non-leap years
        assertFalse(Date.isValidDate("31/13/2019")); // Month exceeds 12

        // valid name
        assertTrue(Date.isValidDate("01/01/2019")); // DD/MM/YYYY format for min date
        assertTrue(Date.isValidDate("31/01/2019")); // DD/MM/YYYY format for max date
        assertTrue(Date.isValidDate("29/02/2016")); // 29th February in leap years
        assertTrue(Date.isValidDate("30/04/2019")); // DD/MM/YYYY format for max date in 30-day month
    }
}
