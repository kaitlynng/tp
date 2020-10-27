package team.baymax.model.calendar;

import static java.util.Objects.requireNonNull;

import team.baymax.model.calendar.utils.CalendarUtil;

public class Month {

    private static int[] numOfDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private final int value;

    public Month(int value) {
        requireNonNull(value);
        if (isValidMonth(value)) {
            this.value = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns true if a given number is a valid month.
     */
    public static boolean isValidMonth(int n) {
        return n > 0 && n <= 12;
    }

    public int getValue() {
        return value;
    }

    public int getNumOfDays() {
        return numOfDays[value - 1];
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Month // instanceof handles nulls
                && value == ((Month) other).value); // state check
    }

    @Override
    public String toString() {
        return CalendarUtil.getMonthForInt(value - 1);
    }
}