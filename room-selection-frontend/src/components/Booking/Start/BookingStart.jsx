import React, { useState } from 'react';
import './BookingStart.css';

function BookingStart({ formData, handleChange, nextStep }) {
    const [error, setError] = useState('');

    const handleNext = () => {
        if (!formData.name.trim()) {
            setError('Booking name is required.');
            return;
        }
        setError('');
        nextStep();
    };

    return (
        <div>
            <h1>Booking a Room</h1>
            <p>Start by giving a name for your booking</p>
            <input
                type="text"
                placeholder="Enter booking name"
                value={formData.name}
                onChange={e => handleChange('name', e.target.value)}
                className="booking-start-input"
            />
            {error && <p className="error-message">{error}</p>}
            <button className="navigation-button" onClick={handleNext}>Next</button>
        </div>
    );
}

export default BookingStart;
