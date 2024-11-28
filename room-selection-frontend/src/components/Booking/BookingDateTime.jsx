import React from 'react';
import './BookingDateTime.css'

function BookingDateTime({ formData, handleChange, nextStep, prevStep }) {
    return (
        <div>
            <div>
                <h2>Choose Date and Time</h2>
                <input
                    type="date"
                    value={formData.date}
                    onChange={e => handleChange('date', e.target.value)}
                    className={"date-input"}
                />
                <div className="navigation-buttons">
                    <button className={"navigation-button"} onClick={prevStep}>Back</button>
                    <button className={"navigation-button"} onClick={nextStep}>Next</button>
                </div>
            </div>
        </div>
    );
}

export default BookingDateTime;