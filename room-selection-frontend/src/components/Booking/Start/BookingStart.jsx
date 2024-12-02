import React, { useState } from 'react';
import './BookingStart.css';

function BookingStart({ formData, handleChange, nextStep }) {
    const [error, setError] = useState('');

    const handleNext = () => {
        if (!formData.name.trim()) {
            setError('Please provide a name for your booking.');
            return;
        }
        setError('');
        nextStep();
    };

    return (
        <div className="booking-start-container">
            <h1>Book a Room</h1>
            <p className="booking-text">Welcome! Let’s get your room booking started. To begin, please provide a name for your booking. This helps us identify your reservation, whether it's for a meeting, an event, or a class.</p>
            <p className="booking-text"><strong>What’s the purpose of your booking?</strong> If you’re a teacher, feel free to use the name of the class you're teaching (e.g., "Math 101", "History Lecture"). If it’s a meeting or event, you can name it accordingly (e.g., "Team Meeting", "Project Planning", or "Workshop").</p>
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
