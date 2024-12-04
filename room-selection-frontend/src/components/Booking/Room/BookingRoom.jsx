import React, { useEffect, useState } from 'react';
import './BookingRoom.css';
import axios from 'axios';

function BookingRoom({ formData, handleChange, nextStep, prevStep }) {
    const [availableRooms, setAvailableRooms] = useState([]);

    useEffect(() => {
        const fetchRooms = async () => {
            try {
                const response = await axios.post(
                    'api/private/room/search', {
                        "roomType": formData.roomType,
                        "capacity": formData.capacity,
                        "hasComputers": formData.hasComputers,
                        "hasProjectors": formData.hasProjectors,
                        "hasWhiteBoard": formData.hasWhiteBoard
                    }, {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("token")}`,
                        },
                        withCredentials: true
                    }
                );
                setAvailableRooms(response.data);
            } catch (error) {
                console.error('Error fetching available rooms:', error);
            }
        };
        fetchRooms();
    }, [formData]);

    const handleRoomSelect = (roomId) => {
        handleChange('roomId', roomId);
    };

    return (
        <div className="booking-room-container">
            <h1>Available Rooms</h1>
            <p className="booking-text">
                Select a room from the available options based on your chosen criteria such as capacity and features.
            </p>
            {availableRooms.length > 0 ? (
                <ul className="room-list">
                    {availableRooms.map(room => (
                        <li
                            key={room.roomId}
                            className={`room-item ${formData.roomId === room.roomId ? 'selected' : ''}`}
                        >
                            <div>
                                <h3>Room ID: {room.roomId}</h3>
                                <p>Capacity: {room.capacity}</p>
                            </div>
                            <button
                                className="select-room-button"
                                onClick={() => handleRoomSelect(room.roomId)}
                            >
                                {formData.roomId === room.roomId ? 'Selected' : 'Select'}
                            </button>
                        </li>
                    ))}
                </ul>
            ) : (
                <p>No rooms available for the selected criteria.</p>
            )}
            <div className="navigation-buttons">
                <button className="navigation-button" onClick={prevStep}>Back</button>
                <button className="navigation-button" onClick={nextStep} disabled={!formData.roomId}>
                    Next
                </button>
            </div>
        </div>
    );
}

export default BookingRoom;
