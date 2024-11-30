import React, { useState } from 'react';
import './Summary.css';
import axios from 'axios';

function Summary({ formData, prevStep }) {
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async () => {
        setIsSubmitting(true);
        setSuccessMessage('');
        setErrorMessage('');

        try {
            const response = await axios.post('http://localhost:8082/api/public/booking', {
                roomId : formData.roomId,
                teacherId: localStorage.getItem('username'),
                date: formData.date,
                startTime: formData.startTime,
                name: formData.name,
            });
            setSuccessMessage('Booking created successfully!');
        } catch (error) {
            setErrorMessage('Failed to create booking. Please try again.');
            console.error('Error creating booking:', error);
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="summary-container">
            <h2>Booking Summary</h2>
            <div className="summary-details">
                <p><strong>Booking Name:</strong> {formData.name}</p>
                <p><strong>Room Type:</strong> {formData.roomType}</p>
                <p><strong>Capacity:</strong> {formData.capacity}</p>
                <p><strong>Features:</strong>
                    {[
                        formData.hasComputers && 'Computers',
                        formData.hasProjectors && 'Projectors',
                        formData.hasWhiteBoard && 'Whiteboard',
                    ].filter(Boolean).join(', ') || 'None'}
                </p>
                <p><strong>Room ID:</strong> {formData.roomId}</p>
                <p><strong>Date:</strong> {formData.date}</p>
                <p><strong>Start Time:</strong> {formData.startTime}</p>
            </div>

            {successMessage && <p className="success-message">{successMessage}</p>}
            {errorMessage && <p className="error-message">{errorMessage}</p>}

            <div className="navigation-buttons">
                <button className="navigation-button" onClick={prevStep}>Back</button>
                <button
                    className="navigation-button"
                    onClick={handleSubmit}
                    disabled={isSubmitting}
                >
                    {isSubmitting ? 'Submitting...' : 'Confirm Booking'}
                </button>
            </div>
        </div>
    );
}

export default Summary;
