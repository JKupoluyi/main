package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.FilterEventCommand;
import seedu.address.model.event.DateEqualsPredicate;

public class FilterEventCommandParserTest {

    private FilterEventCommandParser parser = new FilterEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterEventCommand() {
        // no leading and trailing whitespaces
        FilterEventCommand expectedFindCommand =
                new FilterEventCommand(new DateEqualsPredicate("01/04/2019"));
        assertParseSuccess(parser, "01/04/2019", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 01/04/2019  \t", expectedFindCommand);
    }

}
