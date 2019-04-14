package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//@@author windrichie
/**
 * Represents an Event's date in the event calendar of events.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in standard DD/MM/YYYY format. Eg. 1-30 for certain months, 1-31 for other months,"
                    + " 1-29 in February in leap years.";
    //@@author windrichie-reused
    // regex expression taken from: http://regexlib.com/REDetails.aspx?regexp_id=151
    public static final String VALIDATION_REGEX = "^(?:(?:31(\\/)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)"
            + "(\\/)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/)0?2\\3"
            + "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))"
            + "$|^(?:0?[1-9]|1\\d|2[0-8])(\\/)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    private static final String[] DAY_MAPPING = {"Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"};

    private final String fulldate;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        fulldate = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fulldate;
    }

    public java.util.Date getDateFormat() {
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date;
        try {
            date = dateParser.parse(fulldate);
        } catch (ParseException e) {
            return null;
        }

        return date;
    }

    public String getDayString() {
        return DAY_MAPPING[getDayInt()];
    }

    public int getDayInt() {
        Calendar c = Calendar.getInstance();
        c.setTime(getDateFormat());
        int dayInt = c.get(Calendar.DAY_OF_WEEK); // Sunday = 1
        if (dayInt == 1) {
            dayInt = 6;
        } else {
            dayInt = dayInt - 2;
        }

        return dayInt;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && fulldate.equals(((Date) other).fulldate)); // state check
    }

    @Override
    public int hashCode() {
        return fulldate.hashCode();
    }

}
