package seedu.clinicio.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;

import seedu.clinicio.commons.core.ComponentManager;
import seedu.clinicio.commons.core.LogsCenter;

import seedu.clinicio.logic.commands.Command;
import seedu.clinicio.logic.commands.CommandResult;
import seedu.clinicio.logic.commands.exceptions.CommandException;
import seedu.clinicio.logic.parser.ClinicIoParser;
import seedu.clinicio.logic.parser.exceptions.ParseException;

import seedu.clinicio.model.Model;
import seedu.clinicio.model.analytics.Analytics;
import seedu.clinicio.model.staff.Staff;
import seedu.clinicio.model.person.Person;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final CommandHistory history;
    private final Analytics analytics;
    private final ClinicIoParser clinicIoParser;

    public LogicManager(Model model) {
        this.model = model;
        history = new CommandHistory();
        analytics = new Analytics();
        clinicIoParser = new ClinicIoParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            Command command = clinicIoParser.parseCommand(commandText);
            return command.execute(model, history, analytics);
        } finally {
            history.add(commandText);
        }
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Staff> getFilteredStaffList() {
        return model.getFilteredStaffList();
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
