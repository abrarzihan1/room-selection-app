import React, { useEffect, useState } from 'react';
import './BookingDateTime.css';
import axios from 'axios';

function BookingDateTime({ formData, handleChange, nextStep, prevStep }) {
    const [dates, setDates] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchTimes = async () => {
            try {
                const response = await axios.post(
                    "/api/public/booking/available-times",
                    {
                        'roomId': formData.roomId,
                        'date': formData.date
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
    }, [formData]);

    const handleDateSelect = (date) => {
        // Update both local selectedDate and formData.date
        handleChange('startTime', date);
    };

    const handleNext = () => {
        if (formData.date) { // Check if formData.date is selected
            nextStep();
        } else {
            alert('Please select a date before proceeding.');
        }
    };

    return (
        <div>
            <h2>Choose Date and Time</h2>
            <input
                type="date"
                value={formData.date || ''} // Ensure it's controlled properly
                onChange={(e) => handleChange('date', e.target.value)}
                className="date-input"
            />
            {loading ? (
                <div className="loading-message">Loading...</div>
            ) : (
                <div className="date-list">
                    {dates.length > 0 ? (
                        dates.map((date, index) => (
                            <div key={index} className="date-item">
                                <span>{date}</span>
                                <button
                                    onClick={() => handleDateSelect(date)}
                                    className={`date-item-button ${formData.date === date ? "selected" : ""}`}
                                >
                                    {formData.startTime === date ? "Selected" : "Select"}
                                </button>
                            </div>
                        ))
                    ) : (
                        <div>No available dates</div>
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
