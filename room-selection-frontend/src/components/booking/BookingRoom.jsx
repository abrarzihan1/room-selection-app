import React, { useEffect, useState } from "react";
import "./BookingRoom.css";
import axios from "axios";

const BookingRoom = ({ formData, handleChange, nextStep, prevStep }) => {
    const [availableRooms, setAvailableRooms] = useState([]);
    const [selectedRoom, setSelectedRoom] = useState(null); // Track the selected room

    useEffect(() => {
        const fetchRooms = async () => {
            try {
                const response = await axios.post(
                    "http://localhost:8082/api/public/room/search",
                    formData
                );
                setAvailableRooms(response.data);
            } catch (error) {
                console.error("Error fetching available rooms:", error);
                alert("Failed to fetch available rooms. Please try again later.");
            }
        };

        fetchRooms();
    }, [formData]);

    const handleRoomSelect = (room) => {
        setSelectedRoom(room); // Set the selected room
    };

    if (availableRooms.length === 0) {
        return (
            <div className="no-rooms">
                <p>No rooms available for the selected criteria.</p>
                <button onClick={prevStep}>Back</button>
                <button onClick={nextStep}>Next</button>
            </div>
        );
    }

    return (
        <div className="booking-room-container">
            <h2>Available Rooms</h2>
            <ul className="room-list">
                {availableRooms.map((room) => (
                    <li
                        key={room.roomId}
                        className={`room-item ${selectedRoom?.roomId === room.roomId ? "selected" : ""}`}
                    >
                        <div>
                            <h3>Room ID: {room.roomId}</h3>
                            <p>Capacity: {room.capacity}</p>
                        </div>
                        <button
                            className="select-room-button"
                            onClick={() => handleRoomSelect(room)}
                        >
                            {selectedRoom?.roomId === room.roomId ? "Selected" : "Select"}
                        </button>
                    </li>
                ))}
            </ul>

            <div className="navigation-buttons">
                <button className={"navigation-button"} onClick={prevStep}>Back</button>
                <button className={"navigation-button"} onClick={nextStep} disabled={!selectedRoom}>
                    Next
                </button>
            </div>
        </div>
    );
};

export default BookingRoom;
