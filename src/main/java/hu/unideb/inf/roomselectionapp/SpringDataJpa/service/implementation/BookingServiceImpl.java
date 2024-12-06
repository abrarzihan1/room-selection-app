package hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.TimeAvailabilityRequest;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.BookingRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public String save(Booking booking) {
        boolean isConflict = bookingRepository.existsByRoomIdAndDateAndStartTime(
                booking.getRoomId(), booking.getDate(), booking.getStartTime()
        );

        if (isConflict) {
            return "A booking already exists for the selected room, date, and time.";
        }

        bookingRepository.save(booking);
        return "success";
    }

    @Override
    public List<Booking> findByTeacherId(String teacherId) {

        return bookingRepository.findByTeacherId(teacherId);
    }

    @Override
    public List<LocalTime> findAvailableTimes(TimeAvailabilityRequest request) {
        List<LocalTime> timeSlots = List.of(
                LocalTime.of(8, 0),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0)
        );

        List<LocalTime> bookedTimes = bookingRepository.findByRoomIdAndDate(
                        request.getRoomId(),
                        request.getDate()
                ).stream()
                .map(Booking::getStartTime)
                .toList();

        return timeSlots.stream()
                .filter(slot -> !bookedTimes.contains(slot))
                .toList();
    }

    @Override
    public boolean deleteBookingByBookingId(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            bookingRepository.delete(booking.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean isBookingConflict(String roomId, LocalDate date, LocalTime startTime, long bookingId) {
        return bookingRepository.existsByRoomIdAndDateAndStartTimeAndBookingIdNot(
                roomId, date, startTime, bookingId);
    }

    @Override
    public Optional<Booking> findById(long bookingId) {

        return bookingRepository.findById(bookingId);
    }
}
