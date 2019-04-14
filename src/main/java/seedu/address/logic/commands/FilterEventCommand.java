package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.event.DateEqualsPredicate;

//@@author windrichie
/**
 * Finds and lists all events in event calendar whose date is equal to the argument keywords.
 */
public class FilterEventCommand extends Command {

    public static final String COMMAND_WORD = "filterevent";
    // public static final String COMMAND_ALIAS = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all events that happens on a certain date "
            + "Parameters: filterevent [DATE]...\n"
            + "Example: " + COMMAND_WORD + " 10/14/2019";

    private final DateEqualsPredicate predicate;

    public FilterEventCommand(DateEqualsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()),
                false, false, 1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterEventCommand // instanceof handles nulls
                && predicate.equals(((FilterEventCommand) other).predicate)); // state check
    }
}
