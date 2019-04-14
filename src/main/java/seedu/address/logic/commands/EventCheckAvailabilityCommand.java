package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

//@@author windrichie
/**
 * Checks if an event clashes with a person's timetable.
 */
public class EventCheckAvailabilityCommand extends Command {

    public static final String COMMAND_WORD = "checkAvailEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks if an event clashes with a person's timetable. "
            + "Parameters:  "
            + PREFIX_PERSON + "PERSON_INDEX "
            + PREFIX_EVENT + "EVENT_INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSON + "1 "
            + PREFIX_EVENT + "1 ";

    public static final String MESSAGE_NO_CLASH = "%1$s can attend this event titled \"%2$s\" on %3$s at %4$s!";
    public static final String MESSAGE_CLASH = "%1$s has an activity during this event titled \"%2$s\" "
            + "on %3$s at %4$s!";

    //private final Module modAdd;
    private final Index personIndex;
    private final Index eventIndex;
    private boolean isClash = false;

    /**
     * Creates an AddActivityCommand to add the specified {@code Person}
     */
    public EventCheckAvailabilityCommand(Index personIndex, Index eventIndex) {
        requireNonNull(personIndex);
        requireNonNull(eventIndex);

        this.personIndex = personIndex;
        this.eventIndex = eventIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();
        List<Event> eventList = model.getFilteredEventList();

        if (personIndex.getOneBased() > personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (eventIndex.getOneBased() > eventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Person targetPerson = personList.get(personIndex.getZeroBased());
        Event targetEvent = eventList.get(eventIndex.getZeroBased());

        this.isClash = checkForClash(targetPerson, targetEvent);

        if (this.isClash) {
            return new CommandResult(String.format(MESSAGE_CLASH, targetPerson.getName(),
                    targetEvent.getTitle().toString(), targetEvent.getDate().getDayString(),
                    targetEvent.getTime().startEndTimeToString()), false, false, 2,
                    personIndex.getZeroBased());
        } else {
            return new CommandResult(String.format(MESSAGE_NO_CLASH, targetPerson.getName(),
                    targetEvent.getTitle().toString(), targetEvent.getDate().getDayString(),
                    targetEvent.getTime().startEndTimeToString()), false, false, 2,
                    personIndex.getZeroBased());
        }

    }
    /**
    * Checks for clash
    */
    public boolean checkForClash (Person targetPerson, Event targetEvent) {
        String[][] timetable = targetPerson.getTimeTable().getTimeTableArray();
        int eventDay = targetEvent.getDate().getDayInt();
        System.out.println(timetable[1].length);

        for (int i = 0; i < targetEvent.getTime().getDuration(); i++) {
            int currTime = Integer.valueOf(targetEvent.getTime().startTime.split(":")[0]) + i;
            System.out.println("currTime in array index: " + currTime * 2);
            if (timetable[eventDay][currTime * 2] != null | timetable[eventDay][currTime * 2 + 1] != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventCheckAvailabilityCommand // instanceof handles nulls
                && personIndex.equals(((EventCheckAvailabilityCommand) other).personIndex));
    }
}
