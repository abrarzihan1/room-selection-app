import React, { useState, useEffect } from 'react';
import Calendar from 'react-calendar';
import axios from 'axios';
import 'react-calendar/dist/Calendar.css';
import './Booking.css';

const BookingCalender = () => {
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [availableTimes, setAvailableTimes] = useState([]);

    // Handle date selection
    const onDateChange = (date) => {
        setSelectedDate(date);
        fetchAvailableTimes(date);
    };

    // Fetch available times for the selected date
    const fetchAvailableTimes = async (date) => {
        try {
            const response = await axios.get('/api/available-times', {
                params: { date: date.toISOString().split('T')[0] }
            });
            setAvailableTimes(response.data);
        } catch (error) {
            console.error('Error fetching available times:', error);
        }
    };

    useEffect(() => {
        fetchAvailableTimes(selectedDate);
    }, [selectedDate]);

    return (
        <div className="booking-container">
            <h2>Select date & time</h2>
            <div className="booking-content">
                <div className="calendar-container">
                    <Calendar onChange={onDateChange} value={selectedDate}/>
                    <p className="timezone">Time zone (Europe/Budapest)</p>
                </div>
                <div className="available-times-container">
                    {selectedDate && (
                        <>
                            <h3>{selectedDate.toDateString()}</h3>
                            <div className="time-grid">
                                {availableTimes.length > 0 ? (
                                    availableTimes.map((time, index) => (
                                        <button key={index} className="time-button">
                                            {time}
                                        </button>
                                    ))
                                ) : (
                                    <p>No available times for this date.</p>
                                )}
                            </div>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
};

export default BookingCalender;