package team.baymax.testutil.appointment;

<<<<<<< HEAD
import static team.baymax.testutil.datetime.TypicalDateTimes.DATETIME2;
import static team.baymax.testutil.datetime.TypicalDateTimes.DATETIME3;
import static team.baymax.testutil.datetime.TypicalDateTimes.DATETIME6;
import static team.baymax.testutil.datetime.TypicalDates.DATE2;
import static team.baymax.testutil.patient.TypicalPatients.ALICE;
import static team.baymax.testutil.patient.TypicalPatients.BOB;
import static team.baymax.testutil.patient.TypicalPatients.CARL;
import static team.baymax.testutil.patient.TypicalPatients.DANIEL;
import static team.baymax.testutil.patient.TypicalPatients.ELLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import team.baymax.model.appointment.Appointment;
import team.baymax.model.appointment.AppointmentClashPredicate;
import team.baymax.model.appointment.AppointmentContainsKeywordsPredicate;
import team.baymax.model.appointment.AppointmentIdenticalPredicate;
import team.baymax.model.appointment.AppointmentMatchesDatePredicate;
import team.baymax.model.appointment.AppointmentStatus;
import team.baymax.model.appointment.BelongsToPatientPredicate;
import team.baymax.model.appointment.Description;
import team.baymax.model.appointment.SameDatetimeAndPatientPredicate;
import team.baymax.model.modelmanagers.AppointmentManager;
import team.baymax.model.tag.Tag;
import team.baymax.model.util.datetime.DateTime;
import team.baymax.model.util.datetime.Duration;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    private static final List<Tag> tagsAsList = Arrays.asList(new Tag("Xray"), new Tag("DrGoh"));
    public static final HashSet<Tag> TAGS_VAR_1 = new HashSet<>(tagsAsList);
    private static final List<Tag> tagsAsList2 = Arrays.asList(new Tag("Followup"), new Tag("DrTan"));
    public static final HashSet<Tag> TAGS_VAR_2 = new HashSet<>(tagsAsList2);

    public static final Appointment ALICE_APT = new Appointment(ALICE,
            DateTime.fromString("11-10-2020 12:45"),
            new Duration(60), new Description("desc 1"),
            new HashSet<>(), AppointmentStatus.UPCOMING);

    // Exact duplicate of APT1
    public static final Appointment ALICE_APT_DUPLICATE = new Appointment(ALICE,
            DateTime.fromString("11-10-2020 12:45"),
            new Duration(60), new Description("desc 1"),
            new HashSet<>(), AppointmentStatus.UPCOMING);

    // Variant 1 - same patient and datetime with APT1, but other fields are different
    public static final Appointment ALICE_APT_VAR_1 = new Appointment(ALICE,
            DateTime.fromString("11-10-2020 12:45"),
            new Duration(60), new Description("desc 2"),
            new HashSet<>(), AppointmentStatus.MISSED);

    // Variant 2 - same patient, but different datetime and tags
    public static final Appointment ALICE_APT_VAR_2 = new Appointment(ALICE,
            DateTime.fromString("12-01-2020 12:45"),
            new Duration(60), new Description("desc 2"),
            new HashSet<>(), AppointmentStatus.UPCOMING);

    public static final Appointment CARL_APT = new Appointment(CARL,
            DateTime.fromString("11-11-2020 11:30"),
            new Duration(50), new Description("desc 3"),
            new HashSet<>(), AppointmentStatus.UPCOMING);

    public static final Appointment DANIEL_APT = new Appointment(DANIEL, DATETIME2,
            new Duration(60), new Description("Follow up on broken arm"),
            TAGS_VAR_1, AppointmentStatus.UPCOMING);

    public static final Appointment ELLE_APT = new Appointment(ELLE, DATETIME3,
            new Duration(60), new Description("Blood test needed"),
            TAGS_VAR_2, AppointmentStatus.UPCOMING);

    public static final Appointment BOB_APT = new Appointment(BOB,
            DateTime.fromString("11-10-2020 12:45"),
            new Duration(40), new Description("desc 4"),
            BOB.getTags(), AppointmentStatus.DONE);

    public static final Appointment BOB_APT_VAR_2 = new Appointment(BOB,
            DateTime.fromString("11-12-2020 12:45"),
            new Duration(40), new Description("desc 4"),
            BOB.getTags(), AppointmentStatus.DONE);

    public static final Appointment BOB_APT_CLASH_WITH_BOBV2 = new Appointment(BOB,
            DateTime.fromString("11-12-2020 12:55"),
            new Duration(40), new Description("desc 4"),
            BOB.getTags(), AppointmentStatus.DONE);

    public static final AppointmentContainsKeywordsPredicate KEYWORDS_PREDICATE =
            new AppointmentContainsKeywordsPredicate(List.of("Diabetes", "LTP"));

    public static final AppointmentIdenticalPredicate IDENTICAL_PREDICATE =
            new AppointmentIdenticalPredicate(ALICE_APT);

    public static final AppointmentMatchesDatePredicate MATCHES_DATE_PREDICATE =
            new AppointmentMatchesDatePredicate(DATE2);

    public static final BelongsToPatientPredicate BELONGS_TO_PATIENT_PREDICATE = new BelongsToPatientPredicate(BOB);

    public static final SameDatetimeAndPatientPredicate SAME_DATETIME_AND_PATIENT_PREDICATE =
            new SameDatetimeAndPatientPredicate(DATETIME6, BOB);

    public static final AppointmentClashPredicate APPOINTMENT_CLASH_PREDICATE =
            new AppointmentClashPredicate(BOB_APT_CLASH_WITH_BOBV2, BOB_APT);

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns a {@code PatientManager} with all the typical patients.
     */
    public static AppointmentManager getTypicalAppointmentManager() {
        AppointmentManager appointmentManager = new AppointmentManager();
        for (Appointment appointment : getTypicalAppointments()) {
            appointmentManager.addAppointment(appointment);
        }
        return appointmentManager;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALICE_APT, CARL_APT, BOB_APT));
    }

    public static List<Appointment> getTaggedAndDescriptiveAppointments() {
        return new ArrayList<>(Arrays.asList(CARL_APT, DANIEL_APT, ELLE_APT));
    }
}
