package seedu.address.model.person.modulelist;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidModuleName = "CS32934802398";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Module(invalidModuleName));
    }

    @Test
    public void isValidModuleName() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // invalid name
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only
        assertFalse(Module.isValidModule("^")); // only non-alphanumeric characters
        assertFalse(Module.isValidModule("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Module.isValidModule("CS2343")); // alphabets only
        assertTrue(Module.isValidModule("CS2143")); // numbers only
        assertTrue(Module.isValidModule("CSS2343")); // alphanumeric characters
        assertTrue(Module.isValidModule("AL3333")); // with capital letters
    }
}
