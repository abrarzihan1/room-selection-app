import React, { useState } from "react";
import axios from "axios";
import BookingRoom from "./BookingRoom";
import "./BookingForm.css";

const BookingForm = () => {
    const [formData, setFormData] = useState({
        roomType: "LABROOM", // Enum values
        capacity: 0,
        hasComputers: false,
        hasProjectors: false,
        hasWhiteBoard: false,
    });

    const [availableRooms, setAvailableRooms] = useState([]);
    const [showRooms, setShowRooms] = useState(false);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData({
            ...formData,
            [name]: type === "checkbox" ? checked : value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(
                "http://localhost:8082/api/public/room/search",
                formData
            );
            setAvailableRooms(response.data);
            setShowRooms(true);
        } catch (error) {
            console.error("Error fetching available rooms:", error);
            alert("Failed to fetch available rooms. Please try again later.");
        }
    };

    const goBack = () => {
        setShowRooms(false);
    };

    if (showRooms) {
        return <BookingRoom rooms={availableRooms} goBack={goBack} />;
    }

    return (
        <div className="booking-form-container">
            <h2>Book a Room</h2>
            <form className="booking-form" onSubmit={handleSubmit}>
                <label htmlFor="roomType">Room Type:</label>
                <select
                    id="roomType"
                    name="roomType"
                    value={formData.roomType}
                    onChange={handleChange}
                    required
                >
                    <option value="LABROOM">Lab Room</option>
                    <option value="CONFERENCE">Conference</option>
                    <option value="AUDITORIUM">Auditorium</option>
                    <option value="CLASSROOM">Classroom</option>
                    <option value="MEETING_ROOM">Meeting Room</option>
                    <option value="LIBRARY">Library</option>
                </select>

                <label htmlFor="capacity">Capacity:</label>
                <input
                    type="number"
                    id="capacity"
                    name="capacity"
                    value={formData.capacity}
                    onChange={handleChange}
                    required
                    min="1"
                />

                <div className="checkbox-group">
                    <label>
                        <input
                            type="checkbox"
                            name="hasComputers"
                            checked={formData.hasComputers}
                            onChange={handleChange}
                        />
                        Computers
                    </label>
                    <label>
                        <input
                            type="checkbox"
                            name="hasProjectors"
                            checked={formData.hasProjectors}
                            onChange={handleChange}
                        />
                        Projector
                    </label>
                    <label>
                        <input
                            type="checkbox"
                            name="hasWhiteBoard"
                            checked={formData.hasWhiteBoard}
                            onChange={handleChange}
                        />
                        Whiteboard
                    </label>
                </div>

                <button type="submit" className="next-button">
                    Next
                </button>
            </form>
        </div>
    );
};

export default BookingForm;
