import React from 'react';
import './BookingStart.css'

function BookingStart({ formData, handleChange, nextStep }) {
    return (
        <div>
            <h1>Booking a Room</h1>
            <p>Start by giving a name for your booking</p>
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