package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalClinicIo;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.analytics.Analytics;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();
    private Analytics analytics = new Analytics();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalClinicIo(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getClinicIo(), new UserPrefs());
        expectedModel.addPerson(validPerson);
        expectedModel.commitClinicIo();

        assertCommandSuccess(new AddCommand(validPerson), model, commandHistory,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel, analytics);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getClinicIo().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, commandHistory,
                analytics, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
