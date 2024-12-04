import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Sidebar from "../Sidebar/Sidebar";
import './EditBooking.css'

const EditBooking = () => {
    const { bookingId } = useParams();
    const navigate = useNavigate();

    const [booking, setBooking] = useState({
        date: '',
        startTime: '',
    });
    const [availableTimes, setAvailableTimes] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchBooking = async () => {
            try {
                const response = await axios.get(`/api/private/booking/booking/${bookingId}`, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem('token')}`,
                    },
                    withCredentials: true
                });
                setBooking(response.data);
                fetchAvailableTimes(response.data.date);
            } catch (error) {
                console.error('Error fetching booking details:', error);
            }
        };

        fetchBooking();
    }, [bookingId]);

    const fetchAvailableTimes = async (selectedDate) => {
        setLoading(true);
        try {
            const response = await axios.post(
                "/api/private/booking/available-times",
                {
                    roomId: booking.roomId, // Assuming `roomId` is part of the booking
                    date: selectedDate,
                },
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`,
                    },
                }
            );
            setAvailableTimes(response.data || []);
        } catch (error) {
            console.error('Error fetching available times:', error);
        } finally {
            setLoading(false);
        }
    };

    const handleInputChange = (field, value) => {
        setBooking((prev) => ({ ...prev, [field]: value }));
        if (field === 'date') {
            fetchAvailableTimes(value);
        }
    };

    const handleUpdate = async () => {
        try {
            const response = await axios.put(`/api/private/booking/booking/${bookingId}`, booking, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('token')}`,
                },
            });
            if (response.status === 200) {
                alert('Booking updated successfully');
                navigate('/dashboard');
            } else {
                alert('Failed to update booking');
            }
        } catch (error) {
            console.error('Error updating booking:', error);
            alert('Error updating booking');
        }
    };

    return (
        <div className="dashboard-container">
            <Sidebar />
            <div className="dashboard-content">
                <h1>Edit Booking</h1>
                <div className="edit-form">
                    <label>Date:</label>
                    <input
                        type="date"
                        name="date"
                        value={booking.date}
                        onChange={(e) => handleInputChange('date', e.target.value)}
                    />
                    {loading ? (
                        <div className="loading-message">Loading available times...</div>
                    ) : (
                        <div className="time-options">
                            {availableTimes.length > 0 ? (
                                availableTimes.map((time, index) => (
                                    <div key={index} className="time-item">
                                        <button
                                            onClick={() => handleInputChange('startTime', time)}
                                            className={`time-button ${
                                                booking.startTime === time ? 'selected' : ''
                                            }`}
                                        >
                                            {time === booking.startTime ? 'Selected' : time}
                                        </button>
                                    </div>
                                ))
                            ) : (
                                <div>No available times</div>
                            )}
                        </div>
                    )}
                    <button
                        onClick={handleUpdate}
                        className="update-button"
                        disabled={!booking.startTime} // Disable if no time is selected
                    >
                        Update
                    </button>
                </div>
            </div>
        </div>
    );
};

export default EditBooking;
