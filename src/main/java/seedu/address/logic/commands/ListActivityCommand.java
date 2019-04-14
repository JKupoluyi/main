package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

//@@author windrichie
/**
 * Lists all activities in a person to the user.
 */
public class ListActivityCommand extends Command {

    public static final String COMMAND_WORD = "listactivity";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists activities of a person according to a given person index. Index must "
            + "be numerical starting from 1 or above.\n"
            + "Parameters: INDEX, " + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Listed all activities for %1$s";

    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index is more than number of persons!";

    private int personIndex;

    public ListActivityCommand(int index) {
        personIndex = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        // model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        if (personIndex > model.getFilteredPersonList().size() - 1) {
            return new CommandResult(MESSAGE_INDEX_OUT_OF_BOUNDS, false, false, 2, -1);
        }
        String name = model.getFilteredPersonList().get(personIndex).getName().toString();
        return new CommandResult(String.format(MESSAGE_SUCCESS, name),
                false, false, 2, personIndex);
    }
}
