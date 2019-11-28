package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class DateRange {
    private LocalDate start, end;
    
    /**
     * Creates a new DateRange with a start date and an end date 
     * @param start This is the start date in the type LocalDate.
     * @param end This is the end date in the type LocalDate.
     */
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    /**
     * Gets the start date of the DateRange.
     * @return the start date
     */
    public LocalDate getStart() {
        return this.start;
    }
    
    /**
     * Gets the end date of the DateRange
     * @return the end date
     */
    public LocalDate getEnd() {
        return this.end;
    }
    
    /**
     * Converts the DateRange into the duration between the start and end date in years
     * @return the duration between start and end date in years
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }
    
    /**
     * Converts the DateRange into the duration between the start and end date in days
     * @return the duration between start and end date in days
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
     * Checks if two DateRange overlaps each other
     * @param other This is the DateRange being compared to
     * @return whether the two DateRange overlaps or not
     */
    public Boolean overlaps(DateRange other) {
    	LocalDate firstStart = this.start;
    	LocalDate firstEnd = this.end;
    	LocalDate otherEnd = other.getEnd();
    	LocalDate otherStart = other.getStart();
    	if (firstEnd.isBefore(otherStart)||otherEnd.isBefore(firstStart)) {
    		return false;
    	}
    	return true;
      
    }
    
    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
    // You can add your own methods here
    @Override
    public String toString() {
    	return String.format("From: " + start + " to: "+ end);
    }
}
