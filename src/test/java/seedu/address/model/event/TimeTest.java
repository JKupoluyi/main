package seedu.address.model.event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Time(null, null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        String validTime = "12:00";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime, invalidTime));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time(validTime, invalidTime));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime, validTime));
    }

    @Test
    public void isValidTime() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid name
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("^")); // only non-alphanumeric characters
        assertFalse(Time.isValidTime("abc")); // contains alphabetical characters
        assertFalse(Time.isValidTime("-12:-20")); // negative values
        assertFalse(Time.isValidTime("12.20")); // HH.MM format
        assertFalse(Time.isValidTime("40.11")); // MM.HH format
        assertFalse(Time.isValidTime("2359")); // HHMM format
        assertFalse(Time.isValidTime("25:20")); // HH exceeds 24
        assertFalse(Time.isValidTime("12:60")); // MM exceeds 59
        assertFalse(Time.isValidTime("12:20:50")); // HH:MM:SS format

        // valid name
        assertTrue(Time.isValidTime("00:00")); // HH:MM minimum values
        assertTrue(Time.isValidTime("23:59")); // HH:MM maximum values
    }
}
