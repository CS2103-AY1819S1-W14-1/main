package seedu.clinicio.logic.commands;

import static seedu.clinicio.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinicio.logic.commands.MedicineStatisticsCommand.MESSAGE_SUCCESS;

import org.junit.Test;

import seedu.clinicio.logic.CommandHistory;
import seedu.clinicio.model.Model;
import seedu.clinicio.model.ModelManager;
import seedu.clinicio.model.analytics.Analytics;
import seedu.clinicio.model.analytics.StatisticType;

public class MedicineStatisticsCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();
    private Analytics analytics = new Analytics();

    @Test
    public void execute_medicineStatisticCommand_success() {
        assertCommandSuccess(new MedicineStatisticsCommand(), model, commandHistory,
                String.format(MESSAGE_SUCCESS, StatisticType.MEDICINE), expectedModel, analytics);
    }
}
