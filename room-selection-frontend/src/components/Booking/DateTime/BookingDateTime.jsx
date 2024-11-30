import React, { useEffect, useState } from 'react';
import './BookingDateTime.css';
import axios from 'axios';

function BookingDateTime({ formData, handleChange, nextStep, prevStep }) {
    const [dates, setDates] = useState([]);
    const [loading, setLoading] = useState(true);

    const getTomorrowDate = () => {
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        return tomorrow.toISOString().split("T")[0];
    };

    useEffect(() => {
        // Set the default date to tomorrow if not already set
        if (!formData.date) {
            handleChange('date', getTomorrowDate());
        }

        const fetchTimes = async () => {
            try {
                const response = await axios.post(
                    "/api/public/booking/available-times",
                    {
                        roomId: formData.roomId,
                        date: formData.date || getTomorrowDate(),
                    }
                );
                setDates(response.data || []); // Ensure fallback to an empty array
            } catch (error) {
                console.error("Error fetching available dates:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchTimes();
    }, [formData, handleChange]);

    const handleDateSelect = (date) => {
        // Update both local selectedDate and formData.date
        handleChange('startTime', date);
    };

    const handleNext = () => {
        if (formData.startTime) { // Check if a startTime is selected
            nextStep();
        } else {
            alert('Please select a time before proceeding.');
        }
    };

    return (
        <div>
            <h2>Choose Date and Time</h2>
            <input
                type="date"
                value={formData.date || getTomorrowDate()}
                onChange={(e) => handleChange('date', e.target.value)}
                className="date-input"
                min={getTomorrowDate()}
            />
            {loading ? (
                <div className="loading-message">Loading...</div>
            ) : (
                <div className="date-list">
                    {dates.length > 0 ? (
                        dates.map((time, index) => (
                            <div key={index} className="date-item">
                                <span>{time}</span>
                                <button
                                    onClick={() => handleDateSelect(time)}
                                    className={`date-item-button ${formData.startTime === time ? "selected" : ""}`}
                                >
                                    {formData.startTime === time ? "Selected" : "Select"}
                                </button>
                            </div>
                        ))
                    ) : (
                        <div>No available times</div>
                    )}
                </div>
            )}
            <div className="navigation-buttons">
                <button className="navigation-button" onClick={prevStep}>Back</button>
                <button className="navigation-button" onClick={handleNext}>
                    Next
                </button>
            </div>
        </div>
    );
}

export default BookingDateTime;
