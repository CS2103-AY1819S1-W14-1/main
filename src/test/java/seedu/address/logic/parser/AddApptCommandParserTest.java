package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ID_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY_APPT;

import org.junit.Test;

import seedu.address.logic.commands.AddApptCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.testutil.AppointmentBuilder;

public class AddApptCommandParserTest {
    private AddApptCommandParser parser = new AddApptCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppt = new AppointmentBuilder(AMY_APPT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DATE_DESC_AMY + TIME_DESC_AMY
                + ID_DESC_AMY, new AddApptCommand(expectedAppt));

        //multiple dates - last accepted
        assertParseSuccess(parser, DATE_DESC_BENSON + DATE_DESC_AMY + TIME_DESC_AMY
                + ID_DESC_AMY, new AddApptCommand(expectedAppt));

        //multiple times - last accepted
        assertParseSuccess(parser, DATE_DESC_AMY + TIME_DESC_BENSON + TIME_DESC_AMY
                + ID_DESC_AMY, new AddApptCommand(expectedAppt));

        //multiple ids - last accepted
        assertParseSuccess(parser, DATE_DESC_AMY + TIME_DESC_AMY + ID_DESC_BENSON
                + ID_DESC_BENSON, new AddApptCommand(expectedAppt));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApptCommand.MESSAGE_USAGE);

        //missing date
        assertParseFailure(parser, TIME_DESC_AMY + ID_DESC_AMY, expectedMessage);

        //missing date prefix
        assertParseFailure(parser, VALID_DATE_AMY + TIME_DESC_AMY + ID_DESC_AMY, expectedMessage);

        //missing time
        assertParseFailure(parser, DATE_DESC_AMY + ID_DESC_AMY, expectedMessage);

        //missing time prefix
        assertParseFailure(parser, DATE_DESC_AMY + VALID_TIME_AMY + ID_DESC_AMY, expectedMessage);

        //missing id
        assertParseFailure(parser, DATE_DESC_AMY + TIME_DESC_AMY, expectedMessage);

        //missing id prefix
        assertParseFailure(parser, DATE_DESC_AMY + TIME_DESC_AMY + VALID_ID_AMY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid date
        assertParseFailure(parser, INVALID_DATE_DESC + TIME_DESC_AMY + ID_DESC_AMY,
                Date.MESSAGE_DATE_CONSTRAINTS);

        //invalid time
        assertParseFailure(parser, DATE_DESC_AMY + INVALID_TIME_DESC + ID_DESC_AMY,
                Time.MESSAGE_TIME_CONSTRAINTS);

        //TODO: invalid id
    }
}
