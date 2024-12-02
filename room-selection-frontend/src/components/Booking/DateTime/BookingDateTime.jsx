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
        if (!formData.date) {
            handleChange('date', getTomorrowDate());
        }

        const fetchTimes = async () => {
            try {
                const response = await axios.post(
                    "/api/private/booking/available-times",
                    {
                        roomId: formData.roomId,
                        date: formData.date || getTomorrowDate(),
                    }, {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("token")}`,
                        },
                        withCredentials: true
                    }
                );
                setDates(response.data || []);
            } catch (error) {
                console.error("Error fetching available dates:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchTimes();
    }, [formData, handleChange]);

    const handleDateSelect = (date) => {
        handleChange('startTime', date);
    };

    return (
        <div>
            <h1>Choose Date and Time</h1>
            <p className={"booking-text"}>Select a date and time for your booking based on the available options for the chosen room.</p>
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
                <button
                    className="navigation-button"
                    onClick={nextStep}
                    disabled={!formData.startTime}  // Disable the button if no time is selected
                >
                    Next
                </button>
            </div>
        </div>
    );
}

export default BookingDateTime;
