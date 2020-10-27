package team.baymax.model.calendar;

import static java.util.Objects.requireNonNull;
import static team.baymax.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a DateTime object used in Baymax that wraps around the java.time.LocalDateTime
 * and provides basic functionality for storing and comparing.
 */
public class DateTime implements Comparable<DateTime> {
    public static final String MESSAGE_CONSTRAINTS = "DateTime entered must be in the form of <dd-MM-yyyy HH:mm>";

    private static final DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter FORMAT_STORAGE = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mma");

    private final LocalDateTime dateTime;

    /**
     * DateTime can only be created by calling {@link #fromString(String)}
     */
    private DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Default constructor for {@code DateTime}, with input 2020-12-12 23:59
     */
    protected DateTime() {
        this.dateTime = LocalDateTime.parse("12-12-2020 23:59", FORMAT_INPUT);
    }

    /**
     * Creates a new {@code DateTime} from a given formatted {@code dateTimeString}
     */
    public static DateTime fromString(String dateTimeString) {
        requireNonNull(dateTimeString);
        checkArgument(isValidDateTime((dateTimeString)), MESSAGE_CONSTRAINTS);
        return new DateTime(LocalDateTime.parse(dateTimeString, FORMAT_INPUT));
    }

    /**
     * Returns true if a given string is a valid DateTime format
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test, FORMAT_INPUT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the dateTime in a storage format
     */
    public String getStorageFormat() {
        return dateTime.format(FORMAT_STORAGE);
    }

    public Day getDay() {
        return new Day(dateTime.getDayOfMonth());
    }

    public Month getMonth() {
        return new Month(dateTime.getMonthValue());
    }

    public Year getYear() {
        return new Year(dateTime.getYear());
    }

    public Date getDate() {
        return new Date(getDay(), getMonth(), getYear());
    }

    public Hour getHour() {
        return new Hour(dateTime.getHour());
    }

    public Minute getMinute() {
        return new Minute(dateTime.getMinute());
    }

    public Time getTime() {
        return new Time(getHour(), getMinute());
    }

    @Override
    public int compareTo(DateTime other) {
        return dateTime.compareTo(other.dateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DateTime
                && dateTime.equals(((DateTime) other).dateTime));
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public String toString() {
        return dateTime.format(FORMAT_OUTPUT);
    }
}