import React, { useState } from 'react';
import './Summary.css';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

function Summary({ formData, prevStep }) {
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [bookingConfirmed, setBookingConfirmed] = useState(false);
    const navigate = useNavigate();

    const handleGoBack = () => {
        window.location.reload();
    };

    const handleSubmit = async () => {
        setIsSubmitting(true);
        setSuccessMessage('');
        setErrorMessage('');

        try {
            const response = await axios.post('http://localhost:8082/api/private/booking', {
                roomId: formData.roomId,
                teacherId: localStorage.getItem('username'),
                date: formData.date,
                startTime: formData.startTime,
                name: formData.name,
            }, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
                withCredentials: true
            });
            setSuccessMessage('Booking created successfully!');
            setBookingConfirmed(true);
        } catch (error) {
            setErrorMessage('Failed to create booking. Please try again.');
            setBookingConfirmed(true);
            console.error('Error creating booking:', error);
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="summary-container">
            {!bookingConfirmed ? (
                <>
                    <h1>Booking Summary</h1>
                    <p className={"booking-text"}>Review and confirm your booking details.</p>
                    <div className="summary-details">
                        <div className={"summary-row"}><strong>Booking Name:</strong> {formData.name}</div>
                        <div className={"summary-row"}><strong>Room ID:</strong> {formData.roomId}</div>
                        <div className={"summary-row"}><strong>Date:</strong> {formData.date}</div>
                        <div className={"summary-row"}><strong>Start Time:</strong> {formData.startTime}</div>
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
                </>
            ) : (
                <div className="custom-content">
                    {successMessage && (
                        <div className="custom-success">
                            <h1>Booking Confirmed!</h1>
                            <p>Your booking has been successful!</p>
                            <p>We look forward to seeing you!</p>
                        </div>
                    )}

                    {errorMessage && (
                        <div className="custom-error">
                            <h1>Booking Failed</h1>
                            <p>Oops! Something went wrong while creating your booking. Please try again later.</p>
                        </div>
                    )}
                    <div className="additional-actions">
                        <button className="navigation-button" onClick={handleGoBack}>New booking</button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default Summary;
