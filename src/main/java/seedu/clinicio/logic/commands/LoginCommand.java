package seedu.clinicio.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.clinicio.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.clinicio.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.clinicio.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.List;

import seedu.clinicio.logic.CommandHistory;

import seedu.clinicio.model.Model;
import seedu.clinicio.model.analytics.Analytics;
import seedu.clinicio.model.doctor.Doctor;
import seedu.clinicio.model.doctor.Password;
import seedu.clinicio.model.person.Person;

//@@author jjlee050

/**
 * Authenticate user and provide them access to ClinicIO based on the role.
 */
public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Authenticate user to provide "
            + "user access to ClinicIO based on the roles.\n"
            + "Parameters: " + COMMAND_WORD
            + "[" + PREFIX_ROLE + "ROLE]"
            + "[" + PREFIX_NAME + "NAME]"
            + "[" + PREFIX_PASSWORD + "PASSWORD]\n"
            + "Example: login r/doctor n/Adam Bell pass/doctor1";

    public static final String MESSAGE_SUCCESS = "Login successful.";
    public static final String MESSAGE_FAILURE = "Login failed. Please try again.";

    private final Person toAuthenticate;

    /**
     * Creates an LoginCommand to add the specified {@code Person}.
     * This {@code Person} could possibly be a doctor or receptionist.
     */
    public LoginCommand(Person person) {
        requireNonNull(person);
        toAuthenticate = person;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history, Analytics analytics) {
        requireNonNull(model);

        if (toAuthenticate instanceof Doctor) {
            List<Doctor> doctorsList = model.getFilteredDoctorList();
            if (checkDoctorCred(doctorsList)) {
                return new CommandResult(MESSAGE_SUCCESS);
            }
        }
        return new CommandResult(MESSAGE_FAILURE);
    }

    /**
     * Check through ClinicIO for valid {@code Doctor} credentials.
     * @param doctorsList A list of doctors in ClinicIO.
     * @return Returns true if doctor has valid credentials in ClinicIO.
     */
    public boolean checkDoctorCred(List<Doctor> doctorsList) {
        requireNonNull(doctorsList);

        Doctor anotherDoctor = (Doctor) toAuthenticate;
        Doctor doctorFound = searchDoctor(doctorsList, anotherDoctor);

        if (doctorFound == null) {
            return false;
        }
        return Password.isSameAsHashPassword(
                anotherDoctor.getPassword().toString(),
                doctorFound.getPassword().toString());
    }

    /**
     * Search through {@code Model} doctor list for doctor.
     * @param doctorsList A list of doctors in ClinicIO.
     * @param doctorToSearch The doctor to search inside the list of doctors.
     * @return The doctor found in the list of doctors. Return null if doctor is not found.
     */
    public Doctor searchDoctor(List<Doctor> doctorsList, Doctor doctorToSearch) {
        requireNonNull(doctorsList);
        requireNonNull(doctorToSearch);

        Doctor doctorFound = null;
        try {
            for (Doctor d : doctorsList) {
                if ((d.getName().equals(doctorToSearch.getName()))) {
                    doctorFound = d;
                    break;
                }
            }
        } catch (ClassCastException ex) {
            throw new ClassCastException();
        }

        return doctorFound;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoginCommand // instanceof handles nulls
                && toAuthenticate.equals(((LoginCommand) other).toAuthenticate)); // state check
    }

}
