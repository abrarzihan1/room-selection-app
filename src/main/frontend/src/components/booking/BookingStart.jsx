import React from 'react';

function BookingStart({ bookingDetails, handleChange, nextStep }) {
    return (
        <div>
            <h2>Choose Booking Name</h2>
            <input
                type="text"
                placeholder="Enter booking name"
                value={bookingDetails.name}
                onChange={e => handleChange('name', e.target.value)}
            />
            <button onClick={nextStep}>Next</button>
        </div>
    );
}

export default BookingStart;