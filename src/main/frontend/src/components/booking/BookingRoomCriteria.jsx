import React from 'react';

function BookingRoomCriteria({ formData, handleChange, nextStep, prevStep }) {
    return (
        <div>
            <h2>Room Type:</h2>
            <div className="room-type-buttons">
                <button
                    type="button"
                    className={formData.roomType === "LABROOM" ? "selected" : ""}
                    onClick={() => handleChange("roomType", "LABROOM")}
                >
                    Lab Room
                </button>
                <button
                    type="button"
                    className={formData.roomType === "CONFERENCE" ? "selected" : ""}
                    onClick={() => handleChange("roomType", "CONFERENCE")}
                >
                    Conference
                </button>
                <button
                    type="button"
                    className={formData.roomType === "AUDITORIUM" ? "selected" : ""}
                    onClick={() => handleChange("roomType", "AUDITORIUM")}
                >
                    Auditorium
                </button>
                <button
                    type="button"
                    className={formData.roomType === "CLASSROOM" ? "selected" : ""}
                    onClick={() => handleChange("roomType", "CLASSROOM")}
                >
                    Classroom
                </button>
                <button
                    type="button"
                    className={formData.roomType === "MEETING_ROOM" ? "selected" : ""}
                    onClick={() => handleChange("roomType", "MEETING_ROOM")}
                >
                    Meeting Room
                </button>
                <button
                    type="button"
                    className={formData.roomType === "LIBRARY" ? "selected" : ""}
                    onClick={() => handleChange("roomType", "LIBRARY")}
                >
                    Library
                </button>
            </div>

            <h2>Choose Capacity</h2>
            <input
                type="number"
                placeholder="Enter capacity"
                value={formData.capacity}
                onChange={e => handleChange('capacity', e.target.value)}
            />
            <button onClick={prevStep}>Back</button>
            <button onClick={nextStep}>Next</button>

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

            <button onClick={prevStep}>Back</button>
            <button onClick={nextStep}>Next</button>

        </div>
    );
}

export default BookingRoomCriteria;