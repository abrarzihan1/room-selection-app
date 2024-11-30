import React, { useState } from 'react';
import './BookingRoomCriteria.css';

function BookingRoomCriteria({ formData, handleChange, nextStep, prevStep }) {
    const [error, setError] = useState('');

    const validateAndProceed = () => {
        if (!formData.roomType) {
            setError('Please select a room type.');
            return;
        }
        if (formData.capacity <= 0) {
            setError('Capacity must be greater than 0.');
            return;
        }
        setError('');
        nextStep();
    };

    const toggleFeature = (feature) => {
        handleChange(feature, !formData[feature]);
    };

    return (
        <div className="criteria-container">
            <h1>Select Room Criteria</h1>
            {error && <p className="error-message">{error}</p>}

            <div className="room-type-section">
                <h3>Room Type</h3>
                <div className="room-type-buttons">
                    {['LABROOM', 'CONFERENCE', 'AUDITORIUM', 'CLASSROOM', 'MEETING_ROOM', 'LIBRARY'].map(type => (
                        <button
                            key={type}
                            className={formData.roomType === type ? 'room-button selected' : 'room-button'}
                            onClick={() => handleChange('roomType', type)}
                        >
                            {type.replace('_', ' ')}
                        </button>
                    ))}
                </div>
            </div>

            <div className="capacity-section">
                <h3>Capacity</h3>
                <input
                    type="number"
                    min="1"
                    placeholder="Enter capacity"
                    value={formData.capacity}
                    onChange={e => handleChange('capacity', parseInt(e.target.value) || 0)}
                />
            </div>

            <div className="feature-section">
                <h3>Additional Features</h3>
                <div className="feature-buttons">
                    {['hasComputers', 'hasProjectors', 'hasWhiteBoard'].map(feature => (
                        <button
                            key={feature}
                            className={formData[feature] ? 'feature-button selected' : 'feature-button'}
                            onClick={() => toggleFeature(feature)}
                        >
                            {feature.replace('has', '')}
                        </button>
                    ))}
                </div>
            </div>

            <div className="navigation-buttons">
                <button className="navigation-button" onClick={prevStep}>Back</button>
                <button className="navigation-button" onClick={validateAndProceed}>Next</button>
            </div>
        </div>
    );
}

export default BookingRoomCriteria;
