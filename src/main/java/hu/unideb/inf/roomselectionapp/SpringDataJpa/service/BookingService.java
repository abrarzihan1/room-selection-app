package hu.unideb.inf.roomselectionapp.SpringDataJpa.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.TimeAvailabilityRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    public String save(Booking booking);
    public List<Booking> findByTeacherId(String teacherId);
    public List<LocalTime> findAvailableTimes(TimeAvailabilityRequest request);
    public boolean deleteBookingByBookingId(Long bookingId);

    boolean isBookingConflict(String roomId, LocalDate date, LocalTime startTime, long bookingId);

    Optional<Booking> findById(long bookingId);
}
