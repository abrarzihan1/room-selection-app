import React from 'react';

function BookingDateTime({ formData, handleChange, nextStep, prevStep }) {
    return (
        <div>
            <div>
                <h2>Choose Date and Time</h2>
                <input
                    type="date"
                    value={formData.date}
                    onChange={e => handleChange('date', e.target.value)}
                />
                <button onClick={prevStep}>Back</button>
                <button onClick={nextStep}>Next</button>
            </div>
        </div>
    );
}

export default BookingDateTime;