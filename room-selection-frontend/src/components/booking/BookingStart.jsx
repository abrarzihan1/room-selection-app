import React from 'react';
import './BookingStart.css'

function BookingStart({ formData, handleChange, nextStep }) {
    return (
        <div>
            <h1>Choose Booking Name</h1>
            <input
                type="text"
                placeholder="Enter booking name"
                value={formData.name}
                onChange={e => handleChange('name', e.target.value)}
                className={"booking-start-input"}
            />
            <button className={"navigation-button"} onClick={nextStep}>Next</button>
        </div>
    );
}

export default BookingStart;