import React from "react";
import "./BookingRoom.css";

const BookingRoom = ({ rooms, goBack }) => {
    if (rooms.length === 0) {
        return (
            <div className="no-rooms">
                <p>No rooms available for the selected criteria.</p>
                <button onClick={goBack} className="back-button">
                    Back to Form
                </button>
            </div>
        );
    }

    return (
        <div className="booking-room-container">
            <h2>Available Rooms</h2>
            <ul className="room-list">
                {rooms.map((room) => (
                    <li key={room.roomId} className="room-item">
                        <h3>Room ID: {room.roomId}</h3>
                        <p>Type: {room.roomType}</p>
                        <p>Capacity: {room.capacity}</p>
                        <p>
                            Features:
                            {room.hasComputers && " Computers"}
                            {room.hasProjectors && " Projector"}
                            {room.hasWhiteBoard && " Whiteboard"}
                        </p>
                    </li>
                ))}
            </ul>
            <button onClick={goBack} className="back-button">
                Back to Form
            </button>
        </div>
    );
};

export default BookingRoom;
