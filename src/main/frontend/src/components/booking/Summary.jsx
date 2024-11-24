import React from 'react';

function Summary({ formData, prevStep }) {
    return (
        <div>
            <h2>Summary</h2>
            <p><strong>Booking Name:</strong> {formData.name}</p>
            <p><strong>Room Type:</strong> {formData.roomType}</p>
            <p><strong>Capacity:</strong> {formData.capacity}</p>
            {/*<p><strong>Features:</strong> {formData.features.join(', ')}</p>*/}
            <p><strong>Date:</strong> {new Date(formData.date).toLocaleString()}</p>
            <button onClick={prevStep}>Back</button>
            <button onClick={() => alert('Booking Confirmed!')}>Confirm Booking</button>
        </div>
    );
}

export default Summary;